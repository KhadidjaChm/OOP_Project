import models.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // 1) Create a company
        Company c = new Company(
                1,
                "TechCorp",
                "Algiers",
                "contact@techcorp.com",
                "0550 00 00 00",
                "IT"
        );

        // 2) Create an internship offer
        InternshipOffer offer = new InternshipOffer(
                10,
                "Java Developer Intern",
                "Work on backend systems",
                "Software Development",
                "Algiers",
                "Java, OOP, Git",
                12,
                0.0,
                LocalDate.of(2026, 6, 1),
                c
        );

        // 3) Create a student
        Student s = new Student(
                100,
                "Khadidja",
                "khadidja@example.com",
                "1234",
                "0551 11 11 11",
                2020123456,
                "Computer Science",
                3,
                "UNI123"
        );

        // 4) Create an application
        Application app = new Application(500, s, offer);

        // 5) Print everything to check
        System.out.println("=== TEST MODELS ===");
        System.out.println("Company: " + c.getName());
        System.out.println("Offer: " + offer.getTitle());
        System.out.println("Student: " + s.getFullName());
        System.out.println("Application Status: " + app.getStatus());
        System.out.println("Application Date: " + app.getDateApplied());
    }
}
