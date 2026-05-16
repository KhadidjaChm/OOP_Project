package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.Application;
import services.AdminService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminValidationWindow extends JFrame {

    private final AdminService adminService;

    public AdminValidationWindow(AdminService adminService) {
        this.adminService = adminService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Admin - Validate Applications");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Pending Applications", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== TABLE =====
        String[] columns = {
                "Student", "Email", "Offer Title", "Company", "Status", "Motivation"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load applications
        List<Application> apps = adminService.getAllApplications();
        for (Application app : apps) {
            model.addRow(new Object[]{
                    app.getStudent().getFirstName() + " " + app.getStudent().getLastName(),
                    app.getStudent().getEmail(),
                    app.getOffer().getTitle(),
                    app.getOffer().getCompany().getName(),
                    app.getStatus(),
                    app.getMotivation()
            });
        }

        // ===== BUTTONS =====
        JButton acceptBtn = new JButton("Accept");
        acceptBtn.setBackground(new Color(76, 175, 80));
        acceptBtn.setForeground(Color.WHITE);
        acceptBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton rejectBtn = new JButton("Reject");
        rejectBtn.setBackground(new Color(200, 60, 60));
        rejectBtn.setForeground(Color.WHITE);
        rejectBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel btnPanel = new JPanel();
        btnPanel.add(acceptBtn);
        btnPanel.add(rejectBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ===== ACTIONS =====

        // ✔ Accept application
        acceptBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an application first.");
                return;
            }

            Application selectedApp = apps.get(row);

            adminService.acceptApplication(selectedApp);
            JOptionPane.showMessageDialog(this, "Application accepted!");

            dispose();
            new AdminValidationWindow(adminService).setVisible(true);
        });

        // ✔ Reject application
        rejectBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an application first.");
                return;
            }

            Application selectedApp = apps.get(row);

            adminService.rejectApplication(selectedApp);
            JOptionPane.showMessageDialog(this, "Application rejected!");

            dispose();
            new AdminValidationWindow(adminService).setVisible(true);
        });
    }
}
