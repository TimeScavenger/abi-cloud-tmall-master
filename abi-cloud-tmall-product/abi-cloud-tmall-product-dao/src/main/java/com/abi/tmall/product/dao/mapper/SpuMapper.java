package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.Spu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Spu 持久类
 *
 * @ClassName: SpuMapper
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description:
 */
@Mapper
public interface SpuMapper extends BaseMapper<Spu> {
    /**
     * 修改上架成功的商品的状态
     */
    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
}