package com.dieg0407.ddd.registration.application;

import com.dieg0407.ddd.registration.domain.AccountId;
import org.springframework.stereotype.Service;

@Service
class AccountRegistrationUseCaseImpl implements AccountRegistrationUseCase {
    @Override
    public AccountId execute(String username, String email, String rawPassword) {
        return null;
    }
}
