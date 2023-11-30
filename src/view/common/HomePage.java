package view.common;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class HomePage extends JFrame {

	 public HomePage() {
	        initComponents();
	        setupLayout();
	    }

	    private void initComponents() {
	        JLabel welcomeLabel = new JLabel("Welcome to the Homepage!");
	        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

	        JButton logoutButton = new JButton("Logout");
	        logoutButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                openLoginPage();
	            }
	        });

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        getContentPane().setLayout(new BorderLayout());
	        getContentPane().add(welcomeLabel, BorderLayout.CENTER);
	        getContentPane().add(logoutButton, BorderLayout.SOUTH);
	        pack();
	    }

	    private void setupLayout() {
	        setLocationRelativeTo(null);
	        setVisible(true);
	    }

	    private void openLoginPage() {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new LoginPage().setVisible(true);
	                dispose();
	            }
	        });
	  }
}
