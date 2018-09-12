package com.jiujiu.entity.request;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@ApiModel(description = "用户注册登录请求对象")
public class UserRegisterLoginReq extends BaseEntity {

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "用户登录手机号")
    @NotBlank(message = "手机号不能为空或者字符串")
    private String phone;

    @ApiModelProperty(value = "短信验证码")
    @NotBlank(message = "短信验证码不能为空或者字符串")
    private String verificationCode;

}
