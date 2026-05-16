package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {

        FlatLightLaf.setup();
        setTitle("Internship Management System");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===================== PALETTE =====================
        Color BLUE_MAIN = new Color(0x00, 0x57, 0xD9);     // #0057D9 (Bleu Vigijobs)
        Color RED_ACCENT = new Color(0xE6, 0x39, 0x46);    // #E63946 (Rouge Vigijobs)
        Color WHITE = new Color(0xFF, 0xFF, 0xFF);         // #FFFFFF
        Color LIGHT_GRAY = new Color(0xF5, 0xF5, 0xF5);    // #F5F5F5
        Color BLACK = new Color(0x00, 0x00, 0x00);         // #000000

        // ===================== HEADER =====================
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(BLUE_MAIN);
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        // Logo NSCS
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/nscs_logo.png"));
            Image logoImg = logoIcon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(logoImg));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            header.add(logo);
        } catch (Exception e) {
            JLabel fallback = new JLabel("NSCS");
            fallback.setForeground(WHITE);
            fallback.setFont(new Font("Segoe UI", Font.BOLD, 28));
            fallback.setAlignmentX(Component.CENTER_ALIGNMENT);
            header.add(fallback);
        }

        JLabel schoolName = new JLabel("NATIONAL SCHOOL OF CYBERSECURITY");
        schoolName.setForeground(WHITE);
        schoolName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        schoolName.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(schoolName);

        JLabel appName = new JLabel("Internship Management System");
        appName.setForeground(WHITE);
        appName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        appName.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(appName);

        add(header, BorderLayout.NORTH);

        // ===================== CENTER (TABLETTE + COLLAGE) =====================
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Tablet mockup
        JPanel tabletFrame = new JPanel(new BorderLayout());
        tabletFrame.setBackground(BLACK);
        tabletFrame.setBorder(BorderFactory.createLineBorder(BLACK, 25, true)); // rounded edges

        try {
            ImageIcon collageIcon = new ImageIcon(getClass().getResource("/collage.png"));
            Image collageImg = collageIcon.getImage().getScaledInstance(750, 400, Image.SCALE_SMOOTH);
            JLabel collage = new JLabel(new ImageIcon(collageImg));
            collage.setHorizontalAlignment(SwingConstants.CENTER);
            tabletFrame.add(collage, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel fallback = new JLabel("Collage image missing", SwingConstants.CENTER);
            fallback.setFont(new Font("Segoe UI", Font.ITALIC, 20));
            fallback.setForeground(Color.GRAY);
            tabletFrame.add(fallback, BorderLayout.CENTER);
        }

        center.add(tabletFrame, BorderLayout.CENTER);

        // ===================== SLOGAN =====================
        JLabel slogan = new JLabel("Your Skills , Our Goal");
        slogan.setForeground(BLUE_MAIN);
        slogan.setFont(new Font("Segoe UI", Font.BOLD, 22));
        slogan.setHorizontalAlignment(SwingConstants.CENTER);
        slogan.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        center.add(slogan, BorderLayout.SOUTH);

        add(center, BorderLayout.CENTER);

        // ===================== BOTTOM BUTTONS =====================
        JPanel bottom = new JPanel(new GridLayout(1, 3, 20, 0));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        bottom.setBackground(WHITE);

        JButton student = new JButton("Student Space");
        JButton company = new JButton("Company Space");
        JButton admin = new JButton("Admin Space");

        Font btnFont = new Font("Segoe UI", Font.BOLD, 16);

        for (JButton b : new JButton[]{student, company, admin}) {
            b.setFont(btnFont);
            b.setBackground(BLUE_MAIN);
            b.setForeground(WHITE);
            b.setFocusPainted(false);
        }

        bottom.add(student);
        bottom.add(company);
        bottom.add(admin);

        add(bottom, BorderLayout.SOUTH);

        // ===================== ACTIONS =====================
        student.addActionListener(e -> {
            new StudentAuthWindow().setVisible(true);
            dispose();
        });

        company.addActionListener(e -> {
            new CompanyAuthWindow().setVisible(true);
            dispose();
        });

        admin.addActionListener(e -> {
            new AdminAuthWindow().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
