package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.InternshipOffer;
import models.Student;
import services.ApplicationService;
import services.InternshipService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentDashboard extends JFrame {

    private final Student student;
    private final InternshipService internshipService;
    private final ApplicationService applicationService;

    public StudentDashboard(Student student,
                            InternshipService internshipService,
                            ApplicationService applicationService) {

        this.student = student;
        this.internshipService = internshipService;
        this.applicationService = applicationService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Student Dashboard");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Welcome, " + student.getFirstName() + "!", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== TABLE OF OFFERS =====
        String[] columns = {"Title", "Company", "Domain", "Location", "Duration", "Deadline"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load offers from service
        List<InternshipOffer> offers = internshipService.getAllOffers();
        for (InternshipOffer offer : offers) {
            model.addRow(new Object[]{
                    offer.getTitle(),
                    offer.getCompany().getName(),
                    offer.getDomain(),
                    offer.getLocation(),
                    offer.getDuration() + " months",
                    offer.getDeadline()
            });
        }

        // ===== APPLY BUTTON =====
        JButton applyBtn = new JButton("Apply to Selected Offer");
        applyBtn.setBackground(new Color(0, 120, 215));
        applyBtn.setForeground(Color.WHITE);
        applyBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        applyBtn.setFocusPainted(false);

        JPanel btnPanel = new JPanel();
        btnPanel.add(applyBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // ===== ACTION: APPLY TO OFFER =====
        applyBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an offer first.");
                return;
            }

            InternshipOffer selectedOffer = offers.get(selectedRow);

            new ApplyToOfferWindow(student, selectedOffer, applicationService).setVisible(true);
        });
    }
}
