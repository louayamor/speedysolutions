package view.common;

import javax.swing.*;
import entity.User;
import service.FormValidation;
import view.admin.AdminDashboard;
import controller.UserController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignupPage extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame frame = new JFrame();
	private JTextField tfUsername;
    private JTextField tfEmail;
    private JPasswordField tfPass;

    UserController uc = new UserController();
    public SignupPage() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        tfUsername = new JTextField();
        tfEmail = new JTextField();
        tfPass = new JPasswordField();
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

        /*JLabel haveAccountLabel = new JLabel("Already have an account? Click here to login.");
        haveAccountLabel.setForeground(Color.BLUE);
        haveAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        haveAccountLabel.setBounds(50, 240, 300, 20);
        haveAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                    	JFrame loginFrame = new LoginPage();
                        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        loginFrame.pack();
                        loginFrame.setLocationRelativeTo(null);
                        loginFrame.setVisible(true);
                        frame.dispose();
                    }
                });
            }
        });
        panel.add(haveAccountLabel);  */

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
                        frame.dispose();
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
                        
                    }
                });
                frame.dispose();
                
            }
        });
        
        ImageIcon imageIcon = new ImageIcon("src/asset/image/freelancepic.jpg"); 
        Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(resizedIcon);
        imageLabel.setBounds(0, 400, 500, 100);
        panel.add(imageLabel);
        
        frame.getContentPane().add(panel);
        frame.pack(); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
    }
    
    private void signupAction() {
		// TODO Auto-generated method stub
		
		String username = tfUsername.getText();
        String email = tfEmail.getText();
        char[] password = tfPass.getPassword();
       
        String role = "Admin";
    
        User u = new User(username, password, email, role);
        try {
            if (uc.insertUser(u)) {
            	SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
							new AdminDashboard().setVisible(true);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }});
            } else {
            	JOptionPane.showMessageDialog(null, "Try again", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignupPage.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        frame.dispose();
    	
	}

    private void addLabelAndTextField(JPanel panel, String labelText, JTextField textField, int yPosition) {
    	JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE); // Set label text color
        label.setBounds(50, yPosition, 100, 20);
        panel.add(label);

        textField.setBounds(150, yPosition, 200, 25);
        panel.add(textField);
    }
 

    @SuppressWarnings("unused")
	private JPanel createTextField(JTextField textField, String prompt) {
        JPanel panel = new JPanel(new FlowLayout());
        textField.setPreferredSize(new Dimension(250, 30));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#6aa84f")));
        textField.setBackground(new Color(169, 169, 169));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Monospaced Regular", Font.PLAIN, 12));
        textField.setToolTipText(prompt);

        panel.add(textField);
        return panel;
    }

    @SuppressWarnings("unused")
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
