package com.rongke.enums;public enum TianBeiEnums {    LOGIN_SMS(1,"反欺诈"),    ;    private Integer type;    private String key;    private String value;    TianBeiEnums(Integer type, String key) {        this.type = type;        this.key = key;        this.value = value;    }    TianBeiEnums(Integer type, String key, String value) {        this.type = type;        this.key = key;        this.value = value;    }    public Integer getType() {        return type;    }    public void setType(Integer type) {        this.type = type;    }    public String getKey() {        return key;    }    public void setKey(String key) {        this.key = key;    }    public String getValue() {        return value;    }    public void setValue(String value) {        this.value = value;    }}