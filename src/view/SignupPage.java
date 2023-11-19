package view;

import javax.swing.*;
import entity.User;
import controller.UserController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignupPage{
	
	JFrame frame = new JFrame();
	
	private JTextField tfUsername;
    private JTextField tfEmail;
    private JPasswordField tfPass;

    UserController uc = new UserController();
    public SignupPage() {
        initComponents();
        setupLayout();
    }

    @SuppressWarnings("deprecation")
	private void initComponents() {
        tfUsername = new JTextField();
        tfEmail = new JTextField();
        tfPass = new JPasswordField();

        // Add other components as needed
        JButton btnSignup = new JButton("Signup");
        JButton btnCancel = new JButton("Cancel");

        // Set up event listeners if needed
        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	signupAction();
            }

			private void signupAction() {
				// TODO Auto-generated method stub
				
				String username = tfUsername.getText();
		        String email = tfEmail.getText();
		        String password = tfPass.getText();
		       
		        String role = "Freelancer";
		    
		        User u = new User(username, email, password, role);
		        try {
		            if (uc.insertUser(u)) {
		            	JOptionPane.showMessageDialog(null, "Welcome" + username, "Success", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		            	JOptionPane.showMessageDialog(null, "Try again", "Instalance", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(SignupPage.class.getName()).log(Level.SEVERE, null, ex);
		        }
		        frame.dispose();
		    	
			}
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Cancel button action
            }
        });
    }

    private void setupLayout() {
    	JPanel panel = new JPanel(null); 
        panel.setPreferredSize(new Dimension(500, 500));

        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        welcomeLabel.setBounds(200, 10, 200, 30); 
        panel.add(welcomeLabel);

        addLabelAndTextField(panel, "Username:", tfUsername, 50);
        addLabelAndTextField(panel, "Email:", tfEmail, 100);
        addLabelAndTextField(panel, "Password:", tfPass, 150);

        JLabel haveAccountLabel = new JLabel("Already have an account?");
        haveAccountLabel.setBounds(50, 200, 200, 20);
        panel.add(haveAccountLabel);

        JButton btnSignup = new JButton("Signup");
        JButton btnCancel = new JButton("Cancel");

        btnSignup.setBounds(50, 240, 100, 30);
        btnCancel.setBounds(180, 240, 100, 30);

        panel.add(btnSignup);
        panel.add(btnCancel);

        frame.getContentPane().add(panel);
        frame.pack(); // Adjust the size of the JFrame to fit its contents
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addLabelAndTextField(JPanel panel, String labelText, JTextField textField, int yPosition) {
        JLabel label = new JLabel(labelText);
        label.setBounds(50, yPosition, 100, 20);
        panel.add(label);

        textField.setBounds(150, yPosition, 200, 25);
        panel.add(textField);
    }
 

    private JPanel createTextField(JTextField textField, String prompt) {
        JPanel panel = new JPanel(new FlowLayout());
        textField.setPreferredSize(new Dimension(250, 30));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#6aa84f")));
        textField.setBackground(new Color(169, 169, 169));
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Monospaced Regular", Font.PLAIN, 12));
        textField.setToolTipText(prompt);

        panel.add(textField);
        return panel;
    }

    private JPanel createPasswordField(JPasswordField passwordField, String prompt) {
        JPanel panel = new JPanel(new FlowLayout());
        passwordField.setPreferredSize(new Dimension(250, 30));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#6aa84f")));
        passwordField.setBackground(new Color(169, 169, 169));
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Monospaced Regular", Font.PLAIN, 12));
        passwordField.setToolTipText(prompt);

        panel.add(passwordField);
        return panel;
    }
	
}
