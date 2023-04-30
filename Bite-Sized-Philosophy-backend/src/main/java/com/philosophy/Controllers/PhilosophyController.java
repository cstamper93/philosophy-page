package com.philosophy.Controllers;

import com.philosophy.Daos.IdeaDao;
import com.philosophy.Daos.PhilosopherDao;
import com.philosophy.Models.Idea;
import com.philosophy.Models.Philosopher;
import com.philosophy.Models.School;
import com.philosophy.Services.PhilosopherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // keeps track of api endpoints
@CrossOrigin // running backend on port 9000, frontend on 8080
public class PhilosophyController {

    @Autowired // allows SpringBoot to inject the service
    PhilosopherService service;

    @Autowired
    PhilosopherDao philosopherDao;

    @Autowired
    IdeaDao ideaDao;

    @GetMapping(path="/philosophers") // sidesteps the extra stuff when using @RequestMapping
    public List<Philosopher> getAllPhilosophersFromDb() {
        service.loadAllPhilosophers();
        return philosopherDao.getAllPhilosophersFromDb();
    }

    @PostMapping("/add-ideas")
    public void insertIdeasFromApiIntoDb() {
        service.loadAllIdeas();
    }

    @PostMapping("/add-schools")
    public void insertSchoolsFromApiIntoDb() {
        service.loadAllSchools();
    }

//    @GetMapping("/schools")
//    public List<School> getAllSchoolsFromApi() {
//        return service.loadAllSchools();
//    }

}
