import logic.*;
import models.*;
import storage.FileManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileManager fileManager = new FileManager();

        List<Student> students = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        List<InternshipOffer> offers = new ArrayList<>();
        List<Application> applications = new ArrayList<>();

        Company company = new Company(1, "TechCorp", "Algiers", "tech@corp.com", "0555000000", "IT");
        Student student = new Student(1, "Aya", "aya@mail.com", "1234", "0555111111",
                2024001, "Cybersecurity", 2, "UNI001");

        OfferService offerService = new OfferService();
        ApplicationService applicationService = new ApplicationService();
        AdminService adminService = new AdminService();

        InternshipOffer offer = offerService.createOffer(
                company,
                "Backend Internship",
                "Java OOP internship",
                "IT",
                "Algiers",
                "Java, OOP",
                8,
                20000,
                LocalDate.now().plusDays(10)
        );

        Application app = applicationService.apply(student, offer);
        adminService.adminAccept(app, LocalDate.now().plusDays(3));

        students.add(student);
        companies.add(company);
        offers.add(offer);
        applications.add(app);

        fileManager.saveStudents(students);
        fileManager.saveCompanies(companies);
        fileManager.saveOffers(offers);
        fileManager.saveApplications(applications);

        System.out.println("Data saved successfully.");

        List<Student> loadedStudents = fileManager.loadStudents();
        List<Company> loadedCompanies = fileManager.loadCompanies();
        List<InternshipOffer> loadedOffers = fileManager.loadOffers(loadedCompanies);
        List<Application> loadedApplications = fileManager.loadApplications(loadedStudents, loadedOffers);

        System.out.println("Loaded students: " + loadedStudents.size());
        System.out.println("Loaded companies: " + loadedCompanies.size());
        System.out.println("Loaded offers: " + loadedOffers.size());
        System.out.println("Loaded applications: " + loadedApplications.size());

        if (!loadedApplications.isEmpty()) {
            Application loadedApp = loadedApplications.get(0);

            System.out.println("Loaded application status: " + loadedApp.getStatus());
            System.out.println("Loaded interview date: " + loadedApp.getInterviewDate());
            System.out.println("Loaded student name: " + loadedApp.getStudent().getFullName());
            System.out.println("Loaded offer title: " + loadedApp.getOffer().getTitle());
        }
    }
}