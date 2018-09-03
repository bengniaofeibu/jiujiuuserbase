package com.jiujiu.mapper;

import com.jiujiu.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectUserBaseInfo(String id);

    int updateUserInfoByUserId(UserInfo record);
}