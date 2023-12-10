package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import dao.UserDAO;
import entity.User;
import service.PasswordEncryption;
import util.DBConnection;

public class UserController implements UserDAO{
	
	private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public UserController() {
        conn = DBConnection.getInstance().getCnx();
    }

	@Override
	public boolean insertUser(User u) throws SQLException {
		
		boolean connectedUser = false;
        System.out.print("Creating User ********************************");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "insert into user (username,email,password,role,createdAt,isVerified,isBanned) values (?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, u.getUsername());           
            pst.setString(2, u.getEmail());
            pst.setString(3, PasswordEncryption.cryptage(u.getPassword().toString()));
            pst.setString(4, u.getRole());
            pst.setTimestamp(5, u.getCreatedAt());
            pst.setInt(6, 0);
            pst.setInt(7, 0);
            
            System.out.print(pst);
            
            int pstExecute = pst.executeUpdate();
            if(pstExecute != -1){
                rs = pst.getGeneratedKeys();
                rs.next();
                System.out.println("Successfully signed user! *****************************");
                System.out.print(rs.getInt(1)  + "    OOOOOOOOOOOOOOOOOOOO");
                connectedUser = true;
            } else {
                System.out.println("Failed to sign user! ************************");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class
                    .getName()).log(Level.SEVERE, null, ex);
    }       
        return connectedUser;
	}

	@Override
	public boolean updateUser(User u) throws SQLException {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		String query = "UPDATE `user` SET `username`=?, `email`=?, `role`=?, `updatedAt` = CURRENT_TIMESTAMP WHERE id = ?";
        
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getRole());
            pst.setInt(4, u.getId());
            pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
        
	}

	@Override
	public boolean deleteUser(User u) throws SQLException {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String query = "delete from user where id = ?";

        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
	}

	@Override
    public Object[][] showAllUsers() throws SQLException {
        String query = "Select * from `user`";

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(query);
            
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            
            Object[][] data = new Object[rowCount][9];
            
            int i = 0;
            while (rs.next()) {
            	data[i][0] = rs.getInt("id");
            	data[i][1] = rs.getString("username");
            	data[i][2] = rs.getString("email");
            	data[i][3] = rs.getString("password").toCharArray();
            	data[i][4] = rs.getString("role");
            	data[i][5] = rs.getInt("isVerified");
            	data[i][6] = rs.getInt("isBanned");
            	data[i][7] = rs.getTimestamp("createdAt");
            	data[i][8] = rs.getTimestamp("updatedAt");
            	i++;
            }            

            return data;
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new Object[0][0];
        }
        
	}
	@Override
	public User selectOneUserByID(int id) throws SQLException {
	    User user = null;

	    String query = "SELECT * FROM `user` WHERE `id` = ?";

	    try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
	        preparedStatement.setInt(1, id);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                int userId = resultSet.getInt("id");
	                user = new User(userId);
	            }
	        }
	    }

	    return user;
	}
	
	public JList<User> showFreelancers() {
        DefaultListModel<User> model = new DefaultListModel<>();

        String query = "SELECT * FROM `user` WHERE `role` = 'Freelancer'";

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(query);

            while (rs.next()) {
            	int idUser = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String role = rs.getString("role");
                char[] password = rs.getString("password").toCharArray();
                int isVerified = rs.getInt("isVerified");
                int isBanned = rs.getInt("isBanned");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");

                User user = new User(idUser, username,email,role,password,isVerified,isBanned, createdAt,updatedAt);
                model.addElement(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JList<User> userList = new JList<>(model);
        return userList;
    }

	@Override
	public JList<User> sortUsers(String value) {
		
		return null;
	}

	@Override
	public DefaultListModel<User> filterRole(String value) {
		String req = "SELECT * FROM user WHERE role = ?";

		DefaultListModel<User> model = new DefaultListModel<>();
        try (PreparedStatement pst = conn.prepareStatement(req)) {
            pst.setString(1, value);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                int isVerified = rs.getInt("isVerified");
                int isBanned = rs.getInt("isBanned");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");

                User user = new User(id, username,email,role,password.toCharArray(),isVerified,isBanned, createdAt,updatedAt);
                model.addElement(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
	}
	
	public int countUsersByRole(String role) throws SQLException {
        String query = "SELECT COUNT(*) FROM `user` WHERE `role` = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, role);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }

        return 0;
    }

	public List<User> searchUser(String username) throws SQLException {
	    List<User> userList = new ArrayList<>();
	    
	    PreparedStatement preparedStatement;
	    ResultSet resultSet;

	    try {
	        String query = "SELECT * FROM user WHERE username LIKE ?";
	        preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setString(1, "%" + username + "%");

	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String email = resultSet.getString("email");
	            String password = resultSet.getString("password");
	            String role = resultSet.getString("role");
	            int isVerified = resultSet.getInt("isVerified");
	            int isBanned = resultSet.getInt("isBanned");
	            Timestamp createdAt = resultSet.getTimestamp("createdAt");
	            Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
	            User user = new User(id, username, email, role, password.toCharArray(), isVerified, isBanned, createdAt, updatedAt);
	            userList.add(user);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return userList;
	}


}
