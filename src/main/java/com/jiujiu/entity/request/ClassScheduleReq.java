package com.jiujiu.entity.request;

import com.jiujiu.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ApiModel(description = "上课日程请求对象")
public class ClassSch   duleReq extends BaseEntity {

    private static final long serialVersionUID = 5895699582957353403L;

    @ApiModelProperty(value = "当前页",example = "1")
    @NotNull(message = "当前页字段不能为空")
    @Min(value = 1,message = "当前页超出范围，最小值是1")
    private Integer currentPage;
}
