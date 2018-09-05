package com.jiujiu.service;

import com.jiujiu.entity.request.SendSmsReq;
import com.jiujiuwisdom.utils.AppletResult;

public interface SmsService {

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    AppletResult sendSMSVerificationCode(SendSmsReq sendSmsReq);
}
