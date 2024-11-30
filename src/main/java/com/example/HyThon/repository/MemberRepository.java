package com.example.HyThon.repository;

import com.example.HyThon.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);
}
