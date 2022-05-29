package com.example.backniznes.Ad;

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
    AdRepository repository;

    @Autowired
    public AdController(AdRepository repository) {
        this.repository = repository;
    }

//    @GetMapping("/test")
    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public ResponseEntity<AdEntity> test() {
        AdEntity test = new AdEntity();
        test.setAccountByIdAccount(test.getAccountByIdAccount());
        repository.save(test);
        return new ResponseEntity(test, HttpStatus.OK);
    }

    @GetMapping("")
    ResponseEntity<Collection<AdEntity>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<AdEntity> getById(@PathVariable int id) {
        try {
            AdEntity found = repository.findById(id).orElseThrow();
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<AdEntity> add(@RequestBody AdEntity data) {
        if (data.getId() != 0)
            data.setId(0);
        if (!repository.findAll().contains(data)) {
            repository.save(data);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return new ResponseEntity("Już istnieje taki element", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AdEntity> delete(@PathVariable int id) {
        try {
            AdEntity found = repository.findById(id).orElseThrow();
            repository.deleteById(id);
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<AdEntity> put(@RequestBody AdEntity data, @PathVariable int id) {
        data.setId(id);
        repository.save(data);
        return new ResponseEntity(data, HttpStatus.OK);
    }
}