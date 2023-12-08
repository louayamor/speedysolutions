package view.common;

import javax.swing.*;

import service.PasswordEncryption;
import service.Wrapper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static service.session.UserSession.CURRENT_USER;
import service.AuthService;
import service.session.UserSession;
import view.admin.AdminDashboard;

public class LoginPage extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfUsername;
    private JPasswordField tfPass;
    private JButton btnLogin;
    private JButton btnClose;
    private JLabel labelError;
    
    private AuthService authService;
    private Wrapper wrapper;

    public LoginPage() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
    	authService = new AuthService();
    	wrapper = new Wrapper();
        tfUsername = new JTextField();
        tfPass = new JPasswordField();
        btnLogin = new JButton("Login");
        btnClose = new JButton("Close");
        labelError = new JLabel();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void setupLayout() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(8, 1, 0, 10));
        panel.setBackground(new Color(44, 47, 72));

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Color labelColor = Color.WHITE;

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);

        addLabelAndTextField(panel, "Email		", tfUsername, labelFont, labelColor);
        addLabelAndTextField(panel, "Password", tfPass, labelFont, labelColor);

        JLabel forgotPassLabel = new JLabel("Forgot password?");
        forgotPassLabel.setForeground(Color.WHITE);
        panel.add(forgotPassLabel);
        JLabel haveAccountLabel = new JLabel("Don't have an account?");
        haveAccountLabel.setForeground(Color.WHITE);
        panel.add(haveAccountLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(new Color(44, 47, 72));

        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);

        btnClose.setBackground(new Color(70, 130, 180));
        btnClose.setForeground(Color.WHITE);

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnClose);

        panel.add(buttonPanel);
        
        JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        errorPanel.setBackground(new Color(44, 47, 72));

        labelError.setForeground(Color.RED);
        errorPanel.add(labelError);

        panel.add(errorPanel);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addLabelAndTextField(JPanel panel, String labelText, JTextField textField, Font labelFont, Color labelColor) {
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBackground(new Color(44, 47, 72));

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(labelColor);

        textField.setPreferredSize(new Dimension(200, 25));

        inputPanel.add(label);
        inputPanel.add(textField);

        panel.add(inputPanel);
    }

    private void loginUser() {
        String username = tfUsername.getText().trim();
        String password = PasswordEncryption.cryptage(tfPass.getText().trim());
        
        authService.Authenticate(username, password);

        if (CURRENT_USER.getUser_LoggedIn().getIsBanned()) {
            JOptionPane.showMessageDialog(this, "You are banned", "Ban", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if(CURRENT_USER  == null){
            labelError.setText("User Not Found!");
        }
        
        if (CURRENT_USER != null) {
            if (wrapper.isFreelancer(CURRENT_USER.getUser_LoggedIn().getId())) {
            	HomePage homePage = new HomePage();
                homePage.setVisible(true);
            } else if (wrapper.isAdmin(CURRENT_USER.getUser_LoggedIn().getId())) {
                AdminDashboard ad;
				try {
					ad = new AdminDashboard();
					ad.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            } else if (wrapper.isBusinessOwner(CURRENT_USER.getUser_LoggedIn().getId())) {
            	HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage();
            }
        });
    }
}
