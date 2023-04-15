package com.philosophy.Daos;

import com.philosophy.Models.Philosopher;

import java.util.List;

public interface PhilosopherDao {

    // CRUD METHODS
    // Interface methods are already req'd to be public, don't need that access modifier

    void insertPhilosophersFromApi(List<Philosopher> philosophers);

    List<Philosopher> getAllPhilosophersFromDb();

    List<Philosopher> getFavoritedPhilosophers(); // READ (GET)

    Philosopher getPhilosopherById(int id); // READ (GET)

    Philosopher addNewPhilosopher(Philosopher philosopher); // CREATE

    boolean removePhilosopher(int id); // DELETE

    boolean favorite(int id);

    boolean unfavorite(int id);

}
