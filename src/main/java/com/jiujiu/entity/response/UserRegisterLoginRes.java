package com.jiujiu.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(description = "用户注册登录返回对象")
public class UserRegisterLoginRes {

    @ApiModelProperty(value = "完善资料状态 0 未完善 1 已完善")
    private Integer dataStatus;

    public UserRegisterLoginRes(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }
}
