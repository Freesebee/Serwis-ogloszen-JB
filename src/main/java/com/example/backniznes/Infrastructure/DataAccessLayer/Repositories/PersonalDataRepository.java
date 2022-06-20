package com.example.backniznes.Infrastructure.DataAccessLayer.Repositories;

import com.example.backniznes.Domain.Models.PersonalDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalDataEntity,Integer> {
}