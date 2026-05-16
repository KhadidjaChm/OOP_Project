package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.Student;
import services.StudentService;

import javax.swing.*;
import java.awt.*;

public class StudentAuthWindow extends JFrame {

    private final StudentService studentService;

    public StudentAuthWindow(StudentService studentService) {
        this.studentService = studentService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Student Login");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Student Login", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel form = new JPanel(new GridLayout(3, 2, 12, 12));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        form.add(new JLabel("Email:"));
        form.add(emailField);

        form.add(new JLabel("Password:"));
        form.add(passwordField);

        add(form, BorderLayout.CENTER);

        // ===== BUTTONS =====
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 120, 215));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(76, 175, 80));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel btnPanel = new JPanel();
        btnPanel.add(loginBtn);
        btnPanel.add(registerBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ===== ACTIONS =====

        // ✔ Student Login
        loginBtn.addActionListener(e -> {
            try {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                    return;
                }

                Student student = studentService.login(email, password);

                if (student == null) {
                    JOptionPane.showMessageDialog(this, "Invalid email or password.");
                    return;
                }

                JOptionPane.showMessageDialog(this, "Login successful!");

                // Open Student Dashboard (you will implement this later)
                // new StudentDashboard(student, studentService).setVisible(true);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // ✔ Open Student Registration Window
        registerBtn.addActionListener(e -> {
            new StudentRegistrationWindow(studentService).setVisible(true);
        });
    }
}
