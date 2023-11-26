package dao;

import java.sql.SQLException;
import javax.swing.JList;
import entity.User;

public interface UserDAO {

	public boolean insertUser(User u)throws SQLException;
    public boolean updateUser(User u)throws SQLException;
    public boolean deleteUser(User u)throws SQLException;
    public JList<User> showAllUsers() throws SQLException;
    public User selectOneUserByID(int idUser) throws SQLException;
    public JList<User> sortUsers(String value);
    public JList<User> filterRole(String value);    
}
