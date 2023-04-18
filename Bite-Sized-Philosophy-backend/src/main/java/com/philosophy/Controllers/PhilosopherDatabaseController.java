package com.philosophy.Controllers;

import com.philosophy.Daos.IdeaDao;
import com.philosophy.Daos.PhilosopherDao;
import com.philosophy.Models.Philosopher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PhilosopherDatabaseController {

    @Autowired
    PhilosopherDao philosopherDao;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-philosopher")
    public Philosopher addPhilosopherToDatabase(@RequestBody Philosopher philosopher) {
        return philosopherDao.addNewPhilosopher(philosopher);
    }

    @GetMapping("/favorites")
    public List<Philosopher> fetchFavorites() {
        return philosopherDao.getFavoritedPhilosophers();
    }

    // Unneeded?
    @DeleteMapping("/favorites/{id}")
    public boolean deletePhilosopher(@PathVariable int id) {
        return philosopherDao.removePhilosopher(id);
    }

    @PutMapping("/add-favorites")
    public boolean addToFavs(@RequestBody Philosopher philosopher) {
        return philosopherDao.favorite(philosopher.getId());
    }

    @PutMapping("/remove-favorites")
    public boolean removeFromFavs(@RequestBody Philosopher philosopher) {
        return philosopherDao.unfavorite(philosopher.getId());
    }

}
