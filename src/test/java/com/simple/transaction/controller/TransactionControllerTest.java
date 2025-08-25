package com.simple.transaction.controller;

import com.simple.transaction.service.TransactionService;
import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.model.TransactionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

class TransactionControllerTest {

  TransactionController transactionController;

  @BeforeEach
  void init() {
    TransactionService transactionService = Mockito.mock(TransactionService.class);

    Mockito.when(transactionService.addTransaction(Mockito.any()))
        .thenReturn(Transaction.builder().build());

    transactionController = new TransactionController(transactionService);
  }

  @Test
  void shouldReturnTransactionWhenInputIsValid() {
    ResponseEntity<Transaction> transactionResponseEntity =
        transactionController.addTransaction(TransactionModel.builder().build());

    Assertions.assertNotNull(transactionResponseEntity.getBody());
  }
}
