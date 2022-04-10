package com.abi.tmall.ware.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.ware.*;
import com.abi.tmall.ware.common.response.ware.WarePageResp;
import com.abi.tmall.ware.dao.entity.Ware;
import com.abi.tmall.ware.server.service.WareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 仓库 Console模块
 *
 * @ClassName: ConsoleWareController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Api(tags = "仓库 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/ware")
public class ConsoleWareController {

    @Autowired
    WareService wareService;

    /**
     * 查询 仓库分页列表
     *
     * @param warePageReq 查询条件
     * @return 仓库分页列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "查询 仓库分页列表")
    public ApiResponse<PageResponse<WarePageResp>> queryWarePageByCondition(@RequestBody WarePageReq warePageReq) {
        return ApiResponse.result(wareService.queryWarePageByCondition(warePageReq));
    }

    /**
     * 查询 仓库列表
     *
     * @param wareListReq 查询条件
     * @return 仓库列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询 仓库列表")
    public ApiResponse<List<Ware>> queryWareListByCondition(@RequestBody WareListReq wareListReq) {
        return ApiResponse.result(wareService.queryWareListByCondition(wareListReq));
    }

    /**
     * 添加 仓库信息
     *
     * @param wareAddReq 仓库信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 仓库信息")
    public ApiResponse<Boolean> saveWare(@RequestBody @Validated WareAddReq wareAddReq) {
        return ApiResponse.result(wareService.saveWare(wareAddReq));
    }

    /**
     * 删除 仓库信息
     *
     * @param wareDelReq 仓库Code
     * @return 删除是否成功: true-成功, false-失败
     */
    @PostMapping("/remove")
    @ApiOperation(value = "删除 仓库信息")
    public ApiResponse<Boolean> removeWare(@RequestBody @Validated WareDelReq wareDelReq) {
        return ApiResponse.result(wareService.removeWare(wareDelReq));
    }

    /**
     * 修改 仓库信息
     *
     * @param wareEditReq 仓库信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改 仓库信息")
    public ApiResponse<Boolean> modifyWare(@RequestBody @Validated WareEditReq wareEditReq) {
        return ApiResponse.result(wareService.modifyWare(wareEditReq));
    }

    /**
     * 查询 仓库信息
     *
     * @param wareInfoReq 仓库Code
     * @return 仓库信息
     */
    @PostMapping("/info")
    @ApiOperation(value = "查询 仓库信息")
    public ApiResponse<Ware> findWareByCode(@RequestBody @Validated WareInfoReq wareInfoReq) {
        return ApiResponse.result(wareService.findWareByCode(wareInfoReq));
    }

}
