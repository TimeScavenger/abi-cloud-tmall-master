package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.Attribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性 持久类
 *
 * @ClassName: AttributeMapper
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Mapper
public interface AttributeMapper extends BaseMapper<Attribute> {
}