package com.jiujiu.mapper;

import com.jiujiu.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ArticleInfoMapper {

    List<ArticleInfo> selectArticleInfo();
}