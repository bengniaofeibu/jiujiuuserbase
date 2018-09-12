package com.jiujiu.entity.response;

import com.jiujiu.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ClassScheduleResPage extends BaseEntity{

    private static final long serialVersionUID = 5895699582957353403L;

    private List<ClassScheduleRes> classScheduleResList;

    private Integer totalPage;

}
