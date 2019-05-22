package com.rongke.web.ansutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rongke.utils.ans.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Component
public class RestTemplateUtils {
    @Autowired
    private RestTemplate restTemplate;

    /** 使用默认请求头 默认json utf-8 用于登陆 */
    public R mcGet(String url,String data,HttpHeaders headers) throws UnsupportedEncodingException {
        HttpEntity<String> r = new HttpEntity<>(data,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,r,String.class);
        System.err.println(response.toString());
        return  R.ok(response.getStatusCodeValue(),response.getStatusCode().getReasonPhrase())
                .put("headers",response.getHeaders())
                .put("data", JSON.parseObject(response.getBody()))
                .put(HttpHeaders.SET_COOKIE,response.getHeaders().get(HttpHeaders.SET_COOKIE));
    }

    /** 使用默认请求头 默认json utf-8 用于登陆 */
    public R mcPut(String url, String data, HttpHeaders headers) throws UnsupportedEncodingException {
        HttpEntity<String> r = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT,r,String.class);

        return  R.ok(response.getStatusCodeValue(),response.getStatusCode().getReasonPhrase())
                .put("headers",response.getHeaders())
                .put("data", JSON.parseObject(response.getBody()))
                .put(HttpHeaders.SET_COOKIE,response.getHeaders().get(HttpHeaders.SET_COOKIE));
    }

    /** 使用默认请求头 默认json utf-8 用于登陆 */
    public  R mcPost(String url,String data,HttpHeaders headers) throws UnsupportedEncodingException {
        HttpEntity<String> r = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,r,String.class);

        return  R.ok(response.getStatusCodeValue(),response.getStatusCode().getReasonPhrase())
                .put("headers",response.getHeaders())
                .put("data", JSON.parseObject(response.getBody()))
                .put(HttpHeaders.SET_COOKIE,response.getHeaders().get(HttpHeaders.SET_COOKIE));
    }

    /** 使用默认请求头 默认json utf-8 用于登陆 */
    public static   R mcPost(RestTemplate restTemplate, String url, String data, HttpHeaders headers) throws UnsupportedEncodingException {
        HttpEntity<String> r = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,r,String.class);

        return  R.ok(response.getStatusCodeValue(),response.getStatusCode().getReasonPhrase())
                .put("headers",response.getHeaders())
                .put("data", JSON.parseObject(response.getBody()))
                .put(HttpHeaders.SET_COOKIE,response.getHeaders().get(HttpHeaders.SET_COOKIE));
    }

    /** 使用默认请求头 默认json utf-8 用于登陆 */
    public  R mcPost(String url,Map<String,Object> data,HttpHeaders headers) throws UnsupportedEncodingException {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.setAll(data);

        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(requestEntity, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,r,String.class);
        return  R.ok(response.getStatusCodeValue(),response.getStatusCode().getReasonPhrase())
                .put("headers",response.getHeaders())
                .put("data", JSON.toJSONString(response.getBody()))
                .put(HttpHeaders.SET_COOKIE,response.getHeaders().get(HttpHeaders.SET_COOKIE));
    }


    public JSONObject post(String url, Map<String,Object> data){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.setAll(data);
        String s = restTemplate.postForObject(url, data, String.class);
        System.out.println(s);
        return JSON.parseObject(s);
    }


    public JSONObject post(String url, Map<String,Object> data,HttpHeaders headers){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.setAll(data);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(requestEntity,headers);
        String s = restTemplate.postForObject(url, httpEntity, String.class);
        return JSON.parseObject(s);
    }

    public String postNJ(String url, Map<String,Object> data){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.setAll(data);
        String s = restTemplate.postForObject(url, data, String.class);
        System.out.println(s);
        return s;
    }

    public JSONObject get(String url){
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.getForObject(url, String.class);
        return JSON.parseObject(s);
    }

}
