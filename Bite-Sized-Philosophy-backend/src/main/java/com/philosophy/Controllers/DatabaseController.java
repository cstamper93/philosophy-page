package com.philosophy.Controllers;

import com.philosophy.Daos.IdeaDao;
import com.philosophy.Daos.PhilosopherDao;
import com.philosophy.Models.Idea;
import com.philosophy.Models.Philosopher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DatabaseController {

    @Autowired
    PhilosopherDao philosopherDao;

    @Autowired
    IdeaDao ideaDao;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-philosopher")
    public Philosopher addPhilosopherToDatabase(@RequestBody Philosopher philosopher) {
        return philosopherDao.addNewPhilosopher(philosopher);
    }

    @GetMapping("/favorites")
    public List<Philosopher> fetchFavorites() {
        return philosopherDao.getAllPhilosophers();
    }

    @DeleteMapping("/favorites/{id}")
    public boolean deletePhilosopher(@PathVariable int id) {
        return philosopherDao.removePhilosopher(id);
    }

//    @GetMapping("/ideas")
//    public List<String> fetchIdeasByPhilosopherId(@RequestParam int id) {
//        return ideaDao.getIdeasByPhilosopherId(id);
//    }
}
