package com.jiujiu.mapper;

import com.jiujiu.model.HProjectInfo;

public interface HProjectInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HProjectInfo record);

    int insertSelective(HProjectInfo record);

    HProjectInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HProjectInfo record);

    int updateByPrimaryKey(HProjectInfo record);
}