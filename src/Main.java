import logic.RegistrationService;
import models.Company;
import models.Student;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        List<Company> companies = new ArrayList<>();

        RegistrationService registrationService = new RegistrationService();

        Student student = registrationService.registerStudent(
                users,
                1,
                "Aya",
                "aya@mail.com",
                "1234",
                "0555111111",
                2024001,
                "Cybersecurity",
                2,
                "UNI001"
        );

        Company company = registrationService.registerCompany(
                companies,
                1,
                "TechCorp",
                "Algiers",
                "tech@corp.com",
                "0555000000",
                "IT"
        );

        System.out.println(student.getFullName());
        System.out.println(company.getName());
    }
}