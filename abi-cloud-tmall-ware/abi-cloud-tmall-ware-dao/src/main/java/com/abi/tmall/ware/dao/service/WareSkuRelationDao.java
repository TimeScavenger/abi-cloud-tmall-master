package com.abi.tmall.ware.dao.service;

import com.abi.tmall.ware.dao.entity.WareSkuRelation;
import com.abi.tmall.ware.dao.mapper.WareSkuRelationMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: WareSkuRelationDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: 仓库-商品库存
 */
@Repository
public class WareSkuRelationDao extends ServiceImpl<WareSkuRelationMapper, WareSkuRelation> {

    public Page<WareSkuRelation> queryPageByCondition(Long pageNo, Long pageSize, Long wareCode, List<Long> skuCodes) {
        Page<WareSkuRelation> page = this.lambdaQuery()
                .eq(wareCode != null, WareSkuRelation::getWareCode, wareCode)
                .in(CollectionUtils.isNotEmpty(skuCodes), WareSkuRelation::getSkuCode, skuCodes)
                .page(new Page<WareSkuRelation>(pageNo, pageSize));
        return page;
    }

    public List<WareSkuRelation> queryListByWareCodes(List<Long> wareCodes) {
        List<WareSkuRelation> wareSkuRelations = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(wareCodes), WareSkuRelation::getWareCode, wareCodes)
                .list();
        return wareSkuRelations;
    }

}
