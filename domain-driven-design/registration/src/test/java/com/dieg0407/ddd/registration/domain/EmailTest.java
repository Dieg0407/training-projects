package com.dieg0407.ddd.registration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.dieg0407.ddd.registration.TestTypes;

@Tag(TestTypes.UNIT)
public class EmailTest {

  @Test
  void shouldCreateANewEmailObject() {
    final var email = new Email("some-email@gmail.com");

    assertThat(email).isNotNull().satisfies(e -> "some-email@gmail.com".equals(e.getValidEmail()));
  }

  @ParameterizedTest
  @CsvSource({"incorrect01", "test-test02"})
  void shouldFailWhenCreatingAnIllFormattedEmail(String incorrectEmails) {

    assertThatThrownBy(() -> new Email(incorrectEmails))
        .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("is not valid");
  }
}
