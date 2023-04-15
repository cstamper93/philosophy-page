import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
});

export default {
    viewPhilosophersFromDb() {
        return http.get("/philosophers");
    },
    // addPhilosopherToFavorites(philosopher) {
    //     return http.post("/add-philosopher", philosopher);
    // },
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
}