package com.rongke.web.ansutils;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class ANSUtils {

    /**
     * Base64图片转 byte[]
     */
    public static byte[] base642Byte(String data) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = new byte[0];
        try {
            bytes = decoder.decodeBuffer(data);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
