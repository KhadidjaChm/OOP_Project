package models;

import java.util.ArrayList;
import java.util.List;
public class Student extends User {
    private List<Application> applications = new ArrayList<>();
    public Student(int id , String name ){
        super(id , name);

    }
    public List<Application> getApplications() {
        return applications;

    }
    public void addApplication(Application app) {
        applications.add(app);
    }
}
