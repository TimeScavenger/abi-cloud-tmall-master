package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.Brand;
import com.abi.tmall.product.dao.mapper.BrandMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品品牌 持久业务类
 *
 * @ClassName: BrandDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Repository
public class BrandDao extends ServiceImpl<BrandMapper, Brand> {

    public Page<Brand> queryPageByCondition(Long pageNo, Long pageSize, String brandName) {
        Page<Brand> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(brandName), Brand::getBrandName, brandName)
                .page(new Page<Brand>(pageNo, pageSize));
        return page;
    }

    public List<Brand> queryListByBrandCodes(List<Long> brandCodes) {
        List<Brand> brands = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(brandCodes), Brand::getBrandCode, brandCodes).list();
        return brands;
    }

    public List<Brand> queryListByBrandName(String brandName) {
        List<Brand> brands = this.lambdaQuery()
                .like(StringUtils.isNotBlank(brandName), Brand::getBrandName, brandName)
                .list();
        return brands;
    }

    public Brand queryInfoByBrandCode(Long brandCode) {
        Brand brand = this.lambdaQuery()
                .eq(Brand::getBrandCode, brandCode)
                .one();
        return brand;
    }

    public Brand queryInfoByBrandName(String brandName) {
        Brand brand = this.lambdaQuery()
                .eq(StringUtils.isNotBlank(brandName), Brand::getBrandName, brandName)
                .one();
        return brand;
    }

    public boolean updateShowedByBrandCode(Long brandCode, Integer showed) {
        LambdaUpdateWrapper<Brand> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(Brand::getBrandCode, brandCode)
                .set(Brand::getShowed, showed);
        this.update(null, updateWrapper);
        return true;
    }

    public boolean updateEnabledByBrandCode(Long brandCode, Integer enabled) {
        LambdaUpdateWrapper<Brand> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(Brand::getBrandCode, brandCode)
                .set(Brand::getEnabled, enabled);
        this.update(null, updateWrapper);
        return true;
    }

    public boolean deleteByBrandCodes(List<Long> brandCodes) {
        if (CollectionUtils.isNotEmpty(brandCodes)) {
            LambdaUpdateWrapper<Brand> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper
                    .in(Brand::getBrandCode, brandCodes)
                    .set(Brand::getDeleted, 1);
            this.update(null, updateWrapper);
            return true;
        }
        return false;
    }

}
