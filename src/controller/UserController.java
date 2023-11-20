package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            pst.setString(3, PasswordEncryption.cryptage(u.getPassword()));
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User u) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JList<User> showAllUsers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectOneUserByID(int idUser) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JList<User> sortUsers(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JList<User> filterRole(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
