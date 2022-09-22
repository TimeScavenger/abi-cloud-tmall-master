package com.abi.tmall.ware.client.client.falback;

import com.abi.tmall.ware.client.client.OrderFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 仓库 Feign接口降级
 *
 * @ClassName: WareFeignClientFallback
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@Slf4j
@Component
public class OrderFeignClientFallback implements FallbackFactory<OrderFeignClient> {

    @Override
    public OrderFeignClient create(Throwable cause) {
        return null;
    }
}
