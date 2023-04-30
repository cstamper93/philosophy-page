package com.philosophy.Daos;

import com.philosophy.Models.School;
import com.philosophy.Models.SchoolDatabase;

import java.util.List;

public interface SchoolDao {

    List<SchoolDatabase> schoolsToBeInserted(List<School> incomingSchools);
    Integer getIdFromPhilosopherName(String name);
    void insertSchoolsFromApi(List<School> incomingSchools);
    List<School> getCurrentSchoolsFromDb();
    List<School> getSchoolsFromDbWithPhilosopherId(int id);

}
