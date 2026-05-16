package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.Company;
import models.InternshipOffer;
import services.InternshipService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ManageOffersWindow extends JFrame {

    private final Company company;
    private final InternshipService internshipService;

    public ManageOffersWindow(Company company, InternshipService internshipService) {
        this.company = company;
        this.internshipService = internshipService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Manage Your Internship Offers");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Your Internship Offers", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== TABLE =====
        String[] columns = {
                "Title", "Domain", "Location", "Duration", "Salary", "Deadline"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load offers created by this company
        List<InternshipOffer> offers = internshipService.getOffersByCompany(company);

        for (InternshipOffer offer : offers) {
            model.addRow(new Object[]{
                    offer.getTitle(),
                    offer.getDomain(),
                    offer.getLocation(),
                    offer.getDuration() + " months",
                    offer.getSalary(),
                    offer.getDeadline()
            });
        }

        // ===== BUTTONS =====
        JButton editBtn = new JButton("Edit Offer");
        editBtn.setBackground(new Color(0, 120, 215));
        editBtn.setForeground(Color.WHITE);
        editBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton deleteBtn = new JButton("Delete Offer");
        deleteBtn.setBackground(new Color(200, 60, 60));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel btnPanel = new JPanel();
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ===== ACTIONS =====

        // ✔ Edit Offer
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an offer to edit.");
                return;
            }

            InternshipOffer selectedOffer = offers.get(row);

            // Open edit dialog
            openEditDialog(selectedOffer);

            // Refresh window
            dispose();
            new ManageOffersWindow(company, internshipService).setVisible(true);
        });

        // ✔ Delete Offer
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an offer to delete.");
                return;
            }

            InternshipOffer selectedOffer = offers.get(row);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this offer?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                internshipService.deleteOffer(selectedOffer);
                JOptionPane.showMessageDialog(this, "Offer deleted successfully!");

                dispose();
                new ManageOffersWindow(company, internshipService).setVisible(true);
            }
        });
    }

    // ===== EDIT DIALOG =====
    private void openEditDialog(InternshipOffer offer) {
        JTextField titleField = new JTextField(offer.getTitle());
        JTextField domainField = new JTextField(offer.getDomain());
        JTextField locationField = new JTextField(offer.getLocation());
        JTextField durationField = new JTextField(String.valueOf(offer.getDuration()));
        JTextField salaryField = new JTextField(String.valueOf(offer.getSalary()));
        JTextField deadlineField = new JTextField(offer.getDeadline().toString());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Domain:"));
        panel.add(domainField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("Duration (months):"));
        panel.add(durationField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);
        panel.add(new JLabel("Deadline (yyyy-mm-dd):"));
        panel.add(deadlineField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Offer",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            offer.setTitle(titleField.getText().trim());
            offer.setDomain(domainField.getText().trim());
            offer.setLocation(locationField.getText().trim());
            offer.setDuration(Integer.parseInt(durationField.getText().trim()));
            offer.setSalary(Double.parseDouble(salaryField.getText().trim()));
            offer.setDeadline(LocalDate.parse(deadlineField.getText().trim()));

            internshipService.updateOffer(offer);

            JOptionPane.showMessageDialog(this, "Offer updated successfully!");
        }
    }
}
