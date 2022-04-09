package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.spu.SpuAddReq;
import com.abi.tmall.product.common.request.spu.SpuPageReq;
import com.abi.tmall.product.common.request.spu.SpuUpReq;
import com.abi.tmall.product.common.response.spu.SpuPageResp;
import com.abi.tmall.product.server.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spu Console模块
 *
 * @ClassName: SpuController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description:
 */
@Api(tags = "Spu Console模块")
@Slf4j
@RestController
@RequestMapping("/console/spu-info")
public class ConsoleSpuController {

    @Autowired
    private SpuService spuService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 Spu分页")
    public ApiResponse<PageResponse<SpuPageResp>> querySpuPageByCondition(@RequestBody SpuPageReq spuPageReq) {
        return ApiResponse.result(spuService.querySpuPageByCondition(spuPageReq));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加 Spu")
    public ApiResponse<Boolean> saveSpu(@RequestBody @Validated SpuAddReq spuAddReq) {
        return ApiResponse.result(spuService.saveSpu(spuAddReq));
    }

    @PostMapping("/up")
    @ApiOperation(value = "上架 Spu")
    public ApiResponse<Boolean> upSpu(@RequestBody @Validated SpuUpReq spuUpReq) {
        return ApiResponse.result(spuService.upSpu(spuUpReq));
    }

}
