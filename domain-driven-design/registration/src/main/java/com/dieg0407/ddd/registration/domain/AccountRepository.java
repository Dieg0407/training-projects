package com.dieg0407.ddd.registration.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, AccountId>  {
}
