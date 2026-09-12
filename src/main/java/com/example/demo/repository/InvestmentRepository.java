package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}