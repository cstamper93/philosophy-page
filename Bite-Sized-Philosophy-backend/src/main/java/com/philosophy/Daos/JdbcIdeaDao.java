package com.philosophy.Daos;

import com.philosophy.Models.Idea;
import com.philosophy.Services.PhilosopherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIdeaDao implements IdeaDao{

    private JdbcTemplate template;

    public JdbcIdeaDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void insertIdeasFromApiToDb(List<Idea[]> allIdeasFromApi) {
        String insertSql = "INSERT INTO idea (quote, philosopher_id) " +
                "VALUES (?, ?);";
        String selectSql = "SELECT quote, philosopher_name FROM idea " +
                "JOIN philosopher ON idea.philosopher_id = philosopher.id;";

        List<Idea> currentIdeasInDb = new ArrayList<>();
        SqlRowSet results = template.queryForRowSet(selectSql);
        while(results.next()) {
            Idea incomingIdea = mapRowToIdea(results);
            currentIdeasInDb.add(incomingIdea);
        }

        // 1) Create List<Idea> objects from the List<Idea[]> that is input from argument
        // 2) Filter out duplicates
        // 3) Convert philosopher name to id and insert into db

        List<Idea> incomingIdeasFromApi = new ArrayList<>();
        for(int i=0; i<allIdeasFromApi.size(); i++) {
            for(int j=0; j<allIdeasFromApi.get(i).length; j++) {
                incomingIdeasFromApi.add(allIdeasFromApi.get(i)[j]);
            }
        }

        // Compare incoming Ideas to ideas currently in db.
        // if already present in db, don't insert; else, insert

        for(int i=0; i<incomingIdeasFromApi.size(); i++) {
            int counter = 0;
            for(int j=0; j<currentIdeasInDb.size(); j++) {
                if(incomingIdeasFromApi.get(i).getQuote().equals(currentIdeasInDb.get(j).getQuote())) {
                    counter++;
                }
            }
            if(counter == 0) {
                template.update(insertSql, incomingIdeasFromApi.get(i).getQuote(),
                        getIdByPhilosopherName(incomingIdeasFromApi.get(i).getPhilosopher()));
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
        idea.setPhilosopher(rowSet.getString("philosopher_name"));
        return idea;
    }
}
