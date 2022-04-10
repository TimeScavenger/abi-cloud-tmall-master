package com.abi.tmall.coupon.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.coupon.dao.entity.MemberPrice;
import com.abi.tmall.coupon.server.service.MemberPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品会员价格 Console模块
 *
 * @ClassName: ConsoleMemberPriceController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description:
 */
@Api(tags = "商品会员价格")
@Slf4j
@RestController
@RequestMapping("/console/member-price")
public class ConsoleMemberPriceController {

    @Autowired
    private MemberPriceService memberPriceService;

    /**
     * 添加 商品会员价格
     *
     * @param memberPrice 会员价格
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 商品会员价格")
    public ApiResponse<Boolean> saveMemberPrice(@RequestBody MemberPrice memberPrice) {
        return ApiResponse.result(memberPriceService.save(memberPrice));
    }

}
