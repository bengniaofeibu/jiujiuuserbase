package com.jiujiu.mapper;

import com.jiujiu.model.HUserBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HUserBaseInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HUserBaseInfo record);

    int insertSelective(HUserBaseInfo record);

    HUserBaseInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HUserBaseInfo record);

    int updateByPrimaryKey(HUserBaseInfo record);
}