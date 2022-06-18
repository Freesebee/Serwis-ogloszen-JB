package com.example.backniznes.Account;

import com.example.backniznes.PersonalData.PersonalDataEntity;

import java.util.List;

public interface AccountDao {
    public List<AccountEntity> findAll();
    public AccountEntity findById(int id);
    public void save(AccountEntity personalData);
    public void deleteById(int id);
}
