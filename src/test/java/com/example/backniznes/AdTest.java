package com.example.backniznes;

import com.example.backniznes.Ad.AdDaoImpl;
import com.example.backniznes.Ad.AdEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
public class AdTest {
    @Autowired
    private AdDaoImpl dao;

    private AdEntity ad;
    private AdEntity adBackup;

    @Test
    void returnAdEntittyByIdFromDataSource() {
        AdEntity entity = dao.findById(ad.getId());
        Assertions.assertEquals(entity.getId(), ad.getId());
    }

    @Test
    void updateRowInDatabase() {
        ad.setTitle("test");
        dao.save(ad);
        Assertions.assertEquals(ad, getTestedAdFromDataSource());
    }

    @BeforeEach
    void getFirstAd() {
        try {
            ad = getTestedAdFromDataSource();
            adBackup = new AdEntity(ad);
        } catch (NoSuchElementException e) {
            dao.save(new AdEntity());
            ad = getTestedAdFromDataSource();
            adBackup = new AdEntity(ad);
        }
    }

    @AfterEach
    void setToPreviousState(){
        ad = new AdEntity(adBackup);
        dao.save(ad);
    }

    private AdEntity getTestedAdFromDataSource() {
        return dao.findAll().stream().findFirst().orElseThrow();
    }
}