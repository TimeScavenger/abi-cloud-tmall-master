package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.brand.*;
import com.abi.tmall.product.common.response.brand.BrandInfoResp;
import com.abi.tmall.product.common.response.brand.BrandListResp;
import com.abi.tmall.product.common.response.brand.BrandPageResp;
import com.abi.tmall.product.dao.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品品牌 服务类
 *
 * @ClassName: BrandService
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
public interface BrandService extends IService<Brand> {

    /**
     * 查询 品牌分页列表
     *
     * @param brandPageReq 查询条件
     * @return 品牌分页列表
     */
    PageResponse<BrandPageResp> queryBrandPageByCondition(BrandPageReq brandPageReq);

    /**
     * 查询 品牌列表
     *
     * @param brandListReq 查询条件
     * @return 品牌列表
     */
    List<BrandListResp> queryBrandListByCondition(BrandListReq brandListReq);

    /**
     * 查询 根据品牌Codes查询品牌列表
     *
     * @param brandCodes 品牌Codes
     * @return 品牌列表
     */
    List<BrandListResp> queryBrandListByCodes(List<Long> brandCodes);

    /**
     * 查询 根据品牌Codes查询品牌名称列表
     *
     * @param brandCodes 品牌Codes
     * @return 品牌列表
     */
    List<String> queryBrandNameListByCodes(List<Long> brandCodes);

    /**
     * 添加 品牌信息
     *
     * @param brandAddReq 品牌信息
     * @return 添加是否成功: true-成功, false-失败
     */
    boolean saveBrand(BrandAddReq brandAddReq);

    /**
     * 删除 根据品牌Codes删除品牌信息
     *
     * @param brandCodes 品牌Codes
     * @return 删除是否成功: true-成功, false-失败
     */
    boolean removeBrand(BrandDelReq brandCodes);

    /**
     * 修改 品牌信息
     *
     * @param brandEditReq 品牌信息
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyBrand(BrandEditReq brandEditReq);

    /**
     * 修改 根据品牌Code和状态修改品牌显示状态
     *
     * @param brandShowedReq 品牌Code+品牌状态
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyBrandShowed(BrandShowedReq brandShowedReq);

    /**
     * 修改 根据品牌Code和状态修改品牌可用状态
     *
     * @param brandCode 品牌Code
     * @param enabled   品牌状态
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyBrandEnabled(Long brandCode, Integer enabled);

    /**
     * 查询 根据品牌Code查询品牌信息
     *
     * @param brandCode 品牌Code
     * @return 品牌信息
     */
    BrandInfoResp findBrandByCode(Long brandCode);

}

