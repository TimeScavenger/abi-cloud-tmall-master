package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.Spu;
import com.abi.tmall.product.dao.mapper.SpuMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: SpuDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: Spu信息
 */
@Repository
public class SpuDao extends ServiceImpl<SpuMapper, Spu> {

    public Page<Spu> queryPageByCondition(Long pageNo, Long pageSize, Long categoryCode, Long brandCode, Integer publishStatus, String spuName) {
        // 2、分页查询
        Page<Spu> page = this.lambdaQuery()
                .eq(categoryCode != null, Spu::getCategoryCode, categoryCode)
                .eq(brandCode != null, Spu::getBrandCode, brandCode)
                .eq(publishStatus != null, Spu::getPublishStatus, publishStatus)
                .like(StringUtils.isNotBlank(spuName), Spu::getSpuName, spuName)
                .page(new Page<Spu>(pageNo, pageSize));
        return page;
    }

    public Spu queryInfoBySpuCode(Long spuCode) {
        Spu spu = this.lambdaQuery()
                .eq(spuCode != null, Spu::getSpuCode, spuCode)
                .one();
        return spu;
    }

    public boolean updateSpuPublishStatus(Long spuCode, Integer publishStatus) {
        LambdaUpdateWrapper<Spu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(Spu::getSpuCode, spuCode)
                .set(Spu::getPublishStatus, publishStatus);
        this.update(null, updateWrapper);
        return true;
    }

}
