package view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.UserController;
import entity.User;

public class AdminDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
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
    private JButton btnReturnMenu;
    private JButton btnPdf;
    private JButton btnStat;

    private UserController uc;

    public AdminDashboard() throws SQLException {
        initComponents();

        setupLayout();
    }

    private void initComponents() throws SQLException {
        uc = new UserController();
        tfSearch = createTextField();
        cbSort = createComboBox();
        tfUsername = createTextField();
        tfEmail = createTextField();
        tfPassword = createPasswordField();
        cbRole = createComboBox();
        btnCreate = createButton("Add");
        tableUser = new JTable();
        btnUpdate = createButton("Update");
        btnDelete = createButton("Delete");
        btnSort = createButton("Sort");
        rbAdmin = createRadioButton("Admins");
        rbClient = createRadioButton("Freelancers");
        rbAll = createRadioButton("All");
        btnReturnMenu = createButton("Return to menu");
        btnPdf = createButton("Pdf");
        btnStat = createButton("Statistics");
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
        String[] columns = {"User ID", "Username", "Email", "Password", "Role", "Authorized", "Ban", "Creation Date",
                "Updated Date"};
        Object[][] userData = uc.showAllUsers();
        System.out.println("Updating table ******************");
        tableUser = new JTable(userData, columns);
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1000, 700));
        panel.setBackground(new Color(44, 47, 72));

        tableUser.setBackground(new Color(240, 240, 240));
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
                    int confirmation = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to delete the user: " + selectedUser.getUsername() + "?",
                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                    if (confirmation == JOptionPane.YES_OPTION) {
                        uc.deleteUser(selectedUser);
                        updateTable();

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a user to delete.", "No User Selected",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        buttonPanel.setBackground(new Color(44, 47, 72));
        buttonPanel.add(btnSort);
        buttonPanel.add(rbAdmin);
        buttonPanel.add(rbClient);
        buttonPanel.add(rbAll);
        buttonPanel.add(btnReturnMenu);
        buttonPanel.add(btnPdf);
        buttonPanel.add(btnStat);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(new JScrollPane(tableUser), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(44, 47, 72));
        formPanel.setPreferredSize(new Dimension(300, 300));

        addLabelAndComponent(formPanel, "Search", tfSearch, 0);
        addLabelAndComponent(formPanel, "Sort", cbSort, 20);
        addLabelAndComponent(formPanel, "Username", tfUsername, 30);
        addLabelAndComponent(formPanel, "Email", tfEmail, 40);
        addLabelAndComponent(formPanel, "Password", tfPassword, 90);
        addLabelAndComponent(formPanel, "Role", cbRole, 60);
        panel.add(formPanel, BorderLayout.WEST);

        getContentPane().setBackground(new Color(44, 47, 72));
        getContentPane().add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        return passwordField;
    }

    private JComboBox<String> createComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(200, 25));
        return comboBox;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        return button;
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        return radioButton;
    }

    private void addLabelAndComponent(JPanel panel, String labelText, JComponent component, int yPosition) {
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = yPosition;
        gbcLabel.anchor = GridBagConstraints.WEST;
        gbcLabel.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel(labelText);
        styleLabel(label);
        panel.add(label, gbcLabel);

        GridBagConstraints gbcComponent = new GridBagConstraints();
        gbcComponent.gridx = 1;
        gbcComponent.gridy = yPosition;
        gbcComponent.anchor = GridBagConstraints.WEST;
        gbcComponent.fill = GridBagConstraints.HORIZONTAL;
        gbcComponent.insets = new Insets(5, 5, 5, 5);
        gbcComponent.gridwidth = 2;

        panel.add(component, gbcComponent);
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public static void main(String[] args) {
        try {
            new AdminDashboard();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
