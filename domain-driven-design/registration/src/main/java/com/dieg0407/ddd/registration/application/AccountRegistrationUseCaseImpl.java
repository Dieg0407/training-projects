package com.dieg0407.ddd.registration.application;

import com.dieg0407.ddd.registration.domain.Account;
import com.dieg0407.ddd.registration.domain.AccountId;
import com.dieg0407.ddd.registration.domain.AccountRepository;
import com.dieg0407.ddd.registration.domain.Email;
import org.springframework.stereotype.Service;

@Service
class AccountRegistrationUseCaseImpl implements AccountRegistrationUseCase {
    private final AccountRepository accountRepository;

    public AccountRegistrationUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountId execute(String username, String rawEmail, String rawPassword) throws IllegalArgumentException {
        final var email = new Email(rawEmail);
        final var account = new Account(username, email, rawPassword);

        if (accountRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username %s is already registered".formatted(username));
        }

        return accountRepository.save(account).getId();
    }
}
