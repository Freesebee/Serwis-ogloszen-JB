package com.example.backniznes.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {
    public Optional<AccountEntity> findByLogin(String login);
}