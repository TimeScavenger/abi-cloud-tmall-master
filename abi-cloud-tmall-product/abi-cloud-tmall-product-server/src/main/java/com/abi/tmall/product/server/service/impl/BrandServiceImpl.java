package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.web.util.GenerateCodeUtils;
import com.abi.tmall.product.common.request.brand.*;
import com.abi.tmall.product.common.response.brand.BrandInfoResp;
import com.abi.tmall.product.common.response.brand.BrandListResp;
import com.abi.tmall.product.common.response.brand.BrandPageResp;
import com.abi.tmall.product.dao.entity.Brand;
import com.abi.tmall.product.dao.mapper.BrandMapper;
import com.abi.tmall.product.dao.service.BrandDao;
import com.abi.tmall.product.dao.service.CategoryBrandRelationDao;
import com.abi.tmall.product.server.enums.ProductInitCodeEnum;
import com.abi.tmall.product.server.service.BrandService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品品牌 服务实现类
 *
 * @ClassName: BrandServiceImpl
 * @Author: illCodean
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Slf4j
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    /**
     * 查询 品牌分页列表
     *
     * @param req 查询条件
     * @return 品牌分页列表
     */
    @Override
    public PageResponse<BrandPageResp> queryBrandPageByCondition(BrandPageReq req) {
        // 1、新建分页返回对象
        PageResponse<BrandPageResp> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        req.checkParam();
        // 3、分页查询
        Page<Brand> page = brandDao.queryPageByCondition(req.getPageNo(), req.getPageSize(), req.getBrandName());
        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<BrandPageResp> pageVoList = page.getRecords().stream()
                    .map(brand -> {
                        BrandPageResp brandPageResp = new BrandPageResp();
                        BeanUtils.copyProperties(brand, brandPageResp);
                        return brandPageResp;
                    })
                    .collect(Collectors.toList());
            // 组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(pageVoList);
        }
        // 5、返回数据
        return pageResponse;
    }

    /**
     * 查询 品牌列表
     *
     * @param req 查询条件
     * @return 品牌列表
     */
    @Override
    public List<BrandListResp> queryBrandListByCondition(BrandListReq req) {
        // 1、查询数据
        List<Brand> brands = brandDao.queryListByBrandName(req.getBrandName());
        // 2、组装数据
        List<BrandListResp> brandListResps = brands.stream()
                .map(brand -> {
                    BrandListResp brandListResp = new BrandListResp();
                    BeanUtils.copyProperties(brand, brandListResp);
                    return brandListResp;
                })
                .collect(Collectors.toList());
        // 3、返回数据
        return brandListResps;
    }

    /**
     * 查询 根据品牌Codes查询品牌列表
     *
     * @param brandCodes 品牌Codes
     * @return 品牌列表
     */
    @Override
    public List<BrandListResp> queryBrandListByCodes(List<Long> brandCodes) {
        // 1、判断数据是否为空
        if (CollectionUtils.isEmpty(brandCodes)) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), ResultCode.PARAM_IS_ERROR.message());
        }
        // 2、查询数据
        List<Brand> brands = brandDao.queryListByBrandCodes(brandCodes);
        // 3、组装数据
        List<BrandListResp> brandListResps = brands.stream()
                .map(brand -> {
                    BrandListResp brandListResp = new BrandListResp();
                    BeanUtils.copyProperties(brand, brandListResp);
                    return brandListResp;
                })
                .collect(Collectors.toList());
        // 4、返回数据
        return brandListResps;
    }

    /**
     * 查询 根据品牌Codes查询品牌名称列表
     *
     * @param brandCodes 品牌Codes
     * @return 品牌列表
     */
    @Override
    public List<String> queryBrandNameListByCodes(List<Long> brandCodes) {
        // 1、判断数据是否为空
        if (CollectionUtils.isEmpty(brandCodes)) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), ResultCode.PARAM_IS_ERROR.message());
        }
        // 2、查询数据
        List<Brand> brands = brandDao.queryListByBrandCodes(brandCodes);
        // 3、组装数据
        List<String> brandNames = brands.stream()
                .map(Brand::getBrandName)
                .collect(Collectors.toList());
        // 4、返回数据
        return brandNames;
    }

    /**
     * 添加 品牌信息
     *
     * @param req 品牌信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @Override
    public boolean saveBrand(BrandAddReq req) {
        // 1、判断是否为重复添加
        Brand result = brandDao.queryInfoByBrandName(req.getBrandName());
        // 2、判断是否为重复添加数据
        if (result != null) {
            throw new BusinessException(ResultCode.DATA_IS_EXISTED.code(), ResultCode.DATA_IS_EXISTED.message());
        }
        // 3、新建品牌对象
        Brand brand = new Brand();
        BeanUtils.copyProperties(req, brand);
        brand.setBrandCode(GenerateCodeUtils.getCode(ProductInitCodeEnum.PMS_BRAND_INIT_CODE.getDesc()));
        return brandDao.save(brand);
    }


    /**
     * 删除 根据品牌Codes删除品牌信息
     *
     * @param req 品牌Codes
     * @return 删除是否成功: true-成功, false-失败
     */
    @Override
    public boolean removeBrand(BrandDelReq req) {
        // 1、TODO 拓展：检查当前删除的品牌, 是否被别的地方引用，例如品牌和分组的关联关系
        // 2、逻辑删除
        return brandDao.deleteByBrandCodes(req.getBrandCodes());
    }

    /**
     * 修改 品牌信息
     *
     * @param req 品牌信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @Override
    @Transactional
    public boolean modifyBrand(BrandEditReq req) {
        // 1、查询校验分类是否合法
        Brand brandOld = brandDao.queryInfoByBrandCode(req.getBrandCode());
        if (brandOld != null) {
            Brand brandNew = new Brand();
            BeanUtils.copyProperties(req, brandNew);
            brandNew.setId(brandOld.getId());
            // 2、更新品牌对象数据
            brandDao.updateById(brandNew);
            // 3、同步更新数据表brand_category_relation, 数据表冗余存储了名称字段
            categoryBrandRelationDao.updateBrandNameByBrandCode(brandNew.getBrandCode(), brandNew.getBrandName());
            // 4、返回数据
            return true;
        }
        return false;
    }

    /**
     * 修改 根据品牌Code和状态修改品牌显示状态
     *
     * @param req 品牌Code+品牌状态
     * @return 修改是否成功: true-成功, false-失败
     */
    @Override
    public boolean modifyBrandShowed(BrandShowedReq req) {
        return brandDao.updateShowedByBrandCode(req.getBrandCode(), req.getShowed());
    }

    /**
     * 修改 根据品牌Code和状态修改品牌可用状态
     *
     * @param brandCode 品牌Code
     * @param enabled   品牌状态
     * @return 修改是否成功: true-成功, false-失败
     */
    @Override
    public boolean modifyBrandEnabled(Long brandCode, Integer enabled) {
        // 1、更新数据
        return brandDao.updateEnabledByBrandCode(brandCode, enabled);
    }

    /**
     * 查询 根据品牌Code查询品牌信息
     *
     * @param brandCode 品牌Code
     * @return 品牌信息
     */
    @Override
    public BrandInfoResp findBrandByCode(Long brandCode) {
        // 1、判断数据是否为空
        if (brandCode == null) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), ResultCode.PARAM_IS_ERROR.message());
        }
        // 2、查询数据
        Brand brand = brandDao.queryInfoByBrandCode(brandCode);
        // 3、返回数据
        if (brand != null) {
            BrandInfoResp brandInfoResp = new BrandInfoResp();
            BeanUtils.copyProperties(brand, brandInfoResp);
            return brandInfoResp;
        } else {
            throw new BusinessException(ResultCode.DATA_NOT_EXISTED.code(), ResultCode.DATA_NOT_EXISTED.message());
        }
    }
}
