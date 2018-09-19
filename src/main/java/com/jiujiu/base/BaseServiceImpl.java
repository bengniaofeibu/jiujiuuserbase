package com.jiujiu.base;

import com.jiujiu.Head.ApiHead;
import com.jiujiu.mapper.UserReportMapper;
import com.jiujiu.model.UserReport;
import com.jiujiu.util.PublicEvent;
import com.jiujiu.util.SmsUtil;
import com.jiujiuwisdom.utils.BaiduUtil;
import com.jiujiuwisdom.utils.BigDecimalUtil;
import com.jiujiuwisdom.utils.RedisClient;
import com.jiujiuwisdom.utils.RegularUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

public abstract class BaseServiceImpl {

    @Autowired
    protected PublicEvent publicEvent;

    @Autowired
    protected SmsUtil smsUtil;

    @Autowired
    protected UserReportMapper userReportMapper;

    @Autowired
    protected RedisClient redisClient;

    private BigDecimal bigDecimal = new BigDecimal(0.00);

    /**
     *  获取城市名称
     * @param apiHead
     * @return
     */
    protected String getCityName(ApiHead apiHead){
        return BaiduUtil.getCityName(apiHead.getLatitude(), apiHead.getLongitude());
    }

    /**
     *  获取用户报告
     * @param userId
     * @return
     */
    protected UserReport getUserReport(String userId){

        //用户骑行报告
        UserReport report = userReportMapper.selectUserReportByUserId(userId);

        //如果为空 赋值为默认值
        report =  Optional.ofNullable(report).orElse(new UserReport(bigDecimal,bigDecimal,bigDecimal));

        report.setCalorie(BigDecimalUtil.scale(report.getCalorie(), 2, BigDecimal.ROUND_HALF_UP));
        report.setDuration(BigDecimalUtil.scale(report.getDuration(), 2, BigDecimal.ROUND_HALF_UP));
        report.setKilometers(BigDecimalUtil.scale(report.getKilometers(), 2, BigDecimal.ROUND_HALF_UP));

        return report;
    }

    /**
     *  验证手机号
     * @param phone
     * @return
     */
    protected boolean verifyPhoneFormat(String phone){
        return RegularUtil.verifyPhoneFormat(phone);
    }

    /**
     *  验证短信验证码
     * @param phone
     * @param code
     * @return
     */
    protected boolean checkSMSCode(String phone,String code){
        return smsUtil.checkSMSVerificationCode(phone, code);

    }
}
