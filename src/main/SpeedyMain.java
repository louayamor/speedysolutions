package main;

import javax.swing.SwingUtilities;

import view.LoginPage;
import view.SignupPage;

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
