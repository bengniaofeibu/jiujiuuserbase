package com.jiujiu.enums;


import com.jiujiuwisdom.base.BaseEnum;

public enum ResultEnums implements BaseEnum {

    SERVER_ERROR(-1,"系统内部错误"),

    PERFECT_USER_BASE_INFO_OK(BaseEnum.code,"完善用户基本信息成功"),

    UPDATE_USER_BASE_INFO_OK(BaseEnum.code,"更新用户基本信息成功"),

    SEND_VERIFICATION_CODE_OK(BaseEnum.code,"验证码发送成功"),

    USER_NOT_FOUND_INFO_FAIL(10000,"用户信息获取失败"),

    PERFECT_USER_BASE_INFO_FAIL(10001,"完善用户基本信息失败"),

    VERIFICATION_CODE_CHECK_FAIL(10002,"请输入正确的验证码"),

    PHONE_FORMAT_FAIL(10003,"请输入正确的手机号"),

    SEND_VERIFICATION_CODE_FAIL(10004,"验证码发送失败"),

    CLASS_NOT_FOUND_FAIL(10005,"暂无上课日程"),

    ARTICLE_INFO_NOT_FOUND_FAIL(10006,"暂无文章推荐"),

    USER_BIRTH_FAIL(10007,"生日不能大于现在时间"),

    PERFECT_USER_BASE_NOT_INFO_FAIL(10008,"用户基本信息没有完善");

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


