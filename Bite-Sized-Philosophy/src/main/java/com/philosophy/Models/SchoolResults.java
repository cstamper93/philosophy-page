package com.philosophy.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolResults {

    @JsonProperty("results")
    private School[] schools;

    public School[] getSchools() {
        return schools;
    }

    public void setSchools(School[] schools) {
        this.schools = schools;
    }
}
