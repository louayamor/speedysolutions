package view.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends JFrame {

    private Color leftPanelColor = new Color(44, 47, 72);
    private Color hoverColor = new Color(0, 0, 0, 200);
    private Map<JPanel, Color> originalColors = new HashMap<>();

    public HomePage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Adjusted window size
        setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 800, 600); // Adjusted panel size
        mainPanel.setBackground(leftPanelColor); // Set background color
        mainPanel.setLayout(null);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 150, 600); // Adjusted panel size
        leftPanel.setBackground(leftPanelColor);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        mainPanel.add(leftPanel);

        // Explore Header
        JPanel exploreHeader = new JPanel();
        exploreHeader.setPreferredSize(new Dimension(200, 60)); // Increased height to fit Home item
        exploreHeader.setBackground(leftPanelColor);
        exploreHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(exploreHeader);

        // Home Label inside Explore Header
        JLabel homeLabel = new JLabel("Home");
        homeLabel.setForeground(Color.WHITE);
        homeLabel.setFont(new Font("Lato", Font.BOLD, 17));
        exploreHeader.add(homeLabel);

        // Navigation Items
        String[] navigationItems = {"Profile", "Settings", "Feedback", "Request", "Freelancers"};
        for (String item : navigationItems) {
            JPanel navItem = createNavigationItem(item);
            leftPanel.add(navItem);
        }

        // Logout
        JPanel logoutPanel = createNavigationItem("Log out");
        leftPanel.add(logoutPanel);

        // Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(0, 0, 800, 600); // Adjusted panel size
        rightPanel.setBackground(leftPanelColor); // Set background color
        mainPanel.add(rightPanel);

        // ... Add components to the rightPanel as per your design

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private JPanel createNavigationItem(String label) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 30));
        panel.setBackground(new Color(0, 0, 0, 127));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel textLabel = new JLabel(label);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Lato", Font.PLAIN, 15));
        panel.add(textLabel);

        // Store original background color
        originalColors.put(panel, panel.getBackground());

        // Add MouseListener for onHover effect
        panel.addMouseListener(new NavigationItemMouseListener(panel));

        return panel;
    }

    private class NavigationItemMouseListener extends MouseAdapter {
        private final JPanel panel;

        public NavigationItemMouseListener(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            panel.setBackground(hoverColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            panel.setBackground(originalColors.get(panel));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Implement action on click if needed
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomePage().setVisible(true);
        });
    }
}
