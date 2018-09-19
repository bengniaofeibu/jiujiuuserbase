package com.jiujiu.entity.response;

import com.jiujiu.base.BaseEntity;
import com.jiujiu.model.UserInfo;
import com.jiujiu.model.UserReport;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserInfoRes extends BaseEntity {

    private static final long serialVersionUID = 5895699582957353403L;

    private UserInfo user;

    private UserReport userReport;

    @ApiModelProperty(value = "用户状态")
    private Integer lockCode;

    @ApiModelProperty(value = "用户状态描述")
    private String lockMessage;

    @ApiModelProperty(value = "乐享标识")
    private Integer leXiangNotice;



    public UserInfoRes(UserInfo user,UserReport userReport) {
        this.user = user;
        this.userReport = userReport;
    }
}
