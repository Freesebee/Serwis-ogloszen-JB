package com.example.backniznes.WebAPI;

import com.example.backniznes.Domain.Models.AccountEntity;
import com.example.backniznes.Infrastructure.DataAccessLayer.Impl.AccountDaoImpl;
import com.example.backniznes.Infrastructure.DataAccessLayer.Impl.AdDaoImpl;
import com.example.backniznes.Domain.Models.AdEntity;
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
@RequestMapping("/ad")
public class AdController {
    AdDaoImpl adDao;
    AccountDaoImpl accountDao;

    @Autowired
    public AdController(AdDaoImpl dao, AccountDaoImpl accountDao) {
        this.adDao = dao;
        this.accountDao = accountDao;
    }

    @GetMapping("")
    ResponseEntity<Collection<AdEntity>> getAll() {
        Log.info(PersonalDataController.class.toString(),
                "wszystkie zatwierdzone ogłoszenia zostały wysłane");
        return new ResponseEntity<>(adDao.findByApproval(true), HttpStatus.OK);
    }

    @GetMapping("pending")
    ResponseEntity<Collection<AdEntity>> getAllPending() {
        Log.info(PersonalDataController.class.toString(),
                "wszystkie oczekujące ogłoszenia zostały wysłane");
        return new ResponseEntity<>(adDao.findByApproval(null), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<AdEntity> getById(@PathVariable int id) {
        try {
            AdEntity found = adDao.findById(id);
            Log.info(PersonalDataController.class.toString(),
                    "ogłoszenie o id: " + id + " zostało wysłane");
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

        if (!adDao.findAll().contains(data)) {

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;

            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            Log.info(AccountController.class.toString(),
                    "konto zalogowanego użytkownika zostało wysłane");

            AccountEntity currentAccount = accountDao.findByLogin(username);

            data.setAccountByIdAccount(currentAccount);

            adDao.save(data);

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
            AdEntity found = adDao.findById(id);
            adDao.deleteById(id);
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
        adDao.save(data);
        Log.info(PersonalDataController.class.toString(),
                "ogłoszenie o id: " + id + " zostało zaktualizowane");
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @PostMapping("/approve/{id}")
    ResponseEntity<AdEntity> add(@PathVariable int id, @RequestBody boolean isApproved) {
        try {
            AdEntity found = adDao.findById(id);

            if (found.getApproval() == null) {
                found.setApproval(isApproved);
                adDao.save(found);

                Log.info(PersonalDataController.class.toString(), "ogłoszenie o id: " + id + " zostało zaakceptowane");

                return new ResponseEntity<>(found, HttpStatus.OK);
            }

            Log.info(PersonalDataController.class.toString(), "ogłoszenie o id: " + id + " już zostało ocenione");

            return new ResponseEntity<>(found, HttpStatus.CONFLICT);
        }
        catch (NoSuchElementException e) {
            Log.warn(PersonalDataController.class.toString(), "Nie ma elementu z takim id");

            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }
}