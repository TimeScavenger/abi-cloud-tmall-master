package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.tmall.product.common.request.category.CbRelationAddReq;
import com.abi.tmall.product.common.request.category.CbRelationDelReq;
import com.abi.tmall.product.common.request.category.CbRelationListByBrandReq;
import com.abi.tmall.product.common.request.category.CbRelationListByCategoryReq;
import com.abi.tmall.product.common.response.category.CbRelationListByBrandResp;
import com.abi.tmall.product.common.response.category.CbRelationListByCategoryResp;
import com.abi.tmall.product.dao.entity.Brand;
import com.abi.tmall.product.dao.entity.Category;
import com.abi.tmall.product.dao.entity.CategoryBrandRelation;
import com.abi.tmall.product.dao.mapper.CategoryBrandRelationMapper;
import com.abi.tmall.product.dao.service.BrandDao;
import com.abi.tmall.product.dao.service.CategoryBrandRelationDao;
import com.abi.tmall.product.dao.service.CategoryDao;
import com.abi.tmall.product.server.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.abi.infrastructure.core.constant.CommonConstants.LOG_PRE;

/**
 * 商品分类-品牌关系 服务实现类
 *
 * @ClassName: CategoryBrandRelationServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/5/19
 * @Description:
 */
@Slf4j
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> implements CategoryBrandRelationService {

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 查询 品牌关联的所有分类列表
     *
     * @param req 品牌Code
     * @return 分类列表
     */
    @Override
    public List<CbRelationListByCategoryResp> queryCategoryListByBrandCode(CbRelationListByBrandReq req) {
        // 1、查询品牌关联的分类列表
        List<CategoryBrandRelation> categoryBrandRelations = categoryBrandRelationDao.queryCategoryListByBrandCode(req.getBrandCode());
        log.info(LOG_PRE + "查询品牌关联的分类列表，品牌ID: 【{}】，分类关联的品牌列表：【{}】", req.getBrandCode(), categoryBrandRelations);
        // 2、判断查询出来的数据
        if (CollectionUtils.isEmpty(categoryBrandRelations)) {
            return Lists.newArrayList();
        }
        // 3、数据进行转换、组装返回数据
        List<CbRelationListByCategoryResp> cbRelationListByCategoryResps = categoryBrandRelations.stream()
                .map(categoryBrandRelation -> {
                    CbRelationListByCategoryResp bcRelationBrandListVo = new CbRelationListByCategoryResp();
                    BeanUtils.copyProperties(categoryBrandRelation, bcRelationBrandListVo);
                    return bcRelationBrandListVo;
                })
                .collect(Collectors.toList());
        // 4、返回数据
        return cbRelationListByCategoryResps;
    }

    /**
     * 查询 分类关联的所有品牌列表
     *
     * @param req 分类Code
     * @return 品牌列表
     */
    @Override
    public List<CbRelationListByBrandResp> queryBrandListByCategoryCode(CbRelationListByCategoryReq req) {
        // 1、查询分类关联的品牌列表
        List<CategoryBrandRelation> categoryBrandRelations = categoryBrandRelationDao.queryBrandListByCategoryCode(req.getCategoryCode());
        log.info(LOG_PRE + "查询分类关联的品牌列表，分类ID: 【{}】，分类关联的品牌列表：【{}】", req.getCategoryCode(), categoryBrandRelations);
        // 2、判断查询出来的数据
        if (CollectionUtils.isEmpty(categoryBrandRelations)) {
            return Lists.newArrayList();
        }
        // 3、组装需要返回的数据
        List<CbRelationListByBrandResp> bcRelationCategoryListVos = categoryBrandRelations.stream()
                .map(categoryBrandRelation -> {
                    CbRelationListByBrandResp cbRelationListByBrandResp = new CbRelationListByBrandResp();
                    BeanUtils.copyProperties(categoryBrandRelation, cbRelationListByBrandResp);
                    return cbRelationListByBrandResp;
                })
                .collect(Collectors.toList());
        // 4、返回数据
        return bcRelationCategoryListVos;
    }

    /**
     * 添加 品牌和分类关系的对象
     *
     * @param req 品牌和分类关系
     * @return 添加是否成功: true-成功, false-失败
     */
    @Override
    public boolean saveBrandCategoryRelation(CbRelationAddReq req) {
        // 1、获取到品牌的名字和分类的名字
        Long brandCode = req.getBrandCode();
        Long categoryCode = req.getCategoryCode();
        // 2、查询品牌和分类信息，校验数据是否合法
        Brand brand = brandDao.queryInfoByBrandCode(brandCode);
        Category category = categoryDao.queryInfoByCategoryCode(categoryCode);
        if (brand == null || category == null) {
            throw new BusinessException(ResultCode.DATA_NOT_EXISTED.code(), ResultCode.DATA_NOT_EXISTED.message());
        }
        // 3、校验品牌和分类关系数据库中是否已经存在
        CategoryBrandRelation relationResult = categoryBrandRelationDao.queryListByBrandCodeAndCategoryCode(brandCode, categoryCode);
        if (relationResult != null) {
            throw new BusinessException(ResultCode.DATA_IS_EXISTED.code(), ResultCode.DATA_IS_EXISTED.message());
        }
        // 4、封装分组名称和分类名称，保存数据
        CategoryBrandRelation categoryBrandRelation = new CategoryBrandRelation();
        BeanUtils.copyProperties(req, categoryBrandRelation);
        categoryBrandRelation.setBrandName(brand.getBrandName());
        categoryBrandRelation.setCategoryName(category.getCategoryName());
        return categoryBrandRelationDao.save(categoryBrandRelation);
    }

    /**
     * 删除 品牌和分类关系的对象
     *
     * @param req 品牌和分类关系
     * @return 删除是否成功: true-成功, false-失败
     */
    @Override
    public boolean removeBrandCategoryRelation(CbRelationDelReq req) {
        return categoryBrandRelationDao.removeByIds(req.getIds());
    }

}
