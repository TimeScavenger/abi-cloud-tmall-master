package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.spu.SpuAddReq;
import com.abi.tmall.product.common.request.spu.SpuPageReq;
import com.abi.tmall.product.common.request.spu.SpuUpReq;
import com.abi.tmall.product.common.response.spu.SpuPageResp;
import com.abi.tmall.product.dao.entity.Spu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Spu 服务类
 *
 * @ClassName: SpuService
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description:
 */
public interface SpuService extends IService<Spu> {

    /**
     * 查询 Spu分页列表
     *
     * @param spuPageReq 查询条件
     * @return Spu分页列表
     */
    PageResponse<SpuPageResp> querySpuPageByCondition(SpuPageReq spuPageReq);

    /**
     * 添加 Spu信息
     *
     * @param spuAddReq Spu信息
     * @return 添加是否成功: true-成功, false-失败
     */
    boolean saveSpu(SpuAddReq spuAddReq);

    /**
     * 上架 Spu信息
     *
     * @param spuUpReq spuCode
     * @return 上架是否成功: true-成功, false-失败
     */
    boolean upSpu(SpuUpReq spuUpReq);

}
