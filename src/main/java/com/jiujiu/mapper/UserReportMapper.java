package com.jiujiu.mapper;

import com.jiujiu.model.UserReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserReportMapper {

    UserReport selectUserReportByUserId(String id);

    int insertUserReport(UserReport userReport);
}