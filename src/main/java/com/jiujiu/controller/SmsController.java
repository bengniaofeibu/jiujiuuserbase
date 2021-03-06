package com.jiujiu.controller;

import com.jiujiu.base.BaseController;
import com.jiujiu.entity.request.SendSmsReq;
import com.jiujiu.entity.request.UserRegisterLoginReq;
import com.jiujiu.entity.response.UserRegisterLoginRes;
import com.jiujiu.rabbitmq.SendServer;
import com.jiujiu.service.SmsService;
import com.jiujiuwisdom.utils.AppletResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RefreshScope
@RestController
@Api("短信相关")
public class SmsController extends BaseController{

    @Autowired
    private SmsService smsService;

    @Autowired
    private SendServer sendServer;

    @ApiOperation(value = "发送短信验证码")
    @ApiResponses({
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 10003, message = "请输入正确的手机号"),
    })
    @PostMapping(value = "/sendSms")
    public AppletResult userRegisterLogin(HttpServletRequest request,
                                                               @Valid @RequestBody @ApiParam(name = "发送短信请求对象",required = true)SendSmsReq sendSmsReq){
        sendSmsReq = (SendSmsReq) headUtil.getAllReqHead(request,sendSmsReq);
        return smsService.sendSMSVerificationCode(sendSmsReq);

    }
}
