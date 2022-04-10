package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.attribute.*;
import com.abi.tmall.product.common.response.attribute.AttributeInfoResp;
import com.abi.tmall.product.common.response.attribute.AttributePageResp;
import com.abi.tmall.product.common.response.attribute.BaseAttributeListResp;
import com.abi.tmall.product.common.response.attribute.SaleAttributeListResp;
import com.abi.tmall.product.server.service.AttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性 Console模块
 *
 * @ClassName: AttributeController
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description:
 */
@Api(tags = "商品属性 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/attribute")
public class ConsoleAttributeController {

    @Autowired
    private AttributeService attributeService;

    /**
     * 查询 属性分页列表
     *
     * @param attributePageReq 查询条件
     * @return 属性分页列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "查询 属性分页列表")
    public ApiResponse<PageResponse<AttributePageResp>> queryAttributePageByCondition(@RequestBody AttributePageReq attributePageReq) {
        return ApiResponse.result(attributeService.queryAttributePageByCondition(attributePageReq));
    }

    /**
     * 查询 根据分类Code查询基本属性的信息
     *
     * @param attributeListReq 属性类型+分类Code
     * @return 属性列表
     */
    @PostMapping("/list/base")
    @ApiOperation(value = "查询 根据分类Code查询基本属性的信息")
    public ApiResponse<List<BaseAttributeListResp>> queryBaseAttributeListByCategoryCode(@RequestBody @Validated AttributeListReq attributeListReq) {
        return ApiResponse.result(attributeService.queryBaseAttributeListByCategoryCode(attributeListReq));
    }

    /**
     * 查询 根据分类Code查询销售属性的信息
     *
     * @param attributeListReq 属性类型+分类Code
     * @return 属性列表
     */
    @PostMapping("/list/sale")
    @ApiOperation(value = "查询 根据分类Code查询销售属性的信息")
    public ApiResponse<List<SaleAttributeListResp>> querySaleAttributeListByCategoryCode(@RequestBody @Validated AttributeListReq attributeListReq) {
        return ApiResponse.result(attributeService.querySaleAttributeListByCategoryCode(attributeListReq));
    }

    /**
     * 添加 属性信息
     *
     * @param attributeAddReq 属性信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 属性信息")
    public ApiResponse<Boolean> saveAttribute(@RequestBody @Validated AttributeAddReq attributeAddReq) {
        return ApiResponse.result(attributeService.saveAttribute(attributeAddReq));
    }

    /**
     * 删除 根据Codes删除属性信息
     *
     * @param attributeDelReq 属性Code集合
     * @return 删除是否成功: true-成功, false-失败
     */
    @PostMapping("/remove")
    @ApiOperation(value = "删除 根据Codes删除属性信息")
    public ApiResponse<Boolean> removeAttribute(@RequestBody AttributeDelReq attributeDelReq) {
        return ApiResponse.result(attributeService.removeAttribute(attributeDelReq));
    }

    /**
     * 修改 属性信息
     *
     * @param attributeEditReq 属性信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改 属性信息")
    public ApiResponse<Boolean> modifyAttribute(@RequestBody @Validated AttributeEditReq attributeEditReq) {
        return ApiResponse.result(attributeService.modifyAttribute(attributeEditReq));
    }

    /**
     * 查询 根据属性Code查询属性的信息
     *
     * @param attributeCode 属性Code
     * @return 属性信息
     */
    @GetMapping("/find/{attributeCode}")
    @ApiOperation(value = "查询 根据属性Code查询属性的信息")
    public ApiResponse<AttributeInfoResp> findAttributeByCode(@PathVariable("attributeCode") Long attributeCode) {
        return ApiResponse.result(attributeService.findAttributeByCode(attributeCode));
    }

}
