package Models;

public class School {

    private String school;
    private int philosopherId;

    public School(String school, int philosopherId) {
        this.school = school;
        this.philosopherId = philosopherId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getPhilosopherId() {
        return philosopherId;
    }

    @Override
    public String toString() {
        return "School{" +
                "school='" + school + '\'' +
                ", philosopherId=" + philosopherId +
                '}';
    }
}
