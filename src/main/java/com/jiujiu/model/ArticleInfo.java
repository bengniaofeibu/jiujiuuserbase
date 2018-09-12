package com.jiujiu.model;

import com.jiujiu.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@ApiModel(description = "文章信息集合")
public class ArticleInfo extends BaseModel{

    private static final long serialVersionUID = 5895699582957353403L;

    private Long id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "作者")
    private String author;

    private String userId;

    private Long contentId;

    private String catId;

    private String image;

    private String h5Url;

    @ApiModelProperty(value = "摘要")
    private String desc;

    private Integer sort;

    private Integer top;

    private Long views;

    private Long share;

    private Long discuss;

    private Long collect;

    private Long kudos;

    private Integer auditStatus;

    private Date currentTime;

    private Integer status;

    private Integer delFlag;

    private String createBy;

    private Date createDate;

    @ApiModelProperty(value = "发表时间")
    private String publishedTime;

    private String updateBy;

    private Date updateDate;

    private String remarks;

}