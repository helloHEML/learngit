package com.rongke.web.ans.utils;

import com.alibaba.fastjson.JSONObject;
import com.rongke.utils.ConstantFactory;
import com.rongke.web.ans.config.SysReader;
import com.rongke.web.ansutils.RestTemplateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 类名称: TcreditAPI
 * 描述: 该示例作为天创api的示例
 */
@Component
public  class TcreditAPI {

    static final String tcUrl = "http://47.111.9.78:3335/tianchuang_v1/";
    static final String tcToken = "df5b9006daea4ff9e684d6d70a36182f";

    private final static String appId = ConstantFactory.getConfig().getTzAppId();    // appId

    private final static String tokenId = ConstantFactory.getConfig().getTzTokenId();// tokenId

    @Autowired
    private RestTemplateUtils restTemplate;

    /**
     * 名称: verifyIdcard
     * 描述: 验证身份证姓名一致接口
     * 参数： idcard-身份证号  name-姓名
     * 返回值： void
     * 异常：
     */
    public JSONObject verifyIdcard(String name, String mobile, String idCard){
        // 接口地址
        String url = "http://api.tcredit.com/integration/creditProbeV3_1";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("idCard", idCard);
        param.put("name", name);
        param.put("mobile", mobile);
        // 生成TokenKey
        // 注意：进行MD5摘要时，不能传入appId参数
        String tokenKey = getTokenKey(url, tokenId, param);
        param.put("tokenKey", tokenKey);
        // 注意：appId参数需要在请求的时候作为入参传入，appId不参与MD5摘要
        param.put("appId", appId);
        return restTemplate.post(url, param, null);
    }

    /**
     * 名称: getTokenKey
     * 描述: 生成TokenKey
     * 参数： url-地址  tokenId-客户的tokenId  param-参数
     * 返回值： String 返回的tokenKey
     */
    private String getTokenKey(String url, String tokenId, Map<String, Object> param) {
        String paramStr = sortParam(param);

        return md5Hex(url + tokenId + paramStr);
    }

    /**
     * 名称: sortParam
     * 描述: 生成参数字符串，参数key按字典序排列
     * 参数： param-生成token需要的参数
     * 返回值： String
     */
    private String sortParam(Map<String, Object> param) {
        if (null == param || 0 == param.size()) {
            return "";
        }
        // 排序键，按照字母先后进行排序
        Iterator<String> iterator = param.keySet().iterator();
        String[] arr = new String[param.size()];
        for (int i = 0; iterator.hasNext(); i++) {
            arr[i] = iterator.next();
        }
        Arrays.sort(arr);
        // 生成进行MD5摘要的字符串
        StringBuffer sb = new StringBuffer();
        for (String key : arr) {
            String value = param.get(key).toString();
            if (StringUtils.isNotBlank(value)) {
                sb.append(key).append("=").append(value.toString()).append(",");
            }
        }
        // 检查结果
        if (sb.length() > 0) {
            System.out.println(sb.substring(0, sb.length() - 1));
            return sb.substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

    /**
     * 名称: md5Hex
     * 描述: 对字符串进行md5摘要，然后转化成16进制字符串
     * 使用标准的md5摘要算法
     * 参数： text-需要进行摘要的字符串
     * 返回值： 进行MD5摘要以及16进制转化后的字符串
     */
    private String md5Hex(String text) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(text.trim().getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                int high = (bytes[i] >> 4) & 0x0f;
                int low = bytes[i] & 0x0f;
                sb.append(high > 9 ? (char) ((high - 10) + 'a') : (char) (high + '0'));
                sb.append(low > 9 ? (char) ((low - 10) + 'a') : (char) (low + '0'));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("系统不支持MD5算法");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("系统不支持指定的编码格式");
            e.printStackTrace();
        }
        return null;
    }
    public static String aa(){

        return appId+";;"+tokenId;
    }



}
