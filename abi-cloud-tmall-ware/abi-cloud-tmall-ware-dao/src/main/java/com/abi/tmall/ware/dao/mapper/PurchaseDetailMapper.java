package com.abi.tmall.ware.dao.mapper;

import com.abi.tmall.ware.dao.entity.PurchaseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseDetailMapper extends BaseMapper<PurchaseDetail> {

    Integer updateBatchByCodesSelective(@Param("list") List<PurchaseDetail> purchaseDetails);

}