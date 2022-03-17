package com.abi.tmall.auth.server.service;

import com.abi.base.foundation.exception.BusinessException;

import java.awt.image.BufferedImage;

/**
 * @ClassName: LoginCaptchaService
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录验证码 服务类
 */
public interface LoginCaptchaService {

    /**
     * 生成图片验证码
     *
     * @param uuid
     * @return 图片验证码
     * @throws BusinessException
     */
    BufferedImage generateImgCaptcha(String uuid) throws BusinessException;

    /**
     * 验证图片验证码
     *
     * @param uuid
     * @param code
     * @return
     * @throws BusinessException
     */
    boolean checkImgCaptcha(String uuid, String code) throws BusinessException;
}
