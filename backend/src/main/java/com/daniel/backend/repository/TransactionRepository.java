package com.daniel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.backend.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>{
}
