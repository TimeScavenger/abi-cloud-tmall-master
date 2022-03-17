package com.abi.tmall.ware.dao.service;

import com.abi.tmall.ware.dao.entity.PurchaseDetail;
import com.abi.tmall.ware.dao.mapper.PurchaseDetailMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PurchaseDetailDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: 采购项
 */
@Repository
public class PurchaseDetailDao extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail> {

    @Autowired
    PurchaseDetailMapper purchaseDetailMapper;

    public Page<PurchaseDetail> queryPageByCondition(Long pageNo, Long pageSize, Long wareCode, Integer status, List<Long> skuCodes) {
        Page<PurchaseDetail> page = this.lambdaQuery()
                .eq(wareCode != null, PurchaseDetail::getWareCode, wareCode)
                .eq(status != null, PurchaseDetail::getStatus, status)
                .in(CollectionUtils.isNotEmpty(skuCodes), PurchaseDetail::getSkuCode, skuCodes)
                .page(new Page<PurchaseDetail>(pageNo, pageSize));
        return page;
    }

    public List<PurchaseDetail> queryListByPurchaseDetailCodes(List<Long> purchaseDetailCodes) {
        List<PurchaseDetail> purchaseDetails = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(purchaseDetailCodes), PurchaseDetail::getPurchaseDetailCode, purchaseDetailCodes)
                .list();
        return purchaseDetails;
    }

    public List<PurchaseDetail> queryListByPurchaseCodes(List<Long> purchaseCodes) {
        List<PurchaseDetail> purchaseDetails = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(purchaseCodes), PurchaseDetail::getPurchaseCode, purchaseCodes)
                .list();
        return purchaseDetails;
    }

    public PurchaseDetail queryInfoByPurchaseDetailCode(Long purchaseDetailCode) {
        PurchaseDetail purchaseDetail = this.lambdaQuery()
                .eq(PurchaseDetail::getPurchaseDetailCode, purchaseDetailCode)
                .one();
        return purchaseDetail;
    }

    public Integer updateBatchByPurchaseDetailCodes(List<PurchaseDetail> purchaseDetails) {
        return purchaseDetailMapper.updateBatchByCodesSelective(purchaseDetails);
    }

    public boolean removeBatchByPurchaseDetailCodes(List<Long> purchaseDetailCodes) {
        if (CollectionUtils.isNotEmpty(purchaseDetailCodes)) {
            LambdaUpdateWrapper<PurchaseDetail> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper
                    .in(PurchaseDetail::getPurchaseDetailCode, purchaseDetailCodes)
                    .set(PurchaseDetail::getDeleted, 1);
            this.update(null, updateWrapper);
            return true;
        }
        return false;
    }

}
