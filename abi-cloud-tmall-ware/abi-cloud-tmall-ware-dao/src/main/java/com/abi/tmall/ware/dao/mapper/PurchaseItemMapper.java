package com.abi.tmall.ware.dao.mapper;

import com.abi.tmall.ware.dao.entity.PurchaseItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购项 持久类
 *
 * @ClassName: PurchaseDetailMapper
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Mapper
public interface PurchaseItemMapper extends BaseMapper<PurchaseItem> {

    Integer updateBatchByCodesSelective(@Param("purchaseItems") List<PurchaseItem> purchaseItems);

}