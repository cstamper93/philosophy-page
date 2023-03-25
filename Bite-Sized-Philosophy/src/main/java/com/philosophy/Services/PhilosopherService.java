package com.philosophy.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philosophy.Models.Philosopher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhilosopherService {

    @Value("${PHILOSOPHER_API}") // spring annotation that pulls from application.properties
    private String PHILOSOPHER_API; // CAPS FOR CONST
    RestTemplate template = new RestTemplate();

    public List<Philosopher> getAllPhilosophers() {
        HttpHeaders headers = new HttpHeaders();
        // headers.setBearerAuth(KEY);
        HttpEntity<Void> entity = new HttpEntity<>(headers); // wraps our header into an entity to pass in get request

        ObjectMapper mapper = new ObjectMapper(); // how we take the json and map it
        JsonNode jsonNode; // checked exception - you have to handle by throws in header or try/catch
        // Send entity using rest template
        ResponseEntity<String> responseEntity =
                template.exchange(PHILOSOPHER_API + "/philosophers", HttpMethod.GET, entity, String.class);

        List<Philosopher> philosopherList = new ArrayList<>();

        try {
            jsonNode = mapper.readTree(responseEntity.getBody());
            JsonNode results = jsonNode.path("results");
            for(int i=0; i<results.size(); i++) {
                Philosopher temp = new Philosopher();
                int philosopherId = results.path(i).path("id").asInt();
                temp.setPhilosopherId(philosopherId);
                String name = results.path(i).path("name").asText();
                temp.setName(name);
                String photo = results.path(i).path("photo").asText();
                temp.setPhoto(photo);
                String nationality = results.path(i).path("nationality").asText();
                temp.setNationality(nationality);
                String era = results.path(i).path("era").asText();
                temp.setEra(era);
                philosopherList.add(temp);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return philosopherList;
    }

    // Same as above, just in different and shorter way
    public List<Philosopher> loadAllPhilosophers() {

    }

}
