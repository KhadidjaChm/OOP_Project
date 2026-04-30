<<<<<<< HEAD
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
=======
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    IO.println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        IO.println("i = " + i);
    }
}
>>>>>>> 4de519afee8106b8097606e6ddef964385e34c7a
