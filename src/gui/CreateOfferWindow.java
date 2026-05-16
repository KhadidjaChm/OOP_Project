package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.Company;
import models.InternshipOffer;
import services.InternshipService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class CreateOfferWindow extends JFrame {

    private final Company company;
    private final InternshipService internshipService;

    public CreateOfferWindow(Company company, InternshipService internshipService) {
        this.company = company;
        this.internshipService = internshipService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Create Internship Offer");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Create a New Internship Offer", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel form = new JPanel(new GridLayout(8, 2, 12, 12));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField titleField = new JTextField();
        JTextField domainField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField deadlineField = new JTextField(); // yyyy-mm-dd
        JTextArea descriptionArea = new JTextArea(4, 20);

        form.add(new JLabel("Title:"));
        form.add(titleField);

        form.add(new JLabel("Domain:"));
        form.add(domainField);

        form.add(new JLabel("Location:"));
        form.add(locationField);

        form.add(new JLabel("Duration (months):"));
        form.add(durationField);

        form.add(new JLabel("Salary:"));
        form.add(salaryField);

        form.add(new JLabel("Deadline (yyyy-mm-dd):"));
        form.add(deadlineField);

        form.add(new JLabel("Description:"));
        form.add(new JScrollPane(descriptionArea));

        add(form, BorderLayout.CENTER);

        // ===== BUTTON =====
        JButton createBtn = new JButton("Create Offer");
        createBtn.setBackground(new Color(0, 120, 215));
        createBtn.setForeground(Color.WHITE);
        createBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        createBtn.setFocusPainted(false);

        JPanel btnPanel = new JPanel();
        btnPanel.add(createBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // ===== ACTION =====
        createBtn.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String domain = domainField.getText().trim();
                String location = locationField.getText().trim();
                String durationStr = durationField.getText().trim();
                String salaryStr = salaryField.getText().trim();
                String deadlineStr = deadlineField.getText().trim();
                String description = descriptionArea.getText().trim();

                if (title.isEmpty() || domain.isEmpty() || location.isEmpty() ||
                        durationStr.isEmpty() || salaryStr.isEmpty() ||
                        deadlineStr.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required.");
                    return;
                }

                int duration = Integer.parseInt(durationStr);
                double salary = Double.parseDouble(salaryStr);
                LocalDate deadline = LocalDate.parse(deadlineStr);

                InternshipOffer offer = new InternshipOffer(
                        title,
                        description,
                        domain,
                        deadline,
                        location,
                        duration,
                        salary,
                        company
                );

                internshipService.createOffer(offer);

                JOptionPane.showMessageDialog(this, "Offer created successfully!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}

