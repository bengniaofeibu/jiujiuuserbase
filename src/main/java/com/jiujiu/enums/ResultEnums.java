package com.jiujiu.enums;


import com.jiujiuwisdom.base.BaseEnum;

public enum ResultEnums implements BaseEnum {

 ;

    private Integer code;

    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public void setCode(Integer code) {

    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public void setMsg(String msg) {

    }
}


