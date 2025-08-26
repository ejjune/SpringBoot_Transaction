package com.simple.transaction.service.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransactionResponse {

  private Long id;

  private BigDecimal amount;

  private String type;

  private LocalDateTime transactionDate;

  private String category;

  private LocalDateTime createdDate;

  private LocalDateTime updatedDate;
}
