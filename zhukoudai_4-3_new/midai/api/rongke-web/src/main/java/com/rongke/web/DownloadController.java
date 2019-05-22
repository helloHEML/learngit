package com.rongke.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.model.Channel;
import com.rongke.model.Download;
import com.rongke.model.Order;
import com.rongke.service.ChannelService;
import com.rongke.service.DownloadService;
import com.rongke.service.OrderService;
import com.rongke.utils.ans.R;
import com.rongke.web.common.IPConfig;
import com.rongke.web.config.URLConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/api/download")
public class DownloadController{

    @Autowired
    private ChannelService channelService;
    @Autowired
    private DownloadService downloadService; //运行商
    @Autowired
    private OrderService orderService;

    /**
     *  下载跳转
     */
    @RequestMapping("/redirect")
    @ResponseBody
    public R download(String channel,HttpServletRequest request){
        Channel login_name = channelService.selectOne(new EntityWrapper<Channel>().eq("encryption", channel));
        if(login_name==null){
            return R.error("请从正规渠道下载");
        }else{
            String url = "";
            if(login_name.getStatus()==2){
                return  R.error("该下载渠道已关闭");
            }
            Enumeration typestr = request.getHeaderNames();
            String s1 = request.getHeader("user-agent");
            if(s1.contains("Android")) {
                url = URLConfig.getInstance().getURLValue("android1");
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(url, String.class);
                url = URLConfig.getInstance().getURLValue("android2")+JSON.parseObject(result).getString("download_token");
            } else if(s1.contains("iPhone")) {
                url = URLConfig.getInstance().getURLValue("ios1");
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(url, String.class);
                url = URLConfig.getInstance().getURLValue("ios3")+JSON.parseObject(result).getString("download_token");
                try {
                    url = "itms-services://?action=download-manifest&url="+ URLEncoder.encode(url,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }  else if(s1.contains("iPad")) {
                url = URLConfig.getInstance().getURLValue("ios1");
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(url, String.class);
                url = URLConfig.getInstance().getURLValue("ios3")+JSON.parseObject(result).getString("download_token");
                try {
                    url = "itms-services://?action=download-manifest&url="+ URLEncoder.encode(url,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }  else {
                url = URLConfig.getInstance().getURLValue("android1");
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(url, String.class);
                url = URLConfig.getInstance().getURLValue("android2")+JSON.parseObject(result).getString("download_token");
            }
            if(StringUtils.isNotBlank(url)){
                login_name.setCount(new BigDecimal(login_name.getCount()).add(new BigDecimal("1")).stripTrailingZeros().toPlainString());
                channelService.updateById(login_name);
                //记录下载IP地址
                Download download =  new Download();
                String ip = IPConfig.getIpAddr(request);
                download.setIp(ip);
                download.setChannlid(login_name.getId());
                download.setCreateTime(new Date());
                download.setUpdateTime(new Date());
                download.setStatus(1);
                downloadService.insertOrUpdate(download);
                return R.ok().put("code",0).put("msg","操作成功").put("data",url);
            }else{
                return R.error("无下载链接");
            }
        }
    }


    @RequestMapping("/recording")
    @ResponseBody
    public void recording(HttpServletRequest request){
        List<Order> orders=orderService.selectPayFailOrderList();
        System.out.println(orders);
    }

    public static void main(String[] args) throws Exception {
//        String ios = URLConfig.getInstance().getURLValue("android1");
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(ios, String.class);
//        String url = JSON.parseObject(result).getString("download_token");
//        System.out.println(URLConfig.getInstance().getURLValue("android2")+url);
//        System.out.println(String.valueOf(Integer.valueOf("20")+1));
//        System.out.println(Base64Utils.encodeToString("midai".getBytes()));

//        System.out.println(new String(Base64Utils.encodeToString("bWlkYWk=")));
//        SmsUtilImpl sms = new SmsUtilImpl();
//        Boolean sendsms = sms.sendsms("13067900803","5564","28513");
//        System.out.println(sendsms);

    }



}
