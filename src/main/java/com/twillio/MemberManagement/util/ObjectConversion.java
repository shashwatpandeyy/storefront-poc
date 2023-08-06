package com.twillio.MemberManagement.util;

import com.twillio.MemberManagement.dto.BankDTO;
import com.twillio.MemberManagement.dto.MemberDTO;
import com.twillio.MemberManagement.entity.Bank;
import com.twillio.MemberManagement.entity.Member;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectConversion {
    public static Member memberDtoToMember(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .kycLevel(memberDTO.getKycLevel())
                .mobileNumber(memberDTO.getMobileNumber())
                .mobileVerified(memberDTO.getMobileVerified())
                .createdDate(memberDTO.getCreatedDate())
                .banks(memberDTO.getBanks() != null ?
                        memberDTO
                        .getBanks()
                        .stream()
                        .map(ObjectConversion::bankDtoToBank)
                        .collect(Collectors.toList())
                        : null)
                .build();

    }

    public static Bank bankDtoToBank(BankDTO bankDTO) {
        return Bank.builder()
                .id(bankDTO.getId())
                .name(bankDTO.getName())
                .holderName(bankDTO.getHolderName())
                .country(bankDTO.getCountry())
                .accountNumber(bankDTO.getAccountNumber())
                .build();
    }

    public static MemberDTO memberToMemberDto(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .kycLevel(member.getKycLevel())
                .mobileNumber(member.getMobileNumber())
                .mobileVerified(member.getMobileVerified())
                .createdDate(member.getCreatedDate())
                .banks(member.getBanks() != null ?
                        member
                        .getBanks()
                        .stream()
                        .map(ObjectConversion::bankToBankDto)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }

    public static BankDTO bankToBankDto(Bank bank) {
        return BankDTO.builder()
                .id(bank.getId())
                .name(bank.getName())
                .holderName(bank.getHolderName())
                .country(bank.getCountry())
                .accountNumber(bank.getAccountNumber())
                .build();
    }

    public static List<BankDTO> banksToBankDtos(List<Bank> banks) {
        return banks
                .stream()
                .map(ObjectConversion::bankToBankDto)
                .collect(Collectors.toList());
    }

    public static List<MemberDTO> membersToMemberDtos(List<Member> members) {
        return members
                .stream()
                .map(ObjectConversion::memberToMemberDto)
                .collect(Collectors.toList());
    }
}
