package com.example.backniznes.Category;

import com.example.backniznes.PersonalData.PersonalDataEntity;

import java.util.List;

public interface CategoryDao {
    public List<CategoryEntity> findAll();
    public CategoryEntity findById(int id);
    public void save(CategoryEntity category);
    public void deleteById(int id);
}
