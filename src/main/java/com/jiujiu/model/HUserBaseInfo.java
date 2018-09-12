package com.jiujiu.model;

import com.jiujiu.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class HUserBaseInfo extends BaseModel{

    private static final long serialVersionUID = 5895699582957353403L;

    private String id;

    private String customId;

    private Integer age;

    private Integer weight;

    private Integer height;

    private Date birth;

    private String workType;

    private Integer gender;

    private Integer status;

    private Integer delFlag;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    public HUserBaseInfo() {
    }

    public HUserBaseInfo(String id) {
       this.id = id;
    }

    public HUserBaseInfo(String id,Integer weight, Integer height, Date birth, String workType, Integer gender) {
        this(id);
        this.weight = weight;
        this.height = height;
        this.birth = birth;
        this.workType = workType;
        this.gender = gender;
        this.status = 1;
    }
}