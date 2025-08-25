package com.simple.transaction.service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransactionModel {

    private Long id;

    private Long userId;

    private BigDecimal amount;

    private String type;

    private LocalDateTime transactionDate;

    private Long categoryId;

}
