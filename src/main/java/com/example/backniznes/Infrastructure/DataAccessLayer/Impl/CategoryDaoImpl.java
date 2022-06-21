package com.example.backniznes.Infrastructure.DataAccessLayer.Impl;

import com.example.backniznes.Domain.Models.CategoryEntity;
import com.example.backniznes.Infrastructure.DataAccessLayer.Interfaces.CategoryDao;
import com.example.backniznes.Infrastructure.DataAccessLayer.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    CategoryRepository repository;

    @Override
    public List<CategoryEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public CategoryEntity findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(CategoryEntity category) {
        repository.save(category);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<CategoryEntity> findByIsActive(Boolean isActive) {
        return repository.findAllByIsActive(isActive);
    }
}
