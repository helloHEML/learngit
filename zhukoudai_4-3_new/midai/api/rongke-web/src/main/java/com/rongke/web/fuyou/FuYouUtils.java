package com.rongke.web.fuyou;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.rongke.utils.ConstantFactory;
import com.rongke.utils.DateUtils;
import com.rongke.utils.RandomUtils;
import com.rongke.utils.ans.IdWorker;
import com.rongke.utils.ans.R;
import com.rongke.utils.ans.SnowFlake;
import com.rongke.web.fuyou.base.APIBaseServlet;
import com.rongke.web.fuyou.config.ConfigReader;
import com.rongke.web.fuyou.constant.Constants;
import com.rongke.web.fuyou.entity.NewProtocolBindXmlBeanReq;
import com.rongke.web.fuyou.entity.NewProtocolOrderXmlBeanReq;
import com.rongke.web.fuyou.entity.OrderQryByMSsn;
import com.rongke.web.fuyou.utils.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class FuYouUtils implements APIBaseServlet {
    private static Logger log = Logger.getLogger(FuYouUtils.class);
    /**
     * 是否支持手机支付查询
     */
    public static R cardBinQuery(String cardNo){
        String sign = MD5.MD5Encode(new StringBuffer().append(mchntcd).append("|").append(cardNo)
                .append("|").append(key).toString());
        String fm = new StringBuffer().append("<FM>").append("<MchntCd>").append(mchntcd)
                .append("</MchntCd>").append("<Ono>").append(cardNo).append("</Ono>").append("<Sign>").append(sign)
                .append("</Sign>").append("</FM>").toString();
        Map <String, String> params = new HashMap <String, String>();
        params.put("FM", fm);
        JSONObject access = null;
        try {
            access = postForward(ConfigReader.getString("prod_protocol_card_findPay_url"), params);
            System.err.println(access.toJSONString());
            if("0000".equals(access.getString("Rcd"))){
                System.out.println(access.toJSONString());
                return R.ok(access.getString("RDesc")).put("data",access);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error(access.getString("RDesc"));
    }

    /**
     * 绑卡短信发送
     */
    public static R bindMsg(Map<String,String> params){
        NewProtocolBindXmlBeanReq beanReq = new NewProtocolBindXmlBeanReq();
        beanReq.setVersion("1.0");//版本号必填
        beanReq.setMchntCd(mchntcd);//合作商户的唯一识别码
        beanReq.setTradeDate(DateUtils.nowDate("yyyMMdd"));//交易时间
        beanReq.setUserId(params.get("userId"));//用户唯一编号
        beanReq.setAccount(params.get("account"));//银行卡开户人名称
        beanReq.setCardNo(params.get("card"));//银行卡号
        beanReq.setIdType("0");//证件类型 0身份证
        beanReq.setIdCard(params.get("IdCard"));//证件号码
        beanReq.setMobileNo(params.get("mobile"));//预留手机号
        beanReq.setMchntSsn(String.valueOf("bk"+new IdWorker(0,0).nextId()));//流水号保证唯一
        JSONObject result = null;
        try {
            beanReq.setSign(FuYoufsUtils.getSign(beanReq.sendMsgSignStr(key), "MD5", privatekey));
            Map<String,String> map=Maps.newHashMap();
            map.put("MCHNTCD",mchntcd);
            map.put("APIFMS", DESCoderFUIOU.desEncrypt(XMapUtil.toXML(beanReq, charset), DESCoderFUIOU.getKeyLength8(key)));
            beanReq.setSign(FuYoufsUtils.getSign(beanReq.sendMsgSignStr(key), "MD5", privatekey));
            String cs_protocol_bindmsg_url = new HttpPoster(ConfigReader.getString("prod_protocol_bindmsg_url")).postStr(map);
            JSONObject jsonObject = JSON.parseObject(XmlTool.xml2JSON(DESCoderFUIOU.desDecrypt(cs_protocol_bindmsg_url, DESCoderFUIOU.getKeyLength8(key))));
            result = jsonObject;
            System.err.println(jsonObject.toJSONString());
            if("0000".equals(result.getString("RESPONSECODE"))){
                return R.ok(result.getString("RESPONSEMSG")).put("ssn",beanReq.getMchntSsn());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(result.getString("RESPONSEMSG"));
    }

    /**
     * 绑卡短信发送
     */
    public static R bindCommit(Map<String,String> params){
        NewProtocolBindXmlBeanReq beanReq = new NewProtocolBindXmlBeanReq();
        beanReq.setVersion("1.0");//版本号
        beanReq.setTradeDate(DateUtils.nowDate("yyyyMMdd"));//时间
        beanReq.setMchntCd(mchntcd);//商户号
        beanReq.setUserId(params.get("userId"));//用户标识
        beanReq.setAccount(params.get("account"));//开户人名称
        beanReq.setCardNo(params.get("card"));//卡号
        beanReq.setIdType("0");//证件类型 0
        beanReq.setIdCard(params.get("IdCard"));//身份证号码
        beanReq.setMobileNo(params.get("mobile"));//预留手机号
        beanReq.setMchntSsn(params.get("ssn"));//流水号
        beanReq.setMsgCode(params.get("Vcode"));//短验码
        JSONObject access = null;
        try {
            beanReq.setSign(FuYoufsUtils.getSign(beanReq.proBindSignStr(key), "MD5", privatekey));
            Map<String,String> map = new HashMap<String, String>();
            map.put("MCHNTCD",mchntcd);
            map.put("APIFMS", DESCoderFUIOU.desEncrypt(XMapUtil.toXML(beanReq, charset), DESCoderFUIOU.getKeyLength8(key)));
            access = access(ConfigReader.getString("prod_protocol_bindcommit_url"),map);
            if("0000".equals(access.getString("RESPONSECODE"))){
                return R.ok(access.getString("RESPONSEMSG")).put("xe",access.getString("PROTOCOLNO"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return R.error(access.getString("RESPONSEMSG"));
    }

    /**
     * 解绑
     */
    public static R unbind(String userId,String protocol){
        NewProtocolBindXmlBeanReq beanReq = new NewProtocolBindXmlBeanReq();
        beanReq.setMchntCd(mchntcd);
        beanReq.setVersion("1.0");
        beanReq.setUserId(userId);
        beanReq.setProtocolNo(protocol);
        JSONObject access = null;
        try {
            beanReq.setSign(FuYoufsUtils.getSign(beanReq.proUnBindSignStr(key), "MD5", privatekey));
            Map<String,String> map = new HashMap<String, String>();
            map.put("MCHNTCD",mchntcd);
            map.put("APIFMS", DESCoderFUIOU.desEncrypt(XMapUtil.toXML(beanReq, charset), DESCoderFUIOU.getKeyLength8(key)));
            access = access(ConfigReader.getString("prod_protocol_unbind_url"),map);
            if("0000".equals(access.getString("RESPONSECODE"))){
                return R.ok(access.getString("RESPONSEMSG")).put("xe",access.getString("PROTOCOLNO"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(access.getString("RESPONSEMSG"));
    }

    /**
     * 协议卡查询
     */
    public static R bindQuery(String userId){
        NewProtocolBindXmlBeanReq beanReq = new NewProtocolBindXmlBeanReq();
        beanReq.setVersion("1.0");
        beanReq.setMchntCd(mchntcd);
        beanReq.setUserId(userId);
        JSONObject access = null;
        try {
            beanReq.setSign(FuYoufsUtils.getSign(beanReq.querySignStr(key), "MD5", privatekey));
            Map<String,String> map = new HashMap<String, String>();
            map.put("MCHNTCD",mchntcd);
            map.put("APIFMS", DESCoderFUIOU.desEncrypt(XMapUtil.toXML(beanReq, charset), DESCoderFUIOU.getKeyLength8(key)));
            access = access(ConfigReader.getString("prod_protocol_query_url"),map);
            if("0000".equals(access.getString("RESPONSECODE"))){
                return R.ok(access.getString("RESPONSEMSG")).put("card",access.getString("CARDNO"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return R.error(access.getString("RESPONSEMSG"));
    }

    /**
     * 支付
     */
    public static R order(Map<String,String> params){
        NewProtocolOrderXmlBeanReq beanReq = new NewProtocolOrderXmlBeanReq();
        beanReq.setVersion("1.0");
        beanReq.setMchntCd(mchntcd);
        beanReq.setType("03");
        beanReq.setMchntOrderId(String.valueOf("fk"+IdWorker.getID()));
        beanReq.setUserId(params.get("userId"));
        beanReq.setAmt(params.get("money"));
        beanReq.setProtocolNo(params.get("protocol"));
        beanReq.setBackUrl(ConstantFactory.getFuYouConfig().getFuyouCallback());
        beanReq.setRem1(params.get("rem1"));
        beanReq.setRem2(params.get("rem2"));
        beanReq.setRem3(params.get("rem3"));
        beanReq.setUserIp(ConstantFactory.getFuYouConfig().getFuyouIpadder());//ConfigReader.getString("cd_ipadder")
        beanReq.setNeedSendMsg("0");
        beanReq.setSignTp(signtp);
        JSONObject access = null;
        try {
            beanReq.setSign(FuYoufsUtils.getSign(beanReq.signStr(key), "MD5", privatekey));
            Map<String,String> map = new HashMap<String, String>();
            map.put("MCHNTCD",mchntcd);
            map.put("APIFMS", DESCoderFUIOU.desEncrypt(XMapUtil.toXML(beanReq, charset), DESCoderFUIOU.getKeyLength8(key)));
            access = access(ConfigReader.getString("prod_protocol_order_url"),map);
            if("0000".equals(access.getString("RESPONSECODE"))){
                return R.ok(access.getString("RESPONSEMSG")).put("card",access.getString("BANKCARD"))
                        .put("fFyOrderId",access.getString("ORDERID"))
                        .put("payOrder",access.getString("MCHNTORDERID"));
            }
        }catch(Exception e){
            log.error(e.toString());
        }
        return R.error(access.getString("RESPONSEMSG")).put("result",access.toJSONString());
    }

    /**
     * 订单结果查询（富有）
     */
    public static R queryOrderId(String orderId){
        String sign = MD5.MD5Encode(new StringBuffer().append(mchntcd).append("|").append(orderId)
                .append("|").append(key).toString());
        String fm = new StringBuffer().append("<FM>").append("<MchntCd>").append(mchntcd)
                .append("</MchntCd>").append("<OrderId>").append(orderId).append("</OrderId>").append("<Sign>")
                .append(sign).append("</Sign>").append("</FM>").toString();
        Map<String, String> params = new HashMap<String,String>();
        params.put("FM", fm);
        JSONObject access = null;
        try {
            access = postForward(ConfigReader.getString("prod_protocol_query_orderId_url"), params);
        }catch(Exception e){
            e.printStackTrace();
        }
        return R.ok(access.getString("RDesc"));
    }

    /**
     * 订单结果查询（商户）
     */
    public static R checkInfoOrderId(String orderId){
        OrderQryByMSsn data = new OrderQryByMSsn();
        data.setMchntCd(mchntcd);
        data.setMchntOrderId(orderId);
        data.setVersion("3.0");
        JSONObject access = null;
        try {
            Map<String, String> params = new HashMap<String,String>();
            params.put("FM", data.buildXml(key));
            access = postForward(ConfigReader.getString("prod_protocol_checkInfo_orderId_url"), params);
            if("0000".equals(access.getString("RESPONSECODE"))){
                return R.ok(access.getString("RESPONSEMSG")).put("payOrderId",access.getString("MCHNTORDERID"))
                        .put("card",access.getString("BANKCARD")).put("data",access);
            }
        }catch(Exception e){
            e.printStackTrace();
    }
        return R.error(access.getString("RDesc"));
    }

    /**
     * 代付
     */
    public static R payUser(Map<String,String> params) throws Exception{
        String orderId = "DK"+IdWorker.getID()+RandomUtils.randomString(6);
        String body = String.format(xml,"2.00",DateUtils.format(DateUtils.YYYYMMDD,new Date()),
                orderId,params.get("card"),params.get("account"),params.get("money"),params.get("meno"));


        String macSource = mchntcd + "|" + ConstantFactory.getFuYouConfig().getFuyouMchntUserPay() + "|" + "payforreq" + "|" +body;


        String mac =  DigestUtils.md5Hex(macSource);
        String reqtype = "payforreq";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.set("merid",mchntcd);
        request.set("reqtype",reqtype);
        request.set("xml",body);
        request.set("mac",mac);
        String s = restTemplate.postForObject(ConfigReader.getString("prod_pay_req"), request, String.class);
        JSONObject result = JSON.parseObject(XmlTool.xml2JSON(s));
        if("000000".equals(result.get("ret"))){//受理成功
            return R.ok("受理通知成功").put("orderId",orderId);
        }else{
            return R.error(result.getString("memo"));
        }
    }



    /**
     * 发送请求
     */
    private static JSONObject access(String url,Map<String,String> fs) throws Exception {
        String post = new HttpPoster(url).postStr(fs);
        JSONObject jsonObject = null;
        jsonObject = JSON.parseObject(XmlTool.xml2JSON(DESCoderFUIOU.desDecrypt(post, DESCoderFUIOU.getKeyLength8(key))));
        System.err.println(jsonObject.toJSONString());
        JSONObject result = jsonObject;
        return result;
    }

    private static JSONObject postForward(String url,Map<String,String> fs) throws Exception{
        String result = HttpPostUtil.postForward(url, fs);
        JSONObject json = JSON.parseObject(XmlTool.xml2JSON(result));
        return json;
    }



    /**
     * X2MQMC1000000232414H02
     */
    public static void main(String[] args) throws IOException {
        FuYouUtils f = new FuYouUtils();
//        R r1 = f.cardBinQuery("6217566200011703341");//查询银行卡
//
//        R r = f.bindQuery("13588022187");
//        System.out.println("银行卡查询====>"+r1.toString());
//        System.out.println("绑卡银行卡查询====>"+r.toString());

//        Map<String,String> params1 = Maps.newHashMap();
//        params1.put("account","钱越");
//        params1.put("userId","18367058637");//用户唯一编号
//        params1.put("card","6216631300000003076");//银行卡号
//        params1.put("IdCard","33082119970124401X");//证件号码
//        params1.put("mobile","13588022187");//预留手机号
//        R r2 = FuYouUtils.bindMsg(params1);//绑卡短信
//
//
//        Map<String,String> params2 = Maps.newHashMap();
//        params2.put("userId","18367058637");//用户唯一编号
//        params2.put("account","钱越");//银行卡开户人名称
//        params2.put("card","6216631300000003076");//银行卡号
//        params2.put("IdCard","33082119970124401X");//证件号码
//        params2.put("mobile","13588022187");//预留手机号
//        params2.put("ssn",String.valueOf(r2.get("ssn")));
//        params2.put("Vcode","000000");
//        R r3 = FuYouUtils.bindCommit(params2);//绑卡短信提交
//
//
//
//
//        R r4 = FuYouUtils.bindQuery(params1.get("userId"));
//        Map<String,String> map = Maps.newHashMap();
//        map.put("repayType","3");
//        map.put("money",new BigDecimal("0.006").stripTrailingZeros().toPlainString());
//
//        Map<String,String> params3 = Maps.newHashMap();
//        params3.put("userId","18367058637");//用户唯一编号
//        params3.put("money",new BigDecimal("0.006").multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());//银行卡开户人名称
//        params3.put("protocol",String.valueOf(r3.get("xe")));
//        params3.put("rem1","1");//证件号码
//        params3.put("rem2",JSON.toJSONString(map));//预留手机号
//        params3.put("rem3","7");
//        R r5 = FuYouUtils.order(params3);
//
//
//        R r6 = FuYouUtils.queryOrderId(String.valueOf(r5.get("fyOrderId")));
//        R r7 = FuYouUtils.checkInfoOrderId("fk1065488396136218624");
//        System.out.println(r7);
//        R end = FuYouUtils.unbind("18367058637",String.valueOf(r3.get("xe")));
//        System.out.println("银行卡查询==========>"+r1.toString());
//        System.out.println("绑卡验证码获取======>"+r2.toString());
//        System.out.println("绑卡验证提交======>"+r3.toString());
//        System.out.println("绑卡查询============>"+r4.toString());
//        System.out.println("支付结果============>"+r5.toString());
//        System.out.println("富有号查询==========>"+r6.toString());
//        System.out.println("订单号查询==========>"+r7.toString());
//        System.out.println("解绑================>"+end.toString());
//DK1063726106638548992
//        Map<String,String> mapf = Maps.newHashMap();
//        mapf.put("card","6216631300000003076");
//        mapf.put("account","钱越");
//        mapf.put("money","500");
//        mapf.put("meno","测试");
//        R rf = payUser(mapf);
//        System.err.println("===="+rf.toString());
//        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>"+
//                "<qrytransreq>"+
//                "<ver>1.00</ver>"+
//                "<busicd>AP01</busicd>"+    //AP01:代付  AC01：代收  TP01：退票
//	    			"<orderno>DK1063726106638548992,DK1063730444576489472</orderno>"+      //查询多个流水，流水中间用英文,间隔，一次最多50个
//                "<startdt>20181117</startdt>"+
//                "<enddt>20181118</enddt>"+
////	    			"<transst>1</transst>"+
//                "</qrytransreq>";
//        System.err.println(xml);
//        String macSource = "0002900F0345142|123456|"+"qrytransreq"+"|"+xml;
//        String mac = DigestUtils.md5Hex(macSource);
//        String loginUrl = "https://fht-test.fuiou.com/fuMer/req.do";
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("merid", "0002900F0345142"));
//        params.add(new BasicNameValuePair("reqtype", "qrytransreq"));
//        params.add(new BasicNameValuePair("xml", xml));
//        params.add(new BasicNameValuePair("mac", mac));
//
//        requestPost(loginUrl,params);
        long l = new IdWorker(0, 0).nextId();
        System.out.println( new IdWorker(0,1).nextId());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( new SnowFlake(0,1).nextId());
        System.out.println( new SnowFlake(0,1).nextId());
        System.out.println( new SnowFlake(0,1).nextId());
        System.out.println( new SnowFlake(0,1).nextId());
        System.out.println( new SnowFlake(0,1).nextId());
        System.out.println( new SnowFlake(0,1).nextId());
        System.out.println( new SnowFlake(0,1).nextId());
        System.out.println( new SnowFlake(0,1).nextId());
        IdWorker snowFlake = new IdWorker(0, 1);
        for(int i =0; i <(1<< 12); i++){
            System.out.println(snowFlake.nextId());
        }
    }

    public static String getDate(int i){
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if(i==1){
            String str = sf.format(calendar.getTime());
            return str;
        }else{
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            String str = sf.format(calendar.getTime());
            return str;
        }
    }

    public static void requestPost(String url,List<NameValuePair> params) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));

        CloseableHttpResponse response = httpclient.execute(httppost);
        System.out.println(response.toString());


        HttpEntity entity = response.getEntity();
        String jsonStr = EntityUtils.toString(entity, "utf-8");
        System.out.println(jsonStr);
       // System.out.println(XmlTool.xml2JSON(jsonStr));
        httppost.releaseConnection();
    }

}
