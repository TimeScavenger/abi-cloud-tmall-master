package com.abi.tmall.auth.server.controller;

import com.abi.base.foundation.exception.BusinessException;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.auth.common.request.login.*;
import com.abi.tmall.auth.common.response.login.LoginResult;
import com.abi.tmall.auth.server.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @ClassName: LoginController
 * @Author: illidan
 * @CreateDate: 2022/02/26
 * @Description: 登录 控制器
 */
@Api(tags = "登录模块")
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 账号密码验证码登录系统
     *
     * @param loginByPwdWithCapReq
     * @return
     * @throws BusinessException
     */
    @PostMapping("/login-by-pwd-cap")
    @ApiOperation(value = "账号密码验证码登录系统")
    public ApiResponse<String> loginByPwdWithCap(@RequestBody LoginByPwdWithCapReq loginByPwdWithCapReq) throws BusinessException {
        return ApiResponse.result(loginService.loginByPwdWithCap(loginByPwdWithCapReq));
    }

    /**
     * 账号密码登录系统
     *
     * @param loginByPwdReq
     * @return
     * @throws BusinessException
     */
    @PostMapping("/login-by-pwd")
    @ApiOperation(value = "账号密码登录系统")
    public ApiResponse<LoginResult> loginByPwd(@RequestBody LoginByPwdReq loginByPwdReq) throws BusinessException {
        return ApiResponse.result(loginService.loginByPwd(loginByPwdReq));
    }

    /**
     * 手机号验证码登录
     *
     * @param loginBySmsReq
     * @return
     * @throws BusinessException
     */
    @PostMapping("/login-by-sms")
    @ApiOperation(value = "手机号验证码登录")
    public ApiResponse<LoginResult> loginBySms(@RequestBody LoginBySmsReq loginBySmsReq) throws BusinessException {
        return ApiResponse.result(loginService.loginBySms(loginBySmsReq));
    }

    /**
     * 微信登录
     *
     * @param loginByWechatReq
     * @return
     */
    @PostMapping("/wechat")
    @ApiOperation(value = "微信登录")
    public ApiResponse<LoginResult> loginByWechat(@RequestBody @Valid LoginByWechatReq loginByWechatReq) {
        return ApiResponse.result(loginService.loginByWechat(loginByWechatReq));
    }

    /**
     * 钉钉登录
     *
     * @param loginByDingtalkReq
     * @return
     */
    @PostMapping("/dingtalk")
    @ApiOperation(value = "钉钉登录")
    public ApiResponse<LoginResult> loginByDingtalk(@RequestBody @Valid LoginByDingtalkReq loginByDingtalkReq) {
        return ApiResponse.result(loginService.loginByDingtalk(loginByDingtalkReq));
    }
}
