package com.jiujiu.model;

import com.jiujiu.base.BaseModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class UserInfo extends BaseModel{

    private static final long serialVersionUID = 5895699582957353403L;

    private String id;

    private Integer accountStatus;

    private Date addTime;

    private String appVersion;

    private String certificateNegative;

    private String certificatePositive;

    private Integer changeBatteryStatus;

    private String cityNo;

    private Integer creditScore;

    private BigDecimal deposit;

    private Integer guesterState;

    private String idCardnum;

    private Integer integral;

    private Integer loginState;

    private Date loginTime;

    private Integer mBorrowBicycle;

    private Date mBorrowBicycleDate;

    private String mPhoneSystemVersion;

    private String nationality;

    private String nickname;

    private Date openDate;

    private String phone;

    private String picurl;

    private String realName;

    private Date statusChangeTime;

    private Integer userLevel;

    private Date validateDate;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private BigDecimal balance;

    private BigDecimal balanceFree;

    private Date monthCardTime;

    private Integer gender;

    private BigDecimal luckyMoney;

    private Integer age;

    private String alipayAccount;

    private String phoneProvince;

    private String phoneCity;

    private String channel;

    private String registerCity;

    private String imei;

    private String idfa;

    private Integer userSource;

    private String mac;

    private HUserBaseInfo userBaseInfo;

    public UserInfo(){

    }

    public UserInfo(String id){
        this.id = id;
    }


    public UserInfo(String id,String nickname,String picurl,Integer gender) {
        this(id);
        this.nickname = nickname;
        this.picurl = picurl;
        this.gender = gender;
    }
}