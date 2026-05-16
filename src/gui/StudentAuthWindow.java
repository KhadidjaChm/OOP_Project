package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class StudentAuthWindow extends JFrame {

    public StudentAuthWindow() {

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Student Login");
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel title = new JLabel("Student Authentication", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 120, 215));
        loginBtn.setForeground(Color.WHITE);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(0, 150, 136));
        registerBtn.setForeground(Color.WHITE);

        center.add(emailField);
        center.add(passwordField);
        center.add(loginBtn);
        center.add(registerBtn);

        add(center, BorderLayout.CENTER);

        // ===== FOOTER =====
        JButton backBtn = new JButton("← Back");
        backBtn.setFocusPainted(false);
        backBtn.setBackground(Color.LIGHT_GRAY);

        backBtn.addActionListener(e -> {
            new MainWindow().setVisible(true);
            dispose();
        });

        add(backBtn, BorderLayout.SOUTH);

        // ===== ACTIONS =====
        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String pass = new String(passwordField.getPassword());

            if (email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            // TODO: Add real authentication logic
            JOptionPane.showMessageDialog(this, "Login successful!");
            new StudentDashboard().setVisible(true);
            dispose();
        });

        registerBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Registration page coming soon!");
        });
    }
}
