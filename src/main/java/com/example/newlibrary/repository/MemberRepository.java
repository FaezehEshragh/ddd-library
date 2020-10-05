package com.example.newlibrary.repository;

import com.example.newlibrary.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member,String> {
    Optional<Member> findByNumber(String memberNumber);
}
