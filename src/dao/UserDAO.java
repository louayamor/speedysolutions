package dao;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import entity.User;

public interface UserDAO {

	public boolean insertUser(User u)throws SQLException;
    public boolean updateUser(User u)throws SQLException;
    public boolean deleteUser(User u)throws SQLException;
    public Object[][] showAllUsers() throws SQLException;
    public User selectOneUserByID(int idUser) throws SQLException;
    public JList<User> sortUsers(String value);
    public DefaultListModel<User> filterRole(String value);  
    public int countUsersByRole(String role) throws SQLException;
    
}
