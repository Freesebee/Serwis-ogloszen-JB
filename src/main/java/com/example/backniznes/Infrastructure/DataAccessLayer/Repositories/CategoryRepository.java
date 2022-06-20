package com.example.backniznes.Infrastructure.DataAccessLayer.Repositories;

import com.example.backniznes.Domain.Models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
