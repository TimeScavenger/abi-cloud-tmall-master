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

    PageResponse<GroupPageResp> queryGroupPageByCondition(GroupPageReq groupPageReq);

    boolean saveGroup(GroupAddReq groupAddReq);

    boolean removeGroup(GroupDelReq groupDelReq);

    boolean modifyGroup(GroupEditReq groupEditReq);

    GroupInfoResp findGroupByCode(Long groupCode);

}

