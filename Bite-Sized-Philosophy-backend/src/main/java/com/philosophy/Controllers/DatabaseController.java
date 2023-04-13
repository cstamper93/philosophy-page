package com.philosophy.Controllers;

import com.philosophy.Daos.PhilosopherDao;
import com.philosophy.Models.Philosopher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DatabaseController {

    @Autowired
    PhilosopherDao dao;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-philosopher")
    public Philosopher addPhilosopherToDatabase(@RequestBody Philosopher philosopher) {
        return dao.addNewPhilosopher(philosopher);
    }

    @GetMapping("/favorites")
    public List<Philosopher> fetchFavorites() {
        return dao.getAllPhilosophers();
    }
}
