package com.jiujiu.util;

import com.jiujiu.entity.SmsUrl;
import com.jiujiuwisdom.utils.HttpUtil;
import com.jiujiuwisdom.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SmsUtil {

    @Autowired
    private SmsUrl smsUrl;


    /**
     *  验证短信验证码
     * @param verificationCode
     * @return
     */
    public boolean checkSMSVerificationCode(String phone,String verificationCode){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", phone);
        paramMap.put("captchaNum", verificationCode);
        paramMap.put("markId", 1);
        String result = HttpUtil.doPost(smsUrl.getCkCaptchaUrl(), JSONUtil.toJSONString(paramMap));
        Map map = JSONUtil.parseObject(result, Map.class);
        return map != null && map.get("code").equals(200);
    }

    /**
     *  发送短信验证码
     * @param phone
     * @return
     */
    public boolean sendSMSVerificationCode(String phone){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", phone);
        paramMap.put("expire", 30);
        paramMap.put("length", 6);
        paramMap.put("markId", 1);
        ///验证码短信
        paramMap.put("smsType", 1);
        String result = HttpUtil.doPost(smsUrl.getCaptchaUrl(), JSONUtil.toJSONString(paramMap));
        Map map = JSONUtil.parseObject(result, Map.class);
        return map != null && map.get("code").equals(200);
    }
}
