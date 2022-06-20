package com.example.backniznes.Infrastructure.DataAccessLayer.Interfaces;

import com.example.backniznes.Domain.Models.PersonalDataEntity;

import java.util.List;

public interface PersonalDataDao {
    public List<PersonalDataEntity> findAll();
    public PersonalDataEntity findById(int id);
    public void save(PersonalDataEntity personalData);
    public void deleteById(int id);
}
