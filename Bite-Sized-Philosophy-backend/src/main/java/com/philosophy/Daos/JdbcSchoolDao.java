package com.philosophy.Daos;

import com.philosophy.Models.School;
import com.philosophy.Models.SchoolDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class JdbcSchoolDao implements SchoolDao{

    private JdbcTemplate template;

    public JdbcSchoolDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<SchoolDatabase> schoolsToBeInserted(List<School> incomingSchools) {
        List<School> currentSchools = getCurrentSchoolsFromDb();
        List<School> schoolsToInsert = new ArrayList<>();
        for(School incoming : incomingSchools) {
            int counter = 0;
            for(School current : currentSchools) {
                if(incoming.getSchool().equals(current.getSchool())) {
                    counter++;
                }
            }
            if(counter == 0) {
                //add this school to a list of schools to be inserted
                schoolsToInsert.add(incoming);
            }
        }
        // Convert list of school obj to list of school database obj
        List<SchoolDatabase> schoolDatabaseList = new ArrayList<>();
        //
        for(School school : schoolsToInsert) {
            List<String> associatedPhilosophers = Arrays.asList(school.getPhilosophers());
            for(String philosopher : associatedPhilosophers) {
                SchoolDatabase schoolDatabase = new SchoolDatabase();
                schoolDatabase.setPhilosopherName(philosopher);
                schoolDatabase.setSchool(school.getSchool());
                schoolDatabaseList.add(schoolDatabase);
            }
        }
        return schoolDatabaseList;
    }

    @Override
    public Integer getIdFromPhilosopherName(String name) {
        String sql = "SELECT id FROM philosopher WHERE philosopher_name = ?;";
        return template.queryForObject(sql, Integer.class, name);
    }

    @Override
    public void insertSchoolsFromApi(List<School> incomingSchools) {
        String sql = "INSERT INTO school (school_name, philosopher_id) VALUES(?, ?);";
        List<SchoolDatabase> schoolDatabases = schoolsToBeInserted(incomingSchools);
        for(SchoolDatabase school : schoolDatabases) {
            template.update(sql, school.getSchool(), getIdFromPhilosopherName(school.getPhilosopherName()));
        }
    }

    @Override
    public List<School> getCurrentSchoolsFromDb() {
        List<School> currentSchools = new ArrayList<>();
        List<SchoolDatabase> schoolDatabaseList = new ArrayList<>();
        String sql = "SELECT school_name, philosopher_name FROM school " +
                "JOIN philosopher ON school.philosopher_id = philosopher.id;";
        SqlRowSet results = template.queryForRowSet(sql);
        while(results.next()) {
            SchoolDatabase schoolDatabase = mapRowToSchoolDatabaseObj(results);
            schoolDatabaseList.add(schoolDatabase);
        }
        // Gather list of philosopher names
        List<String> schoolNames = new ArrayList<>();
        for(SchoolDatabase obj : schoolDatabaseList) {
            if(!schoolNames.contains(obj.getSchool())) {
                schoolNames.add(obj.getSchool());
            }
        }
        // use nested for loops to go through and create an array for each philosopher,
        // collecting the associated school names.
        // use the setters to set the name and array of schools for each school object
        for(String name : schoolNames) {
            School school = new School();
            school.setSchool(name);
            List<String> philosopherNames = new ArrayList<>();
            for(SchoolDatabase obj : schoolDatabaseList) {
                if(obj.getSchool().equals(name)) {
                    philosopherNames.add(obj.getPhilosopherName());
                }
            }
            String[] namesArr = new String[philosopherNames.size()];
            int counter = 0;
            // Had to do it this way to avoid class exception, couldn't use toArray() method
            // b/c it produced Object[] instead of String[]
            for(String str : philosopherNames) {
                namesArr[counter] = str;
                counter++;
            }
            school.setPhilosophers(namesArr);
            currentSchools.add(school);
        }
        return currentSchools;
    }

    @Override
    public List<String> getSchoolsFromDbWithPhilosopherId(int id) {
        List<String> schoolsList = new ArrayList<>();
        String sql = "SELECT school_name FROM school WHERE philosopher_id = ?;";
        SqlRowSet results = template.queryForRowSet(sql, id);
        while(results.next()) {
            schoolsList.add(results.getString("school_name"));
        }
        return schoolsList;
    }

    private SchoolDatabase mapRowToSchoolDatabaseObj(SqlRowSet rowSet) {
        SchoolDatabase schoolDatabase = new SchoolDatabase();
        schoolDatabase.setSchool(rowSet.getString("school_name"));
        schoolDatabase.setPhilosopherName(rowSet.getString("philosopher_name"));
        return schoolDatabase;
    }
}
