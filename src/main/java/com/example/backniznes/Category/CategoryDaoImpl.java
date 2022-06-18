package com.example.backniznes.Category;

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
}
