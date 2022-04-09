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

    PageResponse<AttributePageResp> queryAttributePageByCondition(AttributePageReq attributePageReq);

    List<BaseAttributeListResp> queryBaseAttributeListByCategoryCode(AttributeListReq attributeListReq);

    List<SaleAttributeListResp> querySaleAttributeListByCategoryCode(AttributeListReq attributeListReq);

    boolean saveAttribute(AttributeAddReq attributeAddReq);

    boolean removeAttribute(AttributeDelReq attributeDelReq);

    boolean modifyAttribute(AttributeEditReq attributeEditReq);

    AttributeInfoResp findAttributeByCode(Long attributeCode);

}

