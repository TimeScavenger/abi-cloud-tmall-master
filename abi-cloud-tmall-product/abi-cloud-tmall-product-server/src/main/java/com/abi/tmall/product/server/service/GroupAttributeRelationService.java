package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.group.GaRelationAddReq;
import com.abi.tmall.product.common.request.group.GaRelationDelReq;
import com.abi.tmall.product.common.request.group.GaRelationPageReq;
import com.abi.tmall.product.common.response.attribute.AttributePageResp;
import com.abi.tmall.product.dao.entity.GroupAttributeRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品属性分组-属性关系 服务类
 *
 * @ClassName: GroupAttributeRelationService
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description:
 */
public interface GroupAttributeRelationService extends IService<GroupAttributeRelation> {

    PageResponse<AttributePageResp> queryNoAttributePageByGroupCode(GaRelationPageReq gaRelationPageReq);

    PageResponse<AttributePageResp> queryAttributeListByGroupCode(GaRelationPageReq gaRelationPageReq);

    boolean batchSaveGroupAttributeRelation(List<GaRelationAddReq> gaRelationAddReqs);

    boolean batchRemoveGroupAttributeRelation(List<GaRelationDelReq> gaRelationDelReqs);

}
