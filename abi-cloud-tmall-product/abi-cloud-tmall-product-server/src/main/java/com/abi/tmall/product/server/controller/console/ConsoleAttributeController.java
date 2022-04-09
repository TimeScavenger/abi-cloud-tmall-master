package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.core.response.ApiResponse;
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

    @PostMapping("/page")
    @ApiOperation(value = "查询 属性分页列表")
    public ApiResponse<PageResponse<AttributePageResp>> queryAttributePageByCondition(@RequestBody AttributePageReq attributePageReq) {
        return ApiResponse.result(attributeService.queryAttributePageByCondition(attributePageReq));
    }

    @PostMapping("/list/base")
    @ApiOperation(value = "查询 根据分类id查询基本属性的信息")
    public ApiResponse<List<BaseAttributeListResp>> queryBaseAttributeListByCategoryCode(@RequestBody @Validated AttributeListReq attributeListReq) {
        return ApiResponse.result(attributeService.queryBaseAttributeListByCategoryCode(attributeListReq));
    }

    @PostMapping("/list/sale")
    @ApiOperation(value = "查询 根据分类id查询销售属性的信息")
    public ApiResponse<List<SaleAttributeListResp>> querySaleAttributeListByCategoryCode(@RequestBody @Validated AttributeListReq attributeListReq) {
        return ApiResponse.result(attributeService.querySaleAttributeListByCategoryCode(attributeListReq));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加 属性信息")
    public ApiResponse<Boolean> saveAttribute(@RequestBody @Validated AttributeAddReq attributeAddReq) {
        return ApiResponse.result(attributeService.saveAttribute(attributeAddReq));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除 根据ids删除属性信息")
    public ApiResponse<Boolean> removeAttribute(@RequestBody AttributeDelReq attributeDelReq) {
        return ApiResponse.result(attributeService.removeAttribute(attributeDelReq));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 属性信息")
    public ApiResponse<Boolean> modifyAttribute(@RequestBody @Validated AttributeEditReq attributeEditReq) {
        return ApiResponse.result(attributeService.modifyAttribute(attributeEditReq));
    }

    @GetMapping("/find/{attributeCode}")
    @ApiOperation(value = "查询 根据属性id查询属性的信息")
    public ApiResponse<AttributeInfoResp> findAttributeByCode(@PathVariable("attributeCode") Long attributeCode) {
        return ApiResponse.result(attributeService.findAttributeByCode(attributeCode));
    }

}
