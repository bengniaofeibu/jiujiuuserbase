package com.jiujiu.entity.request;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ApiModel(description = "首页文章列表请求对象")
public class ArticleInfoReq extends BaseEntity {

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "完善资料状态 0 未完善 1 已完善")
    @NotNull(message = "用户完善资料状态参数不能为空")
    private Integer dataStatus;
}
