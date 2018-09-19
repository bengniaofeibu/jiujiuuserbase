package com.jiujiu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jiujiu.Listener.UserElsewhereLoginListener;
import com.jiujiu.base.BaseServiceImpl;
import com.jiujiu.entity.request.*;
import com.jiujiu.entity.response.*;
import com.jiujiu.mapper.*;
import com.jiujiu.Listener.UserInfoUpdateListener;
import com.jiujiu.enums.ResultEnums;
import com.jiujiu.model.*;
import com.jiujiu.service.UserService;
import com.jiujiuwisdom.constant.DateFormatConstant;
import com.jiujiuwisdom.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private HUserBaseInfoMapper hUserBaseInfoMapper;

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private HProjectCourseMapper projectCourseMapper;

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Autowired
    private BicycleWxUserInfoMapper bicycleWxUserInfoMapper;


    private static final String USER_ILLEGAL_KEY = "user:illegal:";

    private static final String ACCOUNT_LOCKED_KEY = "account_locked_";

    private static final String FUND_LOCK_USER_KEY = "fund_lock_user_";

    private static final String BLACK_LIST_KEY = "black_list_";

    private static final String SYSTEM_LEXIANG_COUNT_KEY = "system_lexiang_count_";

    private static final String USER_LEXIANG_COUNT_KEY = "user_lexiang_count_";

    private static final String SYSTEM_LEXIANG_COUNT_DEFAULT_KEY = "system_lexiang_count_default";


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 用户注册登录
     *
     * @param userRegisterLoginReq
     * @return
     */
    @Override
    public AppletResult userRegisterLogin(UserRegisterLoginReq userRegisterLoginReq) {

        final String userPhone = userRegisterLoginReq.getPhone();


        if (!verifyPhoneFormat(userPhone)) return ResultUtil.error(ResultEnums.PHONE_FORMAT_FAIL);


        if (!checkSMSCode(userPhone, userRegisterLoginReq.getVerificationCode()))
            return ResultUtil.error(ResultEnums.VERIFICATION_CODE_CHECK_FAIL);


        //获取用户信息
        UserInfo userBaseInfo = userInfoMapper.selectUserBaseInfo(userPhone);
        LOGGER.debug("用户信息 {}", userBaseInfo);


        int dataStatus = 0; //用户是否完善资料状态

        //用户id
        String userId;

        //判断用户是已经注册过了
        if (userBaseInfo == null) {

            userBaseInfo = new UserInfo();

            userId = UUIDUtil.randomUUID();

            //新注册
            userBaseInfo.setPhone(userPhone);
            userBaseInfo.setLoginTime(new Date());
            userBaseInfo.setId(userId);
            userBaseInfo.setAppVersion(userRegisterLoginReq.getAppVersion());
            userBaseInfo.setNickname(new StringBuilder("99").append(userPhone.substring(7, userPhone.length())).toString());
            userBaseInfo.setPicurl("");
            userInfoMapper.insertNewUser(userBaseInfo);

            //记录用户报告
            userReportMapper.insertUserReport(new UserReport(UUIDUtil.randomUUID(), userId));

        } else {

            userId = userInfoMapper.selectUserIdByPhone(userPhone);

            //赋值用户是否完善资料状态
            dataStatus = userBaseInfo.getUserBaseInfo().getStatus();

            //已经注册
            userBaseInfo.setAppVersion(userRegisterLoginReq.getAppVersion());
            userInfoMapper.updateUserInfoByUserIdOrPhone(userBaseInfo);

            //异步处理更新用户deviceToken或通知用户别处登录
            publicEvent.publicEvent(new UserElsewhereLoginListener(this, userRegisterLoginReq));

        }

        return ResultUtil.success(new UserRegisterLoginRes(userId, dataStatus));
    }

    /**
     * 获取用户信息
     *
     * @param userInfoReq
     * @return
     */
    @Override
    public AppletResult<UserInfoRes> getUserInfo(UserInfoReq userInfoReq) {


        //用户基本信息
        UserInfo userInfo = userInfoMapper.selectUserBaseInfo(userInfoReq.getUserId());

        if (userInfo == null) return ResultUtil.error(ResultEnums.USER_NOT_FOUND_INFO_FAIL);

        //用户完善的信息
        HUserBaseInfo userBaseInfo = userInfo.getUserBaseInfo();

        if ( userBaseInfo == null || userBaseInfo.getStatus() == 0 ) return ResultUtil.error(ResultEnums.PERFECT_USER_BASE_NOT_INFO_FAIL);

        //获取用户类型
        Integer userType = systemUserMapper.selectUserTypeByPhone(userInfo.getPhone());

        if (userType == null || userType != 1) {

            // 0 普通用户 1 教练 2 企业用户
            userType = StringUtils.isBlank(userBaseInfo.getCustomId()) ? 0 : 2;
        }

        //用户id
        String userId = userInfoReq.getUserId();

        //赋值用户信息
        userInfo.setWeight(userBaseInfo.getWeight());
        userInfo.setHeight(userBaseInfo.getHeight());
        userInfo.setUserBirth(DateUtil.format(userBaseInfo.getBirth(), DateFormatConstant.DATE_FMT_4));
        userInfo.setWorkTypeTag(userBaseInfo.getWorkTypeTag());
        userInfo.setUserType(userType);
        userInfo.setDataStatus(userInfo.getUserBaseInfo().getStatus());
        userInfo.setUserBaseInfo(null);//不返回用户基本信息model，信息都封装在userInfo里面

        // 1 已绑定 0 未绑定
        userInfo.setIsAuthWx(bicycleWxUserInfoMapper.selectCountByUserId(userId) > 0 ? 1 : 0);


        //赋值用户信息和用户报告
        UserInfoRes userInfoRes = new UserInfoRes(userInfo, getUserReport(userId));


        //赋值用户状态
        setUserLockInfo(userInfoRes, userInfoReq, userId);


        //赋值乐享信息
        setLeiXingNotice(getCityName(userInfoReq), userInfoRes, userId);


        return ResultUtil.success(userInfoRes);
    }

    /**
     * 完善用户资料
     *
     * @param userBaseInfoReq
     * @return
     */
    @Override
    public AppletResult perfectUserBaseInfo(UserBaseInfoReq userBaseInfoReq) {


        Date userBirth = new Date(userBaseInfoReq.getBirth().toString().length() < 13 ? userBaseInfoReq.getBirth() * 1000L : userBaseInfoReq.getBirth());

        Date dateNow = new Date();

        //判断用户生日是否大于现在
        if (DateUtil.isAfter(userBirth, dateNow)) ResultUtil.error(ResultEnums.USER_BIRTH_FAIL);


        //用户年龄
        Long age = DateUtil.betweenDate(userBirth, dateNow, ChronoUnit.YEARS);


        HUserBaseInfo userBaseInfo = new HUserBaseInfo(userBaseInfoReq.getUserId(), age.intValue(), userBaseInfoReq.getWeight(),
                userBaseInfoReq.getHeight(), userBirth,userBaseInfoReq.getWorkTypeTag(), userBaseInfoReq.getUserGender());

        if (Integer.valueOf(userBaseInfoReq.getOperateType()) == 0) {

            //记录用户完善的资料
            int insertCount = hUserBaseInfoMapper.insertUserBaseInfo(userBaseInfo);

            //判断是否已经存入数据库中
            if (insertCount > 0) {

                //更新成功后，异步处理更新用户信息
                userBaseInfoReq.setAge(age.intValue());
                publicEvent.publicEvent(new UserInfoUpdateListener(this, userBaseInfoReq));
                return ResultUtil.success(ResultEnums.PERFECT_USER_BASE_INFO_OK);
            }


        } else {

            //更新用户完善的资料
            int updateCount = hUserBaseInfoMapper.updateUserBaseInfoByUserId(userBaseInfo);

            if (updateCount > 0) {

                //更新成功后，异步处理更新用户信息
                userBaseInfoReq.setAge(age.intValue());
                publicEvent.publicEvent(new UserInfoUpdateListener(this, userBaseInfoReq));
                return ResultUtil.success(ResultEnums.UPDATE_USER_BASE_INFO_OK);
            }

        }

        return ResultUtil.success(ResultEnums.PERFECT_USER_BASE_INFO_FAIL);
    }

    /**
     * 获取上课日程
     *
     * @param classScheduleReq
     * @return
     */
    @Override
    public AppletResult<ClassScheduleResPage> getClassSchedule(ClassScheduleReq classScheduleReq) {

        Page<HProjectCourse> page = PageHelper.startPage(classScheduleReq.getCurrentPage(), 10, false);

        List<HProjectCourse> hProjectCourses = projectCourseMapper.selectCourseInfoByCoachId(classScheduleReq.getUserId());

        if (hProjectCourses == null || hProjectCourses.size() == 0)
            return ResultUtil.success(ResultEnums.CLASS_NOT_FOUND_FAIL);


        List<ClassScheduleRes> classScheduleList = new LinkedList<>();

        ClassScheduleRes classScheduleRes;
        for (HProjectCourse hProjectCourse : hProjectCourses) {
            classScheduleRes = new ClassScheduleRes();
            classScheduleRes.setCompanyId(hProjectCourse.getCustomId());
            classScheduleRes.setClassId(hProjectCourse.getCourseBaseId());
            classScheduleRes.setClassCheckoutCount(hProjectCourse.getCheckoutCount());
            classScheduleRes.setClassTime(DateUtil.format(hProjectCourse.getClassTime(), DateFormatConstant.TIME_PATTERN));
            classScheduleRes.setClassName(hProjectCourse.getHCourseBaseInfo().getName());
            classScheduleRes.setClassCompany(hProjectCourse.getHCustom().getCustomName());
            classScheduleRes.setClassAddress(hProjectCourse.getHCustom().getCustomAddr());
            classScheduleList.add(classScheduleRes);
        }

        //根据上课时间倒序
        classScheduleList.sort(Comparator.comparing(ClassScheduleRes::getClassTime).reversed());

        ClassScheduleResPage classScheduleResPage = new ClassScheduleResPage();
        classScheduleResPage.setClassScheduleResList(classScheduleList);
        classScheduleResPage.setTotalPage(page.getPages());
        return ResultUtil.success(classScheduleResPage);
    }

    /**
     * 获取文章信息
     *
     * @param articleInfoReq
     * @return
     */
    @Override
    public AppletResult<ArticleInfoRes> getArticleInfo(ArticleInfoReq articleInfoReq) {


        //结合社区后台文章标签项推荐符合标签中浏览量最高的2条图文
        //第一版默认推荐浏览量最高的2条文章
        List<ArticleInfo> articleInfos = articleInfoReq.getDataStatus() == 1 ? articleInfoMapper.selectArticleInfo() : articleInfoMapper.selectArticleInfo();


        if (articleInfos == null || articleInfos.size() == 0)
            return ResultUtil.error(ResultEnums.ARTICLE_INFO_NOT_FOUND_FAIL);


        for (ArticleInfo articleInfo : articleInfos) {

            articleInfo.setPublishedTime(DateUtil.format(new Date(), DateFormatConstant.DATE_FMT_9));
        }

        return ResultUtil.success(new ArticleInfoRes(articleInfos));
    }


    private void setLeiXingNotice(String cityName, UserInfoRes userInfoRes, String userId) {

        String systemCountStr = StringUtils.isNotBlank(cityName) ? redisClient.get(SYSTEM_LEXIANG_COUNT_KEY + cityName) : redisClient.get(SYSTEM_LEXIANG_COUNT_DEFAULT_KEY);
        String userCountStr = redisClient.get(USER_LEXIANG_COUNT_KEY + userId);
        setLeXingNotice(userInfoRes, systemCountStr, userCountStr);
    }

    private void setLeXingNotice(UserInfoRes userInfoRes, String systemCountStr, String userCountStr) {

        userInfoRes.setLeXiangNotice((StringUtils.isNotEmpty(systemCountStr) &&
                StringUtils.isNotEmpty(userCountStr)) && (Integer.parseInt(systemCountStr) <= Integer.parseInt(userCountStr)) ? 0 : 1);

    }

    private void setUserLockInfo(UserInfoRes userInfoRes, UserInfoReq userInfoReq, String userId) {

        String accountLockedState = redisClient.get(ACCOUNT_LOCKED_KEY + userId);

        String lockFlag = redisClient.get(FUND_LOCK_USER_KEY + userId);

        String blackList;

        //账户多次被锁定加入黑名单
        if (!StringUtils.isEmpty(blackList = redisClient.get(BLACK_LIST_KEY + userId))) {
            String[] reasons = blackList.split("#");
            if (reasons.length >= 2) {
                userInfoRes.setLockCode(3);
                userInfoRes.setLockMessage("您因" + reasons[0] + "，无法使用赳赳单车，禁用期至" + reasons[1] + ".");
            } else {
                userInfoRes.setLockCode(0);
                userInfoRes.setLockMessage("用户状态正常");
            }
        } else if (!StringUtils.isEmpty(accountLockedState)) {
            if (accountLockedState.equals("out_of_fence")) {
                userInfoRes.setLockCode(2);
                userInfoRes.setLockMessage("您已多次未在骑行区域内还车，账户和押金已被冻结。");//骑出围栏被锁
            } else {
                userInfoRes.setLockCode(2);
                userInfoRes.setLockMessage("亲，您短时间内已连续开锁多次且未正常确认还车，您的账户将在确认关锁后恢复使用。");//账户被锁定
            }

        } else if (!StringUtils.isEmpty(lockFlag)) {
            userInfoRes.setLockCode(1);
            userInfoRes.setLockMessage("押金冻结中，无法退款，运维人员将在1小时内确认车锁信息后解冻。");//押金被冻结
        } else if (!StringUtils.isEmpty(userInfoReq.getIdCardNum()) && !StringUtils.isEmpty(blackList = redisClient.get(BLACK_LIST_KEY + userInfoReq.getIdCardNum()))) {

            if (userInfoReq.getAppVersion().startsWith("Android")) {

                String userIllegalKey = new StringBuilder(USER_ILLEGAL_KEY).append(userId).toString();

                //判断是否是全能车用户
                boolean ifUserIdExist = redisClient.exists(userIllegalKey);

                if (ifUserIdExist) {
                    userInfoRes.setLockCode(2);
                    userInfoRes.setLockMessage("账户异常，正在核实");
                }

            } else {

                String[] reasons = blackList.split("#");
                if (reasons.length >= 2) {
                    userInfoRes.setLockCode(3);
                    userInfoRes.setLockMessage("账户异常，正在核实");
                } else {
                    userInfoRes.setLockCode(0);
                    userInfoRes.setLockMessage("用户状态正常");
                }
            }
        } else {
            userInfoRes.setLockCode(0);
            userInfoRes.setLockMessage("用户状态正常");
        }
    }
}
