package com.simple.transaction.serviceimpl;

import com.simple.transaction.repository.entity.CategoryRepository;
import com.simple.transaction.repository.entity.TransactionRepository;
import com.simple.transaction.repository.entity.UserRepository;
import com.simple.transaction.service.TransactionService;
import com.simple.transaction.service.entity.Category;
import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.entity.Users;
import com.simple.transaction.service.request.TransactionRequest;
import com.simple.transaction.service.response.TransactionResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
  public Transaction addTransaction(TransactionRequest transactionRequest) {

    Category category =
        categoryRepository
            .findById(transactionRequest.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category Not Found"));

    Users users =
        userRepository
            .findById(transactionRequest.getUserId())
            .orElseThrow(() -> new RuntimeException("User Not Found"));

    Transaction transaction =
        Transaction.builder()
            .users(users)
            .transactionDate(transactionRequest.getTransactionDate())
            .type(transactionRequest.getType())
            .amount(transactionRequest.getAmount())
            .category(category)
            .build();

    return transactionRepository.save(transaction);
  }

  @Override
  public List<TransactionResponse> getTransactions(Long userId) {
    return transactionRepository.findByUsersId(userId).stream()
        .map(
            t ->
                TransactionResponse.builder()
                    .amount(t.getAmount())
                    .category(t.getCategory().getName())
                    .createdDate(t.getCreatedDate())
                    .id(t.getId())
                    .transactionDate(t.getTransactionDate())
                    .type(t.getType())
                    .updatedDate(t.getUpdatedDate())
                    .build())
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
