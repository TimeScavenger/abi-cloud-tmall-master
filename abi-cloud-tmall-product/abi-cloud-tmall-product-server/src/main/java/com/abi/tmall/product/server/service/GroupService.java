package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.group.GroupAddDto;
import com.abi.tmall.product.common.request.group.GroupDelDto;
import com.abi.tmall.product.common.request.group.GroupEditDto;
import com.abi.tmall.product.common.request.group.GroupPageDto;
import com.abi.tmall.product.common.response.group.GroupInfoVo;
import com.abi.tmall.product.common.response.group.GroupPageVo;
import com.abi.tmall.product.dao.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: GroupService
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 属性分组
 */
public interface GroupService extends IService<Group> {

    PageResponse<GroupPageVo> queryGroupPageByCondition(GroupPageDto groupPageDto);

    boolean saveGroup(GroupAddDto groupAddDto);

    boolean removeGroup(GroupDelDto groupDelDto);

    boolean modifyGroup(GroupEditDto groupEditDto);

    GroupInfoVo findGroupByCode(Long groupCode);

}

