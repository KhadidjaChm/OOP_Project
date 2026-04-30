package storage;

import models.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private static final String DATA_FOLDER = "data";

    public FileManager() {
        createDataFolder();
    }

    // Create data folder if it does not exist
    private void createDataFolder() {
        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    // ================= STUDENTS =================

    // Save ALL students by rewriting the file
    // This avoids duplicate lines when we run the program many times
    public void saveStudents(List<Student> students) {
        try {
            FileWriter writer = new FileWriter(DATA_FOLDER + "/students.txt", false);

            for (Student student : students) {
                writer.write(
                        student.getId() + "," +
                                student.getFullName() + "," +
                                student.getEmail() + "," +
                                student.getPassword() + "," +
                                student.getPhoneNumber() + "," +
                                student.getBacRegistrationNumber() + "," +
                                student.getMajor() + "," +
                                student.getYearLevel() + "," +
                                student.getUniversityId() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // Load students from students.txt
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        try {
            File file = new File(DATA_FOLDER + "/students.txt");

            if (!file.exists()) {
                return students;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                Student student = new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        Integer.parseInt(data[5]),
                        data[6],
                        Integer.parseInt(data[7]),
                        data[8]
                );

                students.add(student);
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }

    // ================= COMPANIES =================

    // Save ALL companies
    public void saveCompanies(List<Company> companies) {
        try {
            FileWriter writer = new FileWriter(DATA_FOLDER + "/companies.txt", false);

            for (Company company : companies) {
                writer.write(
                        company.getId() + "," +
                                company.getName() + "," +
                                company.getAddress() + "," +
                                company.getEmail() + "," +
                                company.getPhone() + "," +
                                company.getIndustry() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving companies: " + e.getMessage());
        }
    }

    // Load companies from companies.txt
    public List<Company> loadCompanies() {
        List<Company> companies = new ArrayList<>();

        try {
            File file = new File(DATA_FOLDER + "/companies.txt");

            if (!file.exists()) {
                return companies;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                Company company = new Company(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5]
                );

                companies.add(company);
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading companies: " + e.getMessage());
        }

        return companies;
    }

    // ================= OFFERS =================

    // Save ALL internship offers
    public void saveOffers(List<InternshipOffer> offers) {
        try {
            FileWriter writer = new FileWriter(DATA_FOLDER + "/offers.txt", false);

            for (InternshipOffer offer : offers) {
                writer.write(
                        offer.getId() + "," +
                                offer.getTitle() + "," +
                                offer.getDescription() + "," +
                                offer.getDomain() + "," +
                                offer.getLocation() + "," +
                                offer.getRequiredSkills() + "," +
                                offer.getDurationWeeks() + "," +
                                offer.getSalary() + "," +
                                offer.getDeadline() + "," +
                                offer.isActive() + "," +
                                offer.getCompany().getId() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving offers: " + e.getMessage());
        }
    }

    // Load offers and reconnect each offer with its company using companyId
    public List<InternshipOffer> loadOffers(List<Company> companies) {
        List<InternshipOffer> offers = new ArrayList<>();

        try {
            File file = new File(DATA_FOLDER + "/offers.txt");

            if (!file.exists()) {
                return offers;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                int companyId = Integer.parseInt(data[10]);
                Company company = findCompanyById(companies, companyId);

                if (company != null) {
                    InternshipOffer offer = new InternshipOffer(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            data[5],
                            Integer.parseInt(data[6]),
                            Double.parseDouble(data[7]),
                            LocalDate.parse(data[8]),
                            company
                    );

                    offer.setActive(Boolean.parseBoolean(data[9]));
                    offers.add(offer);
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading offers: " + e.getMessage());
        }

        return offers;
    }

    // ================= APPLICATIONS =================

    // Save ALL applications
    public void saveApplications(List<Application> applications) {
        try {
            FileWriter writer = new FileWriter(DATA_FOLDER + "/applications.txt", false);

            for (Application application : applications) {
                writer.write(
                        application.getId() + "," +
                                application.getStudent().getId() + "," +
                                application.getOffer().getId() + "," +
                                application.getStatus() + "," +
                                application.getDateApplied() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving applications: " + e.getMessage());
        }
    }

    // Load applications and reconnect each application with student and offer
    public List<Application> loadApplications(List<Student> students, List<InternshipOffer> offers) {
        List<Application> applications = new ArrayList<>();

        try {
            File file = new File(DATA_FOLDER + "/applications.txt");

            if (!file.exists()) {
                return applications;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                int applicationId = Integer.parseInt(data[0]);
                int studentId = Integer.parseInt(data[1]);
                int offerId = Integer.parseInt(data[2]);

                Student student = findStudentById(students, studentId);
                InternshipOffer offer = findOfferById(offers, offerId);

                if (student != null && offer != null) {
                    Application application = new Application(applicationId, student, offer);
                    applications.add(application);
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading applications: " + e.getMessage());
        }

        return applications;
    }

    // ================= HELPER METHODS =================

    // Search student by id
    private Student findStudentById(List<Student> students, int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Search company by id
    private Company findCompanyById(List<Company> companies, int id) {
        for (Company company : companies) {
            if (company.getId() == id) {
                return company;
            }
        }
        return null;
    }

    // Search offer by id
    private InternshipOffer findOfferById(List<InternshipOffer> offers, int id) {
        for (InternshipOffer offer : offers) {
            if (offer.getId() == id) {
                return offer;
            }
        }
        return null;
    }
}