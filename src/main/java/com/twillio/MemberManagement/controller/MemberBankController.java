package com.twillio.MemberManagement.controller;

import com.twillio.MemberManagement.dto.BankDTO;
import com.twillio.MemberManagement.entity.Bank;
import com.twillio.MemberManagement.entity.Member;
import com.twillio.MemberManagement.service.MemberService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members/{memberId}/banks")
public class MemberBankController {

    @Autowired
    MemberService memberService;

    @PostMapping()
    public ResponseEntity<Object> addBank(@PathVariable("memberId") Long memberId, @RequestBody BankDTO bankDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.addBank(memberId, bankDTO));
    }

    @PutMapping("{bankId}")
    public ResponseEntity<Object> updateBank(@PathVariable("memberId") Long memberId, @PathVariable("bankId") Long bankId,
                                             @RequestBody BankDTO bankDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberService.updateBank(memberId, bankId, bankDTO));
    }

    @DeleteMapping("{bankId}")
    public ResponseEntity<Object> deleteBank(@PathVariable("memberId") Long memberId, @PathVariable("bankId") Long bankId) {
        memberService.removeBank(memberId, bankId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping ("")
    public ResponseEntity<Object> getAllBank(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.accepted().body(memberService.getAllBanks(memberId));
    }
}
