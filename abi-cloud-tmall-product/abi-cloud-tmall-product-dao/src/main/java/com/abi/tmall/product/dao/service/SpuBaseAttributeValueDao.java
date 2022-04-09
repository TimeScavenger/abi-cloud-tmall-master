package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.SpuBaseAttributeValue;
import com.abi.tmall.product.dao.mapper.SpuBaseAttributeValueMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spu规格参数-属性值 持久业务类
 *
 * @ClassName: SpuImgDetailDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Repository
public class SpuBaseAttributeValueDao extends ServiceImpl<SpuBaseAttributeValueMapper, SpuBaseAttributeValue> {

    public List<SpuBaseAttributeValue> queryListBySpuCode(Long spuCode) {
        List<SpuBaseAttributeValue> spuBaseAttributeValues = this.lambdaQuery()
                .eq(spuCode != null, SpuBaseAttributeValue::getSpuCode, spuCode)
                .list();
        return spuBaseAttributeValues;
    }
}
