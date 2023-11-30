package view.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
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

        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        addLabelAndTextField(panel, "Email:", tfEmail, labelFont, labelColor);
        addLabelAndTextField(panel, "Password:", tfPass, labelFont, labelColor);

        chSeePass.setForeground(Color.WHITE);
        panel.add(chSeePass);

        JLabel forgotPassLabel = new JLabel("Forgot password?");
        forgotPassLabel.setForeground(Color.WHITE);
        JLabel haveAccountLabel = new JLabel("Don't have an account?");
        haveAccountLabel.setForeground(Color.WHITE);

        panel.add(forgotPassLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnContinue.setBackground(new Color(70, 130, 180));
        btnContinue.setForeground(Color.WHITE);

        buttonPanel.add(btnContinue);

        panel.add(buttonPanel);

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

        inputPanel.add(label);
        inputPanel.add(textField);

        panel.add(inputPanel);
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
