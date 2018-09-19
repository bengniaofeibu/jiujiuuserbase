package com.jiujiu.controller;

import com.jiujiu.base.BaseController;
import com.jiujiu.entity.request.*;
import com.jiujiu.entity.response.*;
import com.jiujiu.service.UserService;
import com.jiujiuwisdom.utils.AppletResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RefreshScope
@RestController
@Api("用户相关")
public class UserController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户注册登录")
    @ApiResponses({
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 10002, message = "请输入正确的验证码"),
            @ApiResponse(code = 10003, message = "请输入正确的手机号"),
    })
    @PostMapping(value = "/userregisterlogin")
    public AppletResult<UserRegisterLoginRes> userRegisterLogin(HttpServletRequest request,
                                                                @RequestBody @ApiParam(name = "用户注册登录请求对象",required = true)UserRegisterLoginReq userRegisterLoginReq){
        userRegisterLoginReq = (UserRegisterLoginReq) headUtil.getAllReqHead(request,userRegisterLoginReq);
        return userService.userRegisterLogin(userRegisterLoginReq);
    }

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


    @ApiOperation(value = "获取用户信息")
    @ApiResponses({
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 10000, message = "用户信息获取失败"),
    })
    @GetMapping(value = "/getuserinfo")
    public AppletResult<UserInfoRes> getUserInfo(HttpServletRequest request,UserInfoReq userInfoReq){
        userInfoReq = (UserInfoReq) headUtil.getAllReqHead(request,userInfoReq == null?new UserInfoReq():userInfoReq);
        return userService.getUserInfo(userInfoReq);
    }



    @ApiOperation(value = "获取上课日程")
    @ApiResponses({
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 10005, message = "暂无上课日程"),
    })
    @GetMapping(value = "/getclassschedule")
    public AppletResult<ClassScheduleResPage> getClassSchedule(HttpServletRequest request,
                                                               @Valid @ApiParam(name = "上课日程请求对象",required = true) ClassScheduleReq classScheduleReq){
        classScheduleReq = (ClassScheduleReq) headUtil.getAllReqHead(request,classScheduleReq);
        return userService.getClassSchedule(classScheduleReq);
    }


    @ApiOperation(value = "获取首页文章")
    @ApiResponses({
            @ApiResponse(code = 500, message = "系统内部错误"),
    })
    @GetMapping(value = "/getarticleinfo")
    public AppletResult<ArticleInfoRes> getArticleInfo(HttpServletRequest request,
                                                       @Valid @ApiParam(name = "首页文章请求对象",required = true) ArticleInfoReq articleInfoReq){
        articleInfoReq = (ArticleInfoReq) headUtil.getAllReqHead(request,articleInfoReq);
        return userService.getArticleInfo(articleInfoReq);
    }
}
