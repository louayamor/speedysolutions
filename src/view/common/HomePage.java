package view.common;

import javax.swing.*;

import controller.UserController;
import entity.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends JFrame {

    private Color leftPanelColor = new Color(44, 47, 72);
    private Color hoverColor = new Color(0, 0, 0, 200);
    private Map<JPanel, Color> originalColors = new HashMap<>();

    private JPanel rightPanel;

    public HomePage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 800, 600);
        mainPanel.setBackground(leftPanelColor);
        mainPanel.setLayout(null);

        JPanel leftPanel = createLeftPanel();
        mainPanel.add(leftPanel);

        rightPanel = new JPanel();
        rightPanel.setBounds(150, 0, 650, 600);
        rightPanel.setBackground(leftPanelColor);
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(rightPanel);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 150, 600);
        leftPanel.setBackground(leftPanelColor);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Navigation Item: Freelancers
        JPanel freelancersPanel = createNavigationItem("Freelancers");
        freelancersPanel.addMouseListener(new NavigationItemMouseListener(freelancersPanel) {
            @Override
            public void mouseClicked(MouseEvent e) {
                showFreelancers();
            }
        });
        leftPanel.add(freelancersPanel);

        // Navigation Item: Profile
        JPanel profilePanel = createNavigationItem("Profile");
        profilePanel.addMouseListener(new NavigationItemMouseListener(profilePanel) {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement action for Profile click
                JOptionPane.showMessageDialog(null, "Profile Clicked");
            }
        });
        leftPanel.add(profilePanel);

        // Navigation Item: Settings
        JPanel settingsPanel = createNavigationItem("Settings");
        settingsPanel.addMouseListener(new NavigationItemMouseListener(settingsPanel) {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement action for Settings click
                JOptionPane.showMessageDialog(null, "Settings Clicked");
            }
        });
        leftPanel.add(settingsPanel);

        // Add more navigation items as needed

        return leftPanel;
    }

    private void showFreelancers() {
        UserController userController = new UserController();
        JList<User> freelancerList = userController.showFreelancers();

        // Clear the rightPanel and add the freelancerList
        rightPanel.removeAll();
        rightPanel.add(createFreelancerCards(freelancerList)); // Use a custom method to create cards
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private JPanel createFreelancerCards(JList<User> freelancerList) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Adjust layout based on your design
        cardPanel.setBackground(new Color(64, 68, 106)); // Set a lighter color for the card panel

        // Customize the appearance of each card
        freelancerList.setCellRenderer(new FreelancerCardRenderer());

        for (int i = 0; i < freelancerList.getModel().getSize(); i++) {
            User user = freelancerList.getModel().getElementAt(i);

            // Create a custom panel for each freelancer
            JPanel freelancerCard = createFreelancerCard(user);
            cardPanel.add(freelancerCard);
        }

        return cardPanel;
    }


    private JPanel createFreelancerCard(User user) {
        JPanel card = new JPanel();
        card.setLayout(new FlowLayout(FlowLayout.LEFT));
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        card.setBackground(leftPanelColor); // Set background color

        // Add an image label to the card
        ImageIcon imageIcon = new ImageIcon("src/asset/image/try.png"); // Provide the correct path
        Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);
        card.add(imageLabel);

        // Add some spacing between image and text
        card.add(Box.createHorizontalStrut(10));

        // Create a panel for username and email
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username: " + user.getUsername());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());

        // Customize label font and style
        Font boldFont = new Font("Arial", Font.BOLD, 16);
        Font regularFont = new Font("Arial", Font.PLAIN, 14);

        usernameLabel.setFont(boldFont);
        emailLabel.setFont(regularFont);

        textPanel.add(usernameLabel);
        textPanel.add(emailLabel);

        card.add(textPanel);

        return card;
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

        originalColors.put(panel, panel.getBackground());
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
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
