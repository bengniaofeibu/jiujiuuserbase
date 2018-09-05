package com.jiujiu.entity.response;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserInfoRes extends BaseEntity {

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "用户头像")
    private String headImage;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户性别")
    private Integer gender;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户生日")
    private String birth;

    @ApiModelProperty(value = "用户身高")
    private Integer height;

    @ApiModelProperty(value = "用户体重")
    private Integer weight;

    @ApiModelProperty(value = "用户工作类型")
    private String workType;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    public UserInfoRes(String headImage, String nickName, Integer gender, String phone, String birth, Integer height, Integer weight, String workType,Integer userType) {
        this.headImage = headImage;
        this.nickName = nickName;
        this.gender = gender;
        this.phone = phone;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.workType = workType;
        this.userType = userType;
    }
}
