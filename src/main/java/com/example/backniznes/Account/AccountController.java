package com.example.backniznes.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    AccountRepository repository;

    @Autowired
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    public ResponseEntity<AccountEntity> test(){
        AccountEntity test = new AccountEntity();
        repository.save(test);
        return new ResponseEntity(test, HttpStatus.OK);
    }

    @GetMapping("")
    ResponseEntity<Collection<AccountEntity>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<AccountEntity> getById(@PathVariable int id) {
        try {
            AccountEntity found = repository.findById(id).orElseThrow();
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<AccountEntity> add(@RequestBody AccountEntity data) {
        if (data.getId() != 0)
            data.setId(0);
        if (! repository.findAll().contains(data)) {
            repository.save(data);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return new ResponseEntity("Już istnieje taki element", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AccountEntity> delete(@PathVariable int id) {
        try {
            AccountEntity found = repository.findById(id).orElseThrow();
            repository.deleteById(id);
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<AccountEntity> put(@RequestBody AccountEntity data, @PathVariable int id) {
        data.setId(id);
        repository.save(data);
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
