package com.abi.tmall.auth.dao.mapper;

import com.abi.tmall.auth.dao.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: MemberMapper
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 会员信息
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}