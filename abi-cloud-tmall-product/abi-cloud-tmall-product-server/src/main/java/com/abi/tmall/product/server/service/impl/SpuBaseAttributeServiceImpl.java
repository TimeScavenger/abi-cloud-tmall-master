package com.abi.tmall.product.server.service.impl;

import com.abi.tmall.product.dao.entity.SpuBaseAttributeValue;
import com.abi.tmall.product.dao.mapper.SpuBaseAttributeValueMapper;
import com.abi.tmall.product.server.service.SpuBaseAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Spu规格参数-属性值 服务实现类
 *
 * @ClassName: SpuAttrValueServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description:
 */
@Slf4j
@Service
public class SpuBaseAttributeServiceImpl extends ServiceImpl<SpuBaseAttributeValueMapper, SpuBaseAttributeValue> implements SpuBaseAttributeService {

}
