package com.simple.transaction.controller;

import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.TransactionService;
import com.simple.transaction.service.request.TransactionRequest;
import com.simple.transaction.service.response.TransactionResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
      @RequestBody TransactionRequest transactionRequest) {
    return ResponseEntity.ok(transactionService.addTransaction(transactionRequest));
  }

  @GetMapping
  public ResponseEntity<List<TransactionResponse>> getTransactions(
      @RequestParam @NotNull @Positive Long userId) {
    return ResponseEntity.ok(transactionService.getTransactions(userId));
  }
}
