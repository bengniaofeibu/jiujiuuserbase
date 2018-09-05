package com.jiujiu.service.impl;

import com.jiujiu.base.BaseServiceImpl;
import com.jiujiu.entity.request.SendSmsReq;
import com.jiujiu.enums.ResultEnums;
import com.jiujiu.service.SmsService;
import com.jiujiuwisdom.utils.AppletResult;
import com.jiujiuwisdom.utils.RegularUtil;
import com.jiujiuwisdom.utils.ResultUtil;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl extends BaseServiceImpl implements SmsService {

    /**
     * 发送短信验证码
     *
     * @param sendSmsReq
     * @return
     */
    @Override
    public AppletResult sendSMSVerificationCode(SendSmsReq sendSmsReq) {

        //验证手机号格式
        boolean phoneFormat = RegularUtil.verifyPhoneFormat(sendSmsReq.getPhone());

        if (!phoneFormat){

            return ResultUtil.error(ResultEnums.PHONE_FORMAT_FAIL);

        }

        return smsUtil.sendSMSVerificationCode(sendSmsReq.getPhone()) ? ResultUtil.success(ResultEnums.SEND_VERIFICATION_CODE_OK) : ResultUtil.error(ResultEnums.SEND_VERIFICATION_CODE_FAIL);
    }
}
