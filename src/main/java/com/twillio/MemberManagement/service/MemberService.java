package com.twillio.MemberManagement.service;

import com.twillio.MemberManagement.dto.BankDTO;
import com.twillio.MemberManagement.dto.MemberDTO;
import com.twillio.MemberManagement.entity.Bank;
import com.twillio.MemberManagement.entity.Member;
import com.twillio.MemberManagement.exception.MemberException;
import com.twillio.MemberManagement.repository.BankRepository;
import com.twillio.MemberManagement.repository.MemberRepository;
import com.twillio.MemberManagement.util.ObjectConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BankRepository bankRepository;
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = ObjectConversion.memberDtoToMember(memberDTO);
        member = memberRepository.save(member);
        return ObjectConversion.memberToMemberDto(member);
    }

    public MemberDTO updateMember(Long memberId, MemberDTO memberDTO) {
        assert memberDTO != null;
        assertMemberExists(memberId);
        Member member = ObjectConversion.memberDtoToMember(memberDTO);
        member = memberRepository.save(member);
        return ObjectConversion.memberToMemberDto(member);
    }

    public void deleteMember(Long memberId) {
        assertMemberExists(memberId);
        memberRepository.deleteById(memberId);
    }

    public List<MemberDTO> findAll() {
        return ObjectConversion.membersToMemberDtos(memberRepository.findAll());
    }

    private void assertMemberExists(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(HttpStatus.NOT_FOUND, "Resource with id " + memberId + " not exists.");
        }
    }

    private void assertBankExists(Long bankId) {
        if (!bankRepository.existsById(bankId)) {
            throw new MemberException(HttpStatus.NOT_FOUND, "Resource with id " + bankId + " not exists.");
        }
    }

    //Bank operation related method
    public BankDTO addBank(Long memberId, BankDTO bankDTO) {
        assertMemberExists(memberId);
        Bank bank = ObjectConversion.bankDtoToBank(bankDTO);
        bank.setMember(Member.builder().id(memberId).build());
        bank = bankRepository.save(bank);
        return ObjectConversion.bankToBankDto(bank);
    }

    public BankDTO updateBank(Long memberId, Long bankId, BankDTO bankDTO) {
        assertMemberExists(memberId);
        assertBankExists(bankId);

        Bank bankToUpdate = bankRepository.getReferenceById(bankId);

        if (!bankToUpdate.getMember().getId().equals(memberId)) {
            throw new MemberException(HttpStatus.BAD_REQUEST, "Bank not associated with this Member");
        }

        bankToUpdate.setCountry(bankDTO.getCountry());
        bankToUpdate.setAccountNumber(bankDTO.getAccountNumber());
        bankToUpdate.setName(bankDTO.getName());
        bankToUpdate.setHolderName(bankDTO.getHolderName());
        bankToUpdate = bankRepository.save(bankToUpdate);
        return ObjectConversion.bankToBankDto(bankToUpdate);
    }

    public void removeBank(Long memberId, Long bankId) {
        assertMemberExists(memberId);
        assertBankExists(bankId);

        Bank bankToUpdate = bankRepository.getReferenceById(bankId);

        if (!bankToUpdate.getMember().getId().equals(memberId)) {
            throw new MemberException(HttpStatus.BAD_REQUEST, "Bank not associated with this Member");
        }

        bankRepository.deleteById(bankId);
    }

    public List<BankDTO> getAllBanks(Long memberId) {
        assertMemberExists(memberId);
        return ObjectConversion.banksToBankDtos(bankRepository.findByMemberId(memberId));
    }

    public List<MemberDTO> findMembersByCountry(String country) {
        return ObjectConversion.membersToMemberDtos(memberRepository.findByBanksCountry(country));
    }
}
