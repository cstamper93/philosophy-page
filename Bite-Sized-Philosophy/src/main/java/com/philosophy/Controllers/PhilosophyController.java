package com.philosophy.Controllers;

import com.philosophy.Models.Philosopher;
import com.philosophy.Services.PhilosopherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // keeps track of api endpoints
@CrossOrigin
public class PhilosophyController {

    @Autowired // allows SpringBoot to inject the service
    PhilosopherService service;

    @GetMapping(path="/philosophers") // sidesteps the extra stuff when using @RequestMapping
    public List<Philosopher> getAllPhilosophersFromApi() {
        return service.getAllPhilosophers();
    }

}
