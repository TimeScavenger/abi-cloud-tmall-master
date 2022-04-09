package com.abi.tmall.ware.dao.service;

import com.abi.tmall.ware.dao.entity.PurchaseItem;
import com.abi.tmall.ware.dao.mapper.PurchaseItemMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购项 持久服务类
 *
 * @ClassName: PurchaseDetailDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Repository
public class PurchaseItemDao extends ServiceImpl<PurchaseItemMapper, PurchaseItem> {

    @Autowired
    PurchaseItemMapper purchaseItemMapper;

    public Page<PurchaseItem> queryPageByCondition(Long pageNo, Long pageSize, Long wareCode, Integer status, List<Long> skuCodes) {
        Page<PurchaseItem> page = this.lambdaQuery()
                .eq(wareCode != null, PurchaseItem::getWareCode, wareCode)
                .eq(status != null, PurchaseItem::getStatus, status)
                .in(CollectionUtils.isNotEmpty(skuCodes), PurchaseItem::getSkuCode, skuCodes)
                .page(new Page<PurchaseItem>(pageNo, pageSize));
        return page;
    }

    public List<PurchaseItem> queryListByPurchaseDetailCodes(List<Long> purchaseDetailCodes) {
        List<PurchaseItem> purchaseItems = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(purchaseDetailCodes), PurchaseItem::getPurchaseDetailCode, purchaseDetailCodes)
                .list();
        return purchaseItems;
    }

    public List<PurchaseItem> queryListByPurchaseCodes(List<Long> purchaseCodes) {
        List<PurchaseItem> purchaseItems = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(purchaseCodes), PurchaseItem::getPurchaseCode, purchaseCodes)
                .list();
        return purchaseItems;
    }

    public PurchaseItem queryInfoByPurchaseDetailCode(Long purchaseDetailCode) {
        PurchaseItem purchaseItem = this.lambdaQuery()
                .eq(PurchaseItem::getPurchaseDetailCode, purchaseDetailCode)
                .one();
        return purchaseItem;
    }

    public Integer updateBatchByPurchaseDetailCodes(List<PurchaseItem> purchaseItems) {
        return purchaseItemMapper.updateBatchByCodesSelective(purchaseItems);
    }

    public boolean removeBatchByPurchaseDetailCodes(List<Long> purchaseDetailCodes) {
        if (CollectionUtils.isNotEmpty(purchaseDetailCodes)) {
            LambdaUpdateWrapper<PurchaseItem> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper
                    .in(PurchaseItem::getPurchaseDetailCode, purchaseDetailCodes)
                    .set(PurchaseItem::getDeleted, 1);
            this.update(null, updateWrapper);
            return true;
        }
        return false;
    }

}
