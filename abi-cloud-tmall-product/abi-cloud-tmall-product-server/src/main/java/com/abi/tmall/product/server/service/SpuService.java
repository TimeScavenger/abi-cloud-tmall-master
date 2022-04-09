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

    PageResponse<SpuPageResp> querySpuPageByCondition(SpuPageReq spuPageReq);

    boolean saveSpu(SpuAddReq spuAddReq);

    boolean upSpu(SpuUpReq spuUpReq);

}
