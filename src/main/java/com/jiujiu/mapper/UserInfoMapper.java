package com.jiujiu.mapper;

import com.jiujiu.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoMapper {

    int insertNewUser(UserInfo record);

    UserInfo selectUserBaseInfo(String id);

    String selectUserIdByPhone(String phone);

    int updateUserInfoByUserIdOrPhone(UserInfo record);
}