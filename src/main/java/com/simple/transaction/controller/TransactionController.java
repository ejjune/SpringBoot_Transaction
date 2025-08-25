package com.simple.transaction.controller;

import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.TransactionService;
import com.simple.transaction.service.model.TransactionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<Transaction> addTransaction(
      @RequestBody TransactionModel transactionModel) {
    return ResponseEntity.ok(transactionService.addTransaction(transactionModel));
  }
}
