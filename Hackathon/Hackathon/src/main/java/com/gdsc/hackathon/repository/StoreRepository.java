package com.gdsc.hackathon.repository;

import com.gdsc.hackathon.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByNameContaining(String name);  //상호명으로 가게를 검색하는 메소드 추가
    Optional<Store> findById(Long id);  // 아이디로 가게를 찾는 메소드 추가
}

