package com.example.backniznes.Ad;

import com.example.backniznes.PersonalData.PersonalDataEntity;

import java.util.List;

public interface AdDao {
    public List<AdEntity> findAll();
    public AdEntity findById(int id);
    public void save(AdEntity ad);
    public void deleteById(int id);
}
