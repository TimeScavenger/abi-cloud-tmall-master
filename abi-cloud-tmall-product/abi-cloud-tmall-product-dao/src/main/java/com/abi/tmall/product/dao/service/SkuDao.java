package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.Sku;
import com.abi.tmall.product.dao.mapper.SkuMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: SkuDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: Sku信息
 */
@Repository
public class SkuDao extends ServiceImpl<SkuMapper, Sku> {

    public Page<Sku> queryPageByCondition(Long pageNo, Long pageSize, Long categoryCode, Long brandCode, String skuName, String priceMax, String priceMin) {
        Page<Sku> page = this.lambdaQuery()
                .eq(categoryCode != null, Sku::getCategoryCode, categoryCode)
                .eq(brandCode != null, Sku::getBrandCode, brandCode)
                .like(StringUtils.isNotBlank(skuName), Sku::getSkuName, skuName)
                .le(StringUtils.isNotBlank(priceMax) && !"0".equals(priceMax), Sku::getSkuPrice, priceMax)
                .ge(StringUtils.isNotBlank(priceMin) && !"0".equals(priceMin), Sku::getSkuPrice, priceMin)
                .page(new Page<Sku>(pageNo, pageSize));
        return page;
    }

    public List<Sku> queryListBySkuCodes(List<Long> skuCodes) {
        List<Sku> skus = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(skuCodes), Sku::getSkuCode, skuCodes)
                .list();
        return skus;
    }

    public List<Sku> queryListBySpuCode(Long spuCode) {
        List<Sku> skus = this.lambdaQuery()
                .eq(spuCode != null, Sku::getSpuCode, spuCode)
                .list();
        return skus;
    }

    public List<Sku> queryListBySkuName(String skuName) {
        List<Sku> skus = this.lambdaQuery()
                .like(StringUtils.isNotBlank(skuName), Sku::getSkuName, skuName)
                .list();
        return skus;
    }

    public Sku queryItemBySkuCode(Long skuCode) {
        Sku sku = this.lambdaQuery()
                .eq(skuCode != null, Sku::getSkuCode, skuCode)
                .one();
        return sku;
    }

}
