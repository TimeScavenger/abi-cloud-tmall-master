package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.web.util.GenerateCodeUtils;
import com.abi.tmall.product.common.request.group.GroupAddReq;
import com.abi.tmall.product.common.request.group.GroupDelReq;
import com.abi.tmall.product.common.request.group.GroupEditReq;
import com.abi.tmall.product.common.request.group.GroupPageReq;
import com.abi.tmall.product.common.response.group.GroupInfoResp;
import com.abi.tmall.product.common.response.group.GroupPageResp;
import com.abi.tmall.product.dao.entity.Group;
import com.abi.tmall.product.dao.mapper.GroupMapper;
import com.abi.tmall.product.dao.service.GroupAttributeRelationDao;
import com.abi.tmall.product.dao.service.GroupDao;
import com.abi.tmall.product.server.enums.ProductInitCodeEnum;
import com.abi.tmall.product.server.service.CategoryService;
import com.abi.tmall.product.server.service.GroupService;
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
 * 商品属性分组 服务实现类
 *
 * @ClassName: GroupServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description:
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GroupAttributeRelationDao groupAttributeRelationDao;

    /**
     * 查询 分组分页列表
     *
     * @param groupPageReq 查询条件
     * @return 分组分页列表
     */
    @Override
    public PageResponse<GroupPageResp> queryGroupPageByCondition(GroupPageReq req) {
        // 1、新建分页返回对象
        PageResponse<GroupPageResp> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        req.checkParam();
        // 3、分页查询
        Page<Group> page = groupDao.queryPage(req.getPageNo(), req.getPageSize(), req.getCategoryCode(), req.getGroupName());
        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<GroupPageResp> pageVoList = page.getRecords().stream()
                    .map(group -> {
                        GroupPageResp groupPageResp = new GroupPageResp();
                        BeanUtils.copyProperties(group, groupPageResp);
                        return groupPageResp;
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
     * 添加 分组信息
     *
     * @param req 分组信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @Override
    public boolean saveGroup(GroupAddReq req) {
        // 1、判断是否为重复添加
        Group result = groupDao.queryInfoByCategoryCodeAndGroupName(req.getCategoryCode(), req.getGroupName());
        // 2、判断是否为重复添加数据
        if (result != null) {
            throw new BusinessException(ResultCode.DATA_IS_EXISTED.code(), ResultCode.DATA_IS_EXISTED.message());
        }
        // 3、新建分组对象
        Group group = new Group();
        BeanUtils.copyProperties(req, group);
        group.setGroupCode(GenerateCodeUtils.getCode(ProductInitCodeEnum.PMS_GROUP_INIT_CODE.getDesc()));
        return groupDao.save(group);
    }

    /**
     * 删除 根据Codes删除分组信息
     *
     * @param req 分组Codes
     * @return 删除是否成功: true-成功, false-失败
     */
    @Override
    public boolean removeGroup(GroupDelReq req) {
        // 1、TODO 拓展：检查当前删除的分组, 是否被别的地方引用，例如分组和属性的关联关系
        // 2、逻辑删除
        return groupDao.deleteByGroupCodes(req.getGroupCodes());
    }

    /**
     * 修改 分组信息
     *
     * @param req 分组信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @Transactional
    @Override
    public boolean modifyGroup(GroupEditReq req) {
        // 1、查询校验分类是否合法
        Group groupOld = groupDao.queryInfoByGroupCode(req.getGroupCode());
        if (groupOld != null) {
            // 2、新建分组对象
            Group groupNew = new Group();
            BeanUtils.copyProperties(req, groupNew);
            groupNew.setId(groupOld.getId());
            // 3、更新分组对象数据
            baseMapper.updateById(groupNew);
            // 4、保证冗余字段的数据一致 如果品牌名不为空 同步更新其他的数据表【部分数据表冗余存储了名称字段】
            groupAttributeRelationDao.updateGroupNameByGroupCode(groupNew.getGroupCode(), groupNew.getGroupName());
            // 5、返回数据
            return true;
        }
        return false;
    }

    /**
     * 查询 根据分组Code查询分组信息
     *
     * @param groupCode 分组Code
     * @return 分组信息
     */
    @Override
    public GroupInfoResp findGroupByCode(Long groupCode) {
        // 1、判断数据是否为空
        if (groupCode == null) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), ResultCode.PARAM_IS_ERROR.message());
        }
        // 2、查询数据
        Group group = groupDao.queryInfoByGroupCode(groupCode);
        // 3、返回数据
        if (group != null) {
            GroupInfoResp groupInfoResp = new GroupInfoResp();
            BeanUtils.copyProperties(group, groupInfoResp);
            // 3.1、根据分类Code查询出分类的路径
            Long[] path = categoryService.findCategoryPath(group.getCategoryCode());
            // 3.2、设置分类路径
            groupInfoResp.setCategoryPath(path);
            return groupInfoResp;
        } else {
            throw new BusinessException(ResultCode.DATA_NOT_EXISTED.code(), ResultCode.DATA_NOT_EXISTED.message());
        }
    }
}
