package com.abi.tmall.ware.server.enums;

import com.abi.base.foundation.code.ResultCode;
import com.abi.base.foundation.entity.BaseEnumEntity;
import com.abi.base.foundation.exception.BusinessException;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * @ClassName: PurchaseDetailStatusEnum
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description: 采购需求状态枚举
 */
@Getter
@AllArgsConstructor
public enum PurchaseDetailStatusEnum {

    CREATED(0, "新建"),
    ASSIGNED(1, "已分配"),
    BUYING(2, "正在采购"),
    FINISH(3, "已完成"),
    HASERROR(4, "采购失败");

    private static final Set<PurchaseDetailStatusEnum> ALL = EnumSet.allOf(PurchaseDetailStatusEnum.class);
    private final Integer code;
    private final String label;

    /**
     * 根据code获取到枚举的label
     *
     * @param code
     * @return
     */
    public static String getLabelByCode(Integer code) {
        if (code != null) {
            for (PurchaseDetailStatusEnum enums : values()) {
                if (enums.getCode().intValue() == code.intValue()) {
                    return enums.label;
                }
            }
        }
        return "";
    }

    /**
     * 根据code获取枚举对象
     *
     * @param code
     * @return
     */
    public static PurchaseDetailStatusEnum getEnumByCode(Integer code) {
        return ALL.stream()
                .filter(enums -> enums.code.equals(code))
                .findAny()
                .orElseThrow(() -> new BusinessException(ResultCode.ENUM_DATA_ERROR.code(), "有效无效状态枚举类: " + code + "未匹配到相关值！"));
    }

    /**
     * 将枚举转换成list格式
     * 这样前台遍历的时候比较容易，列如 下拉框 后台调用toList方法，你就可以得到 code 和 label 了
     * Lists.newArrayList()其实和new ArrayList()几乎一模一样, 唯一它帮你做的(其实是javac帮你做的), 就是自动推导(不是"倒")尖括号里的数据类型.
     *
     * @return
     */
    public static List<BaseEnumEntity> toList() {
        List<BaseEnumEntity> codeLabelList = Lists.newArrayList();
        for (PurchaseDetailStatusEnum airlineTypeEnum : PurchaseDetailStatusEnum.values()) {
            BaseEnumEntity codeLabel = new BaseEnumEntity();
            codeLabel.setCode(airlineTypeEnum.getCode());
            codeLabel.setLabel(airlineTypeEnum.getLabel());
            codeLabelList.add(codeLabel);
        }
        return codeLabelList;
    }

    /**
     * 将枚举转换成map格式
     *
     * @return
     */
    public static Map<Integer, String> toMap() {
        Map<Integer, String> codeLabelMap = new HashMap<>();
        for (PurchaseDetailStatusEnum next : ALL) {
            codeLabelMap.put(next.code, next.label);
        }
        return codeLabelMap;
    }

}

