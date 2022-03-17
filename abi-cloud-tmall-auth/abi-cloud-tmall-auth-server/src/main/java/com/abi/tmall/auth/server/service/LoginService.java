package com.abi.tmall.auth.server.service;

import com.abi.base.foundation.exception.BusinessException;
import com.abi.tmall.auth.common.request.login.*;
import com.abi.tmall.auth.common.response.login.LoginResult;

/**
 * @ClassName: LoginService
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录 服务类
 */
public interface LoginService {

    /**
     * 账号密码验证码登录系统
     *
     * @param loginByPwdWithCapReq
     * @return
     * @throws BusinessException
     */
    String loginByPwdWithCap(LoginByPwdWithCapReq loginByPwdWithCapReq) throws BusinessException;

    /**
     * 账号密码登录系统
     *
     * @param loginByPwdReq
     * @return
     */
    LoginResult loginByPwd(LoginByPwdReq loginByPwdReq);

    /**
     * 手机号验证码登录
     *
     * @param loginBySmsReq
     * @return
     */
    LoginResult loginBySms(LoginBySmsReq loginBySmsReq);

    /**
     * 微信登录
     *
     * @param loginByWechatReq
     * @return
     */
    LoginResult loginByWechat(LoginByWechatReq loginByWechatReq);

    /**
     * 钉钉登录
     *
     * @param loginByDingtalkReq
     * @return
     */
    LoginResult loginByDingtalk(LoginByDingtalkReq loginByDingtalkReq);
}
