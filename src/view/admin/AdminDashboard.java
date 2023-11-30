package view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.TableModel;

import controller.UserController;
import entity.User;
import entity.UserTableModel;


public class AdminDashboard extends JFrame {

    private JTextField tfSearch;
    private JComboBox<String> cbSort;
    private JTextField tfUsername;
    private JTextField tfEmail;
    private JPasswordField tfPassword;
    private JComboBox<String> cbRole;
    private JButton btnCreate;
    private JTable tableUser;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSort;
    private JRadioButton rbAdmin;
    private JRadioButton rbClient;
    private JRadioButton rbAll;
    private JComboBox<String> cbRechPar;
    private JMenuBar menuBar;
    private JButton btnReturnMenu;
    private JButton btnPdf;
    private JButton btnPrint;
    private JButton btnStat;
    
    private UserController uc;

    public AdminDashboard() throws SQLException {
        initComponents();
        setupLayout();
    }

    private void initComponents() throws SQLException {

        uc = new UserController();
        tfSearch = new JTextField();
        cbSort = new JComboBox<>();
        tfUsername = new JTextField();
        tfEmail = new JTextField();
        tfPassword = new JPasswordField();
        cbRole = new JComboBox<>();
        btnCreate = new JButton("Add");
        String[] columns = {"User ID", "Username", "Email", "Password", "Role", "Authorized", "Ban", "Creation Date", "Updated Date"};
        Object[][] userData = uc.showAllUsers();
        tableUser = new JTable(userData, columns);
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSort = new JButton("Sort");
        rbAdmin = new JRadioButton("Admins");
        rbClient = new JRadioButton("Freelancers");
        rbAll = new JRadioButton("All");
        cbRechPar = new JComboBox<>();
        menuBar = new JMenuBar();
        btnReturnMenu = new JButton("Return to menu");
        btnPdf = new JButton("Pdf");
        btnPrint = new JButton("Print");
        btnStat = new JButton("Statistics");
        
    }

    private void setupLayout() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1000, 700));
        panel.setBackground(new Color(44, 47, 72));

        // Create a JPanel for the buttons and add it to the south
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSort);
        buttonPanel.add(rbAdmin);
        buttonPanel.add(rbClient);
        buttonPanel.add(rbAll);
        buttonPanel.add(cbRechPar);
        buttonPanel.add(menuBar);
        buttonPanel.add(btnReturnMenu);
        buttonPanel.add(btnPdf);
        buttonPanel.add(btnPrint);
        buttonPanel.add(btnStat);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the table to the center
        panel.add(new JScrollPane(tableUser), BorderLayout.CENTER);

        // Add labels and text fields to the west
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        addLabelAndTextField(formPanel, "Search", tfSearch, 10);
        addLabelAndComboBox(formPanel, "Sort", cbSort,20 );
        addLabelAndTextField(formPanel, "Username", tfUsername, 30);
        addLabelAndTextField(formPanel, "Email", tfEmail, 40);
        addLabelAndPasswordField(formPanel, "Password", tfPassword, 50);
        addLabelAndComboBox(formPanel, "Role", cbRole, 60);
        panel.add(formPanel, BorderLayout.WEST);

        getContentPane().setBackground(new Color(44, 47, 72));
        getContentPane().add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    

    private void addLabelAndTextField(JPanel panel, String labelText, JTextField textField, int yPosition) {
        JLabel label = new JLabel(labelText);
        styleLabel(label);
        label.setBounds(50, yPosition, 100, 20);
        panel.add(label);

        textField.setBounds(150, yPosition, 200, 25);
        panel.add(textField);
    }

    private void addLabelAndPasswordField(JPanel panel, String labelText, JPasswordField passwordField, int yPosition) {
        JLabel label = new JLabel(labelText);
        styleLabel(label);
        label.setBounds(50, yPosition, 100, 20);
        panel.add(label);

        passwordField.setBounds(150, yPosition, 200, 25);
        panel.add(passwordField);
    }

    private void addLabelAndComboBox(JPanel panel, String labelText, JComboBox<String> comboBox, int yPosition) {
        JLabel label = new JLabel(labelText);
        styleLabel(label);
        label.setBounds(50, yPosition, 100, 20);
        panel.add(label);

        comboBox.setBounds(150, yPosition, 200, 25);
        panel.add(comboBox);
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
    }

}
