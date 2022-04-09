package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.Attribute;
import com.abi.tmall.product.dao.mapper.AttributeMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品属性 持久服务类
 *
 * @ClassName: AttributeDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Repository
public class AttributeDao extends ServiceImpl<AttributeMapper, Attribute> {

    public Page<Attribute> queryPageByCondition(Long pageNo, Long pageSize, String attributeName, Integer type, Long categoryCode) {
        Page<Attribute> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(attributeName), Attribute::getAttributeName, attributeName)
                .eq(type != null, Attribute::getType, type)
                .eq(categoryCode != null && categoryCode != 0, Attribute::getCategoryCode, categoryCode)
                .page(new Page<Attribute>(pageNo, pageSize));
        return page;
    }

    public Page<Attribute> queryPageByCondition(Long pageNo, Long pageSize, Long categoryCode, Integer type, List<Long> attributeCodes, String attributeName) {
        Page<Attribute> page = this.lambdaQuery()
                .eq(categoryCode != null, Attribute::getCategoryCode, categoryCode)
                .eq(type != null, Attribute::getType, type)
                .notIn(CollectionUtils.isNotEmpty(attributeCodes), Attribute::getAttributeCode, attributeCodes)
                .like(StringUtils.isNotBlank(attributeName), Attribute::getAttributeName, attributeName)
                .page(new Page<Attribute>(pageNo, pageSize));
        return page;
    }

    public Page<Attribute> queryPageByAttributeCodes(Long pageNo, Long pageSize, List<Long> attributeCodes) {
        Page<Attribute> page = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(attributeCodes), Attribute::getAttributeCode, attributeCodes)
                .page(new Page<Attribute>(pageNo, pageSize));
        return page;
    }

    public List<Attribute> queryListByAttributeCodes(List<Long> attributeCodes) {
        List<Attribute> attributes = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(attributeCodes), Attribute::getAttributeCode, attributeCodes)
                .list();
        return attributes;
    }

    public List<Attribute> queryListByAttributeCodes(List<Long> attributeCodes, Integer code) {
        List<Attribute> attributes = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(attributeCodes), Attribute::getAttributeCode, attributeCodes)
                .eq(code != null, Attribute::getSearchType, code)
                .list();
        return attributes;
    }

    public Attribute queryInfoByAttributeCode(Long attributeCode) {
        Attribute attribute = this.lambdaQuery()
                .eq(Attribute::getAttributeCode, attributeCode)
                .one();
        return attribute;
    }

    public Attribute queryInfoByCategoryCodeAndAttributeName(Long categoryCode, String attributeName) {
        Attribute attribute = this.lambdaQuery()
                .eq(categoryCode != null, Attribute::getCategoryCode, categoryCode)
                .eq(StringUtils.isNotBlank(attributeName), Attribute::getAttributeName, attributeName)
                .one();
        return attribute;
    }

    public List<Attribute> queryListByType(Long categoryCode, Integer type) {
        List<Attribute> attributes = this.lambdaQuery()
                .eq(categoryCode != null, Attribute::getCategoryCode, categoryCode)
                .eq(type != null, Attribute::getType, type)
                .list();
        return attributes;
    }

    public boolean deleteByAttributeCodes(List<Long> attributeCodes) {
        if (CollectionUtils.isNotEmpty(attributeCodes)) {
            LambdaUpdateWrapper<Attribute> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper
                    .in(Attribute::getAttributeCode, attributeCodes)
                    .set(Attribute::getDeleted, 1);
            this.update(null, updateWrapper);
            return true;
        }
        return false;
    }

}
