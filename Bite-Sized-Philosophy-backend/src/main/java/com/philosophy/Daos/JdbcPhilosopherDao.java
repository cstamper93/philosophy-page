package com.philosophy.Daos;

import com.philosophy.Models.Philosopher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPhilosopherDao implements PhilosopherDao{

    // "Dependency Injection"
    private JdbcTemplate template; // What we use to communicate with the db

    public JdbcPhilosopherDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void insertPhilosophersFromApi(List<Philosopher> philosophers) {
        String sql = "INSERT INTO philosopher (philosopher_name, photo, nationality, era, favorited) " +
                "VALUES(?, ?, ?, ?, ?);";
        for(Philosopher item : philosophers) {
            template.update(sql, item.getName(),item.getPhoto(), item.getNationality(),
                    item.getEra(), item.getFavorited());
        }
    }

    @Override
    public List<Philosopher> getAllPhilosophers() {
        String sql = "SELECT * FROM philosopher;";
        SqlRowSet results = template.queryForRowSet(sql);
        List<Philosopher> philosophers = new ArrayList<>();
        while(results.next()) {
            Philosopher philosopher = mapRowToPhilosopher(results);
            philosophers.add(philosopher);
        }
        return philosophers;
    }

    @Override
    public Philosopher getPhilosopherById(int id) {
        String sql = "SELECT * FROM philosopher WHERE id = ?;";
        SqlRowSet results = template.queryForRowSet(sql, id);
//        Philosopher philosopher = new Philosopher();
//        philosopher.setId(id);
        if(results.next()) {
            return mapRowToPhilosopher(results);
        }
        return null;
    }

    @Override
    public Philosopher addNewPhilosopher(Philosopher philosopher) {
        String sql = "INSERT INTO philosopher (philosopher_name, photo, nationality, era) " +
                "VALUES (?, ?, ?, ?) RETURNING id;";
        Integer newId = template.queryForObject(sql, Integer.class,
                philosopher.getName(), philosopher.getPhoto(), philosopher.getNationality(), philosopher.getEra());
        return getPhilosopherById(newId); // verifies that the movie object was written to db
    }

    @Override
    public boolean removePhilosopher(int id) {
        boolean success = false;
        String sql = "DELETE FROM philosopher WHERE id = ?;";
        int linesUpdated = template.update(sql, id);
        if(linesUpdated == 1) {
            success = true;
        }
        return success;
    }

    private Philosopher mapRowToPhilosopher(SqlRowSet rs) {
        Philosopher philosopher = new Philosopher();
        philosopher.setId(rs.getInt("id"));
        philosopher.setName(rs.getString("philosopher_name"));
        philosopher.setPhoto(rs.getString("photo"));
        philosopher.setNationality(rs.getString("nationality"));
        philosopher.setEra(rs.getString("era"));
        philosopher.setFavorited(rs.getBoolean("favorited"));
        return philosopher;
    }

}
