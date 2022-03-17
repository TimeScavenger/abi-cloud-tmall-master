package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.SkuSaleAttributeValue;
import com.abi.tmall.product.dao.mapper.SkuSaleAttributeValueMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: SkuSaleAttributeValueDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: Sku销售属性属性值
 */
@Repository
public class SkuSaleAttributeValueDao extends ServiceImpl<SkuSaleAttributeValueMapper, SkuSaleAttributeValue> {

    public List<SkuSaleAttributeValue> queryListBySkuCode(Long skuCode) {
        List<SkuSaleAttributeValue> skuSaleAttributeValues = this.lambdaQuery()
                .eq(skuCode != null, SkuSaleAttributeValue::getSkuCode, skuCode)
                .list();
        return skuSaleAttributeValues;
    }
}
