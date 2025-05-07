package com.daniel.backend.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.daniel.backend.entity.TransactionEntity;
import com.daniel.backend.service.Transactionservice;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api")
public class TransactionsController {

    private final Transactionservice transactionServ;

    public TransactionsController(Transactionservice transactionServ) {
        this.transactionServ = transactionServ;
    }

    @GetMapping("/dashboard")
    public List<TransactionEntity> dashboard(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) BigDecimal value
    ) {
        return transactionServ.filtersTransactions(category, value);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionEntity>> transaction() {
        return transactionServ.listTransactions();
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionEntity> specificTransaction(@PathVariable Long id) {
        return transactionServ.getTransaction(id);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<TransactionEntity> updateTransaction(@Valid @PathVariable Long id, @RequestBody TransactionEntity transaction) {
        return transactionServ.upTransaction(id, transaction);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<Void> deleteTransaction(@Valid @PathVariable Long id) {
        return transactionServ.delTransaction(id);
    }

    @PostMapping("/transaction")
    public TransactionEntity addTransaction(@Valid @RequestBody TransactionEntity transaction) {
        return transactionServ.createTransaction(transaction);
    }
}
