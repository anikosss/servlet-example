package com.example.servlet.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode
public class ProductEntity {

    long id;
    String title;
    double price;

}
