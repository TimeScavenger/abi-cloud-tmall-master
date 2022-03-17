package com.abi.tmall.auth.dao.mapper;

import com.abi.tmall.auth.dao.entity.LoginToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: LoginTokenMapper
 * @Author: illidan
 * @CreateDate: 2022/2/6
 * @Description: 登录Token Mapper接口
 */
@Mapper
public interface LoginTokenMapper extends BaseMapper<LoginToken> {
}