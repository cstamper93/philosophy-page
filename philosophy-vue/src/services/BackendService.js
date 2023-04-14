import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
});

export default {
    viewPhilosophersFromAPI() {
        return http.get("/philosophers");
    },
    addPhilosopherToFavorites(philosopher) {
        return http.post("/add-philosopher", philosopher);
    },
    fetchFavorites() {
        return http.get("/favorites");
    },
    removeFromFavs(id) {
        return http.delete(`/favorites/${id}`);
    }
}