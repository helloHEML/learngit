package com.rongke.enums;

/**
 * 启用/禁用
 */
public enum DisableStatus {

    USER(1,"启用"),
    NOT_USER(0,"禁用");

    private Integer type;

    private String typeName;

    DisableStatus(Integer type, String typeName){
        this.type = type;
        this.typeName = typeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
