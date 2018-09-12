package com.jiujiu.mapper;

import com.jiujiu.model.HCourseBaseInfo;

public interface HCourseBaseInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HCourseBaseInfo record);

    int insertSelective(HCourseBaseInfo record);

    HCourseBaseInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HCourseBaseInfo record);

    int updateByPrimaryKey(HCourseBaseInfo record);
}