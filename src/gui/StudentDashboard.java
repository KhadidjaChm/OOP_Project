package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {

    public StudentDashboard() {

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Student Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel title = new JLabel("Welcome, Student!", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 1, 15, 15));
        center.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JButton viewOffersBtn = new JButton("View Internship Offers");
        viewOffersBtn.setBackground(new Color(0, 120, 215));
        viewOffersBtn.setForeground(Color.WHITE);
        viewOffersBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton viewStatusBtn = new JButton("View Application Status");
        viewStatusBtn.setBackground(new Color(0, 150, 136));
        viewStatusBtn.setForeground(Color.WHITE);
        viewStatusBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(200, 60, 60));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        center.add(viewOffersBtn);
        center.add(viewStatusBtn);
        center.add(logoutBtn);

        add(center, BorderLayout.CENTER);

        // ===== ACTIONS =====
        viewOffersBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Offers page coming soon!");
        });

        viewStatusBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Application status coming soon!");
        });

        logoutBtn.addActionListener(e -> {
            new MainWindow().setVisible(true);
            dispose();
        });
    }
}

