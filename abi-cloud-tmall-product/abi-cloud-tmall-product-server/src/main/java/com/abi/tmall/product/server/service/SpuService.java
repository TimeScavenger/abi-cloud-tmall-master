package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.spu.SpuAddDto;
import com.abi.tmall.product.common.request.spu.SpuPageDto;
import com.abi.tmall.product.common.request.spu.SpuUpDto;
import com.abi.tmall.product.common.response.spu.SpuPageVo;
import com.abi.tmall.product.dao.entity.Spu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ClassName: SpuService
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description: Spu信息
 */
public interface SpuService extends IService<Spu> {

    PageResponse<SpuPageVo> querySpuPageByCondition(SpuPageDto spuPageDto);

    boolean saveSpu(SpuAddDto spuAddDto);

    boolean upSpu(SpuUpDto spuUpDto);

}
