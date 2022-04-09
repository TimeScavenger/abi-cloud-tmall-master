package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.group.GaRelationAddReq;
import com.abi.tmall.product.common.request.group.GaRelationDelReq;
import com.abi.tmall.product.common.request.group.GaRelationPageReq;
import com.abi.tmall.product.common.response.attribute.AttributePageResp;
import com.abi.tmall.product.dao.entity.Attribute;
import com.abi.tmall.product.dao.entity.Group;
import com.abi.tmall.product.dao.entity.GroupAttributeRelation;
import com.abi.tmall.product.dao.mapper.GroupAttributeRelationMapper;
import com.abi.tmall.product.dao.service.AttributeDao;
import com.abi.tmall.product.dao.service.GroupAttributeRelationDao;
import com.abi.tmall.product.dao.service.GroupDao;
import com.abi.tmall.product.server.enums.AttributeTypeEnum;
import com.abi.tmall.product.server.service.GroupAttributeRelationService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品属性分组-属性关系 服务实现类
 *
 * @ClassName: GroupAttributeRelationServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description:
 */
@Slf4j
@Service
public class GroupAttributeRelationServiceImpl extends ServiceImpl<GroupAttributeRelationMapper, GroupAttributeRelation> implements GroupAttributeRelationService {

    @Autowired
    private GroupAttributeRelationDao groupAttributeRelationDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private AttributeDao attributeDao;

    /**
     * 查询 分组没有关联的属性列表
     *
     * @param dto
     * @return
     */
    @Override
    public PageResponse<AttributePageResp> queryNoAttributePageByGroupCode(GaRelationPageReq dto) {
        // 1、新建分页返回对象
        PageResponse<AttributePageResp> pageResponse = new PageResponse<>();

        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();

        // 3、当前分组只能关联自己所属的分类里面的所有属性
        Group group = groupDao.queryInfoByGroupCode(dto.getGroupCode());
        Long categoryCode = group.getCategoryCode();

        // 4、当前分组只能关联别的分组没有引用的属性
        // 4.1、查询当前分组所属的分类下的其他分组（内存 属于 手机，查询出手机下所有的分组）
        List<Group> groups = groupDao.queryListByCategoryCode(categoryCode);
        List<Long> groupCodes = groups.stream()
                .map(Group::getGroupCode)
                .collect(Collectors.toList());
        // 4.2、查询分类下的所有分组已经关联的属性
        List<GroupAttributeRelation> groupAttributeRelations = groupAttributeRelationDao.queryListByGroupCodes(groupCodes);
        List<Long> attributeCodes = groupAttributeRelations.stream()
                .map(GroupAttributeRelation::getAttributeCode)
                .collect(Collectors.toList());

        // 5、从当前分类的所有属性中移除这些属性；
        Page<Attribute> page = attributeDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), categoryCode, AttributeTypeEnum.ATTRIBUTE_TYPE_BASE.getCode(), attributeCodes, dto.getAttributeName());

        // 6、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<AttributePageResp> pageVoList = page.getRecords().stream()
                    .map(brand -> {
                        AttributePageResp attributePageResp = new AttributePageResp();
                        BeanUtils.copyProperties(brand, attributePageResp);
                        return attributePageResp;
                    })
                    .collect(Collectors.toList());
            // 组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(pageVoList);
        }
        // 7、返回数据
        return pageResponse;
    }

    /**
     * 根据分组id查找关联的所有基本属性
     *
     * @param dto
     * @return
     */
    @Override
    public PageResponse<AttributePageResp> queryAttributeListByGroupCode(GaRelationPageReq dto) {
        // 1、新建分页返回对象
        PageResponse<AttributePageResp> pageResponse = new PageResponse<>();

        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();

        // 3、根据分组ID查询出所有已关联的属性
        List<GroupAttributeRelation> groupAttributeRelations = groupAttributeRelationDao.queryListByGroupCodes(Lists.newArrayList(dto.getGroupCode()));
        if (CollectionUtils.isEmpty(groupAttributeRelations)) {
            return pageResponse;
        }

        // 5、取出分组关联的属性ids
        List<Long> attributeCodes = groupAttributeRelations.stream()
                .map(GroupAttributeRelation::getAttributeCode)
                .collect(Collectors.toList());

        // 6、从当前分类的所有属性中移除这些属性；
        Page<Attribute> page = attributeDao.queryPageByAttributeCodes(dto.getPageNo(), dto.getPageSize(), attributeCodes);

        // 7、数据进行转换
        if (page.getRecords() != null) {
            List<AttributePageResp> pageVoList = page.getRecords().stream()
                    .map(brand -> {
                        AttributePageResp attributePageResp = new AttributePageResp();
                        BeanUtils.copyProperties(brand, attributePageResp);
                        return attributePageResp;
                    })
                    .collect(Collectors.toList());
            // 4、组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(pageVoList);
        }
        // 8、返回数据
        return pageResponse;
    }

    /**
     * 添加 分组和属性关系的对象
     *
     * @param dtos
     * @return
     */
    @Override
    public boolean batchSaveGroupAttributeRelation(List<GaRelationAddReq> dtos) {
        // 1、查询出全部的分组集合
        List<Group> groups = groupDao.list();
        Map<Long, String> groupCodeAndGroupNameMap = groups.stream()
                .collect(Collectors.toMap(Group::getGroupCode, Group::getGroupName));
        // 2、查询出全部的属性集合
        List<Attribute> attributes = attributeDao.list();
        Map<Long, String> attributeCodeAndAttributeNameMap = attributes.stream()
                .collect(Collectors.toMap(Attribute::getAttributeCode, Attribute::getAttributeName));
        // 3、对拷数据然后批量保存
        List<GroupAttributeRelation> relationList = dtos.stream()
                .map(item -> {
                    GroupAttributeRelation relation = new GroupAttributeRelation();
                    BeanUtils.copyProperties(item, relation);
                    relation.setGroupName(groupCodeAndGroupNameMap.get(item.getGroupCode()));
                    relation.setAttributeName(attributeCodeAndAttributeNameMap.get(item.getAttributeCode()));
                    relation.setSort(0);
                    return relation;
                })
                .collect(Collectors.toList());
        // 2、批量保存数据
        return groupAttributeRelationDao.saveBatch(relationList);
    }

    /**
     * 删除 分组和属性关系的对象
     *
     * @param dtos
     * @return
     */
    @Override
    public boolean batchRemoveGroupAttributeRelation(List<GaRelationDelReq> dtos) {
        // 1、查询出全部的分组属性关系集合
        List<GroupAttributeRelation> groupAttributeRelations = groupAttributeRelationDao.list();
        Map<Long, Long> groupCodeWithAttribueCodeAndCodeMap = groupAttributeRelations.stream()
                .collect(HashMap::new, (m, v) -> m.put(v.getGroupCode() + v.getAttributeCode(), v.getId()), HashMap::putAll);
        // 2、过滤出全部的分组ID集合
        List<Long> ids = new ArrayList<>();
        dtos.forEach(item -> {
            Long aLong = groupCodeWithAttribueCodeAndCodeMap.get(item.getGroupCode() + item.getAttributeCode());
            ids.add(aLong);
        });
        // 3、批量删除数据
        return groupAttributeRelationDao.removeByIds(ids);
    }

}
