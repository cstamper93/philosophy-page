import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
});

export default {
    viewPhilosophersFromDb() {
        return http.get("/philosophers");
    },
    fetchFavorites() {
        return http.get("/favorites");
    },
    // Unneeded?
    removePhilosopher(id) {
        return http.delete(`/favorites/${id}`);
    },
    addToFavs(philosopher) {
        return http.put("/add-favorites", philosopher);
    },
    removeFromFavs(philosopher) {
        return http.put("/remove-favorites", philosopher);
    },
    loadIdeas() {
        return http.post("/add-ideas");
    },
    loadSchools() {
        return http.post("/add-schools");
    },
    fetchIdeasWithId(id) {
        return http.get(`/ideas/${id}`);
    },
    fetchPhilosopherDeets(id) {
        return http.get(`/philosopher-details/${id}`);
    },
    fetchSchoolsWithId(id) {
        return http.get(`/schools/${id}`);
    }
}