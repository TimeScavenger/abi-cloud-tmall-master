package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.GroupAttributeRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性分组-属性关系 持久类
 *
 * @ClassName: GroupAttributeRelationMapper
 * @Author: illidan
 * @CreateDate: 2021/05/20
 * @Description:
 */
@Mapper
public interface GroupAttributeRelationMapper extends BaseMapper<GroupAttributeRelation> {

    /**
     * 删除 分组和属性关系的对象
     *
     * @param entities
     */
    void deleteBatchRelation(@Param("entities") List<GroupAttributeRelation> entities);

}
