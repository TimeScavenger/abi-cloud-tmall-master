package com.abi.tmall.auth.server.service;

import com.abi.tmall.auth.common.response.login.LoginResult;
import com.abi.tmall.auth.dao.entity.LoginToken;
import com.abi.tmall.auth.dao.entity.Member;

/**
 * @ClassName: LoginTokenService
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 用户Token 服务类
 */
public interface LoginTokenService {

    /**
     * 创建token
     *
     * @param userCode
     * @return
     */
    String createToken(Long userCode);

    /**
     * 构建登录返回结果
     *
     * @param member
     * @return
     */
    LoginResult buildLoginResult(Member member);
}
