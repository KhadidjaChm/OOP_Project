package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Admin Dashboard");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel title = new JLabel("Administrator Panel", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 15, 15));
        center.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JButton manageStudentsBtn = new JButton("Manage Students");
        manageStudentsBtn.setBackground(new Color(0, 120, 215));
        manageStudentsBtn.setForeground(Color.WHITE);
        manageStudentsBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton manageCompaniesBtn = new JButton("Manage Companies");
        manageCompaniesBtn.setBackground(new Color(0, 150, 136));
        manageCompaniesBtn.setForeground(Color.WHITE);
        manageCompaniesBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton manageOffersBtn = new JButton("Manage Internship Offers");
        manageOffersBtn.setBackground(new Color(255, 152, 0));
        manageOffersBtn.setForeground(Color.WHITE);
        manageOffersBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(200, 60, 60));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        center.add(manageStudentsBtn);
        center.add(manageCompaniesBtn);
        center.add(manageOffersBtn);
        center.add(logoutBtn);

        add(center, BorderLayout.CENTER);

        // ===== ACTIONS =====
        manageStudentsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Student management coming soon!");
        });

        manageCompaniesBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Company management coming soon!");
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

