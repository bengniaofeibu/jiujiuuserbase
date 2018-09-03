package com.jiujiu.controller;

import com.jiujiu.Head.ApiHead;
import com.jiujiu.base.BaseController;
import com.jiujiu.entity.request.UserBaseInfoReq;
import com.jiujiu.entity.request.UserInfoReq;
import com.jiujiu.enums.ResultEnums;
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
@Api("用户相关")
public class NutritionController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(NutritionController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "完善用户基本信息")
    @ApiResponses({
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 10001, message = "完善用户基本信息失败"),
    })
    @PostMapping(value = "/perfectuserbaseinfo")
    public AppletResult perfectUserBaseInfo(HttpServletRequest request, @RequestBody @ApiParam(name = "用户基本信息请求对象",required = true)UserBaseInfoReq userBaseInfoReq){
        userBaseInfoReq = (UserBaseInfoReq) headUtil.getAllReqHead(request,userBaseInfoReq);
        return userService.perfectUserBaseInfo(userBaseInfoReq);
    }



    @ApiOperation(value = "首页饮食信息")
    @ApiResponses({
    })
    @PostMapping(value = "/gethomepagehealthinfo")
    public AppletResult gethomepagehealthinfo(HttpServletRequest request,@RequestBody(required = false) @ApiParam(name = "用户对象",required = true)UserInfoReq userInfoReq){
        userInfoReq = (UserInfoReq) headUtil.getAllReqHead(request,userInfoReq);
        return userService.getHomePageNutritionAndExerciseInfo(userInfoReq);
    }
}
