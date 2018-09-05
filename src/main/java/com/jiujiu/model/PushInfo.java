package com.jiujiu.model;

import java.util.Date;

public class PushInfo {
    private String id;

    private String devicetoken;

    private Date addtime;

    private String userid;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String brandTag;

    private String brandPushId;

    private String baiduCityTag;

    public PushInfo(String id,String userId,String devicetoken){
        this(userId,devicetoken);
        this.id = id;
    }

    public PushInfo(String userId,String devicetoken){
         this.userid = userId;
         this.devicetoken = devicetoken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken == null ? null : devicetoken.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getBrandTag() {
        return brandTag;
    }

    public void setBrandTag(String brandTag) {
        this.brandTag = brandTag == null ? null : brandTag.trim();
    }

    public String getBrandPushId() {
        return brandPushId;
    }

    public void setBrandPushId(String brandPushId) {
        this.brandPushId = brandPushId == null ? null : brandPushId.trim();
    }

    public String getBaiduCityTag() {
        return baiduCityTag;
    }

    public void setBaiduCityTag(String baiduCityTag) {
        this.baiduCityTag = baiduCityTag == null ? null : baiduCityTag.trim();
    }
}