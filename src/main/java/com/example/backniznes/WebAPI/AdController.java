package com.example.backniznes.WebAPI;

import com.example.backniznes.Infrastructure.DataAccessLayer.Impl.AdDaoImpl;
import com.example.backniznes.Domain.Models.AdEntity;
import com.example.backniznes.Infrastructure.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/ad")
public class AdController {
    AdDaoImpl dao;

    @Autowired
    public AdController(AdDaoImpl dao) {
        this.dao = dao;
    }

    @GetMapping("")
    ResponseEntity<Collection<AdEntity>> getAll() {
        Log.info(PersonalDataController.class.toString(),
                "wszystkie ogłoszenia zostały wysłane");
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<AdEntity> getById(@PathVariable int id) {
        try {
            AdEntity found = dao.findById(id);
            Log.info(PersonalDataController.class.toString(),
                    "ogłoszenie o id: " + id + "zostało wysłane");
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            Log.warn(PersonalDataController.class.toString(),
                    "Nie ma elementu z takim id");
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<AdEntity> add(@RequestBody AdEntity data) {
        if (data.getId() != 0)
            data.setId(0);
        if (!dao.findAll().contains(data)) {
            data.setApproval(false);
            dao.save(data);
            Log.info(PersonalDataController.class.toString(),
                    "ogłoszenie o nazwie: " + data.getTitle() + "zostały zapisane");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        Log.warn(PersonalDataController.class.toString(),
                "Już istnieje taki element");
        return new ResponseEntity("Już istnieje taki element", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AdEntity> delete(@PathVariable int id) {
        try {
            AdEntity found = dao.findById(id);
            dao.deleteById(id);
            Log.info(PersonalDataController.class.toString(),
            "ogłoszenie o id: " + id + "zostało usunięte");
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            Log.warn(PersonalDataController.class.toString(),
                    "Nie ma elementu z takim id");
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<AdEntity> put(@RequestBody AdEntity data, @PathVariable int id) {
        data.setId(id);
        dao.save(data);
        Log.info(PersonalDataController.class.toString(),
                "ogłoszenie o id: " + id + "zostało zaktualiuzowane");
        return new ResponseEntity(data, HttpStatus.OK);
    }
}