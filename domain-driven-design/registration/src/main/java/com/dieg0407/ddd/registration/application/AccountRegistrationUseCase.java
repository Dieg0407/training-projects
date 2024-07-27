package com.dieg0407.ddd.registration.application;

import com.dieg0407.ddd.registration.domain.AccountId;

public interface AccountRegistrationUseCase {
    AccountId execute(String username, String email, String rawPassword);
}
