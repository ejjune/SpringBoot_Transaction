package com.simple.transaction.service.model;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;
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
        Arguments.of("No userId", "Missing userId", TransactionModel.builder().build()),
        Arguments.of(
            "Negative userId",
            "userId must be greater than 0",
            TransactionModel.builder().userId(-1L).build()),
        Arguments.of("No Amount", "Missing amount", TransactionModel.builder().build()),
        Arguments.of(
            "Negative Amount",
            "amount must be greater than 0",
            TransactionModel.builder().amount(BigDecimal.valueOf(-1)).build()),
        Arguments.of("No type", "Missing type", TransactionModel.builder().build()),
        Arguments.of(
            "Incorrect type",
            "Type must be either DEBIT or CREDIT",
            TransactionModel.builder().type("Incorrect").build()),
        Arguments.of(
            "No transaction date", "Missing transactionDate", TransactionModel.builder().build()),
        Arguments.of("No categoryId", "Missing categoryId", TransactionModel.builder().build()),
        Arguments.of(
            "Negative categoryId",
            "categoryId must be greater than 0",
            TransactionModel.builder().categoryId(-1L).build()));
  }
}
