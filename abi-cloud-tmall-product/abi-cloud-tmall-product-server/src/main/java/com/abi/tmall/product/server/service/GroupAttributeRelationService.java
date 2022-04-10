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

    /**
     * 查询 分组没有关联的属性列表
     *
     * @param gaRelationPageReq 属性分组名字+属性名称
     * @return 属性列表
     */
    PageResponse<AttributePageResp> queryNoAttributePageByGroupCode(GaRelationPageReq gaRelationPageReq);

    /**
     * 查询 根据分组Code查找关联的所有基本属性
     *
     * @param gaRelationPageReq 属性分组名字+属性名称
     * @return 属性列表
     */
    PageResponse<AttributePageResp> queryAttributeListByGroupCode(GaRelationPageReq gaRelationPageReq);

    /**
     * 添加 分组和属性关系
     *
     * @param gaRelationAddReqs 分组和属性关系列表
     * @return 添加是否成功: true-成功, false-失败
     */
    boolean batchSaveGroupAttributeRelation(List<GaRelationAddReq> gaRelationAddReqs);

    /**
     * 删除 分组和属性关系
     *
     * @param gaRelationDelReqs 分组和属性关系列表
     * @return 删除是否成功: true-成功, false-失败
     */
    boolean batchRemoveGroupAttributeRelation(List<GaRelationDelReq> gaRelationDelReqs);

}
