package gui;

import com.formdev.flatlaf.FlatLightLaf;
import models.Student;
import models.InternshipOffer;
import services.ApplicationService;

import javax.swing.*;
import java.awt.*;

public class ApplyToOfferWindow extends JFrame {

    private final Student student;
    private final InternshipOffer offer;
    private final ApplicationService applicationService;

    public ApplyToOfferWindow(Student student,
                              InternshipOffer offer,
                              ApplicationService applicationService) {

        this.student = student;
        this.offer = offer;
        this.applicationService = applicationService;

        // UI Theme
        FlatLightLaf.setup();

        setTitle("Apply to Internship Offer");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel header = new JLabel("Apply to: " + offer.getTitle(), SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(header, BorderLayout.NORTH);

        // ===== OFFER INFO =====
        JPanel info = new JPanel(new GridLayout(5, 1, 5, 5));
        info.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        info.add(new JLabel("Company: " + offer.getCompany().getName()));
        info.add(new JLabel("Domain: " + offer.getDomain()));
        info.add(new JLabel("Location: " + offer.getLocation()));
        info.add(new JLabel("Duration: " + offer.getDuration() + " months"));
        info.add(new JLabel("Deadline: " + offer.getDeadline()));

        add(info, BorderLayout.CENTER);

        // ===== MOTIVATION LETTER =====
        JPanel motivationPanel = new JPanel(new BorderLayout());
        motivationPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JLabel motivationLabel = new JLabel("Motivation Letter:");
        JTextArea motivationArea = new JTextArea(5, 20);
        motivationArea.setLineWrap(true);
        motivationArea.setWrapStyleWord(true);

        motivationPanel.add(motivationLabel, BorderLayout.NORTH);
        motivationPanel.add(new JScrollPane(motivationArea), BorderLayout.CENTER);

        add(motivationPanel, BorderLayout.SOUTH);

        // ===== APPLY BUTTON =====
        JButton applyBtn = new JButton("Apply");
        applyBtn.setBackground(new Color(0, 120, 215));
        applyBtn.setForeground(Color.WHITE);
        applyBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        applyBtn.setFocusPainted(false);

        JPanel btnPanel = new JPanel();
        btnPanel.add(applyBtn);
        add(btnPanel, BorderLayout.PAGE_END);

        // ===== ACTION =====
        applyBtn.addActionListener(e -> {
            try {
                String motivation = motivationArea.getText().trim();

                if (motivation.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please write a motivation letter.");
                    return;
                }

                applicationService.apply(student, offer, motivation);

                JOptionPane.showMessageDialog(this, "Application submitted successfully!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
