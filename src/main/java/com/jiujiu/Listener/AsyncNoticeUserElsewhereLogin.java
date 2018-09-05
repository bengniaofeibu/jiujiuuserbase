package com.jiujiu.Listener;


import com.jiujiu.mapper.PushInfoMapper;
import com.jiujiu.mapper.UserInfoMapper;
import com.jiujiu.model.PushInfo;
import com.jiujiu.util.PushUtil;
import com.jiujiuwisdom.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步通知用户设备在别处登录或记录用户设备信息
 */
@Component
public class AsyncNoticeUserElsewhereLogin {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PushInfoMapper pushInfoMapper;

    @Async
    @EventListener
    public void noticeUserElsewhereLogin(UserElsewhereLoginListener userElsewhereLoginListener){

        String userId = userInfoMapper.selectUserIdByPhone(userElsewhereLoginListener.getT().getPhone());

        String deviceToken = pushInfoMapper.selectDeviceTokenByUserId(userId);

        if (StringUtils.isBlank(deviceToken)){

            //记录用户设备信息
            pushInfoMapper.insertPushInfo(new PushInfo(UUIDUtil.randomUUID(),userId,userElsewhereLoginListener.getT().getDeviceToken()));
            return;
        }


        if (!deviceToken.equals(userElsewhereLoginListener.getT().getDeviceToken())){

            //推送
            PushUtil.pushsingle(deviceToken,"您的账户在其他地方登录，请注意账号安全，请重新登录");

            //更新用户设备信息
            pushInfoMapper.updateDevicetokenByUserId(new PushInfo(userId,userElsewhereLoginListener.getT().getDeviceToken()));

        }
    }
}
