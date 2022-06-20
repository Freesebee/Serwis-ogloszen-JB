package com.example.backniznes.Infrastructure.DataAccessLayer.Interfaces;

import com.example.backniznes.Domain.Models.AdEntity;

import java.util.List;

public interface AdDao {
    public List<AdEntity> findAll();
    public AdEntity findById(int id);
    public void save(AdEntity ad);
    public void deleteById(int id);
}
