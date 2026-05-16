package gui;

import models.Company;
import services.CompanyService;

import javax.swing.*;
import java.awt.*;

public class CompanyRegistrationWindow extends JFrame {

    private final CompanyService companyService;

    public CompanyRegistrationWindow(CompanyService companyService) {
        this.companyService = companyService;

        setTitle("Company Registration");
        setSize(450, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fields
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField domainField = new JTextField();
        JTextField locationField = new JTextField();
        JTextArea descriptionArea = new JTextArea(4, 20);

        // Add components
        panel.add(new JLabel("Company Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        panel.add(new JLabel("Domain:"));
        panel.add(domainField);

        panel.add(new JLabel("Location:"));
        panel.add(locationField);

        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descriptionArea));

        JButton registerBtn = new JButton("Register");
        panel.add(registerBtn);

        add(panel);

        // Action
        registerBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String domain = domainField.getText().trim();
                String location = locationField.getText().trim();
                String description = descriptionArea.getText().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                        domain.isEmpty() || location.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required.");
                    return;
                }

                Company company = new Company(
                        name,
                        email,
                        password,
                        domain,
                        location,
                        description
                );

                companyService.register(company);

                JOptionPane.showMessageDialog(this, "Company registered successfully!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
