package com.dieg0407.ddd.registration.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountRegistrationUseCaseTest {
    private AccountRegistrationUseCase accountRegistrationUseCase;

    @BeforeEach
    void beforeEach() {
        accountRegistrationUseCase = new AccountRegistrationUseCaseImpl();
    }

    @Test
    void shouldRegisterNewAccount() {
        final var accountId = accountRegistrationUseCase.execute("dieg0407", "test@test.com", "1234");
        assertThat(accountId)
                .isNotNull();
    }
}
