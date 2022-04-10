package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.web.util.GenerateCodeUtils;
import com.abi.tmall.product.common.request.attribute.*;
import com.abi.tmall.product.common.response.attribute.AttributeInfoResp;
import com.abi.tmall.product.common.response.attribute.AttributePageResp;
import com.abi.tmall.product.common.response.attribute.BaseAttributeListResp;
import com.abi.tmall.product.common.response.attribute.SaleAttributeListResp;
import com.abi.tmall.product.dao.entity.Attribute;
import com.abi.tmall.product.dao.entity.Category;
import com.abi.tmall.product.dao.entity.Group;
import com.abi.tmall.product.dao.entity.GroupAttributeRelation;
import com.abi.tmall.product.dao.mapper.AttributeMapper;
import com.abi.tmall.product.dao.service.AttributeDao;
import com.abi.tmall.product.dao.service.CategoryDao;
import com.abi.tmall.product.dao.service.GroupAttributeRelationDao;
import com.abi.tmall.product.dao.service.GroupDao;
import com.abi.tmall.product.server.enums.AttributeTypeEnum;
import com.abi.tmall.product.server.enums.ProductInitCodeEnum;
import com.abi.tmall.product.server.service.AttributeService;
import com.abi.tmall.product.server.service.CategoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品属性 服务实现类
 *
 * @ClassName: AttributeServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description:
 */
