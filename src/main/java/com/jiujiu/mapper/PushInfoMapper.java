package com.jiujiu.mapper;

import com.jiujiu.model.PushInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PushInfoMapper {

    int insertPushInfo(PushInfo record);

    String selectDeviceTokenByUserId(String userid);

    int updateDevicetokenByUserId(PushInfo record);

}