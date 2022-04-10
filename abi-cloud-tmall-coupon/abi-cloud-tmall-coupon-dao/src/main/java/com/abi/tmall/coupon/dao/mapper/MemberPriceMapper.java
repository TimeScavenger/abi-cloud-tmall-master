package com.abi.tmall.coupon.dao.mapper;

import com.abi.tmall.coupon.dao.entity.MemberPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格 持久类
 *
 * @ClassName: MemberPriceMapper
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description:
 */
@Mapper
public interface MemberPriceMapper extends BaseMapper<MemberPrice> {
}