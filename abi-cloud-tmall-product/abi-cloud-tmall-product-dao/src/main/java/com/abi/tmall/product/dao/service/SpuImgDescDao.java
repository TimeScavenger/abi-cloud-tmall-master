package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.SpuImgDesc;
import com.abi.tmall.product.dao.mapper.SpuImgDescMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spu介绍图片 持久服务类
 *
 * @ClassName: SpuImgDescDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Repository
public class SpuImgDescDao extends ServiceImpl<SpuImgDescMapper, SpuImgDesc> {

    public List<SpuImgDesc> queryListBySpuCode(Long spuCode) {
        List<SpuImgDesc> spuImgDescList = this.lambdaQuery()
                .eq(spuCode != null, SpuImgDesc::getSpuCode, spuCode)
                .list();
        return spuImgDescList;
    }
}
