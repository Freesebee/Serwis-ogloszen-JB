package com.example.backniznes.Ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdDaoImpl implements AdDao{
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
