package com.example.demo.repository;

import com.example.demo.entity.UsrToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrTokenRepository extends JpaRepository<UsrToken, Integer> {
}
