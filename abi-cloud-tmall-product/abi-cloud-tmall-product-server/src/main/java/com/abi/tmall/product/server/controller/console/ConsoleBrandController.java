package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.brand.*;
import com.abi.tmall.product.common.response.brand.BrandInfoResp;
import com.abi.tmall.product.common.response.brand.BrandListResp;
import com.abi.tmall.product.common.response.brand.BrandPageResp;
import com.abi.tmall.product.server.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌 Console模块
 *
 * @ClassName: BrandController
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Api(tags = "商品品牌 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/brand")
public class ConsoleBrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询 品牌分页列表
     *
     * @param brandPageReq 查询条件
     * @return 品牌分页列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "查询 品牌分页列表")
    public ApiResponse<PageResponse<BrandPageResp>> queryBrandPageByCondition(@RequestBody BrandPageReq brandPageReq) {
        return ApiResponse.result(brandService.queryBrandPageByCondition(brandPageReq));
    }

    /**
     * 查询 品牌列表
     *
     * @param brandListReq 查询条件
     * @return 品牌列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询 品牌列表")
    public ApiResponse<List<BrandListResp>> queryBrandListByCondition(@RequestBody BrandListReq brandListReq) {
        return ApiResponse.result(brandService.queryBrandListByCondition(brandListReq));
    }

    /**
     * 查询 根据品牌Codes查询品牌列表
     *
     * @param brandCodes 品牌Codes
     * @return 品牌列表
     */
    @PostMapping("/list/by/code")
    @ApiOperation(value = "查询 根据品牌Codes查询品牌列表")
    public ApiResponse<List<BrandListResp>> queryBrandListByCodes(@RequestBody List<Long> brandCodes) {
        return ApiResponse.result(brandService.queryBrandListByCodes(brandCodes));
    }

    /**
     * 查询 根据品牌Codes查询品牌名称列表
     *
     * @param brandCodes 品牌Codes
     * @return 品牌列表
     */
    @PostMapping("/list/brandName/by/code")
    @ApiOperation(value = "查询 根据品牌Codes查询品牌名称列表")
    public ApiResponse<List<String>> queryBrandNameListByCodes(@RequestBody List<Long> brandCodes) {
        return ApiResponse.result(brandService.queryBrandNameListByCodes(brandCodes));
    }

    /**
     * 添加 品牌信息
     *
     * @param brandAddReq 品牌信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 品牌信息")
    public ApiResponse<Boolean> saveBrand(@RequestBody @Validated BrandAddReq brandAddReq) {
        return ApiResponse.result(brandService.saveBrand(brandAddReq));
    }

    /**
     * 删除 根据品牌Codes删除品牌信息
     *
     * @param brandCodes 品牌Codes
     * @return 删除是否成功: true-成功, false-失败
     */
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除 根据品牌Codes删除品牌信息")
    public ApiResponse<Boolean> removeBrand(@RequestBody BrandDelReq brandCodes) {
        return ApiResponse.result(brandService.removeBrand(brandCodes));
    }

    /**
     * 修改 品牌信息
     *
     * @param brandEditReq 品牌信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改 品牌信息")
    public ApiResponse<Boolean> modifyBrand(@RequestBody @Validated BrandEditReq brandEditReq) {
        return ApiResponse.result(brandService.modifyBrand(brandEditReq));
    }

    /**
     * 修改 根据品牌Code和状态修改品牌显示状态
     *
     * @param brandShowedReq 品牌Code+品牌状态
     * @return 修改是否成功: true-成功, false-失败
     */
    @PostMapping("/modify/showed")
    @ApiOperation(value = "修改 根据品牌Code和品牌状态修改品牌显示状态")
    public ApiResponse<Boolean> modifyBrandShowed(@RequestBody @Validated BrandShowedReq brandShowedReq) {
        return ApiResponse.result(brandService.modifyBrandShowed(brandShowedReq));
    }

    /**
     * 修改 根据品牌Code和状态修改品牌可用状态
     *
     * @param brandCode 品牌Code
     * @param enabled   品牌状态
     * @return 修改是否成功: true-成功, false-失败
     */
    @GetMapping("/modify/{brandCode}/{enabled}")
    @ApiOperation(value = "修改 根据品牌Code和品牌状态修改品牌可用状态")
    public ApiResponse<Boolean> modifyBrandEnabled(@PathVariable("brandCode") Long brandCode, @PathVariable("enabled") Integer enabled) {
        return ApiResponse.result(brandService.modifyBrandEnabled(brandCode, enabled));
    }

    /**
     * 查询 根据品牌Code查询品牌信息
     *
     * @param brandCode 品牌Code
     * @return 品牌信息
     */
    @GetMapping("/info/{brandCode}")
    @ApiOperation(value = "查询 根据品牌Code查询品牌信息")
    public ApiResponse<BrandInfoResp> findBrandByCode(@PathVariable("brandCode") Long brandCode) {
        return ApiResponse.result(brandService.findBrandByCode(brandCode));
    }

}
