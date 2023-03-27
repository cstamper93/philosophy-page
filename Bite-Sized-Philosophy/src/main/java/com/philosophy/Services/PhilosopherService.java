package com.philosophy.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philosophy.Models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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
        ResponseEntity<PhilosopherResults> responseEntity =
                template.exchange(PHILOSOPHER_API + "/philosophers",
                        HttpMethod.GET, makeEntity(), PhilosopherResults.class);

        PhilosopherResults philosopherResults = responseEntity.getBody();
        Philosopher[] philosophers = philosopherResults.getPhilosophers();
        return Arrays.asList(philosophers);
    }

    // Pull in idea data
    // Many pages of ideas, have to come up with creative way to iterate thru,
    // then combine the arrays :)
    public List<Idea[]> loadAllIdeas() {
        ResponseEntity<IdeaResults> responseEntity =
                template.exchange(PHILOSOPHER_API + "/ideas",
                        HttpMethod.GET, makeEntity(), IdeaResults.class);

//        ResponseEntity<Endpoint> responseEntity2 =
//                template.exchange(PHILOSOPHER_API + "/ideas",
//                        HttpMethod.GET, makeEntity(), Endpoint.class);
//
//        String endpoint = responseEntity2.getBody().getEndpoint();

        List<Idea[]> ideaResultsPageList = new ArrayList<>();
        Idea[] ideaResultsPageArray = responseEntity.getBody().getIdeas();
        ideaResultsPageList.add(ideaResultsPageArray);

        for(int i=2; i<=17; i++) {
            String queryString = "/?page=" + String.valueOf(i);
            ResponseEntity<IdeaResults> responseEntity2 =
                    template.exchange(PHILOSOPHER_API + "/ideas" + queryString,
                            HttpMethod.GET, makeEntity(), IdeaResults.class);
            Idea[] ideas = responseEntity2.getBody().getIdeas();
            ideaResultsPageList.add(ideas);
        }

        // Idea[] ideas = responseEntity.getBody().getIdeas();
        return ideaResultsPageList;
    }

    // Pull in school data
    public List<School> loadAllSchools() {
        ResponseEntity<SchoolResults> responseEntityPg1 =
                template.exchange(PHILOSOPHER_API + "/schools",
                        HttpMethod.GET, makeEntity(), SchoolResults.class);

        ResponseEntity<SchoolResults> responseEntityPg2 =
                template.exchange(PHILOSOPHER_API + "/schools/?page=2",
                        HttpMethod.GET, makeEntity(), SchoolResults.class);

        School[] schoolsPg1 = responseEntityPg1.getBody().getSchools();
        School[] schoolsPg2 = responseEntityPg2.getBody().getSchools();

        int length1 = schoolsPg1.length;
        int length2 = schoolsPg2.length;
        int totalLength = length1 + length2;
        School[] combinedSchools = new School[totalLength];

        for(int i=0; i<totalLength; i++) {
            if(i<length1) {
                combinedSchools[i] = schoolsPg1[i];
            } else {
                combinedSchools[i] = schoolsPg2[i - length1];
            }
        }

        return Arrays.asList(combinedSchools);
    }



    private HttpEntity<Void> makeEntity() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return entity;
    }

}
