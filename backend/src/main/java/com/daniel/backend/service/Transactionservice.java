package com.daniel.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.daniel.backend.entity.TransactionEntity;
import com.daniel.backend.repository.TransactionRepository;

@Service
public class Transactionservice {

    private final TransactionRepository transactionRepo;

    public Transactionservice(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<TransactionEntity> listTransactions() {
        return transactionRepo.findAll();
    }
    
    public ResponseEntity<TransactionEntity> getTransaction(Long id) {
        return transactionRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<TransactionEntity> upTransaction(Long id, TransactionEntity transaction) {
        return transactionRepo.findById(id).map(exists -> {
            exists.setValue(transaction.getValue());
            exists.setEarnedDate(transaction.getEarnedDate());
            exists.setUpdated_Date(LocalDateTime.now());
            TransactionEntity updated = transactionRepo.save(exists);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<Void> delTransaction(Long id) {
        if (transactionRepo.existsById(id)) {
            transactionRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public TransactionEntity createTransaction(TransactionEntity transaction) {
        return transactionRepo.save(transaction);
    }
}
