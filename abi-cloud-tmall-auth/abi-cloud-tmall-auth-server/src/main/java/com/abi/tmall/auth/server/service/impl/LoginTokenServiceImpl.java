package com.abi.tmall.auth.server.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.abi.base.foundation.code.ResultCode;
import com.abi.tmall.auth.common.constant.SessionConstant;
import com.abi.tmall.auth.common.response.login.LoginResult;
import com.abi.tmall.auth.dao.entity.LoginToken;
import com.abi.tmall.auth.dao.entity.Member;
import com.abi.tmall.auth.dao.service.LoginTokenDao;
import com.abi.tmall.auth.dao.service.MemberDao;
import com.abi.tmall.auth.server.enums.MemberLoginEunm;
import com.abi.tmall.auth.server.service.LoginTokenService;
import com.abi.tmall.auth.server.utils.TokenGeneratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: LoginTokenServiceImpl
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 用户Token 服务实现类
 */
@Service
public class LoginTokenServiceImpl implements LoginTokenService {

    @Autowired
    private LoginTokenDao loginTokenDao;

    @Autowired
    private MemberDao memberDao;

    @Override
    public String createToken(Long memberCode) {
        // 生成一个token
        String token = TokenGeneratorUtils.generateValue();
        // 当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        // 过期时间
        LocalDateTime expireTime = localDateTime.plusHours(12);

        // 判断是否生成过token
        LoginToken loginToken = loginTokenDao.queryInfoByMemberCode(memberCode + "");
        if (Objects.isNull(loginToken)) {
            loginToken = new LoginToken();
            loginToken.setMemberCode(memberCode);
            loginToken.setToken(token);
            loginToken.setUpdateTime(localDateTime);
            loginToken.setExpireTime(expireTime);
            // 保存token
            loginTokenDao.save(loginToken);
        } else {
            loginToken.setToken(token);
            loginToken.setUpdateTime(localDateTime);
            loginToken.setExpireTime(expireTime);
            // 更新token
            loginTokenDao.updateById(loginToken);
        }

        return token;
    }

    @Override
    public LoginResult buildLoginResult(Member member) {
        return buildLoginResult(member, null);
    }

    private LoginResult buildLoginResult(Member member, String device) {
        // 1、判断用户token是否为空
        Assert.notNull(member, ResultCode.PARAM_IS_ERROR.message());

        // 2、登录
        StpUtil.login(member.getMemberCode(), device == null || StringUtils.isBlank(device) ? MemberLoginEunm.PC.name() : device);

        // 3、缓存用户信息
        StpUtil.getSession().set(SessionConstant.SATOKEN_LOGIN_SESSION_MEMBER_INFO, member);

        // 4、缓存角色信息
        List<Object> roleCodes = new ArrayList<>();
        StpUtil.getSession().set(SessionConstant.SATOKEN_LOGIN_SESSION_MEMBER_ROLE_LIST, roleCodes);

        // 5、封装token返给前端
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginResult loginResult = new LoginResult();
        loginResult.setTokenValue(tokenInfo.getTokenValue());
        loginResult.setTokenName(tokenInfo.getTokenName());
        return loginResult;
    }
}
