package com.abi.tmall.auth.dao.service;

import com.abi.tmall.auth.dao.entity.Member;
import com.abi.tmall.auth.dao.mapper.MemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: MemberDao
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 会员信息
 */
@Repository
public class MemberDao extends ServiceImpl<MemberMapper, Member> {

    public Member queryInfoByUsername(String username) {
        Member member = this.lambdaQuery()
                .eq(Member::getUsername, username)
                .one();
        return member;
    }

    public Member queryInfoByPhone(String phone, Integer status) {
        Member member = this.lambdaQuery()
                .eq(Member::getMobile, phone)
                .eq(Member::getStatus, status)
                .one();
        return member;
    }
}
