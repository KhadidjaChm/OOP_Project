import models.*;

public class Main {
    public static void main(String[] args) {

        Student s = new Student(1, "Khadidja");
        Company c = new Company(1, "Google");
        InternshipOffer o = new InternshipOffer(1, "Backend Intern", "Java + Spring", c);
        Application app = new Application(1, s, o);

        System.out.println(app.getStatus()); // Should print PENDING
    }
}