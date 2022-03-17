package com.abi.tmall.auth.dao.service;

import com.abi.tmall.auth.dao.entity.LoginCaptcha;
import com.abi.tmall.auth.dao.mapper.LoginCaptchaMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: LoginCaptchaDao
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录验证码 Dao接口
 */
@Repository
public class LoginCaptchaDao extends ServiceImpl<LoginCaptchaMapper, LoginCaptcha> {

    public LoginCaptcha queryInfoByUuidAndCatcha(String uuid, String code) {
        LoginCaptcha loginCaptcha = this.lambdaQuery()
                .eq(StringUtils.isNotBlank(uuid), LoginCaptcha::getUuid, uuid)
                .eq(StringUtils.isNotBlank(code), LoginCaptcha::getCode, code)
                .one();
        return loginCaptcha;
    }
}
