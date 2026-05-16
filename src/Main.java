import exceptions.*;
import logic.*;
import models.*;
import storage.FileManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        RegistrationService registrationService = new RegistrationService();
        OfferService offerService = new OfferService();
        ApplicationService applicationService = new ApplicationService();
        AdminService adminService = new AdminService();
        CompanyService companyService = new CompanyService();
        OfferSearchService searchService = new OfferSearchService();
        FileManager fileManager = new FileManager();

        List<User> users = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        List<InternshipOffer> offers = new ArrayList<>();
        List<Application> applications = new ArrayList<>();

        System.out.println("========== TEST START ==========\n");

        // ================= REGISTRATION =================
        Student aya = registrationService.registerStudent(
                users, 1, "Aya", "aya@mail.com", "1234", "0555111111",
                2024001, "Cybersecurity", 2, "UNI001"
        );
        students.add(aya);

        Student sara = registrationService.registerStudent(
                users, 2, "Sara", "sara@mail.com", "1234", "0555222222",
                2024002, "AI", 2, "UNI002"
        );
        students.add(sara);

        Student lina = registrationService.registerStudent(
                users, 3, "Lina", "lina@mail.com", "1234", "0555333333",
                2024003, "Software Engineering", 3, "UNI003"
        );
        students.add(lina);

        Company techCorp = registrationService.registerCompany(
                companies, 1, "TechCorp", "Algiers", "tech@corp.com",
                "0555000000", "IT"
        );

        Company cyberDZ = registrationService.registerCompany(
                companies, 2, "CyberDZ", "Oran", "cyber@dz.com",
                "0555666666", "Cybersecurity"
        );

        System.out.println("Registration test passed.");

        // ================= OFFERS =================
        InternshipOffer backendOffer = offerService.createOffer(
                techCorp, "Backend Internship", "Java OOP internship",
                "Software Development", "Algiers", "Java, OOP",
                8, 20000, LocalDate.now().plusDays(15)
        );
        offers.add(backendOffer);

        InternshipOffer securityOffer = offerService.createOffer(
                cyberDZ, "Security Analyst Internship", "Security review internship",
                "Cybersecurity", "Oran", "Networking, Security",
                6, 25000, LocalDate.now().plusDays(20)
        );
        offers.add(securityOffer);

        InternshipOffer aiOffer = offerService.createOffer(
                techCorp, "AI Internship", "Machine learning project",
                "Artificial Intelligence", "Algiers", "Python, ML",
                10, 30000, LocalDate.now().plusDays(25)
        );
        offers.add(aiOffer);

        System.out.println("Offer creation test passed.\n");

        // ================= NORMAL FULL FLOW =================
        try {
            Application app1 = applicationService.apply(aya, backendOffer);
            applications.add(app1);

            adminService.adminAccept(app1, LocalDate.now().plusDays(5));
            companyService.companyAccept(techCorp, app1);

            System.out.println("Normal flow passed: " + app1.getStatus());
        } catch (Exception e) {
            System.out.println("Normal flow failed: " + e.getMessage());
        }

        // ================= DUPLICATE APPLICATION =================
        try {
            applicationService.apply(sara, securityOffer);
            applicationService.apply(sara, securityOffer);

            System.out.println("Duplicate test failed.");
        } catch (DuplicateApplicationException e) {
            System.out.println("Duplicate test passed: " + e.getMessage());
        }

        // clear Sara applications for later tests
        sara.getApplications().clear();

        // ================= DEADLINE PASSED =================
        try {
            InternshipOffer expiredOffer = offerService.createOffer(
                    techCorp, "Expired Offer", "Old internship",
                    "IT", "Algiers", "Java",
                    4, 10000, LocalDate.now().minusDays(1)
            );
            offers.add(expiredOffer);

            applicationService.apply(sara, expiredOffer);

            System.out.println("Deadline test failed.");
        } catch (DeadlinePassedException e) {
            System.out.println("Deadline test passed: " + e.getMessage());
        }

        // ================= BLOCKED STUDENT =================
        try {
            sara.setBlocked(true);
            applicationService.apply(sara, securityOffer);

            System.out.println("Blocked student test failed.");
        } catch (UnauthorizedActionException e) {
            System.out.println("Blocked student test passed: " + e.getMessage());
        } finally {
            sara.setBlocked(false);
        }

        // ================= INACTIVE OFFER =================
        try {
            offerService.deleteOffer(cyberDZ, securityOffer);
            applicationService.apply(sara, securityOffer);

            System.out.println("Inactive offer test failed.");
        } catch (UnauthorizedActionException e) {
            System.out.println("Inactive offer test passed: " + e.getMessage());
        } finally {
            securityOffer.setActive(true);
        }

        // ================= COMPANY ACCEPT BEFORE ADMIN =================
        try {
            Application app2 = applicationService.apply(sara, securityOffer);
            applications.add(app2);

            companyService.companyAccept(cyberDZ, app2);

            System.out.println("Company before admin test failed.");
        } catch (InvalidStatusTransitionException e) {
            System.out.println("Company before admin test passed: " + e.getMessage());
        }

        sara.getApplications().clear();

        // ================= WRONG COMPANY =================
        try {
            Application app3 = applicationService.apply(sara, securityOffer);
            applications.add(app3);

            adminService.adminAccept(app3, LocalDate.now().plusDays(4));
            companyService.companyAccept(techCorp, app3);

            System.out.println("Wrong company test failed.");
        } catch (UnauthorizedActionException e) {
            System.out.println("Wrong company test passed: " + e.getMessage());
        }

        sara.getApplications().clear();

        // ================= CANCEL PENDING =================
        try {
            Application app4 = applicationService.apply(sara, securityOffer);
            applicationService.cancel(sara, app4);

            System.out.println("Cancel pending test passed. Sara applications: " + sara.getApplications().size());
        } catch (Exception e) {
            System.out.println("Cancel pending test failed: " + e.getMessage());
        }

        // ================= CANCEL AFTER ADMIN ACCEPT =================
        try {
            Application app5 = applicationService.apply(sara, securityOffer);
            applications.add(app5);

            adminService.adminAccept(app5, LocalDate.now().plusDays(4));
            applicationService.cancel(sara, app5);

            System.out.println("Cancel after admin test failed.");
        } catch (InvalidStatusTransitionException e) {
            System.out.println("Cancel after admin test passed: " + e.getMessage());
        }

        sara.getApplications().clear();

        // ================= ADMIN REJECT =================
        try {
            Application app6 = applicationService.apply(sara, securityOffer);
            applications.add(app6);

            adminService.adminReject(app6, "Missing documents");

            System.out.println("Admin reject test passed: " + app6.getStatus()
                    + " / " + app6.getRejectType()
                    + " / " + app6.getRejectReason());
        } catch (Exception e) {
            System.out.println("Admin reject test failed: " + e.getMessage());
        }

        sara.getApplications().clear();

        // ================= COMPANY REJECT =================
        try {
            Application app7 = applicationService.apply(sara, securityOffer);
            applications.add(app7);

            adminService.adminAccept(app7, LocalDate.now().plusDays(6));
            companyService.companyReject(cyberDZ, app7, "Company selected another candidate");

            System.out.println("Company reject test passed: " + app7.getStatus()
                    + " / " + app7.getRejectType()
                    + " / " + app7.getRejectReason());
        } catch (Exception e) {
            System.out.println("Company reject test failed: " + e.getMessage());
        }

        // ================= MAX 5 APPLICATIONS =================
        try {
            Student maxStudent = registrationService.registerStudent(
                    users, 4, "Max Student", "max@mail.com", "1234", "0555444444",
                    2024004, "IT", 2, "UNI004"
            );
            students.add(maxStudent);

            for (int i = 0; i < 6; i++) {
                InternshipOffer tempOffer = offerService.createOffer(
                        techCorp,
                        "Offer " + i,
                        "Test offer " + i,
                        "IT",
                        "Algiers",
                        "Java",
                        4,
                        10000,
                        LocalDate.now().plusDays(10)
                );
                offers.add(tempOffer);

                applicationService.apply(maxStudent, tempOffer);
            }

            System.out.println("Max applications test failed.");
        } catch (MaxApplicationReachedException e) {
            System.out.println("Max applications test passed: " + e.getMessage());
        }

        // ================= SEARCH / FILTER =================
        System.out.println("\nSearch backend results: "
                + searchService.searchByKeyword(offers, "backend").size());

        System.out.println("Filter domain Cybersecurity results: "
                + searchService.filterByDomain(offers, "Cybersecurity").size());

        System.out.println("Filter location Algiers results: "
                + searchService.filterByLocation(offers, "Algiers").size());

        System.out.println("Filter company TechCorp results: "
                + searchService.filterByCompany(offers, "TechCorp").size());

        // ================= STORAGE TEST =================
        fileManager.saveStudents(students);
        fileManager.saveCompanies(companies);
        fileManager.saveOffers(offers);
        fileManager.saveApplications(applications);

        List<Student> loadedStudents = fileManager.loadStudents();
        List<Company> loadedCompanies = fileManager.loadCompanies();
        List<InternshipOffer> loadedOffers = fileManager.loadOffers(loadedCompanies);
        List<Application> loadedApplications = fileManager.loadApplications(loadedStudents, loadedOffers);

        System.out.println("\nStorage test:");
        System.out.println("Loaded students: " + loadedStudents.size());
        System.out.println("Loaded companies: " + loadedCompanies.size());
        System.out.println("Loaded offers: " + loadedOffers.size());
        System.out.println("Loaded applications: " + loadedApplications.size());

        System.out.println("\n========== TEST END ==========");
    }
}