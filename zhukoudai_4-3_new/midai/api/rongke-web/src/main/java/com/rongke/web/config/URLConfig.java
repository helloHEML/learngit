package com.rongke.web.config;

import com.rongke.web.yibao.Config;

import java.util.ResourceBundle;

public class URLConfig {
    public static Object object=new Object();
    public static URLConfig config=null;
    public static ResourceBundle rb=null;//读取资源文件的类
    public static final String  File_Name="url";

    public URLConfig(){
        rb=ResourceBundle.getBundle(File_Name);
    }

    public static URLConfig getInstance(){
        synchronized(object){
            if(config==null){
                config=new URLConfig();
            }
            return config;
        }
    }

    public String getURLValue(String key) {
        return (rb.getString(key));
    }
}
