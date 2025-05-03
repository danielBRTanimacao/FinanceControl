package com.daniel.backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    // Pesquisar como funciona
    public List<TransactionEntity> filtersTransactions(String category, BigDecimal value) { 
        return transactionRepo.findAll().stream()
        .filter(t -> category == null || 
                     (t.getCategory() != null && 
                      t.getCategory().getName().equalsIgnoreCase(category)))
        .filter(t -> value == null || value.compareTo(t.getValue()) == 0)
        .collect(Collectors.toList());
    }

    public ResponseEntity<List<TransactionEntity>> listTransactions() {
        List<TransactionEntity> transactions = transactionRepo.findAll();
        return ResponseEntity.ok(transactions);
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
