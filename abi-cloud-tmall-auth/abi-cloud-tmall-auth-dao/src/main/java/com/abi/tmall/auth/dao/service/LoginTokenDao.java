package com.abi.tmall.auth.dao.service;

import com.abi.tmall.auth.dao.entity.LoginToken;
import com.abi.tmall.auth.dao.mapper.LoginTokenMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: LoginTokenDao
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录Token Dao接口
 */
@Repository
public class LoginTokenDao extends ServiceImpl<LoginTokenMapper, LoginToken> {

    public LoginToken queryInfoByMemberCode(String memberCode) {
        LoginToken loginToken = this.lambdaQuery()
                .eq(LoginToken::getMemberCode, memberCode)
                .one();
        return loginToken;
    }
}
