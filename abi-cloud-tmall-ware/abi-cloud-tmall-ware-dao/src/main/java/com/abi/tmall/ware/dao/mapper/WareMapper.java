package com.abi.tmall.ware.dao.mapper;

import com.abi.tmall.ware.dao.entity.Ware;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库 持久类
 *
 * @ClassName: WareMapper
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Mapper
public interface WareMapper extends BaseMapper<Ware> {
}