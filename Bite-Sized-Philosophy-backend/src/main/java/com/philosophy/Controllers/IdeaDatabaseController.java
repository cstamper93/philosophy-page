package com.philosophy.Controllers;

import com.philosophy.Daos.IdeaDao;
import com.philosophy.Models.Idea;
import com.philosophy.Services.PhilosopherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class IdeaDatabaseController {

    @Autowired
    PhilosopherService service;

    @Autowired
    IdeaDao ideaDao;

    @GetMapping("/ideas/{id}")
    public List<String> getAllIdeasByIdFromDb(@PathVariable int id) {
        return ideaDao.getIdeasByPhilosopherId(id);
    }

}
