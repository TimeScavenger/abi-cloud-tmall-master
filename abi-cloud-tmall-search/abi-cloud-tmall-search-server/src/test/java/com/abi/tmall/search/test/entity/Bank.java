package com.abi.tmall.search.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bank {
    private Long accountNumber;
    private Long balance;
    private String firstname;
    private String lastname;
    private Integer age;// 价格
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}