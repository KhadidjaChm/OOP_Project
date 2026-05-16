
package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class CompanyDashboard extends JFrame {

    public CompanyDashboard() {

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Company Dashboard");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel title = new JLabel("Welcome, Company!", SwingConstants.CENTER);
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
        center.add(viewApplicantsBtn);
        center.add(manageOffersBtn);
        center.add(logoutBtn);

        add(center, BorderLayout.CENTER);

        // ===== ACTIONS =====
        createOfferBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Offer creation page coming soon!");
        });

        viewApplicantsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Applicants list coming soon!");
        });

        manageOffersBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Offer management coming soon!");
        });

        logoutBtn.addActionListener(e -> {
            new MainWindow().setVisible(true);
            dispose();
        });
    }
}
