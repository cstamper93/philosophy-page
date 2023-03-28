import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
});

export default {
    viewPhilosophersFromAPI() {
        return http.get("/philosophers");
    },
    addPhilosopherToDatabase(philosopher) {
        return http.post("/add-philosopher", philosopher);
    }
}