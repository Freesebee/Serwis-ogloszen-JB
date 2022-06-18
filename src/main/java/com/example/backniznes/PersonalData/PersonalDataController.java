package com.example.backniznes.PersonalData;

import com.example.backniznes.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/personalData")
public class PersonalDataController {
    PersonalDataDaoImpl dao;

    @Autowired
    public PersonalDataController(PersonalDataDaoImpl dao) {
        this.dao = dao;
    }

    @GetMapping("")
    ResponseEntity<Collection<PersonalDataEntity>> getAll() {
        Log.info(PersonalDataController.class.toString(),
                "wszystkie dane osobowe zostały wysłane");
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<PersonalDataEntity> getById(@PathVariable int id) {
        try {
            PersonalDataEntity found = dao.findById(id);
            Log.info(PersonalDataController.class.toString(),
                    "dane osobowe o id: " + id + "zostały wysłane");
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            Log.warn(PersonalDataController.class.toString(),
                    "Nie ma elementu z takim id");
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<PersonalDataEntity> add(@RequestBody PersonalDataEntity data) {
        if (data.getId() != 0)
            data.setId(0);
        if (!dao.findAll().contains(data)) {
            dao.save(data);
            Log.info(PersonalDataController.class.toString(),
                    "dane osobowe : " + data + "zostały wysłane");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        Log.warn(PersonalDataController.class.toString(),
                "Już istnieje taki element");
        return new ResponseEntity("Już istnieje taki element", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<PersonalDataEntity> delete(@PathVariable int id) {
        try {
            PersonalDataEntity found = dao.findById(id);
            dao.deleteById(id);
            Log.info(PersonalDataController.class.toString(),
                    "usunięto dane osobowe o id: " + id);
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            Log.warn(PersonalDataController.class.toString(),
                    "Już istnieje taki element");
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<PersonalDataEntity> put(@RequestBody PersonalDataEntity data, @PathVariable int id) {
        data.setId(id);
        dao.save(data);
        Log.info(PersonalDataController.class.toString(),
                "zaktualizowano dane osobowe o id: " + id);
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
