package com.rongke.web.metahandler;import com.baomidou.mybatisplus.mapper.MetaObjectHandler;import org.apache.commons.lang3.StringUtils;import org.apache.ibatis.reflection.MetaObject;import java.lang.annotation.Annotation;import java.util.Date;public class MyMetaObjectHandler extends MetaObjectHandler {    @Override    public void insertFill(MetaObject metaObject) {        Object create_time = getFieldValByName("createTime", metaObject);        Object update_time = getFieldValByName("updateTime", metaObject);        System.err.println(create_time);        System.err.println(update_time);        if(create_time == null){            setFieldValByName("createTime", new Date(), metaObject);        }        if(update_time == null){            setFieldValByName("updateTime", new Date(), metaObject);        }    }    @Override    public void updateFill(MetaObject metaObject) {        Object update_time = getFieldValByName("updateTime", metaObject);        if(update_time == null){            setFieldValByName("updateTime", new Date(), metaObject);        }    }}