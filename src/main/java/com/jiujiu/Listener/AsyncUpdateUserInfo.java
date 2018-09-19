package com.jiujiu.Listener;

import com.jiujiu.mapper.UserInfoMapper;
import com.jiujiu.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *  异步更新用户信息
 */
@Component
public class AsyncUpdateUserInfo {

  @Autowired
  private UserInfoMapper userInfoMapper;

  @Async
  @EventListener
  public void updateUserInfo(UserInfoUpdateListener userListener){

    UserInfo userInfo  = new UserInfo(userListener.getT().getUserId(),userListener.getT().getAge(),userListener.getT().getUserNickname(),
            userListener.getT().getUserHeadImage(),userListener.getT().getUserGender());
    userInfoMapper.updateUserInfoByUserIdOrPhone(userInfo);
  }
}


