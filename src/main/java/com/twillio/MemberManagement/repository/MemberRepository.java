package com.twillio.MemberManagement.repository;

import com.twillio.MemberManagement.entity.Bank;
import com.twillio.MemberManagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByBanksCountry(String country);
}
