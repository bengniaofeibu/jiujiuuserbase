package com.jiujiu.mapper;

import com.jiujiu.model.HArticleInfo;

public interface HArticleInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HArticleInfo record);

    int insertSelective(HArticleInfo record);

    HArticleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HArticleInfo record);

    int updateByPrimaryKey(HArticleInfo record);
}