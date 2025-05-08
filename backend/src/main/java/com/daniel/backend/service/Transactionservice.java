package com.daniel.backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.daniel.backend.exceptions.InvalidCredencialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.daniel.backend.entity.TransactionEntity;
import com.daniel.backend.entity.UserEntity;
import com.daniel.backend.repository.TransactionRepository;
import com.daniel.backend.repository.UserRepository;

@Service
public class Transactionservice {

    private final TransactionRepository transactionRepository;
    public Transactionservice(TransactionRepository transactionRepo, UserRepository userRepo) {
        this.transactionRepository = transactionRepo;
    }

    public List<TransactionEntity> filtersTransactions(String category, BigDecimal value) {
        return transactionRepository.findAll().stream()
        .filter(t -> category == null || 
                     (t.getCategory() != null && 
                      t.getCategory().getName().equalsIgnoreCase(category)))
        .filter(t -> value == null || value.compareTo(t.getValue()) == 0)
        .collect(Collectors.toList());
    }

    public ResponseEntity<List<TransactionEntity>> listTransactions() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions);
    }

    public ResponseEntity<TransactionEntity> getTransaction(Long id) {
        return transactionRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<TransactionEntity> upTransaction(Long id, TransactionEntity transaction) {
        return transactionRepository.findById(id).map(exists -> {
            exists.setTitle(transaction.getTitle());
            exists.setValue(transaction.getValue());
            exists.setEarnedDate(transaction.getEarnedDate());
            exists.setCategory(transaction.getCategory());
            exists.setUpdatedDate(LocalDateTime.now());
            return ResponseEntity.ok(transactionRepository.save(exists));
        }).orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<Void> delTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public TransactionEntity createTransaction(TransactionEntity transaction) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (user != null) {
            transaction.setUser(user);
        }

        if (transaction.getEarnedDate() == null) {
            transaction.setEarnedDate(LocalDateTime.now().toLocalDate());
        }
        
        transaction.setUpdatedDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
