package com.philosophy.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Philosopher {

    @JsonProperty("id")
    private int id;
    private int philosopherId;
    private String name;
    private String photo;
    private String nationality;
    private String era;

    public Philosopher(int id, int philosopherId, String name, String photo, String nationality, String era) {
        this.id = id;
        this.philosopherId = philosopherId;
        this.name = name;
        this.photo = photo;
        this.nationality = nationality;
        this.era = era;
    }

    public Philosopher() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhilosopherId() {
        return philosopherId;
    }

    public void setPhilosopherId(int philosopherId) {
        this.philosopherId = philosopherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                ", philosopherId=" + philosopherId +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", nationality='" + nationality + '\'' +
                ", era='" + era + '\'' +
                '}';
    }

}
