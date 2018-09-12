package com.jiujiu.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class HProjectCourse {
    private String id;

    private String customId;

    private String projectId;

    private String courseBaseId;

    private String coachId;

    private Date classTime;

    private Integer signInCount;

    private Integer checkoutCount;

    private Integer delFlag;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private HCustom hCustom;

    private HCourseBaseInfo hCourseBaseInfo;

}