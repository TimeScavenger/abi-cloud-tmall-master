package com.abi.tmall.product.server.enums;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: AttributeTypeEnum
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description: 属性类型
 */
@Getter
@AllArgsConstructor
public enum SpuPublishTypeEnum {

    NEW_SPU(0, "新建"),
    SPU_UP(1, "商品上架"),
    SPU_DOWN(2, "商品下架");

    private static final Set<SpuPublishTypeEnum> ALL = EnumSet.allOf(SpuPublishTypeEnum.class);
    private final Integer code;
    private final String label;

    /**
     * 根据code获取到枚举的label
     *
     * @param code
     * @return
     */
    public static String getLabelByCode(Integer code) {
        return ALL.stream()
                .filter(enums -> enums.getCode().equals(code))
                .findFirst()
                .map(SpuPublishTypeEnum::getLabel)
                .orElseThrow(() -> new BusinessException(ResultCode.ENUM_DATA_ERROR.code(), "无效状态枚举类: " + code + "未匹配到相关值！"));
    }

    /**
     * 根据code获取枚举对象
     *
     * @param code
     * @return
     */
    public static SpuPublishTypeEnum getEnumByCode(Integer code) {
        return ALL.stream()
                .filter(enums -> enums.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new BusinessException(ResultCode.ENUM_DATA_ERROR.code(), "无效状态枚举类: " + code + "未匹配到相关值！"));
    }
//
//    /**
//     * 将枚举转换成list格式
//     *
//     * @return
//     */
//    public static List<EnumEntity> toList() {
//        return ALL.stream()
//                .map(item -> {
//                    EnumEntity codeLabel = new EnumEntity();
//                    codeLabel.setCode(item.getCode());
//                    codeLabel.setLabel(item.getLabel());
//                    return codeLabel;
//                })
//                .collect(Collectors.toList());
//    }

    /**
     * 将枚举转换成map格式
     *
     * @return
     */
    public static Map<Integer, String> toMap() {
        return ALL.stream()
                .collect(Collectors.toMap(SpuPublishTypeEnum::getCode, SpuPublishTypeEnum::getLabel));
    }

}