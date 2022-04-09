package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.SpuCommentReplayRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Spu评价-回复关系 持久类
 *
 * @ClassName: SpuCommentReplayMapper
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description: Spu商品评价回复关系
 */
@Mapper
public interface SpuCommentReplayMapper extends BaseMapper<SpuCommentReplayRelation> {
}
