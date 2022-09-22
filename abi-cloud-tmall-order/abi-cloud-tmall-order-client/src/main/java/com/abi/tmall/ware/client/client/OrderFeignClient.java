package com.abi.tmall.ware.client.client;

import com.abi.tmall.ware.client.client.falback.OrderFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 仓库 Feign接口
 *
 * @ClassName: WareFeignClient
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@FeignClient(name = "abi-cloud-tmall-order", fallbackFactory = OrderFeignClientFallback.class)
public interface OrderFeignClient {

}