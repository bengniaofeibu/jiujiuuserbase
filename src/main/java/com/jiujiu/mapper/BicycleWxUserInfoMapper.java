package com.jiujiu.mapper;

import com.jiujiu.model.BicycleWxUserInfo;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface BicycleWxUserInfoMapper {

    Integer selectCountByUserId(String userId);
}