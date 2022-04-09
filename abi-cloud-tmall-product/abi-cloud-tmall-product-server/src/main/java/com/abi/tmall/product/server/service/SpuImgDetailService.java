package com.abi.tmall.product.server.service;

import com.abi.tmall.product.dao.entity.SpuImgDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Spu详情图片
 *
 * @ClassName: SpuImgDetailService
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description:
 */
public interface SpuImgDetailService extends IService<SpuImgDetail> {

    void saveSpuDetailImgs(Long id, List<String> spuDetailImgs);

}
