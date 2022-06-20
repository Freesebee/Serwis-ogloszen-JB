package com.example.backniznes.Infrastructure.DataAccessLayer.Interfaces;

import com.example.backniznes.Domain.Models.AccountEntity;

import java.util.List;

public interface AccountDao {
    public List<AccountEntity> findAll();
    public AccountEntity findById(int id);
    public void save(AccountEntity personalData);
    public void deleteById(int id);
    public AccountEntity findByLogin(String login);
}
