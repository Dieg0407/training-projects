package com.dieg0407.ddd.registration.application;

import com.dieg0407.ddd.registration.domain.Account;
import com.dieg0407.ddd.registration.domain.AccountId;
import com.dieg0407.ddd.registration.domain.AccountRepository;
import com.dieg0407.ddd.registration.domain.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountRegistrationUseCaseTest {
    // mocks
    @Mock
    private AccountRepository accountRepository;

    private AccountRegistrationUseCase accountRegistrationUseCase;

    @BeforeEach
    void beforeEach() {
        accountRegistrationUseCase = new AccountRegistrationUseCaseImpl(accountRepository);
    }

    @Test
    void shouldRegisterNewAccount() {
        when(accountRepository.save(any())).thenReturn(new Account("dieg0407", new Email("test@test.com"), "1234"));
        final var accountId = accountRegistrationUseCase.execute("dieg0407", "test@test.com", "1234");
        assertThat(accountId)
                .isNotNull();
    }

    @Test
    void shouldFailIfProvidedEmailIsInvalid() {
        assertThatThrownBy(() -> {
            accountRegistrationUseCase.execute("dieg0407", "some ill formated email", "1234");
        }).hasMessageContaining("not valid");
    }
}
