package com.storefront.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Order {

    private String id;
    private Long itemId;
    private String fullName;
    private String address;
    private String email;
    private String phoneNumber;
    private String creditCardNumber;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
}
