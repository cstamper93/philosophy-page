package com.philosophy.Daos;

import com.philosophy.Models.Idea;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class JdbcIdeaDao implements IdeaDao{

    private JdbcTemplate template;

    public JdbcIdeaDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<String> getIdeasByPhilosopherId(int id) {
        List<String> quotes = new ArrayList<>();
        String sql = "SELECT quote FROM idea WHERE philosopher_id = ?;";
        SqlRowSet results = template.queryForRowSet(sql, id);
        while(results.next()) {
            quotes.add(results.getString("quote"));
        }

        return quotes;
    }

    @Override
    public Idea createNewIdea(Idea idea) {
        return null;
    }

    @Override
    public boolean deleteIdea(Idea idea) {
        return false;
    }

    @Override
    public Idea editIdea(Idea idea) {
        return null;
    }

    private Idea mapRowToIdea(SqlRowSet rowSet) {
        Idea idea = new Idea();
        idea.setQuote(rowSet.getString("quote"));
        //idea.setPhilosopher(rowSet.getString())

        return idea;
    }
}
