package com.abi.tmall.coupon.dao.mapper;

import com.abi.tmall.coupon.dao.entity.SkuFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品满减信息 持久类
 *
 * @ClassName: SkuFullReductionMapper
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 商品满减信息
 */
@Mapper
public interface SkuFullReductionMapper extends BaseMapper<SkuFullReduction> {
}