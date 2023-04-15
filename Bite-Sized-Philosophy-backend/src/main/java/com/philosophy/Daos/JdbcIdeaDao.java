package com.philosophy.Daos;

import com.philosophy.Models.Idea;
import com.philosophy.Services.PhilosopherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIdeaDao implements IdeaDao{

    private JdbcTemplate template;

    public JdbcIdeaDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void insertIdeasFromApiToDb(List<Idea[]> allIdeas) {
        String sql = "INSERT INTO idea (quote, philosopher_id) " +
                "VALUES (?, ?);";
//        List<Idea[]> allIdeas = new ArrayList<>();
//        allIdeas = service.loadAllIdeas();
        // iterate through each array, then through each idea, and map that to db
        for(int i=0; i<allIdeas.size(); i++) {
            for(int j=0; j<allIdeas.get(i).length; j++) {
                template.update(sql, allIdeas.get(i)[j].getQuote(),
                        getIdByPhilosopherName(allIdeas.get(i)[j].getPhilosopher()));
            }
        }
    }

    @Override
    public Integer getIdByPhilosopherName(String name) {
        String sql = "SELECT id FROM philosopher WHERE philosopher_name = ?;";
        Integer id = template.queryForObject(sql, Integer.class, name);
        return id;
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
