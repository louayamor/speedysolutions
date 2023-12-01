package service.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.Wrapper;
import util.DBConnection;

public class AuthService extends Wrapper{

	PreparedStatement ps;
    Connection conn;

    public AuthService() {
    	this.conn = new DBConnection().getCnx();
    }
    
    

    public boolean Authenticate(String username, String password) {
            
    

        try {
            ps = conn.prepareStatement
                    ("select * from user where username= '" + username + "' ");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (resultSet != null) {
                    PreparedStatement psCheckPassword = conn.prepareStatement
                            ("select * from user where username= '" + username + "' and password = '" + password + "' ");
                    ResultSet resultSetFinalCheck = psCheckPassword.executeQuery();

                    while (resultSetFinalCheck.next()) {

                        if (resultSetFinalCheck != null) {

                            AuthResponseDTO _this = new AuthResponseDTO();
                            _this.setId((resultSetFinalCheck.getInt("idUser")));
                            _this.setUsername(resultSetFinalCheck.getString("username"));
                            _this.setEmail(resultSetFinalCheck.getString("email"));
                            _this.setRole(resultSetFinalCheck.getString("role"));
                            _this.setIsBanned(resultSetFinalCheck.getBoolean("isBanned"));
                           
                            UserSession.getSameInstance(_this);
                            if (resultSet != null && resultSetFinalCheck != null) {
                                System.out.println(_this);
                            }
                        }

                    }
                }
               System.out.println("bad credentials");
                return false;
            }
           System.out.println("username does not exist");

            return false;
        } catch (SQLException es) {
            es.printStackTrace();
        }
        return false;
    }
}
