package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.attribute.*;
import com.abi.tmall.product.common.response.attribute.AttributeInfoVo;
import com.abi.tmall.product.common.response.attribute.AttributePageVo;
import com.abi.tmall.product.common.response.attribute.BaseAttributeListVo;
import com.abi.tmall.product.common.response.attribute.SaleAttributeListVo;
import com.abi.tmall.product.dao.entity.Attribute;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: AttributeService
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 商品属性
 */
public interface AttributeService extends IService<Attribute> {

    PageResponse<AttributePageVo> queryAttributePageByCondition(AttributePageDto attributePageDto);

    List<BaseAttributeListVo> queryBaseAttributeListByCategoryCode(AttributeListDto attributeListDto);

    List<SaleAttributeListVo> querySaleAttributeListByCategoryCode(AttributeListDto attributeListDto);

    boolean saveAttribute(AttributeAddDto attributeAddDto);

    boolean removeAttribute(AttributeDelDto attributeDelDto);

    boolean modifyAttribute(AttributeEditDto attributeEditDto);

    AttributeInfoVo findAttributeByCode(Long attributeCode);

}

