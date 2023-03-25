package com.philosophy.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.philosophy.Services.PhilosopherService;

public class PhilosopherResults {

    @JsonProperty("results")
    private Philosopher[] philosophers;

    public Philosopher[] getPhilosophers() {
        return philosophers;
    }

    public void setPhilosophers(Philosopher[] philosophers) {
        this.philosophers = philosophers;
    }
}
