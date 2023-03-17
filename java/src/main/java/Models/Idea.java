package Models;

public class Idea {

    private String quote;
    private int philosopherId;

    public Idea(String quote, int philosopherId) {
        this.quote = quote;
        this.philosopherId = philosopherId;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getPhilosopherId() {
        return philosopherId;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "quote='" + quote + '\'' +
                ", philosopherId=" + philosopherId +
                '}';
    }
}
