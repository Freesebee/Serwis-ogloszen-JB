package com.example.backniznes.PersonalData;

import com.example.backniznes.Ad.AdEntity;
import com.example.backniznes.Ad.AdRepository;
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
    PersonalDataRepository repository;

    @Autowired
    public PersonalDataController(PersonalDataRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    ResponseEntity<Collection<PersonalDataEntity>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<PersonalDataEntity> getById(@PathVariable int id) {
        try {
            PersonalDataEntity found = repository.findById(id).orElseThrow();
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<PersonalDataEntity> add(@RequestBody PersonalDataEntity data) {
        if (data.getId() != 0)
            data.setId(0);
        if (!repository.findAll().contains(data)) {
            repository.save(data);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return new ResponseEntity("Już istnieje taki element", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<PersonalDataEntity> delete(@PathVariable int id) {
        try {
            PersonalDataEntity found = repository.findById(id).orElseThrow();
            repository.deleteById(id);
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<PersonalDataEntity> put(@RequestBody PersonalDataEntity data, @PathVariable int id) {
        data.setId(id);
        repository.save(data);
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
