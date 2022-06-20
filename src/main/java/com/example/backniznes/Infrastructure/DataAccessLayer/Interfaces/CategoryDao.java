package com.example.backniznes.Infrastructure.DataAccessLayer.Interfaces;

import com.example.backniznes.Domain.Models.CategoryEntity;

import java.util.List;

public interface CategoryDao {
    public List<CategoryEntity> findAll();
    public CategoryEntity findById(int id);
    public void save(CategoryEntity category);
    public void deleteById(int id);
    public List<CategoryEntity> findByIsActive(Boolean isActive);
}
