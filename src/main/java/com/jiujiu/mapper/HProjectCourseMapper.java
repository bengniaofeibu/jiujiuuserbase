package com.jiujiu.mapper;

import com.jiujiu.model.HProjectCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface HProjectCourseMapper {


    List<HProjectCourse> selectCourseInfoByCoachId(String coachId);
}