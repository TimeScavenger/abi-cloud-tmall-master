package com.abi.tmall.ware.server.service.impl;

import com.abi.base.foundation.page.PageResponse;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.ware.common.request.ware.WareStockDto;
import com.abi.tmall.ware.common.request.waresku.WareSkuRelationPageDto;
import com.abi.tmall.ware.common.response.waresku.WareSkuRelationPageVo;
import com.abi.tmall.ware.common.response.waresku.WareSkuRelationStockVo;
import com.abi.tmall.ware.dao.entity.WareSkuRelation;
import com.abi.tmall.ware.dao.mapper.WareSkuRelationMapper;
import com.abi.tmall.ware.dao.service.WareDao;
import com.abi.tmall.ware.dao.service.WareSkuRelationDao;
import com.abi.tmall.ware.server.client.ProductFeignClient;
import com.abi.tmall.ware.server.client.request.SkuListByCodeReq;
import com.abi.tmall.ware.server.client.request.SkuListByNameReq;
import com.abi.tmall.ware.server.client.response.SkuListResp;
import com.abi.tmall.ware.server.service.WareService;
import com.abi.tmall.ware.server.service.WareSkuRelationService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: WareSkuRelationServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 库存信息
 */
@Service
public class WareSkuRelationServiceImpl extends ServiceImpl<WareSkuRelationMapper, WareSkuRelation> implements WareSkuRelationService {

    @Autowired
    private WareService wareService;

    @Autowired
    private WareSkuRelationDao wareSkuRelationDao;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private WareDao wareDao;

    @Override
    public PageResponse<WareSkuRelationPageVo> queryWareSkuRelationPageByCondition(WareSkuRelationPageDto dto) {
        // 1、新建用于返回的分页对象
        PageResponse<WareSkuRelationPageVo> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();
        // 3、分页查询
        // 调用商品服务 传入Sku的名字模糊查询出符合条件的SkuCode集合
        List<Long> skuCodeList = new ArrayList<>();
        if (StringUtils.isNotBlank(dto.getSkuName())) {
            SkuListByNameReq skuListByNameReq = new SkuListByNameReq();
            skuListByNameReq.setSkuName(dto.getSkuName());
            ApiResponse<List<SkuListResp>> apiResponse = productFeignClient.querySkuListByName(skuListByNameReq);
            if (apiResponse != null && CollectionUtils.isNotEmpty(apiResponse.getData())) {
                skuCodeList = apiResponse.getData().stream().map(SkuListResp::getSkuCode).collect(Collectors.toList());
            }
        }
        Page<WareSkuRelation> page = wareSkuRelationDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getWareCode(), skuCodeList);

        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 4.1、查询出所有的仓库信息
            List<Long> wareCodes = page.getRecords().stream()
                    .map(WareSkuRelation::getWareCode)
                    .collect(Collectors.toList());
            Map<Long, String> wareCodeAndWareNameMap = wareDao.queryListByWareCodes(wareCodes).stream()
                    .collect(HashMap::new, (m, v) -> m.put(v.getWareCode(), v.getWareName()), HashMap::putAll);

            // 4.2、调用商品服务 根据SkuCode查询出所有的商品信息
            List<Long> skuCodes = page.getRecords().stream()
                    .map(WareSkuRelation::getSkuCode)
                    .collect(Collectors.toList());
            SkuListByCodeReq skuListByCodeReq = new SkuListByCodeReq();
            skuListByCodeReq.setSkuCodes(skuCodes);
            ApiResponse<List<SkuListResp>> apiResponse = productFeignClient.querySkuListByCodes(skuListByCodeReq);
            Map<Long, String> skuCodeAndSkuNameMap = new HashMap<>();
            if (apiResponse != null && CollectionUtils.isNotEmpty(apiResponse.getData())) {
                skuCodeAndSkuNameMap = apiResponse.getData().stream()
                        .collect(HashMap::new, (m, v) -> m.put(v.getSkuCode(), v.getSkuName()), HashMap::putAll);
            }

            // 4.3、数据进行转换
            Map<Long, String> finalSkuCodeAndSkuNameMap = skuCodeAndSkuNameMap;
            List<WareSkuRelationPageVo> warePageRespList = page.getRecords().stream()
                    .map(ware -> {
                        WareSkuRelationPageVo warePageResp = new WareSkuRelationPageVo();
                        BeanUtils.copyProperties(ware, warePageResp);
                        if (ware.getWareCode() != null && MapUtils.isNotEmpty(wareCodeAndWareNameMap)) {
                            warePageResp.setWareName(wareCodeAndWareNameMap.get(ware.getWareCode()));
                        }
                        if (ware.getSkuCode() != null && MapUtils.isNotEmpty(finalSkuCodeAndSkuNameMap)) {
                            warePageResp.setSkuName(finalSkuCodeAndSkuNameMap.get(ware.getSkuCode()));
                        }
                        return warePageResp;
                    })
                    .collect(Collectors.toList());

            // 4.4、组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(warePageRespList);
        }

        // 5、返回数据
        return pageResponse;
    }

    @Override
    public void stockWareSkuRelation(Long skuCode, Long wareCode, Integer skuNum) {
        // TODO 此处代码直接存在及更新进行操作。
    }

    @Override
    public List<WareSkuRelationStockVo> querySkuHasStock(WareStockDto dto) {
        return dto.getSkuCodes().stream()
                .map(item -> {
                    Integer count = baseMapper.getSkuStock(item);
                    WareSkuRelationStockVo skuHasStockVo = new WareSkuRelationStockVo();
                    skuHasStockVo.setSkuCode(item);
                    skuHasStockVo.setHasStock(count != null && count > 0);
                    return skuHasStockVo;
                }).collect(Collectors.toList());
    }

}
