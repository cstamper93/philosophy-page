package com.philosophy.Daos;

import com.philosophy.Models.Philosopher;

import java.util.List;

public interface PhilosopherDao {

    // CRUD METHODS
    // Interface methods are already req'd to be public, don't need that access modifier

    List<Philosopher> getAllPhilosophers(); // READ (GET)

    Philosopher getPhilosopherById(int id); // READ (GET)

    Philosopher addNewPhilosopher(Philosopher philosopher); // CREATE


}
