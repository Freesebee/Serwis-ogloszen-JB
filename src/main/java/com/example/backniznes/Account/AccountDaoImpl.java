package com.example.backniznes.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDaoImpl implements AccountDao{
    @Autowired
    AccountRepository repository;

    @Override
    public List<AccountEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public AccountEntity findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(AccountEntity personalData) {
        repository.save(personalData);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
