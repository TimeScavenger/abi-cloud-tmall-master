package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.group.GroupAddReq;
import com.abi.tmall.product.common.request.group.GroupDelReq;
import com.abi.tmall.product.common.request.group.GroupEditReq;
import com.abi.tmall.product.common.request.group.GroupPageReq;
import com.abi.tmall.product.common.response.group.GroupInfoResp;
import com.abi.tmall.product.common.response.group.GroupPageResp;
import com.abi.tmall.product.dao.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 商品属性分组 服务类
 *
 * @ClassName: GroupService
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description:
 */
public interface GroupService extends IService<Group> {

    /**
     * 查询 分组分页列表
     *
     * @param groupPageReq 查询条件
     * @return 分组分页列表
     */
    PageResponse<GroupPageResp> queryGroupPageByCondition(GroupPageReq groupPageReq);

    /**
     * 添加 分组信息
     *
     * @param groupAddReq 分组信息
     * @return 添加是否成功: true-成功, false-失败
     */
    boolean saveGroup(GroupAddReq groupAddReq);

    /**
     * 删除 根据Codes删除分组信息
     *
     * @param groupDelReq 分组Codes
     * @return 删除是否成功: true-成功, false-失败
     */
    boolean removeGroup(GroupDelReq groupDelReq);

    /**
     * 修改 分组信息
     *
     * @param groupEditReq 分组信息
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyGroup(GroupEditReq groupEditReq);

    /**
     * 查询 根据分组Code查询分组信息
     *
     * @param groupCode 分组Code
     * @return 分组信息
     */
    GroupInfoResp findGroupByCode(Long groupCode);

}

