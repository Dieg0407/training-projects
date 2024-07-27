package com.dieg0407.ddd.registration.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.springframework.util.Assert;

import java.util.Objects;

@Entity
public class Account {
    @EmbeddedId
    private AccountId id;
    private String username;
    private String email;
    private String password;

    Account() {}

    public Account(String username, String email, String password) {
        Assert.notNull(username, "The 'username' can't be null");
        Assert.notNull(email, "The 'email' can't be null");
        Assert.notNull(password, "The 'password' can't be null");

        this.id = new AccountId();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
