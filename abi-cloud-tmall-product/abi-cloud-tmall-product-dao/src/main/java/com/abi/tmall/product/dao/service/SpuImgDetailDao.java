package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.SpuImgDetail;
import com.abi.tmall.product.dao.mapper.SpuImgDetailMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: SpuImgDetailDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: Spu商品详情图片
 */
@Repository
public class SpuImgDetailDao extends ServiceImpl<SpuImgDetailMapper, SpuImgDetail> {

    public List<SpuImgDetail> queryListBySpuCode(Long spuCode) {
        List<SpuImgDetail> spuImgDetailList = this.lambdaQuery()
                .eq(spuCode != null, SpuImgDetail::getSpuCode, spuCode)
                .list();
        return spuImgDetailList;
    }
}
