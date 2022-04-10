package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.attribute.*;
import com.abi.tmall.product.common.response.attribute.AttributeInfoResp;
import com.abi.tmall.product.common.response.attribute.AttributePageResp;
import com.abi.tmall.product.common.response.attribute.BaseAttributeListResp;
import com.abi.tmall.product.common.response.attribute.SaleAttributeListResp;
import com.abi.tmall.product.dao.entity.Attribute;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品属性 服务类
 *
 * @ClassName: AttributeService
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description:
 */
public interface AttributeService extends IService<Attribute> {

    /**
     * 查询 属性分页列表
     *
     * @param attributePageReq 查询条件
     * @return 属性分页列表
     */
    PageResponse<AttributePageResp> queryAttributePageByCondition(AttributePageReq attributePageReq);

    /**
     * 查询 根据分类Code查询基本属性的信息
     *
     * @param attributeListReq 属性类型+分类Code
     * @return 属性列表
     */
    List<BaseAttributeListResp> queryBaseAttributeListByCategoryCode(AttributeListReq attributeListReq);

    /**
     * 查询 根据分类Code查询销售属性的信息
     *
     * @param attributeListReq 属性类型+分类Code
     * @return 属性列表
     */
    List<SaleAttributeListResp> querySaleAttributeListByCategoryCode(AttributeListReq attributeListReq);

    /**
     * 添加 属性信息
     *
     * @param attributeAddReq 属性信息
     * @return 添加是否成功: true-成功, false-失败
     */
    boolean saveAttribute(AttributeAddReq attributeAddReq);

    /**
     * 删除 根据Codes删除属性信息
     *
     * @param attributeDelReq 属性Code集合
     * @return 删除是否成功: true-成功, false-失败
     */
    boolean removeAttribute(AttributeDelReq attributeDelReq);

    /**
     * 修改 属性信息
     *
     * @param attributeEditReq 属性信息
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyAttribute(AttributeEditReq attributeEditReq);

    /**
     * 查询 根据属性Code查询属性的信息
     *
     * @param attributeCode 属性Code
     * @return 属性信息
     */
    AttributeInfoResp findAttributeByCode(Long attributeCode);

}

