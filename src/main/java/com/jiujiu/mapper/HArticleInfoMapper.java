package com.jiujiu.mapper;

import com.jiujiu.model.HArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HArticleInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HArticleInfo record);

    int insertSelective(HArticleInfo record);

    HArticleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HArticleInfo record);

    int updateByPrimaryKey(HArticleInfo record);
}