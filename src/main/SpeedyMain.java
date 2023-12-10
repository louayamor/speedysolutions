package main;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import view.admin.AdminDashboard;
import view.common.HomePage;
import view.common.LoginPage;
import view.common.SignupPage;
import view.freelancer.ServicePage;

public class SpeedyMain {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	new SignupPage();
            }
        });
    }
}
