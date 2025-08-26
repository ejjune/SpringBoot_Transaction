package com.simple.transaction.service.request;

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

class TransactionRequestTest {

  private Validator validator;

  @BeforeEach
  void init() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("invalidTransactionModels")
  void shouldFail(String title, String object, TransactionRequest transactionRequest) {
    Set<ConstraintViolation<TransactionRequest>> violationSet =
        validator.validate(transactionRequest);

    assertThat(violationSet).anyMatch(v -> v.getMessage().equalsIgnoreCase(object));
  }

  public static Stream<Arguments> invalidTransactionModels() {
    return Stream.of(
        Arguments.of("No userId", "Missing userId", TransactionRequest.builder().build()),
        Arguments.of(
            "Negative userId",
            "userId must be greater than 0",
            TransactionRequest.builder().userId(-1L).build()),
        Arguments.of("No Amount", "Missing amount", TransactionRequest.builder().build()),
        Arguments.of(
            "Negative Amount",
            "amount must be greater than 0",
            TransactionRequest.builder().amount(BigDecimal.valueOf(-1)).build()),
        Arguments.of("No type", "Missing type", TransactionRequest.builder().build()),
        Arguments.of(
            "Incorrect type",
            "Type must be either DEBIT or CREDIT",
            TransactionRequest.builder().type("Incorrect").build()),
        Arguments.of(
            "No transaction date", "Missing transactionDate", TransactionRequest.builder().build()),
        Arguments.of("No categoryId", "Missing categoryId", TransactionRequest.builder().build()),
        Arguments.of(
            "Negative categoryId",
            "categoryId must be greater than 0",
            TransactionRequest.builder().categoryId(-1L).build()));
  }
}
