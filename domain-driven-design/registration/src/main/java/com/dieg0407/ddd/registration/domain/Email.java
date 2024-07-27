package com.dieg0407.ddd.registration.domain;

import org.springframework.util.Assert;

public class Email {
    private String validEmail;

    Email() {

    }

    public Email(String rawEmail) {
        Assert.notNull(rawEmail, "The 'email' can't be null");

    }
}
