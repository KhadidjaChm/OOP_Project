package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class AdminAuthWindow extends JFrame {

    public AdminAuthWindow() {

        FlatLightLaf.setup();

        setTitle("Admin Login");
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Admin Authentication", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 1, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Admin Email"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 120, 215));
        loginBtn.setForeground(Color.WHITE);

        center.add(emailField);
        center.add(passwordField);
        center.add(loginBtn);

        add(center, BorderLayout.CENTER);

        JButton backBtn = new JButton("← Back");
        backBtn.setBackground(Color.LIGHT_GRAY);

        backBtn.addActionListener(e -> {
            new MainWindow().setVisible(true);
            dispose();
        });

        add(backBtn, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String pass = new String(passwordField.getPassword());

            if (email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Admin login successful!");
            new AdminDashboard().setVisible(true);
            dispose();
        });
    }
}