@Slf4j
@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements AttributeService {

    @Autowired
    private AttributeDao attributeDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private GroupAttributeRelationDao groupAttributeRelationDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询 属性分页列表
     *
     * @param req 查询条件
     * @return 属性分页列表
     */
    @Override
    public PageResponse<AttributePageResp> queryAttributePageByCondition(AttributePageReq req) {
        // 1、新建分页返回对象
        PageResponse<AttributePageResp> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        req.checkParam();
        // 3、分页查询数据
        Page<Attribute> page = attributeDao.queryPageByCondition(req.getPageNo(), req.getPageSize(), req.getAttributeName(), req.getType(), req.getCategoryCode());
        // 4、查询出所有的分类几黑和分组集合
        Map<Long, String> categoryCodeAndcategoryNameMap = categoryDao.list().stream()
                .collect(Collectors.toMap(Category::getCategoryCode, Category::getCategoryName));
        Map<Long, String> groupAttrribueCodeAndGroupNameMap = groupAttributeRelationDao.list().stream()
                .collect(Collectors.toMap(GroupAttributeRelation::getAttributeCode, GroupAttributeRelation::getGroupName));
        // 5、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<AttributePageResp> pageVoList = page.getRecords().stream()
                    .map(attribute -> {
                        // 5.1新建一个VO返回对象
                        AttributePageResp attributePageResp = new AttributePageResp();
                        BeanUtils.copyProperties(attribute, attributePageResp);
                        // 5.2、设置分类的名字
                        attributePageResp.setCategoryName(categoryCodeAndcategoryNameMap.get(attribute.getCategoryCode()));
                        // 5.3、设置分组的名字
                        if (AttributeTypeEnum.ATTRIBUTE_TYPE_BASE.getCode().equals(req.getType())) {
                            attributePageResp.setGroupName(groupAttrribueCodeAndGroupNameMap.get(attribute.getAttributeCode()));
                        }
                        return attributePageResp;
                    })
                    .collect(Collectors.toList());
            // 组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(pageVoList);
        }
        // 6、返回数据
        return pageResponse;
    }

    /**
     * 查询 根据分类Code查询基本属性的信息
     *
     * @param req 属性类型+分类Code
     * @return 属性列表
     */
    @Override
    public List<BaseAttributeListResp> queryBaseAttributeListByCategoryCode(AttributeListReq req) {
        // 1、查询出分类关联的分组列表
        List<Group> groupList = groupDao.queryListByCategoryCode(req.getCategoryCode());
        if (CollectionUtils.isEmpty(groupList)) {
            throw new BusinessException(ResultCode.FAIL.code(), "根据分类ID无法查询到相关联的分组信息");
        }
        // 过滤出所有的分类关联的分组Code
        List<Long> groupCodes = groupList.stream()
                .map(Group::getGroupCode)
                .collect(Collectors.toList());

        // 2、查询出分组关联的属性列表
        List<GroupAttributeRelation> groupAttributeRelations = groupAttributeRelationDao.queryListByGroupCodes(groupCodes);
        // 转换成Map结构
        Map<Long, List<GroupAttributeRelation>> groupAttributeMap = groupAttributeRelations.stream()
                .collect(Collectors.groupingBy(GroupAttributeRelation::getGroupCode));
        // 过滤出所有的属性ID
        List<Long> attributeCodes = groupAttributeRelations.stream()
                .map(GroupAttributeRelation::getAttributeCode)
                .distinct()
                .collect(Collectors.toList());
        List<Attribute> attributes = attributeDao.queryListByAttributeCodes(attributeCodes);
        Map<Long, Attribute> codeEntiryMap = attributes.stream()
                .collect(Collectors.toMap(Attribute::getAttributeCode, attribute -> attribute));

        // 3、查出每个属性分组的所有属性
        List<BaseAttributeListResp> baseAttributeListResps = new ArrayList<>();

        for (Group group : groupList) {
            BaseAttributeListResp relationVo = new BaseAttributeListResp();
            BeanUtils.copyProperties(group, relationVo);
            List<GroupAttributeRelation> attributeRelations = groupAttributeMap.get(group.getGroupCode());

            List<BaseAttributeListResp.AttributeInfo> attributeInfos = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(attributeRelations)) {
                for (GroupAttributeRelation attributeRelation : attributeRelations) {
                    BaseAttributeListResp.AttributeInfo attributeInfo = new BaseAttributeListResp.AttributeInfo();
                    Attribute attribute = codeEntiryMap.get(attributeRelation.getAttributeCode());
                    BeanUtils.copyProperties(attribute, attributeInfo);
                    attributeInfos.add(attributeInfo);
                }
                relationVo.setAttributeList(attributeInfos);
                baseAttributeListResps.add(relationVo);
            }
        }

        // 4、返回数据
        return baseAttributeListResps;
    }

    /**
     * 查询 根据分类Code查询销售属性的信息
     *
     * @param req 属性类型+分类Code
     * @return 属性列表
     */
    @Override
    public List<SaleAttributeListResp> querySaleAttributeListByCategoryCode(AttributeListReq req) {
        // 查询出分类关联的所有销售属性
        List<Attribute> attributes = attributeDao.queryListByType(req.getCategoryCode(), req.getType());

        // 封装返回对象给前端
        List<SaleAttributeListResp> saleAttributeListResps = attributes.stream()
                .map(item -> {
                    SaleAttributeListResp saleAttributeListResp = new SaleAttributeListResp();
                    BeanUtils.copyProperties(item, saleAttributeListResp);
                    return saleAttributeListResp;
                })
                .collect(Collectors.toList());

        return saleAttributeListResps;
    }

    /**
     * 添加 属性信息
     *
     * @param req 属性信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @Override
    @Transactional
    public boolean saveAttribute(AttributeAddReq req) {
        // 1、判断是否为重复添加
        Attribute result = attributeDao.queryInfoByCategoryCodeAndAttributeName(req.getCategoryCode(), req.getAttributeName());
        // 2、判断是否为重复添加数据
        if (result != null) {
            throw new BusinessException(ResultCode.DATA_IS_EXISTED.code(), ResultCode.DATA_IS_EXISTED.message());
        }
        // 3、新建属性对象
        Attribute attribute = new Attribute();
        BeanUtils.copyProperties(req, attribute);
        attribute.setAttributeCode(GenerateCodeUtils.getCode(ProductInitCodeEnum.PMS_ATTRIBUTE_INIT_CODE.getDesc()));
        // 4、保存属性对象
        attributeDao.save(attribute);
        // 5、保存关联关系 判断是 规格参数 还是 销售属性
        if (AttributeTypeEnum.ATTRIBUTE_TYPE_BASE.getCode().equals(attribute.getType()) && req.getGroupCode() != null) {
            GroupAttributeRelation relation = new GroupAttributeRelation();
            relation.setAttributeCode(attribute.getAttributeCode());
            relation.setAttributeName(attribute.getAttributeName());
            Group group = groupDao.queryInfoByGroupCode(req.getGroupCode());
            relation.setGroupCode(group.getGroupCode());
            relation.setGroupName(group.getGroupName());
            relation.setSort(0);
            groupAttributeRelationDao.save(relation);
        }
        // 6、返回数据
        return true;
    }

    /**
     * 删除 根据Codes删除属性信息
     *
     * @param req 属性Code集合
     * @return 删除是否成功: true-成功, false-失败
     */
    @Override
    public boolean removeAttribute(AttributeDelReq req) {
        // 1、TODO 拓展：检查当前删除的属性, 是否被别的地方引用，例如分组和属性的关联关系
        // 2、逻辑删除
        return attributeDao.deleteByAttributeCodes(req.getAttributeCodes());
    }

    /**
     * 修改 属性信息
     *
     * @param req 属性信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @Override
    @Transactional
    public boolean modifyAttribute(AttributeEditReq req) {
        // 1、新建属性对象
        Attribute attributeOld = attributeDao.queryInfoByAttributeCode(req.getAttributeCode());
        if (attributeOld != null) {
            Attribute attributeNew = new Attribute();
            BeanUtils.copyProperties(req, attributeNew);
            attributeNew.setId(attributeOld.getId());
            // 2、更新属性对象数据
            attributeDao.updateById(attributeNew);
            // 3、修改关联关系 判断是 规格参数 还是 销售属性
            // 移除掉分组和属性的关系
            groupAttributeRelationDao.deleteByAttributeCode(attributeNew.getAttributeCode());
            if (req.getGroupCode() != null && AttributeTypeEnum.ATTRIBUTE_TYPE_BASE.getCode().equals(attributeNew.getType())) {
                // 3.2、修改分组关联
                GroupAttributeRelation relation = new GroupAttributeRelation();
                relation.setAttributeCode(attributeNew.getAttributeCode());
                relation.setAttributeName(attributeNew.getAttributeName());
                relation.setSort(0);
                Group group = groupDao.queryInfoByGroupCode(req.getGroupCode());
                relation.setGroupCode(group.getGroupCode());
                relation.setGroupName(group.getGroupName());
                // 3.3、同步更新数据表group_attribute_relation, 数据表冗余存储了名称字段
                groupAttributeRelationDao.save(relation);
            }
            // 4、返回数据
            return true;
        }
        return false;
    }

    /**
     * 查询 根据属性Code查询属性的信息
     *
     * @param attributeCode 属性Code
     * @return 属性信息
     */
    @Override
    public AttributeInfoResp findAttributeByCode(Long attributeCode) {
        // 1、判断数据是否为空
        if (attributeCode == null) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), ResultCode.PARAM_IS_ERROR.message());
        }
        // 2、新建返回的Vo对象
        AttributeInfoResp attributeInfoResp = new AttributeInfoResp();
        // 3、查询出属性的基本信息
        Attribute attribute = attributeDao.queryInfoByAttributeCode(
                attributeCode);
        BeanUtils.copyProperties(attribute, attributeInfoResp);
        // 4、设置分组信息
        if (AttributeTypeEnum.ATTRIBUTE_TYPE_BASE.getCode().equals(attribute.getType())) {
            GroupAttributeRelation groupAttributeRelation = groupAttributeRelationDao.queryInfoByAttributeCode(attributeCode);
            if (groupAttributeRelation != null) {
                attributeInfoResp.setGroupCode(groupAttributeRelation.getGroupCode());
                attributeInfoResp.setGroupName(groupAttributeRelation.getGroupName());
            }
        }
        // 5、设置分类路径
        Long categoryCode = attribute.getCategoryCode();
        Long[] catelogPath = categoryService.findCategoryPath(categoryCode);
        attributeInfoResp.setCategoryPath(catelogPath);
        // 6、设置分类名称
        Category category = categoryDao.queryInfoByCategoryCode(categoryCode);
        if (category != null) {
            attributeInfoResp.setCategoryName(category.getCategoryName());
        }
        // 7、返回数据
        return attributeInfoResp;
    }

}
