package com.jiujiu.util;

import com.jiujiu.Head.ApiHead;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ReqHeadUtil {


    //获取所有请求头
    public ApiHead getAllReqHead(HttpServletRequest request, ApiHead baseInfo){

            if (baseInfo == null){

                baseInfo = new ApiHead();
            }

            baseInfo.setVersion(request.getHeader("version"));
            baseInfo.setCertType(request.getHeader("certType"));
            baseInfo.setCertification(request.getHeader("certification"));
            baseInfo.setTimestamp(request.getHeader("timestamp"));
            baseInfo.setDeviceToken(request.getHeader("deviceToken"));
            baseInfo.setUserId(request.getHeader("userId"));
            baseInfo.setPlat(request.getHeader("plat"));
            baseInfo.setOsInformation(request.getHeader("osInformation"));
            baseInfo.setChannel(request.getHeader("channel"));
            baseInfo.setLongitude(request.getHeader("longitude"));
            baseInfo.setLatitude(request.getHeader("latitude"));
            baseInfo.setIsTest(request.getIntHeader("isTest"));
            baseInfo.setAppVersion(request.getHeader("appVersion"));

        return baseInfo;
    }
}
