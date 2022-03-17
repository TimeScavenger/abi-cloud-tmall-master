package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: SkuMapper
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description: Sku信息
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {
}