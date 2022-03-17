package com.abi.tmall.search.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;// 产品名称
    private String images;// 图片
    private Integer price;// 价格
    private String desc;// 描述
}