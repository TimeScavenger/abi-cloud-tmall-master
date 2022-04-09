package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.Group;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性分组 持久类
 *
 * @ClassName: GroupMapper
 * @Author: illidan
 * @CreateDate: 2021/05/20
 * @Description:
 */
@Mapper
public interface GroupMapper extends BaseMapper<Group> {
}
