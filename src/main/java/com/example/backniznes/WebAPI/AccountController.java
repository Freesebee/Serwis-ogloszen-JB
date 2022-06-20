package com.example.backniznes.WebAPI;

import com.example.backniznes.Infrastructure.DataAccessLayer.Impl.AccountDaoImpl;
import com.example.backniznes.Domain.Models.AccountEntity;
import com.example.backniznes.Infrastructure.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("current")
    ResponseEntity<AccountEntity> getCurrentAccount() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Log.info(AccountController.class.toString(),
                "konto zalogowanego użytkownika zostało wysłane");

        return new ResponseEntity<>(dao.findByLogin(username), HttpStatus.OK);
    }

    @GetMapping("")
    ResponseEntity<Collection<AccountEntity>> getAll() {
        Log.info(AccountController.class.toString(),
                "konta użytkowników zostały wysłane");
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<AccountEntity> getById(@PathVariable int id) {
        try {
            AccountEntity found = dao.findById(id);
            Log.info(AccountController.class.toString(),
                    "konto użytkownika o id: " + id + "zostało wysłane");
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
                    "żytkownik o id: " + data + "został dodany");
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