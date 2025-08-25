package com.simple.transaction.service.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TransactionModelTest {

  private Validator validator;

  @BeforeEach
  void init() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("invalidTransactionModels")
  void shouldFail(String title, String object, TransactionModel transactionModel) {
    Set<ConstraintViolation<TransactionModel>> violationSet = validator.validate(transactionModel);

    assertThat(violationSet).anyMatch(v -> v.getMessage().toString().equalsIgnoreCase(object));
  }

  public static Stream<Arguments> invalidTransactionModels() {
    return Stream.of(
        Arguments.of("No userId", "userId", TransactionModel.builder().build()),
        Arguments.of("Negative userId", "userId", TransactionModel.builder().userId(-1L).build()),
        Arguments.of("No Amount", "amount", TransactionModel.builder().build()),
        Arguments.of(
            "Negative Amount",
            "amount",
            TransactionModel.builder().amount(BigDecimal.valueOf(-1)).build()),
        Arguments.of("No type", "type", TransactionModel.builder().build()),
        Arguments.of(
            "Incorrect type", "type", TransactionModel.builder().type("Incorrect").build()),
        Arguments.of("No transaction date", "transactionDate", TransactionModel.builder().build()),
        Arguments.of(
            "Past transaction date",
            "transactionDate",
            TransactionModel.builder()
                .transactionDate(LocalDateTime.of(1990, Month.APRIL, 01, 01, 01))
                .build()),
        Arguments.of("No categoryId", "transactionDate", TransactionModel.builder().build()),
        Arguments.of(
            "Negative categoryId",
            "transactionDate",
            TransactionModel.builder().categoryId(-1L).build()));
  }
}
