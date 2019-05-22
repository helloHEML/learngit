package com.rongke.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.output.ByteArrayOutputStream;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

//对外提供的接口
public class RSMohe {

    static final String moheUrl = "http://47.111.9.78:3333/mohe_v1/";
    //商户的token
    static final String moheToken = "50dec4d4aec13fa549ad36173281d7e4";

    public static JSONObject createTask(String name, String idcard, String mobile) {
        String url = moheUrl + "createTask";
        String body = "real_name=" + name + "&identity_code=" + idcard + "&user_mobile=" + mobile + "&passback_params=" + moheToken;
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        System.out.print(result);
        return JSON.parseObject(result);
    }

public static JSONObject acquire(String taskId, String userName, String userPass, String taskStage, String requestType, String sms_code, String auth_code) {
    String url = moheUrl + "acquire";
    String body = "task_id=" + taskId + "&user_name=" + userName + "&user_pass=" + userPass + "&task_stage=" + taskStage + "&request_type=" + requestType +
            "&sms_code=" + sms_code + "&auth_code=" + auth_code;

    String result1 = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
    System.out.print(result1);
    return JSON.parseObject(result1);
}
    public static JSONObject getAll(String taskId) {
        String url = moheUrl + "getAll";
        String body = "task_id=" + taskId;
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        return JSON.parseObject(result);
    }

    public static JSONObject getTaobaoData(String taskId) {
        String url = moheUrl + "getTaobaoData";
        String body = "task_id=" + taskId;
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        return JSON.parseObject(result);
    }

    public static JSONObject getZhifubaoData(String taskId) {
        String url = moheUrl + "getZhifubaoData";
        String body = "task_id=" + taskId;
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        return JSON.parseObject(result);
    }

    public static JSONObject getReportRawData(String taskId) {
        String url = moheUrl + "getReportRawData";
        String body = "task_id=" + taskId;
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        return JSON.parseObject(result);
    }


    public static JSONObject getReportUrlData() {
        String url = moheUrl + "getReportUrlData";
        String body = "" ;
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        return JSON.parseObject(result);
    }


    public static JSONObject activeMsgdemo(String taskId, String partnerMoheCode, String partnerMoheKey) {
        JSONObject result = getReportRawData(taskId);
        String resultData = gunzip(result.getString("data"));
        return JSON.parseObject(resultData);
    }


    public static String gunzip(String compressedStr) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        Object compressed = null;
        String decompressed = null;

        try {
            try {
                byte[] compressed1 = (new BASE64Decoder()).decodeBuffer(compressedStr);
                in = new ByteArrayInputStream(compressed1);
                ginzip = new GZIPInputStream(in);
                byte[] buffer = new byte[1024];
                boolean var8 = true;

                int offset1;
                while((offset1 = ginzip.read(buffer)) != -1) {
                    out.write(buffer, 0, offset1);
                }

                decompressed = out.toString("utf-8");
            } catch (IOException var13) {
            }

            return decompressed;
        } finally {
            ;
        }
    }
}
