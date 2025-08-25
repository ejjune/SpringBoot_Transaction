package com.simple.transaction.service;

import com.simple.transaction.service.entity.Transaction;
import com.simple.transaction.service.model.TransactionModel;

public interface TransactionService {

    Transaction addTransaction(TransactionModel transactionModel);
}
