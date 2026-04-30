import models.*;
import storage.FileManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileManager fileManager = new FileManager();

        List<Company> companies = new ArrayList<>();
        List<InternshipOffer> offers = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Application> applications = new ArrayList<>();

        Company company = new Company(
                1,
                "TechCorp",
                "Algiers",
                "contact@techcorp.com",
                "0550000000",
                "IT"
        );

        InternshipOffer offer = new InternshipOffer(
                10,
                "Java Developer Intern",
                "Backend internship",
                "Software Development",
                "Algiers",
                "Java OOP Git",
                12,
                0.0,
                LocalDate.of(2026, 6, 1),
                company
        );

        Student student = new Student(
                100,
                "Maya",
                "maya@example.com",
                "1234",
                "0550000000",
                2020123456,
                "Computer Science",
                3,
                "UNI001"
        );

        Application application = new Application(
                500,
                student,
                offer
        );

        companies.add(company);
        offers.add(offer);
        students.add(student);
        applications.add(application);

        fileManager.saveCompanies(companies);
        fileManager.saveOffers(offers);
        fileManager.saveStudents(students);
        fileManager.saveApplications(applications);

        List<Company> loadedCompanies = fileManager.loadCompanies();
        List<InternshipOffer> loadedOffers = fileManager.loadOffers(loadedCompanies);
        List<Student> loadedStudents = fileManager.loadStudents();
        List<Application> loadedApplications = fileManager.loadApplications(loadedStudents, loadedOffers);

        System.out.println("===== FINAL PROFESSIONAL STORAGE TEST =====");

        for (Application app : loadedApplications) {
            System.out.println(
                    "Application ID: " + app.getId()
                            + " | Student: " + app.getStudent().getFullName()
                            + " | Offer: " + app.getOffer().getTitle()
                            + " | Company: " + app.getOffer().getCompany().getName()
                            + " | Status: " + app.getStatus()
            );
        }
    }
}