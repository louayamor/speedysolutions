package view.admin;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.UserController;
import entity.User;

public class UpdateUserPopup extends JDialog {

    private JTextField updateUsernameField;
    private JTextField updateEmailField;
    private JComboBox<String> updateRoleComboBox;
    private JButton btnUpdate;

    private UserController uc;
    private User selectedUser;

    public UpdateUserPopup(Frame parent, User selectedUser) {
        super(parent, "Update User", true);
        this.selectedUser = selectedUser;
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        uc = new UserController();

        updateUsernameField = new JTextField(selectedUser.getUsername());
        updateEmailField = new JTextField(selectedUser.getEmail());

        updateRoleComboBox = new JComboBox<>();
        updateRoleComboBox.addItem("Admin");
        updateRoleComboBox.addItem("Freelancer");
        updateRoleComboBox.addItem("Business Owner");

        btnUpdate = new JButton("Update");

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserData();
            }
        });
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(1000, 1000);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 8, 8, 8);

        Color panelColor = new Color(44, 47, 72);
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 2));

        Color textColor = new Color(236, 240, 241);

        addLabelAndComponent(panel, "Username", updateUsernameField, gbc, 0, textColor);
        addLabelAndComponent(panel, "Email", updateEmailField, gbc, 1, textColor);
        addLabelAndComponent(panel, "Role", updateRoleComboBox, gbc, 2, textColor);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnUpdate);
        buttonPanel.setBackground(panelColor);
        panel.add(buttonPanel, gbc);

        getContentPane().add(panel);
        
        
        
        pack();
        setLocationRelativeTo(null);
    }

    private void addLabelAndComponent(JPanel panel, String labelText, JComponent component, GridBagConstraints gbc,
            int yPosition, Color textColor) {
        gbc.gridx = 0;
        gbc.gridy = yPosition;
        gbc.fill = GridBagConstraints.NONE;

        JLabel label = new JLabel(labelText);
        label.setForeground(textColor);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = yPosition;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(component, gbc);
    }

    private void updateUserData() {
        try {
            String updatedUsername = updateUsernameField.getText();
            String updatedEmail = updateEmailField.getText();
            String updatedRole = (String) updateRoleComboBox.getSelectedItem();

            selectedUser.setUsername(updatedUsername);
            selectedUser.setEmail(updatedEmail);
            selectedUser.setRole(updatedRole);

            uc.updateUser(selectedUser);
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
