package com.kong.king.spring.youtuber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kong.king.spring.youtuber.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long	> {

}
