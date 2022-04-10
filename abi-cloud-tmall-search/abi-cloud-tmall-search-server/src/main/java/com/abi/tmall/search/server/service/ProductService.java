package com.abi.tmall.search.server.service;

import com.abi.tmall.search.common.request.SkuEsAddReq;
import com.abi.tmall.search.common.request.SkuEsSearchReq;
import com.abi.tmall.search.common.response.SkuEsSearchResp;

import java.io.IOException;
import java.util.List;

/**
 * 商品模块 服务类
 *
 * @ClassName: ProductService
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
public interface ProductService {

    /**
     * 搜索 商品信息
     *
     * @param skuEsSearchReq 搜索条件
     * @return 搜索结果
     */
    SkuEsSearchResp searchProductFromEs(SkuEsSearchReq skuEsSearchReq);

    /**
     * 添加 商品信息
     *
     * @param skuEsAddReqs 商品信息
     * @return 添加是否成功: true-成功, false-失败
     * @throws IOException
     */
    boolean saveProductToEs(List<SkuEsAddReq> skuEsAddReqs) throws IOException;

}

