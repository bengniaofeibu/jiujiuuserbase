package com.jiujiu.mapper;

import com.jiujiu.model.HCustom;

public interface HCustomMapper {
    int deleteByPrimaryKey(String id);

    int insert(HCustom record);

    int insertSelective(HCustom record);

    HCustom selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HCustom record);

    int updateByPrimaryKey(HCustom record);
}