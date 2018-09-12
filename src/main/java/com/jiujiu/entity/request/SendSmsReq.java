package com.jiujiu.entity.request;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@ApiModel(description = "发送短信验证码请求对象")
public class SendSmsReq extends BaseEntity{

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "用户手机号")
    @NotBlank(message = "手机号不能为空或者空字符串")
    private String phone;
}
