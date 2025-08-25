package com.simple.transaction.serviceimpl;

import com.simple.transaction.repository.entity.CategoryRepository;
import com.simple.transaction.repository.entity.TransactionRepository;
import com.simple.transaction.repository.entity.UserRepository;
import com.simple.transaction.service.TransactionService;
import com.simple.transaction.service.entity.Category;
import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.entity.Users;
import com.simple.transaction.service.model.TransactionModel;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TransactionServiceImplTest {

  TransactionService transactionService;

  private CategoryRepository categoryRepository;

  private TransactionRepository transactionRepository;

  private UserRepository userRepository;

  @BeforeEach
  void init() {
    categoryRepository = Mockito.mock(CategoryRepository.class);

    transactionRepository = Mockito.mock(TransactionRepository.class);

    userRepository = Mockito.mock(UserRepository.class);

    transactionService =
        new TransactionServiceImpl(categoryRepository, transactionRepository, userRepository);
  }

  @Test
  void shouldReturnValue() {
    Mockito.when(categoryRepository.findById(1L))
        .thenReturn(Optional.of(Category.builder().id(1L).build()));

    Mockito.when(userRepository.findById(1L))
        .thenReturn(Optional.of(Users.builder().id(1L).build()));

    Mockito.when(transactionRepository.save(Mockito.any()))
        .thenReturn(Transaction.builder().id(1L).build());

    Transaction transaction =
        transactionService.addTransaction(
            TransactionModel.builder().categoryId(1L).userId(1L).build());

    assertEquals(1L, transaction.getId());
  }

  @Test
  void shouldReturnExceptionWhenCategoryRepositoryFails() {
    Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

    Mockito.when(userRepository.findById(1L))
        .thenReturn(Optional.of(Users.builder().id(1L).build()));

    Mockito.when(transactionRepository.save(Mockito.any()))
        .thenReturn(Transaction.builder().id(1L).build());

    RuntimeException runtimeException =
        assertThrows(
            RuntimeException.class,
            () ->
                transactionService.addTransaction(
                    TransactionModel.builder().categoryId(1L).userId(1L).build()));

    assertEquals("Category Not Found", runtimeException.getMessage());
  }

  @Test
  void shouldReturnExceptionWhenUserRepositoryFails() {
    Mockito.when(categoryRepository.findById(1L))
        .thenReturn(Optional.of(Category.builder().id(1L).build()));

    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

    Mockito.when(transactionRepository.save(Mockito.any()))
        .thenReturn(Transaction.builder().id(1L).build());

    RuntimeException runtimeException =
        assertThrows(
            RuntimeException.class,
            () ->
                transactionService.addTransaction(
                    TransactionModel.builder().categoryId(1L).userId(1L).build()));

    assertEquals("User Not Found", runtimeException.getMessage());
  }
}
