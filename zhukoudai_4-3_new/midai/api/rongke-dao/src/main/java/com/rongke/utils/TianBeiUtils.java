package com.rongke.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TianBeiUtils {

    public static JSONObject radarApi(String phone,String name,String idCard){
        String url = "http://credit.whtianbei.com/api/radar/get-report?phone=%s&name=%s&idcard=%s";
        url = String.format(url,phone,name,idCard);
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Mall-Token",ConstantFactory.getConfig().getTianbeiToken());
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);
        ResponseEntity<String> exchange = rest.exchange(url, HttpMethod.GET, entity, String.class);
        return JSON.parseObject(exchange.getBody());
    }


    public static JSONObject smsApi(String phone,String name,String idCard){
        String url = "http://credit.whtianbei.com/api/tanzhi/get-report?phone=%s&name=%s&idcard=%s";
        return  zApi(url,phone,name,idCard);
    }

    public static JSONObject fqzApi(String phone,String name,String idCard){
        String url = "http://credit.whtianbei.com/api/fraud/get-report?phone=%s&name=%s&idcard=%s";
        return zApi(url,phone,name,idCard);
    }

    public static void main(String[] args) {

    }

    public static JSONObject zApi(String url,String phone,String name,String idCard){
        url = String.format(url,phone,name,idCard);
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Mall-Token",ConstantFactory.getConfig().getTianbeiToken());
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);
        ResponseEntity<String> exchange = rest.exchange(url, HttpMethod.GET, entity, String.class);
        return JSON.parseObject(exchange.getBody());
    }
}
