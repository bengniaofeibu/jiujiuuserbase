package com.jiujiu.entity.response;

import com.jiujiu.base.BaseEntity;
import com.jiujiu.model.ArticleInfo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@ApiModel(description = "文章信息集合")
public class ArticleInfoRes extends BaseEntity{

    private static final long serialVersionUID = 5895699582957353403L;

    private List<ArticleInfo> articleInfoList;

    public ArticleInfoRes(List<ArticleInfo> articleInfoList){
        this.articleInfoList = articleInfoList;
    }
}
