package view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import api.ExportToPdf;
import controller.UserController;
import entity.User;
import service.FormValidation;
import service.PasswordEncryption;

public class AdminDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField tfSearch;
    private JComboBox<String> cbSort;
    private JTextField tfUsername;
    private JTextField tfEmail;
    private JPasswordField tfPassword;
    private JPasswordField tfConfirmPassword;
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
    private JButton btnSearch;

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
        tfConfirmPassword = createPasswordField();
        cbRole = createComboBox();
        btnCreate = createButton("Add");
        tableUser = new JTable();
        btnUpdate = createButton("Update");
        btnDelete = createButton("Delete");
        btnSort = createButton("Sort");
        btnSearch = createButton("Search");
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
        tfConfirmPassword.setText("");
        String[] roles = {"Admin", "Freelancer", "Business Owner"};
        cbRole.removeAllItems();
        for (String role : roles) {
            cbRole.addItem(role);
        }
    }

    private void updateTable() throws SQLException {
        String[] columns = {"User ID", "Username", "Email", "Password", "Role", "Authorized", "Ban", "Creation Date",
                "Updated Date"};
        Object[][] userData = uc.showAllUsers();
        
        System.out.println("Updating table ******************");
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        model.setDataVector(userData, columns);
        tableUser.setBackground(new Color(255, 255, 255)); // Set a new background color
        tableUser.getTableHeader().setBackground(new Color(70, 130, 180)); // Set a new header background color
        tableUser.getTableHeader().setForeground(Color.WHITE);
        tableUser.setRowHeight(30);
        tableUser.setSelectionBackground(new Color(135, 206, 250)); // Set a new selection background color
        tableUser.setSelectionForeground(Color.WHITE);
        tableUser.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Use a modern font
        tableUser.setForeground(Color.DARK_GRAY);
    }

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1200, 600));

        // Top Panel for Search and Radio Buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(44, 47, 72));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(new Color(44, 47, 72));
        searchPanel.add(createWhiteLabel("Search")); 
        searchPanel.add(createRoundedTextField(tfSearch)); 
        searchPanel.add(btnSearch);
        topPanel.add(searchPanel, BorderLayout.WEST);
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUser();
            }
        });

        // Radio Buttons Panel
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        radioPanel.setBackground(new Color(44, 47, 72));
        radioPanel.add(rbAdmin);
        radioPanel.add(rbClient);
        radioPanel.add(rbAll);
        radioPanel.add(btnSort);
        
        
        topPanel.add(radioPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel for User Information and Operations
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(800, 500));
        centerPanel.setBackground(new Color(44, 47, 72));

        // Left Panel for User Information and Operations
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(44, 47, 72));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        

        addLabelAndComponent(leftPanel, "Username", tfUsername, gbc, 0);
        addLabelAndComponent(leftPanel, "Email", tfEmail, gbc, 1);
        addLabelAndComponent(leftPanel, "Password", tfPassword, gbc, 2);
        addLabelAndComponent(leftPanel, "Confirm Password", tfConfirmPassword, gbc, 3);
        addLabelAndComponent(leftPanel, "Role", cbRole, gbc, 4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.setBackground(new Color(44, 47, 72));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        leftPanel.add(buttonPanel, gbc);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = tfUsername.getText();
                    String email = tfEmail.getText();
                    char[] password = tfPassword.getPassword();
                    char[] confirmPassword = tfConfirmPassword.getPassword();
                    String role = (String) cbRole.getSelectedItem();

                    if (FormValidation.validateSignupFields(username, email, password)) {
                        if (Arrays.equals(password, confirmPassword)) {
                            String hashedPassword = PasswordEncryption.cryptage(new String(password));

                            
                            User newUser = new User(username, hashedPassword.toCharArray(), email, role);

                            uc.insertUser(newUser);

                        } else {
                            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    updateTable();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

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
        
        btnUpdate.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            	User selectedUser = getSelectedUser();

                                // Open the UpdateUserPopup
                                UpdateUserPopup updatePopup = new UpdateUserPopup(AdminDashboard.this, selectedUser);
                                updatePopup.setVisible(true);

                                try {
                                    updateTable();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });


        // Right Panel for User Table
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(44, 47, 72));
        rightPanel.add(new JScrollPane(tableUser), BorderLayout.CENTER);

        centerPanel.add(leftPanel, BorderLayout.WEST);
        centerPanel.add(rightPanel, BorderLayout.CENTER);

        // Bottom Panel with Control Buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnReturnMenu);
        bottomPanel.add(btnPdf);
        
        btnPdf.addActionListener(e -> {
        	JFileChooser fileChooser = new JFileChooser();

            // Show the save dialog
            int userSelection = fileChooser.showSaveDialog(AdminDashboard.this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File fileToSave = fileChooser.getSelectedFile();

                // Convert the file path to a string
                String filePath = fileToSave.getAbsolutePath();

                // Call the exportTableToPdf method with the table and chosen file path
                try {
					ExportToPdf.exportTableToPdf(tableUser, filePath);
				} catch (IOException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        bottomPanel.add(btnStat);
        
        btnStat.addActionListener(e -> {
           
                // Open the Statistics page
                try {
					StatisticsPage statisticsPage = new StatisticsPage();
					statisticsPage.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            
        });

        bottomPanel.setBackground(new Color(44, 47, 72));

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(new Color(44, 47, 72));
        getContentPane().add(mainPanel);
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

    private void addLabelAndComponent(JPanel panel, String labelText, JComponent component, GridBagConstraints gbc, int yPosition) {
        gbc.gridx = 0;
        gbc.gridy = yPosition;
        gbc.fill = GridBagConstraints.NONE;

        JLabel label = new JLabel(labelText);
        styleLabel(label);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = yPosition;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(component, gbc);
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
    }
    
    private JLabel createWhiteLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }
    
    private JTextField createRoundedTextField(JTextField textField) {
        textField.setPreferredSize(new Dimension(200, 25));
        textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Set a white border
        textField.setMargin(new Insets(5, 5, 5, 5)); // Set margins for better appearance
        textField.setOpaque(false); // Make the text field transparent
        textField.setBackground(new Color(44, 47, 72)); // Set the background color
        textField.setForeground(Color.WHITE); // Set the text color
        textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Set a white border
        return textField;
    }
    
    private User getSelectedUser() {
        int selectedRowIndex = tableUser.getSelectedRow();

        if (selectedRowIndex != -1) {
        	int userId = (int) tableUser.getValueAt(selectedRowIndex, 0);
            String username = (String) tableUser.getValueAt(selectedRowIndex, 1);
            String email = (String) tableUser.getValueAt(selectedRowIndex, 2);
            
            String role= (String) tableUser.getValueAt(selectedRowIndex, 4);

            return new User(userId,username, email, role);
        }

        return null; // No row selected
    }
    
    private void searchUser() {
    	try {
            String username = tfSearch.getText(); 
            List<User> userList = uc.searchUser(username);
            
            displayResults(userList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void displayResults(List<User> userList) {
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        model.setRowCount(0);
        for (User user : userList) {
            model.addRow(new Object[]{user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                    user.getRole(), user.getIsVerified(), user.getIsBanned(), user.getCreatedAt(), user.getUpdatedAt()});
        }
    }
 
    
    
    
    public static void main(String[] args) {
        try {
            new AdminDashboard();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
