package com.ecobank.soole.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecobank.soole.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    List<Account> findByVerified(String status);
    List<Account> findByAuthorities(String authority);
    Optional<Account> findByToken(String token);
}
