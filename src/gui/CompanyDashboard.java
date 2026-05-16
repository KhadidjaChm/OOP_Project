package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.Company;
import services.CompanyService;
import services.InternshipService;
import services.ApplicationService;

import javax.swing.*;
import java.awt.*;

public class CompanyDashboard extends JFrame {

    private final Company company;
    private final CompanyService companyService;
    private final InternshipService internshipService;
    private final ApplicationService applicationService;

    public CompanyDashboard(Company company,
                            CompanyService companyService,
                            InternshipService internshipService,
                            ApplicationService applicationService) {

        this.company = company;
        this.companyService = companyService;
        this.internshipService = internshipService;
        this.applicationService = applicationService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Company Dashboard");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel title = new JLabel("Welcome, " + company.getName() + "!", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 15, 15));
        center.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JButton createOfferBtn = new JButton("Create Internship Offer");
        createOfferBtn.setBackground(new Color(0, 120, 215));
        createOfferBtn.setForeground(Color.WHITE);
        createOfferBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton registerCompanyBtn = new JButton("Register New Company");
        registerCompanyBtn.setBackground(new Color(76, 175, 80));
        registerCompanyBtn.setForeground(Color.WHITE);
        registerCompanyBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton viewApplicantsBtn = new JButton("View Applicants");
        viewApplicantsBtn.setBackground(new Color(0, 150, 136));
        viewApplicantsBtn.setForeground(Color.WHITE);
        viewApplicantsBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton manageOffersBtn = new JButton("Manage Offers");
        manageOffersBtn.setBackground(new Color(255, 152, 0));
        manageOffersBtn.setForeground(Color.WHITE);
        manageOffersBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(200, 60, 60));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        center.add(createOfferBtn);
        center.add(registerCompanyBtn);
        center.add(viewApplicantsBtn);
        center.add(manageOffersBtn);
        center.add(logoutBtn);

        add(center, BorderLayout.CENTER);

        // ===== ACTIONS =====

        // ✔ Open Create Offer Window
        createOfferBtn.addActionListener(e -> {
            new CreateOfferWindow(company, internshipService).setVisible(true);
        });

        // ✔ Open Company Registration Window
        registerCompanyBtn.addActionListener(e -> {
            new CompanyRegistrationWindow(companyService).setVisible(true);
        });

        // ✔ Open View Applicants Window
        viewApplicantsBtn.addActionListener(e -> {
            new ViewApplicantsWindow(company, applicationService).setVisible(true);
        });

        // ✔ Open Manage Offers Window
        manageOffersBtn.addActionListener(e -> {
            new ManageOffersWindow(company, internshipService).setVisible(true);
        });

        // ✔ Logout
        logoutBtn.addActionListener(e -> {
            new MainWindow().setVisible(true);
            dispose();
        });
    }
}


