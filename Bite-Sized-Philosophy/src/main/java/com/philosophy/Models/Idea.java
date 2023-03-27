package com.philosophy.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Idea {

    @JsonProperty("quote")
    private String quote;
    @JsonProperty("author")
    private String philosopher;

    public Idea(String quote, String philosopher) {
        this.quote = quote;
        this.philosopher = philosopher;
    }

    public Idea(){}

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getPhilosopher() {
        return philosopher;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "quote='" + quote + '\'' +
                ", philosopherId=" + philosopher +
                '}';
    }

}
