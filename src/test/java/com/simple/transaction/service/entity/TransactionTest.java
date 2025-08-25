package com.simple.transaction.service.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class TransactionTest {

  Transaction transaction;

  @Test
  void shouldReturnPrePersist() {
    transaction = new Transaction();

    assertNull(transaction.getCreatedDate());
    assertNull(transaction.getUpdatedDate());
    transaction.prePersist();
    assertNotNull(transaction.getCreatedDate());
    assertNotNull(transaction.getUpdatedDate());
  }

  @Test
  void shouldReturnPreUpdate() {
    transaction = new Transaction();

    assertNull(transaction.getCreatedDate());
    assertNull(transaction.getUpdatedDate());
    transaction.preUpdate();
    assertNull(transaction.getCreatedDate());
    assertNotNull(transaction.getUpdatedDate());
  }
}
