package com.rongke.web.fuyou.utils;

public class FuYoufsUtils {

    /**
     * 获取签名
     * @param signStr  签名串
     * @param signtp   签名类型
     * @param key      密钥
     * @return
     * @throws Exception
     */
    public static String getSign(String signStr,String signtp,String key) throws  Exception{
        String sign = "";
        if ("md5".equalsIgnoreCase(signtp)) {
            sign = MD5.MD5Encode(signStr);//进行md5加密
        } else {
            sign =	RSAUtils.sign(signStr.getBytes("utf-8"), key);//进行RSA签名
        }
        return sign;
    }
}
