package com.abi.tmall.ware.dao.mapper;

import com.abi.tmall.ware.dao.entity.Purchase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurchaseMapper extends BaseMapper<Purchase> {

    Integer updateInfoByCodeSelective(@Param("purchase") Purchase purchase);

}