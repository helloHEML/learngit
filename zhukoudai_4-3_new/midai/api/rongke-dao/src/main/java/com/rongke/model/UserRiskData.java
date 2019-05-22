package com.rongke.model;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.rongke.utils.StringUtils.underline2Camel;

public class UserRiskData {

    //各种模型
    Long userId;

    private TongDunMessage tongdunMessage;

    private TianZhenMessage tianzhenData;

    //通过type和name从riskdata中获得风控值
    public Object getRiskValue(String type, String field){

        String camelType = underline2Camel(type, false);
        //转成大驼峰命名法 比如 message_data 转换成MessageData

        String methodName = "get"+camelType;//转成本类中的get方法 如：getMessageData
        try {
            Method method = this.getClass().getDeclaredMethod(methodName);
//            表示通过反射获取本类的get方法
            try {
                Object decisionModel= method.invoke(this);// get instance 获取get方法的对象
                Object fieldValue =  getInstanceField(decisionModel, camelType,field);
                return fieldValue;
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (InvocationTargetException e){
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获得风控模型中的字段
    //object风控模型实例
    //filename风控模型中的字段
    public Object getInstanceField(Object object, String fieldName,String field) {
        if (object == null){
            return null;
        }

        String[] items2;
        String cameTable ="";
        String camelField = "";
        if(field.indexOf("--") != -1){
            items2 = field.split("--");
            if(items2.length != 2 ){
                return null;
            }
            items2[0] = items2[0].substring(items2[0].indexOf("_")+1);
            cameTable = items2[0];
            camelField = items2[1];
        }else{
            camelField = field;
        }

        String fieldGetMethodName = "get"+fieldName;
        try {
            Method method = object.getClass().getDeclaredMethod(fieldGetMethodName);
            try {
                Object fieldValue= method.invoke(object);
                JSONObject data = JSONObject.parseObject(fieldValue.toString());//把获取的字符串类型转换为JSONObject类型
                if(!("".equals(cameTable)) && cameTable != null){
                    Object result = data.getJSONObject(cameTable).getString(camelField);
                     return result;
                }else{
                    Object result = data.getString(camelField);
                    return result;
                }

            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (InvocationTargetException e){
                e.printStackTrace();
            }
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获得风控模型中的字段
    //object风控模型实例
    //filename风控模型中的字段
//    public Object getInstanceField(Object object, String fieldName) {
//        if (object == null){
//            return null;
//        }
//        String fieldGetMethodName = "get"+fieldName;
//        try {
//            Method method = object.getClass().getDeclaredMethod(fieldGetMethodName);
//            try {
//                Object fieldValue= method.invoke(object);
//                return fieldValue;
//            }catch (IllegalAccessException e){
//                e.printStackTrace();
//            }catch (InvocationTargetException e){
//                e.printStackTrace();
//            }
//        }
//        catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public TongDunMessage getTongdunMessage() {
        return tongdunMessage;
    }

    public void setTongdunMessage(TongDunMessage tongdunMessage) {
        this.tongdunMessage = tongdunMessage;
    }

    public TianZhenMessage getTianzhenData() {
        return tianzhenData;
    }

    public void setTianzhenData(TianZhenMessage tianzhenData) {
        this.tianzhenData = tianzhenData;
    }
}
