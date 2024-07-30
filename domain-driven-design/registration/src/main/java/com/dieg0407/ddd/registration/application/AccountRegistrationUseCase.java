package com.dieg0407.ddd.registration.application;

import com.dieg0407.ddd.registration.domain.AccountId;

public interface AccountRegistrationUseCase {
    /**
     * Execution of the user story to generate a new account.
     * 
     * @param username    the username of the account to be registered
     * @param rawEmail    the unparsed email value to be created
     * @param rawPassword the unencrypted password, the stored value will be hashed
     * @throws IllegalArgumentException when the username is already registered or
     *                                  if the email is not valid.
     */
    AccountId execute(String username, String rawEmail, String rawPassword) throws IllegalArgumentException;
}
