package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.sku.SkuListByCodeDto;
import com.abi.tmall.product.common.request.sku.SkuListByNameDto;
import com.abi.tmall.product.common.request.sku.SkuPageDto;
import com.abi.tmall.product.common.response.sku.SkuItemVo;
import com.abi.tmall.product.common.response.sku.SkuListVo;
import com.abi.tmall.product.common.response.sku.SkuPageVo;
import com.abi.tmall.product.dao.entity.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: SkuService
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description: Sku信息
 */
public interface SkuService extends IService<Sku> {

    PageResponse<SkuPageVo> querySkuPageByCondition(SkuPageDto skuPageDto);

    List<SkuListVo> querySkuListByCodes(SkuListByCodeDto skuListByCodeDto);

    List<SkuListVo> querySkuListByName(SkuListByNameDto skuListByNameDto);

    SkuItemVo querySkuItemBySkuCode(Long skuCode) throws ExecutionException, InterruptedException;

}