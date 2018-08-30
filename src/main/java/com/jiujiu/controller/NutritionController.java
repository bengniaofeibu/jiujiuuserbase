package com.jiujiu.controller;

import com.jiujiu.Head.ApiHead;
import com.jiujiu.base.BaseController;
import com.jiujiu.entity.request.UserInfoReq;
import com.jiujiu.model.UserInfo;
import com.jiujiu.service.UserService;
import com.jiujiuwisdom.utils.AppletResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RefreshScope
@RestController
@Api("用户饮食营养api")
public class NutritionController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(NutritionController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "首页饮食信息",notes = "测试Swagger是否可行")
    @ApiResponses({
            @ApiResponse(code = 500, message = "2001:因输入数据问题导致的报错"),
    })
    @PostMapping(value = "/gethomepagehealthinfo")
    public AppletResult gethomepagehealthinfo(HttpServletRequest request,@RequestBody(required = false) @ApiParam(name = "用户对象",required = true)UserInfoReq userInfoReq){
        userInfoReq = (UserInfoReq) headUtil.getAllReqHead(request,userInfoReq);
        return userService.getHomePageNutritionAndExerciseInfo(userInfoReq);
    }
}
