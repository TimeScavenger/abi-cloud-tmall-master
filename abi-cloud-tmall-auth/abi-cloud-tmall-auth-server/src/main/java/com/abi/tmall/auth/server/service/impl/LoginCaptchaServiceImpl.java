package com.abi.tmall.auth.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.tmall.auth.dao.entity.LoginCaptcha;
import com.abi.tmall.auth.dao.service.LoginCaptchaDao;
import com.abi.tmall.auth.server.service.LoginCaptchaService;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @ClassName: LoginCaptchaServiceImpl
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录验证码 服务实现类
 */
@Service
public class LoginCaptchaServiceImpl implements LoginCaptchaService {

    @Autowired
    private Producer producer;

    @Autowired
    private LoginCaptchaDao loginCaptchaDao;

    /**
     * 生成图片验证码
     *
     * @param uuid UUID
     * @return 图片验证码
     */
    @Override
    public BufferedImage generateImgCaptcha(String uuid) throws BusinessException {
        // 1、校验uuid是否为空
        if (StringUtils.isBlank(uuid)) {
            throw new BusinessException(ResultCode.SERVER_ERROR.code(), "获取图片验证码UUID不能为空");
        }

        // 2、生成文字验证码
        String code = producer.createText();

        // 3、保存验证码对象
        LoginCaptcha loginCaptcha = new LoginCaptcha();
        loginCaptcha.setUuid(uuid);
        loginCaptcha.setCode(code);

        // 4、5分钟后过期
        loginCaptcha.setExpireTime(LocalDateTime.now().plusMinutes(5));
        loginCaptchaDao.save(loginCaptcha);

        return producer.createImage(code);
    }

    /**
     * 验证图片验证码
     *
     * @param uuid
     * @param code
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean checkImgCaptcha(String uuid, String code) throws BusinessException {
        // 1、校验验证码参数
        if (StringUtils.isBlank(uuid) || StringUtils.isBlank(code)) {
            throw new BusinessException(ResultCode.PARAM_IS_NULL.code(), "校验验证码UUID、验证码不能为空");
        }

        // 2、校验验证码参数是否合法
        LoginCaptcha loginCaptcha = loginCaptchaDao.queryInfoByUuidAndCatcha(uuid, code);

        if (Objects.isNull(loginCaptcha)) {
            return false;
        } else {
            // 3、删除验证码
            loginCaptchaDao.removeById(loginCaptcha);
            // 4、判断验证码过期时间是否在当前时间的后面
            return loginCaptcha.getExpireTime().isAfter(LocalDateTime.now());
        }
    }
}
