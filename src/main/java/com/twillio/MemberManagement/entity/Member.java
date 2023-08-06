package com.twillio.MemberManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "KYC_LEVEL")
    KycLevel kycLevel;

    @Column(name = "MOBILE_NUMBER")
    String mobileNumber;

    @Column(name = "MOBILE_VERIFIED")
    Boolean mobileVerified;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    List<Bank> banks;

    @Column(name = "CREATED_DATE", updatable = false)
    Date createdDate;

    @PrePersist
    protected void prePersist() {
        if (this.createdDate == null || this.id == null) createdDate = new Date();
    }

}
