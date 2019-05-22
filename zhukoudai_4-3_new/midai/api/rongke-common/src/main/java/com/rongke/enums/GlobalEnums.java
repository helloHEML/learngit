package com.rongke.enums;

public enum GlobalEnums {
    RENEWAL(1,"手机续期","Renewal"),
    PAYPASSWORD(2,"打款密码","payPassWord"),
    EXAMINE(3,"机审审核","examine"),
    Make(4,"机审打款","make"),
    ;
    private Integer type;
    private String key;
    private String value;
    GlobalEnums(Integer type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
