package com.rongke.web.fuyou.utils;


import net.sf.json.xml.XMLSerializer;
import org.json.JSONException;

public class XmlTool {
    public static String xml2JSON(String xml) throws JSONException {
        return  new XMLSerializer().read(xml).toString();
    }
}
