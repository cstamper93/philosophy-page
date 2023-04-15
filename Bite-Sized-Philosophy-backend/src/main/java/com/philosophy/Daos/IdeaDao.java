package com.philosophy.Daos;

import com.philosophy.Models.Idea;

import java.util.List;

public interface IdeaDao {

    void insertIdeasFromApiToDb(List<Idea[]> allIdeas);

    Integer getIdByPhilosopherName(String name);

    List<String> getIdeasByPhilosopherId(int id);

    Idea createNewIdea(Idea idea);

    boolean deleteIdea(Idea idea);

    Idea editIdea(Idea idea);
}
