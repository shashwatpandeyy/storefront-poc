package com.twillio.MemberManagement.dto;


import lombok.*;

@Data
@Getter
@Setter
@Builder
@ToString
public class BankDTO {
    Long id;
    String accountNumber;
    String name;
    String country;
    String holderName;
}
