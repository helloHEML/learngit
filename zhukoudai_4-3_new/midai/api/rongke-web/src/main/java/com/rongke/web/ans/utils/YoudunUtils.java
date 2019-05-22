package com.rongke.web.ans.utils;

import com.alibaba.fastjson.JSONObject;
import com.rongke.utils.ConstantFactory;
import com.rongke.utils.DateUtils;
import com.rongke.web.ans.config.SysReader;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.io.UnsupportedEncodingException;
import java.util.Date;


public class YoudunUtils {

    public static JSONObject api(String idcard){
        JSONObject json = new JSONObject();
        json.put("id_no",idcard);
        String dataservice_url = "https://api4.udcredit.com/dsp-front/4.1/dsp-front/default/pubkey/%s/product_code/%s/out_order_id/%s/signature/%s";
        String sign = String.format("%s|%s",json , ConstantFactory.getConfig().getYhySecurityKey());
        System.out.println("测试签名" + sign);
        String s = DigestUtils.md5Hex(sign);
        System.out.println(s);
        String url = String.format(dataservice_url, ConstantFactory.getConfig().getYhyPubKey(), "Y1001005",
                DateUtils.dateTOStrFormat(DateUtils.YYYYMMDDHHMMSS,new Date()),s);
        System.out.println("url地址为：" + url);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<JSONObject> http = new HttpEntity<JSONObject>(json,headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, http, String.class);
        String response = exchange.getBody();
        return JSONObject.parseObject(response);
    }

    public static void main(String[] args){

    }

}
