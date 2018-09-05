package com.jiujiu.entity.request;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "用户注册登录请求对象")
public class UserRegisterLoginReq extends BaseEntity {

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "用户登录手机号")
    private String phone;

    @ApiModelProperty(value = "短信验证码")
    private String verificationCode;

}
