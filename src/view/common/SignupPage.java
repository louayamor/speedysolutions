package view.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import api.Mailing;
import controller.UserController;
import entity.User;
import service.FormValidation;
import service.PasswordEncryption;

public class SignupPage extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfUsername;
    private JTextField tfEmail;
    private JPasswordField tfPass;
    private JComboBox<String> cbRole;

    private UserController uc = new UserController();

    public SignupPage() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        tfUsername = new JTextField();
        tfEmail = new JTextField();
        tfPass = new JPasswordField();
        cbRole = new JComboBox<>(new String[]{"Freelancer", "Business Owner"});
    }

    private void setupLayout() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(450, 650));
        panel.setBackground(new Color(44, 47, 72));

        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(200, 10, 200, 30);
        panel.add(welcomeLabel);

        addLabelAndTextField(panel, "Username:", tfUsername, 50);
        addLabelAndTextField(panel, "Email:", tfEmail, 100);
        addLabelAndTextField(panel, "Password:", tfPass, 150);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setBounds(50, 200, 100, 20);
        panel.add(roleLabel);

        cbRole.setBounds(150, 200, 200, 25);
        panel.add(cbRole);

        JButton btnSignup = new JButton("Signup");
        JButton btnCancel = new JButton("Back to login");
        btnSignup.setBackground(new Color(70, 130, 180));
        btnCancel.setBackground(new Color(128, 128, 128));

        btnSignup.setForeground(Color.WHITE);
        btnCancel.setForeground(Color.WHITE);

        btnSignup.setBounds(50, 280, 100, 30);
        btnCancel.setBounds(180, 280, 150, 30);

        panel.add(btnSignup);

        JLabel haveAccountLabel = new JLabel("Already have an account? Click here to login.");
        haveAccountLabel.setForeground(Color.white);
        haveAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        haveAccountLabel.setBounds(50, 240, 300, 20);
        haveAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame loginFrame = new LoginPage();
                        loginFrame.setVisible(true);
                        dispose();
                    }
                });
            }
        });
        panel.add(haveAccountLabel);

        panel.add(btnCancel);

        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FormValidation.validateSignupFields(tfUsername.getText(), tfEmail.getText(), tfPass.getPassword())) {
                    signupAction();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame loginFrame = new LoginPage();
                        loginFrame.setVisible(true);
                        dispose();
                    }
                });
            }
        });

        ImageIcon imageIcon = new ImageIcon("src/asset/image/freelancepic.jpg");
        Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(resizedIcon);
        imageLabel.setBounds(0, 400, 500, 100);
        panel.add(imageLabel);

        getContentPane().add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void signupAction() {
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = PasswordEncryption.cryptage(tfPass.getPassword().toString());
        String role = (String) cbRole.getSelectedItem();

        User u = new User(username, password.toCharArray(), email, role);
        try {
            if (uc.insertUser(u)) {
            	sendConfirmationEmail(username,email);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                            if (role.equals("Freelancer")) {
                                new HomePage().setVisible(true);
                            } else {
                                new HomePage().setVisible(true);
                            }
                        dispose();
    
                    }
                });
            } else {
                JOptionPane.showMessageDialog(null, "Try again", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignupPage.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    private void sendConfirmationEmail(String username,String userEmail) {
        try {
            // Modify the parameters according to your needs
            String subject = "Signup Confirmation";
            String body = "Dear user,\n\nThank you for signing up!";

            Mailing.sendEmail(userEmail, subject, body);

            System.out.println("Confirmation email sent successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to send confirmation email.", "Email Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLabelAndTextField(JPanel panel, String labelText, JTextField textField, int yPosition) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE); // Set label text color
        label.setBounds(50, yPosition, 100, 20);
        panel.add(label);

        textField.setBounds(150, yPosition, 200, 25);
        panel.add(textField);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SignupPage();
            }
        });
    }
}
