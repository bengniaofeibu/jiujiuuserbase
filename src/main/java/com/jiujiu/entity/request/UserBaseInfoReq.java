package com.jiujiu.entity.request;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@ApiModel(description = "完善用户基本信息请求参数")
public class UserBaseInfoReq extends BaseEntity {

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "用户头像")
    private String userHeadImage;

    @ApiModelProperty(value = "用户昵称")
    private String userNickname;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    @ApiModelProperty(value = "用户性别 0女  1男")
    private Integer userGender;

    @ApiModelProperty(value = "用户生日",example = "1535904000")
    private Long birth;

    @ApiModelProperty(value = "用户身高")
    private Integer height;

    @ApiModelProperty(value = "用户体重")
    private Integer weight;

    @ApiModelProperty(value = "用户工作类型")
    private String workType;
}
