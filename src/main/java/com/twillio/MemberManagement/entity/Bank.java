package com.twillio.MemberManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Bank {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Long id;
    @Column(name = "ACCOUNT_NUMBER")
    String accountNumber;
    @Column(name = "NAME")
    String name;
    @Column(name = "COUNTRY")
    String country;
    @Column(name = "HOLDER_NAME")
    String holderName;
    @ManyToOne
    Member member;
}
