package com.simple.transaction.serviceimpl;

import com.simple.transaction.repository.entity.CategoryRepository;
import com.simple.transaction.repository.entity.TransactionRepository;
import com.simple.transaction.repository.entity.UserRepository;
import com.simple.transaction.service.TransactionService;
import com.simple.transaction.service.entity.Category;
import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.entity.Users;
import com.simple.transaction.service.model.TransactionModel;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final CategoryRepository categoryRepository;

  private final TransactionRepository transactionRepository;

  private final UserRepository userRepository;

  public TransactionServiceImpl(
      CategoryRepository categoryRepository,
      TransactionRepository transactionRepository,
      UserRepository userRepository) {
    this.categoryRepository = categoryRepository;
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Transaction addTransaction(TransactionModel transactionModel) {

    Category category =
        categoryRepository
            .findById(transactionModel.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category Not Found"));

    Users users =
        userRepository
            .findById(transactionModel.getUserId())
            .orElseThrow(() -> new RuntimeException("User Not Found"));

    Transaction transaction =
        Transaction.builder()
            .users(users)
            .transactionDate(transactionModel.getTransactionDate())
            .type(transactionModel.getType())
            .amount(transactionModel.getAmount())
            .category(category)
            .build();

    return transactionRepository.save(transaction);
  }
}
