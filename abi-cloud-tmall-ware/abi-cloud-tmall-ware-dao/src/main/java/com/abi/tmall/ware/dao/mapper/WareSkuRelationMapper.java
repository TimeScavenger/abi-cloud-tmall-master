package com.abi.tmall.ware.dao.mapper;

import com.abi.tmall.ware.dao.entity.WareSkuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存 持久类
 *
 * @ClassName: WareSkuRelationMapper
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Mapper
public interface WareSkuRelationMapper extends BaseMapper<WareSkuRelation> {

    Integer getSkuStock(@Param("skuCode") Long skuCode);

}