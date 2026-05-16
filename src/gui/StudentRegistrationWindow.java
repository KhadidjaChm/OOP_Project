package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.Student;
import services.StudentService;

import javax.swing.*;
import java.awt.*;

public class StudentRegistrationWindow extends JFrame {

    private final StudentService studentService;

    public StudentRegistrationWindow(StudentService studentService) {
        this.studentService = studentService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Student Registration");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Create Your Student Account", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel form = new JPanel(new GridLayout(8, 2, 12, 12));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField phoneField = new JTextField();
        JTextField departmentField = new JTextField();
        JTextField addressField = new JTextField();

        form.add(new JLabel("First Name:"));
        form.add(firstNameField);

        form.add(new JLabel("Last Name:"));
        form.add(lastNameField);

        form.add(new JLabel("Email:"));
        form.add(emailField);

        form.add(new JLabel("Password:"));
        form.add(passwordField);

        form.add(new JLabel("Phone:"));
        form.add(phoneField);

        form.add(new JLabel("Department:"));
        form.add(departmentField);

        form.add(new JLabel("Address:"));
        form.add(addressField);

        add(form, BorderLayout.CENTER);

        // ===== REGISTER BUTTON =====
        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(0, 120, 215));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerBtn.setFocusPainted(false);

        JPanel btnPanel = new JPanel();
        btnPanel.add(registerBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // ===== ACTION =====
        registerBtn.addActionListener(e -> {
            try {
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String phone = phoneField.getText().trim();
                String department = departmentField.getText().trim();
                String address = addressField.getText().trim();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                        password.isEmpty() || phone.isEmpty() ||
                        department.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required.");
                    return;
                }

                Student student = new Student(
                        firstName,
                        lastName,
                        email,
                        password,
                        phone,
                        department,
                        address
                );

                studentService.register(student);

                JOptionPane.showMessageDialog(this, "Student registered successfully!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}

