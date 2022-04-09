package com.abi.tmall.ware.dao.service;

import com.abi.tmall.ware.dao.entity.Purchase;
import com.abi.tmall.ware.dao.entity.Ware;
import com.abi.tmall.ware.dao.mapper.PurchaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购单 持久服务类
 *
 * @ClassName: PurchaseDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Repository
public class PurchaseDao extends ServiceImpl<PurchaseMapper, Purchase> {

    @Autowired
    PurchaseMapper purchaseMapper;

    public Page<Purchase> queryPageByCondition(Long pageNo, Long pageSize, Integer status, String purchaseName) {
        Page<Purchase> page = this.lambdaQuery()
                .eq(status != null, Purchase::getStatus, status)
                .eq(StringUtils.isNotBlank(purchaseName), Purchase::getPurchaseName, purchaseName)
                .page(new Page<Purchase>(pageNo, pageSize));
        return page;
    }

    public List<Purchase> queryListByPurchaseCodes(List<Long> purchaseCodes) {
        List<Purchase> purchases = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(purchaseCodes), Purchase::getPurchaseCode, purchaseCodes)
                .list();
        return purchases;
    }

    public Purchase queryInfoByPurchaseCode(Long purchaseCode) {
        Purchase purchase = this.lambdaQuery()
                .eq(purchaseCode != null, Purchase::getPurchaseCode, purchaseCode)
                .one();
        return purchase;
    }

    public Integer updateInfoByPurchaseCode(Purchase purchase) {
        return purchaseMapper.updateInfoByCodeSelective(purchase);
    }
}
