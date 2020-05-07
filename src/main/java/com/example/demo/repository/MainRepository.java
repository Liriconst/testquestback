package com.example.demo.repository;

import com.example.demo.model.Registration;
import com.example.demo.model.UsrToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MainRepository extends JpaRepository<Registration, Integer> {
    Boolean existsByUsername(String username);
    Registration findByUsername(String username);
}