package com.rongke.web.saas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rongke.utils.HttpClientUtil;
import com.rongke.utils.ans.R;

//
public class RSMlAPI {

    static final String tcUrl = "http://47.111.9.78:3336/tcml_v1/";
    static final String tcToken = "50dec4d4aec13fa549ad36173281d7e4";


    public static Boolean test() {
        String url = tcUrl + "test";
        String body = "";
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        System.out.print(result);
        return true;
    }

    public static Boolean test2() {
        R r = R.ok();
        JSONObject json = new JSONObject();
        json.put("code", 100);
        json.put("msg", "success1");
        r.put("code", json.get("code"));
        r.put("msg", json.get("msg"));
        return true;
    }


    public static JSONObject tcpredict(String data) {

        String url = tcUrl + "tcpredict";
        String body = "data="+data+
                "&passbackParams=" + tcToken;
        System.out.println("body*************************"+body);
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        JSONObject json = JSON.parseObject(result);
        return json;
    }



    //{"code":"0","data":{"predict":"pass"}}
    //{"code":"0","data":{"predict":"reject"}}
    public static void main(String[] args) {
        RSMlAPI.tcpredict("safasdf");

    }

}

