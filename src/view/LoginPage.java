package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {

	private JTextField tfEmail;
    private JPasswordField tfPass;
    private JCheckBox chSeePass;
    private JButton btnContinue;
    private JButton btnClose;

    public LoginPage() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        tfEmail = new JTextField();
        tfPass = new JPasswordField();
        chSeePass = new JCheckBox("Show password");
        btnContinue = new JButton("Continue");
        btnClose = new JButton("Close");

        // Set up event listeners if needed
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Continue button action
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Close button action
            }
        });
    }

    private void setupLayout() {
    	JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        panel.add(new JLabel("Login", SwingConstants.CENTER));
        panel.add(tfEmail);
        panel.add(tfPass);
        panel.add(chSeePass);
        panel.add(new JLabel("Forgot password ?", SwingConstants.CENTER));
        panel.add(new JLabel("Already have an account ?", SwingConstants.CENTER));
        panel.add(btnContinue);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
