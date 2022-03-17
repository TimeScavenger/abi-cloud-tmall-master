package com.abi.tmall.coupon.server.controller.console;

import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.coupon.dao.entity.MemberPrice;
import com.abi.tmall.coupon.server.service.MemberPriceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MemberPriceController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description: 商品会员价格
 */
@Api(tags = "商品会员价格")
@Slf4j
@RestController
@RequestMapping("/console/member-price")
public class MemberPriceController {

    @Autowired
    private MemberPriceService memberPriceService;

    /**
     * 添加 会员价格
     *
     * @param memberPrice
     * @return
     */
    @PostMapping("/save")
    public ApiResponse<Boolean> saveMemberPrice(@RequestBody MemberPrice memberPrice) {
        return ApiResponse.result(memberPriceService.save(memberPrice));
    }

}
