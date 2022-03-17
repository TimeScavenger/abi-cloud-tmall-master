package com.abi.tmall.search.server.service;

import com.abi.tmall.search.common.request.SkuEsAddReq;
import com.abi.tmall.search.common.request.SkuEsSearchReq;
import com.abi.tmall.search.common.response.SkuEsSearchResp;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: ProductService
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: 商品模块
 */
public interface ProductService {

    SkuEsSearchResp searchProductFromEs(SkuEsSearchReq skuEsSearchReq);

    boolean saveProductToEs(List<SkuEsAddReq> skuEsAddReqs) throws IOException;

}

