package com.jiujiu.entity.response;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "上课日程返回对象")
public class ClassScheduleRes extends BaseEntity{

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "企业Id")
    private String companyId;

    @ApiModelProperty(value = "课程Id")
    private String classId;

    @ApiModelProperty(value = "上课签到人数")
    private Integer classCheckoutCount;

    @ApiModelProperty(value = "上课时间")
    private String classTime;

    @ApiModelProperty(value = "课程名称")
    private String className;

    @ApiModelProperty(value = "上课企业名称")
    private String classCompany;

    @ApiModelProperty(value = "上课企业地址")
    private String classAddress;

    @ApiModelProperty(value = "课程二维码")
    private String classCode;
}
