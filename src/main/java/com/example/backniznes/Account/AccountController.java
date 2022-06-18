package com.example.backniznes.Account;

import com.example.backniznes.Log;
import com.example.backniznes.PersonalData.PersonalDataController;
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

    AccountDaoImpl dao;

    @Autowired
    public AccountController(AccountDaoImpl dao) {
        this.dao = dao;
    }

    @GetMapping("")
    ResponseEntity<Collection<AccountEntity>> getAll() {
        Log.info(AccountController.class.toString(),
                "wszystkie użytkownicy zostały wysłane");
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<AccountEntity> getById(@PathVariable int id) {
        try {
            AccountEntity found = dao.findById(id);
            Log.info(AccountController.class.toString(),
                    "użytkownik o id: " + id + "zostało wysłane");
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            Log.warn(PersonalDataController.class.toString(),
                    "Nie ma elementu z takim id");
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<AccountEntity> add(@RequestBody AccountEntity data) {
        if (data.getId() != 0)
            data.setId(0);
        if (! dao.findAll().contains(data)) {
            dao.save(data);
            Log.info(AccountController.class.toString(),
                    "użytkownik o id: " + data + "został dodany");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        Log.warn(PersonalDataController.class.toString(),
                "Nie ma elementu z takim id");
        return new ResponseEntity("Już istnieje taki element", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AccountEntity> delete(@PathVariable int id) {
        try {
            AccountEntity found = dao.findById(id);
            dao.deleteById(id);
            Log.info(AccountController.class.toString(),
                    "użytkownik o id: " + id + "został usunięty");
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            Log.warn(PersonalDataController.class.toString(),
                    "Nie ma elementu z takim id");
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<AccountEntity> put(@RequestBody AccountEntity data, @PathVariable int id) {
        data.setId(id);
        dao.save(data);
        Log.info(AccountController.class.toString(),
                "użytkownik o id: " + id + "został zaktualizowany");
        return new ResponseEntity(data, HttpStatus.OK);
    }
}