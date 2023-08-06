package com.twillio.MemberManagement.dto;

import com.twillio.MemberManagement.entity.KycLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@Setter
public class MemberDTO {
    Long id;
    String name;
    KycLevel kycLevel;
    String mobileNumber;
    Boolean mobileVerified;
    List<BankDTO> banks;
    Date createdDate;
}
