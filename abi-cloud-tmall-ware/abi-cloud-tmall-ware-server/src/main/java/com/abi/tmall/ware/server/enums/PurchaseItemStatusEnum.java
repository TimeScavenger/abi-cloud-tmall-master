package com.abi.tmall.ware.server.enums;

import com.abi.infrastructure.web.enums.base.NameValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 采购项状态 枚举类
 *
 * @ClassName: PurchaseItemStatusEnum
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum PurchaseItemStatusEnum implements NameValueEnum<Integer> {

    CREATED(0, "新建"),
    ASSIGNED(1, "已分配"),
    BUYING(2, "正在采购"),
    FINISH(3, "已完成"),
    HASERROR(4, "采购失败");

    private final Integer value;
    private final String name;
}

