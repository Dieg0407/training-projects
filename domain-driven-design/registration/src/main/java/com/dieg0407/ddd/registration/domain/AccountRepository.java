package com.dieg0407.ddd.registration.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, AccountId> {

  Optional<Account> findByUsername(String username);
}
