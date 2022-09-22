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
 * @ClassName: ProductInitCodeEnum
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description: 商品服务初始化Code
 */
@Getter
@AllArgsConstructor
public enum ProductInitCodeEnum {

    PMS_CATEGORY_INIT_CODE(1, "商品分类", 11000000000000000L),
    PMS_BRAND_INIT_CODE(2, "商品品牌", 12000000000000000L),
    PMS_GROUP_INIT_CODE(3, "属性分组", 13000000000000000L),
    PMS_ATTRIBUTE_INIT_CODE(4, "商品属性", 14000000000000000L),
    PMS_SPU_INIT_CODE(5, "SPU", 15000000000000000L),
    PMS_SKU_INIT_CODE(6, "SKU", 16000000000000000L);

    private static final Set<ProductInitCodeEnum> ALL = EnumSet.allOf(ProductInitCodeEnum.class);
    private final Integer code;
    private final String label;
    private final Long desc;

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
                .map(ProductInitCodeEnum::getLabel)
                .orElseThrow(() -> new BusinessException(ResultCode.ENUM_DATA_ERROR.code(), "无效状态枚举类: " + code + "未匹配到相关值！"));
    }

    /**
     * 根据code获取枚举对象
     *
     * @param code
     * @return
     */
    public static ProductInitCodeEnum getEnumByCode(Integer code) {
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
                .collect(Collectors.toMap(ProductInitCodeEnum::getCode, ProductInitCodeEnum::getLabel));
    }

}