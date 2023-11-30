package main;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import view.admin.AdminDashboard;
import view.common.LoginPage;
import view.common.SignupPage;

public class SpeedyMain {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
					new AdminDashboard();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
}
