package com.simple.transaction.service;

import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.request.TransactionRequest;
import com.simple.transaction.service.response.TransactionResponse;
import java.util.List;

public interface TransactionService {

  Transaction addTransaction(TransactionRequest transactionRequest);

  List<TransactionResponse> getTransactions(Long userId);
}
