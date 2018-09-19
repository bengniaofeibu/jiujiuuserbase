package com.jiujiu.model;

import com.jiujiu.base.BaseModel;
import com.jiujiuwisdom.utils.UUIDUtil;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserReport extends BaseModel{

    private static final long serialVersionUID = 5895699582957353403L;

    private String id;

    private BigDecimal calorie;

    private BigDecimal duration;

    private BigDecimal kilometers;

    private String userId;

    public UserReport() {
    }

    public UserReport(String id, String userId){
        this.id = id;
        this.userId = userId;
    }

    public UserReport(BigDecimal calorie, BigDecimal duration, BigDecimal kilometers) {
        this.calorie = calorie;
        this.duration = duration;
        this.kilometers = kilometers;
    }
}