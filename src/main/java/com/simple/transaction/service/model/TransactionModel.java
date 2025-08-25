package com.simple.transaction.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransactionModel {

  @NotNull(message = "Missing userId")
  @Positive(message = "userId must be greater than 0")
  private Long userId;

  @NotNull(message = "Missing amount")
  @Positive(message = "amount must be greater than 0")
  private BigDecimal amount;

  @NotBlank(message = "Missing type")
  @Pattern(regexp = "(?i)DEBIT|CREDIT", message = "Type must be either DEBIT or CREDIT")
  private String type;

  @NotNull(message = "Missing transactionDate")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime transactionDate;

  @NotNull(message = "Missing categoryId")
  @Positive(message = "categoryId must be greater than 0")
  private Long categoryId;
}
