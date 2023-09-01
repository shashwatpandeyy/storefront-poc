package com.storefront.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Item {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int discountPercentage;
    private String pictureLocation;
}
