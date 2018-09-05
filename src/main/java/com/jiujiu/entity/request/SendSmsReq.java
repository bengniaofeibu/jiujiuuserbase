package com.jiujiu.entity.request;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "发送短信验证码请求对象")
public class SendSmsReq extends BaseEntity{

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "用户手机号")
    private String phone;
}
