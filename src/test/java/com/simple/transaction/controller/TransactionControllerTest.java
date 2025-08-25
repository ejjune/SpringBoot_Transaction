package com.simple.transaction.controller;


import com.simple.transaction.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TransactionControllerTest {

    TransactionController transactionController;

    @BeforeEach
    void init() {
        TransactionService transactionService = Mockito.mock(TransactionService.class);
        transactionController = new TransactionController(transactionService);
    }

    @Test
    void shouldReturnTransactionWhenInputIsValid() {

    }

}