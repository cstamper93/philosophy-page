package com.philosophy.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdeaResults {

    @JsonProperty("results")
    private Idea[] ideas;

    public Idea[] getIdeas() {
        return ideas;
    }

    public void setIdeas(Idea[] ideas) {
        this.ideas = ideas;
    }
}
