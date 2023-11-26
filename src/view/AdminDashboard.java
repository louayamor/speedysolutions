package view;

import java.awt.Dimension;

import javax.swing.*;


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

    public AdminDashboard() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        // Initialize Swing components
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
    }

    private void setupLayout() {
        // Set up the layout and add components to containers
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(1320, 800));

        // Add components to the panel (adjust positions accordingly)
        panel.add(tfSearch);
        panel.add(cbSort);
        panel.add(tfUsername);
        panel.add(tfEmail);
        panel.add(tfPassword);
        panel.add(cbRole);
        panel.add(btnCreate);
        panel.add(tableUser);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnSort);
        panel.add(rbAdmin);
        panel.add(rbClient);
        panel.add(rbAll);
        panel.add(cbRechPar);
        panel.add(menuBar);
        panel.add(btnReturnMenu);
        panel.add(btnPdf);
        panel.add(btnPrint);
        panel.add(btnStat);

        // Add the panel to the frame
        getContentPane().add(panel);

        // Set up other frame properties
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
