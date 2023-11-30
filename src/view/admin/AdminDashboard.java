package view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    private void initComponents() throws SQLException{
    	uc = new UserController();
    	       
        tfSearch = new JTextField();
        cbSort = new JComboBox<>();
        tfUsername = new JTextField();
        tfEmail = new JTextField();
        tfPassword = new JPasswordField();
        cbRole = new JComboBox<>();
        btnCreate = new JButton("Add");       
        tableUser = new JTable();
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
        init();
    }
    
    public void init() throws SQLException {
        updateTable();
        tfUsername.setText("");
        tfEmail.setText("");
        tfPassword.setText("");
        cbRole.removeAllItems();
    }
    
    private void updateTable() throws SQLException {
    	String[] columns = {"User ID", "Username", "Email", "Password", "Role", "Authorized", "Ban", "Creation Date", "Updated Date"};
        Object[][] userData = uc.showAllUsers();   	
        System.out.println("Updating table ************************");
        tableUser = new JTable(userData, columns);
    }

    private void setupLayout() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1000, 700));
        panel.setBackground(new Color(44, 47, 72));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        
        buttonPanel.add(btnDelete);
        btnDelete.addActionListener(e -> {
        	try {
                int selectedRow = tableUser.getSelectedRow();
                if (selectedRow != -1) {
                    int userId = (int) tableUser.getValueAt(selectedRow, 0);
                    User selectedUser = uc.selectOneUserByID(userId);
                    int confirmation = JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete the user: " + selectedUser.getUsername() + "?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                    );
                    
                    if (confirmation == JOptionPane.YES_OPTION) {
                        uc.deleteUser(selectedUser);
                        updateTable();
                        
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a user to delete.", "No User Selected", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }});
        	     
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
        panel.add(new JScrollPane(tableUser), BorderLayout.CENTER);

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
        panel.add(label);

        panel.add(textField);
    }

    private void addLabelAndPasswordField(JPanel panel, String labelText, JPasswordField passwordField, int yPosition) {
        JLabel label = new JLabel(labelText);
        styleLabel(label);
        panel.add(label);

        panel.add(passwordField);
    }

    private void addLabelAndComboBox(JPanel panel, String labelText, JComboBox<String> comboBox, int yPosition) {
        JLabel label = new JLabel(labelText);
        styleLabel(label);
        panel.add(label);

        panel.add(comboBox);
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
    }  
}
