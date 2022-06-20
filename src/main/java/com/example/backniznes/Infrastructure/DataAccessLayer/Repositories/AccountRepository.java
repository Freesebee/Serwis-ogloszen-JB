package com.example.backniznes.Infrastructure.DataAccessLayer.Repositories;

import com.example.backniznes.Domain.Models.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {
    public Optional<AccountEntity> findByLogin(String login);
}