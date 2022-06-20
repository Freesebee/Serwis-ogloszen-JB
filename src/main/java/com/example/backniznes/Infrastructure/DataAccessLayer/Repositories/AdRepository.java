package com.example.backniznes.Infrastructure.DataAccessLayer.Repositories;

import com.example.backniznes.Domain.Models.AccountEntity;
import com.example.backniznes.Domain.Models.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<AdEntity,Integer> {
    public Optional<List<AdEntity>> findByApproval(boolean approval);
}