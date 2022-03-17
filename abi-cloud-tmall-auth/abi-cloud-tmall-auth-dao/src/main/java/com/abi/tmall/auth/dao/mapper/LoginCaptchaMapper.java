package com.abi.tmall.auth.dao.mapper;

import com.abi.tmall.auth.dao.entity.LoginCaptcha;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: LoginCaptchaDao
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录验证码 Mapper接口
 */
@Mapper
public interface LoginCaptchaMapper extends BaseMapper<LoginCaptcha> {
}