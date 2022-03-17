package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.tmall.product.dao.entity.SpuImgDetail;
import com.abi.tmall.product.dao.mapper.SpuImgDetailMapper;
import com.abi.tmall.product.dao.service.SpuImgDetailDao;
import com.abi.tmall.product.server.service.SpuImgDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.abi.infrastructure.core.constant.CommonConstants.LOG_PRE;

/**
 * @ClassName: SpuImgDetailServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description: Spu商品详情图片
 */
@Slf4j
@Service
public class SpuImgDetailServiceImpl extends ServiceImpl<SpuImgDetailMapper, SpuImgDetail> implements SpuImgDetailService {

    @Autowired
    private SpuImgDetailDao spuImgDetailDao;

    @Override
    public void saveSpuDetailImgs(Long spuCode, List<String> spuDetailImgs) {
        log.info(LOG_PRE + "SpuCode：【{}】，详情图片列表：【{}】", spuCode, spuDetailImgs);
        // 1、判断传输过来的参数是否异常
        if (spuCode == null) {
            throw new BusinessException("SpuCode为空");
        } else if (CollectionUtils.isEmpty(spuDetailImgs)) {
            throw new BusinessException("详情图片列表为空");
        } else {
            // 2、保存所有图片
            List<SpuImgDetail> spuImgDetails = spuDetailImgs.stream()
                    .map(img -> {
                        SpuImgDetail spuImgDetail = new SpuImgDetail();
                        spuImgDetail.setSpuCode(spuCode);
                        spuImgDetail.setImgUrl(img);
                        return spuImgDetail;
                    })
                    .collect(Collectors.toList());
            spuImgDetailDao.saveBatch(spuImgDetails);
        }
    }
}
