package com.jiujiu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SystemUserMapper {

    Integer selectUserTypeByPhone(String phone);
}