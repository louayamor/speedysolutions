package entity;

import java.util.List;

import javax.swing.DefaultListModel;

public class UserTableModel {

	private final List<User> userList;
    private final String[] columnNames = {"ID", "Username", "Email", "Role", "Is Verified", "Is Banned", "Created At", "Updated At"};

    public UserTableModel(DefaultListModel<User> usersListModel) {
        this.userList = (List<User>) usersListModel;
    }

    public int getRowCount() {
        return userList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getUsername();
            case 2:
                return user.getEmail();
            case 3:
                return user.getRole();
            case 4:
                return user.getIsVerified();
            case 5:
                return user.getIsBanned();
            case 6:
                return user.getCreatedAt();
            case 7:
                return user.getUpdatedAt();
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }
}
