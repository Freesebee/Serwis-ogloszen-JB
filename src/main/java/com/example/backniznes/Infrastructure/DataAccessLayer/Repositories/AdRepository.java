package com.example.backniznes.Infrastructure.DataAccessLayer.Repositories;

import com.example.backniznes.Domain.Models.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<AdEntity,Integer> {
}