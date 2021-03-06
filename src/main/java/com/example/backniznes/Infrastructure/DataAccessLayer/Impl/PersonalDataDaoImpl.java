package com.example.backniznes.Infrastructure.DataAccessLayer.Impl;

import com.example.backniznes.Domain.Models.PersonalDataEntity;
import com.example.backniznes.Infrastructure.DataAccessLayer.Interfaces.PersonalDataDao;
import com.example.backniznes.Infrastructure.DataAccessLayer.Repositories.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalDataDaoImpl implements PersonalDataDao {
    @Autowired
    PersonalDataRepository repository;

    @Override
    public List<PersonalDataEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public PersonalDataEntity findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(PersonalDataEntity personalData) {
        repository.save(personalData);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
