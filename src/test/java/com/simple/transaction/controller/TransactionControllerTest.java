package com.simple.transaction.controller;

import com.simple.transaction.service.TransactionService;
import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.request.TransactionRequest;
import com.simple.transaction.service.response.TransactionResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

class TransactionControllerTest {

  TransactionController transactionController;

  TransactionService transactionService;

  Validator validator;

  @BeforeEach
  void init() {
    transactionService = Mockito.mock(TransactionService.class);

    Mockito.when(transactionService.addTransaction(Mockito.any()))
        .thenReturn(Transaction.builder().build());

    Mockito.when(transactionService.getTransactions(Mockito.any()))
        .thenReturn(List.of(TransactionResponse.builder().build()));

    transactionController = new TransactionController(transactionService);

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void shouldReturnTransactionWhenInputIsValid() {
    ResponseEntity<Transaction> transactionResponseEntity =
        transactionController.addTransaction(TransactionRequest.builder().build());

    Assertions.assertNotNull(transactionResponseEntity.getBody());
  }

  @Test
  void shouldReturnListOfTransaction() {
    ResponseEntity<List<TransactionResponse>> responseEntity =
        transactionController.getTransactions(1L);

    Assertions.assertNotNull(responseEntity.getBody());
  }

  @Test
  void shouldReturnExceptionInReturnListTransactionWhenUserIsNull() throws NoSuchMethodException {
    Set<ConstraintViolation<TransactionController>> violations =
        validator
            .forExecutables()
            .validateParameters(
                transactionController,
                TransactionController.class.getMethod("getTransactions", Long.class),
                new Object[] {null});

    Assertions.assertFalse(violations.isEmpty());
    Assertions.assertEquals("must not be null", violations.iterator().next().getMessage());
  }

  @Test
  void shouldReturnExceptionInReturnListTransactionWhenUserIsNegative()
      throws NoSuchMethodException {
    Set<ConstraintViolation<TransactionController>> violations =
        validator
            .forExecutables()
            .validateParameters(
                transactionController,
                TransactionController.class.getMethod("getTransactions", Long.class),
                new Object[] {-1L});

    Assertions.assertFalse(violations.isEmpty());
    Assertions.assertEquals("must be greater than 0", violations.iterator().next().getMessage());
  }
}
