package com.philosophy.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class School {

    @JsonProperty("name")
    private String school;
    @JsonProperty("philosophers")
    private String[] philosophers;

    public School(String school, String[] philosophers) {
        this.school = school;
        this.philosophers = philosophers;
    }

    public School(){}

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String[] getPhilosophers() {
        return philosophers;
    }

    @Override
    public String toString() {
        return "School{" +
                "school='" + school + '\'' +
                ", philosopherIds=" + philosophers +
                '}';
    }

}
