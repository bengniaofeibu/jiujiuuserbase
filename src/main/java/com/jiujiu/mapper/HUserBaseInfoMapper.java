package com.jiujiu.mapper;

import com.jiujiu.model.HUserBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HUserBaseInfoMapper {

    int insertUserBaseInfo(HUserBaseInfo record);

    HUserBaseInfo selectByPrimaryKey(String id);

    int updateUserBaseInfoByUserId(HUserBaseInfo record);
}