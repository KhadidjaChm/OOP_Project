package gui;

import com.formdev.flatlaf.FlatLightLaf;
import services.AdminService;
import services.CompanyService;
import services.StudentService;
import services.InternshipService;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private final AdminService adminService;
    private final CompanyService companyService;
    private final StudentService studentService;
    private final InternshipService internshipService;

    public AdminDashboard(AdminService adminService,
                          CompanyService companyService,
                          StudentService studentService,
                          InternshipService internshipService) {

        this.adminService = adminService;
        this.companyService = companyService;
        this.studentService = studentService;
        this.internshipService = internshipService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Admin Dashboard");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 15, 15));
        center.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JButton validateAppsBtn = new JButton("Validate Applications");
        validateAppsBtn.setBackground(new Color(0, 120, 215));
        validateAppsBtn.setForeground(Color.WHITE);
        validateAppsBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton viewCompaniesBtn = new JButton("View All Companies");
        viewCompaniesBtn.setBackground(new Color(255, 152, 0));
        viewCompaniesBtn.setForeground(Color.WHITE);
        viewCompaniesBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton viewStudentsBtn = new JButton("View All Students");
        viewStudentsBtn.setBackground(new Color(0, 150, 136));
        viewStudentsBtn.setForeground(Color.WHITE);
        viewStudentsBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(200, 60, 60));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        center.add(validateAppsBtn);
        center.add(viewCompaniesBtn);
        center.add(viewStudentsBtn);
        center.add(logoutBtn);

        add(center, BorderLayout.CENTER);

        // ===== ACTIONS =====

        // ✔ Open Admin Validation Window
        validateAppsBtn.addActionListener(e -> {
            new AdminValidationWindow(adminService).setVisible(true);
        });

        // ✔ View all companies
        viewCompaniesBtn.addActionListener(e -> {
            new ViewCompaniesWindow(companyService).setVisible(true);
        });

        // ✔ View all students
        viewStudentsBtn.addActionListener(e -> {
            new ViewStudentsWindow(studentService).setVisible(true);
        });

        // ✔ Logout
        logoutBtn.addActionListener(e -> {
            new MainWindow().setVisible(true);
            dispose();
        });
    }
}
