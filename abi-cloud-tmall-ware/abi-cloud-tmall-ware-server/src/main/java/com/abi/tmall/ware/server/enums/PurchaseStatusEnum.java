package com.abi.tmall.ware.server.enums;

import com.abi.infrastructure.web.enums.base.NameValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 采购单状态 枚举类
 *
 * @ClassName: PurchaseStatusEnum
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum PurchaseStatusEnum implements NameValueEnum<Integer> {

    CREATED(0, "新建"),
    ASSIGNED(1, "已分配"),
    RECEIVE(2, "已领取"),
    FINISH(3, "已完成"),
    HASERROR(4, "有异常");

    private final Integer value;
    private final String name;
}

