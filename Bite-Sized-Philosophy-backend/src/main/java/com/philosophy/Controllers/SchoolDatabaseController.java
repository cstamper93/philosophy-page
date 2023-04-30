package com.philosophy.Controllers;

import com.philosophy.Daos.SchoolDao;
import com.philosophy.Models.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SchoolDatabaseController {

    @Autowired
    SchoolDao schoolDao;

    @GetMapping("/schools/{id}")
    public List<String> getSchoolsWithId(@PathVariable int id) {
        return schoolDao.getSchoolsFromDbWithPhilosopherId(id);
    }

}
