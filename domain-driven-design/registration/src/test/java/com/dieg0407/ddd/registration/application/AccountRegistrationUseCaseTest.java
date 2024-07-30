package com.dieg0407.ddd.registration.application;

import com.dieg0407.ddd.registration.domain.AccountRepository;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class AccountRegistrationUseCaseTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountRegistrationUseCase accountRegistrationUseCase;

    @Test
    void shouldRegisterNewAccount() {
        final var accountId = accountRegistrationUseCase.execute("dieg0407", "test@test.com", "1234");
        assertThat(accountId)
                .isNotNull();

        final var savedAccount = accountRepository.findById(accountId);
        assertThat(savedAccount)
                .isPresent();
    }

    @Test
    void shouldFailIfAttemptedToRegisterAnAccountWithExistingUsername() {
        final var repeatedUsername = "username01";
        final var createdId = accountRegistrationUseCase.execute(repeatedUsername, "test@test.com", "1234");
        assertThat(createdId).isNotNull();

        assertThatThrownBy(() -> accountRegistrationUseCase.execute(repeatedUsername, "test@test.com", "1234"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Username %s is already registered".formatted(repeatedUsername));
    }
}
