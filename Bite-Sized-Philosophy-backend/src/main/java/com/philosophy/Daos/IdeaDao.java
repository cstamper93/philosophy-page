package com.philosophy.Daos;

import com.philosophy.Models.Idea;

import java.util.List;

public interface IdeaDao {

    List<String> getIdeasByPhilosopherId(int id);

    Idea createNewIdea(Idea idea);

    boolean deleteIdea(Idea idea);

    Idea editIdea(Idea idea);
}
