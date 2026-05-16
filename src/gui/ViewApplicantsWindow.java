package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.Application;
import models.Company;
import services.ApplicationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewApplicantsWindow extends JFrame {

    private final Company company;
    private final ApplicationService applicationService;

    public ViewApplicantsWindow(Company company, ApplicationService applicationService) {
        this.company = company;
        this.applicationService = applicationService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Applicants for Your Offers");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Applicants for Your Internship Offers", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== TABLE =====
        String[] columns = {
                "Student", "Email", "Offer Title", "Motivation", "Status"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ===== LOAD APPLICATIONS FOR THIS COMPANY =====
        List<Application> apps = applicationService.getApplicationsForCompany(company);

        for (Application app : apps) {
            model.addRow(new Object[]{
                    app.getStudent().getFirstName() + " " + app.getStudent().getLastName(),
                    app.getStudent().getEmail(),
                    app.getOffer().getTitle(),
                    app.getMotivation(),
                    app.getStatus()
            });
        }

        // ===== CLOSE BUTTON =====
        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(200, 60, 60));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel btnPanel = new JPanel();
        btnPanel.add(closeBtn);
        add(btnPanel, BorderLayout.SOUTH);

        closeBtn.addActionListener(e -> dispose());
    }
}

