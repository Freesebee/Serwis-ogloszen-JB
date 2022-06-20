package com.example.backniznes.Infrastructure.DataAccessLayer.Interfaces;

import com.example.backniznes.Domain.Models.AdEntity;

import java.util.List;
import java.util.Optional;

public interface AdDao {
    public List<AdEntity> findAll();
    public List<AdEntity> findByApproval(boolean approval);
    public AdEntity findById(int id);
    public void save(AdEntity ad);
    public void deleteById(int id);
}
