package com.example.backniznes.Infrastructure.DataAccessLayer.Impl;

import com.example.backniznes.Domain.Models.AdEntity;
import com.example.backniznes.Infrastructure.DataAccessLayer.Interfaces.AdDao;
import com.example.backniznes.Infrastructure.DataAccessLayer.Repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdDaoImpl implements AdDao {
    @Autowired
    AdRepository repository;

    @Override
    public List<AdEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public AdEntity findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(AdEntity ad) {
        repository.save(ad);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
