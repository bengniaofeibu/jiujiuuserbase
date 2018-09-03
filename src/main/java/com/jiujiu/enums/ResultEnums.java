package com.jiujiu.enums;


import com.jiujiuwisdom.base.BaseEnum;

public enum ResultEnums implements BaseEnum {

    SERVER_ERROR(-1,"系统内部错误"),

    PERFECT_USER_BASE_INFO_OK(BaseEnum.code,"完善用户基本信息成功"),

    PERFECT_USER_BASE_INFO_FAIL(10001,"完善用户基本信息失败");

    private Integer code;

    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public void setCode(Integer code) {

    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public void setMsg(String msg) {

    }
}


