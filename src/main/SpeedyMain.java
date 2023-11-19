package main;

import javax.swing.SwingUtilities;

import view.LoginPage;

public class SpeedyMain {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage();
            }
        });
    }

}
