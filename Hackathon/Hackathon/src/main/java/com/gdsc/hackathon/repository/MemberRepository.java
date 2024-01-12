package com.gdsc.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<com.gdsc.hackathon.domain.Member, Long> {
    Optional<com.gdsc.hackathon.domain.Member> findByEmail(String email);
    Optional<com.gdsc.hackathon.domain.Member> findByName(String name);
    Optional<com.gdsc.hackathon.domain.Member> findUserById(Long id);

    boolean existsByEmail(String email);
}
