package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.group.GaRelationAddDto;
import com.abi.tmall.product.common.request.group.GaRelationDelDto;
import com.abi.tmall.product.common.request.group.GaRelationPageDto;
import com.abi.tmall.product.common.response.attribute.AttributePageVo;
import com.abi.tmall.product.dao.entity.GroupAttributeRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: GroupAttributeRelationService
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 属性分组-属性关系
 */
public interface GroupAttributeRelationService extends IService<GroupAttributeRelation> {

    PageResponse<AttributePageVo> queryNoAttributePageByGroupCode(GaRelationPageDto gaRelationPageDto);

    PageResponse<AttributePageVo> queryAttributeListByGroupCode(GaRelationPageDto gaRelationPageDto);

    boolean batchSaveGroupAttributeRelation(List<GaRelationAddDto> gaRelationAddDtos);

    boolean batchRemoveGroupAttributeRelation(List<GaRelationDelDto> gaRelationDelDtos);

}
