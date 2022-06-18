package com.example.backniznes.Category;

import com.example.backniznes.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryDaoImpl dao;

    @Autowired
    public CategoryController(CategoryDaoImpl dao) {
        this.dao = dao;
    }

    @GetMapping("")
    ResponseEntity<Collection<CategoryEntity>> getAll() {
        Log.info(CategoryController.class.toString(),
                "wszystkie categorie zostały wysłane");
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<CategoryEntity> getById(@PathVariable int id) {
        try {
            CategoryEntity found = dao.findById(id);
            Log.info(CategoryController.class.toString(),
                    "categoria o id: " + id + "została wysłana");
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            Log.warn(CategoryController.class.toString(),
                    "Nie ma elementu z takim id");
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<CategoryEntity> add(@RequestBody CategoryEntity data) {
        if (data.getId() != 0)
            data.setId(0);
        if (!dao.findAll().contains(data)) {
            dao.save(data);
            Log.info(CategoryController.class.toString(),
                    "dcategoria : " + data + "zostały wysłane");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        Log.warn(CategoryController.class.toString(),
                "Już istnieje taki element");
        return new ResponseEntity("Już istnieje taki element", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CategoryEntity> delete(@PathVariable int id) {
        try {
            CategoryEntity found = dao.findById(id);
            dao.deleteById(id);
            Log.info(CategoryController.class.toString(),
                    "usunięto dane osobowe o id: " + id);
            return new ResponseEntity<>(found, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            Log.warn(CategoryController.class.toString(),
                    "Już istnieje taki element");
            return new ResponseEntity("Nie ma elementu z takim id", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<CategoryEntity> put(@RequestBody CategoryEntity data, @PathVariable int id) {
        data.setId(id);
        dao.save(data);
        Log.info(CategoryController.class.toString(),
                "zaktualizowano dane osobowe o id: " + id);
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
