package com.twillio.MemberManagement.controller;

import com.twillio.MemberManagement.dto.MemberDTO;
import com.twillio.MemberManagement.entity.Member;
import com.twillio.MemberManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
public class MemberController {


    @Autowired
    MemberService memberService;

    //MEMBER RELATED API
    @PostMapping()
    public ResponseEntity<Object> createMember(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMember(@PathVariable("id") Long memberId, @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberService.updateMember(memberId, memberDTO));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable("id") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping ()
    public ResponseEntity<Object> getAllMember() {
        return ResponseEntity.accepted().body(memberService.findAll());
    }

    @GetMapping ("/bank/{country}")
    public ResponseEntity<Object> getAllMemberHavingBankAccountInCountry(@PathVariable("country") String country) {
        return ResponseEntity.accepted().body(memberService.findMembersByCountry(country));
    }


}
