package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.WrapperDAO;
import util.DBConnection;

public class Wrapper implements WrapperDAO {

	private Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public Wrapper() {
        this.conn = new DBConnection().getCnx();
    }

    @Override
    public boolean isAdmin(int id) {
        try {

            ps = conn.prepareStatement("select * FROM user WHERE idUser ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("role").equals("Admin")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isFreelancer(int id) {
        try {

            ps = conn.prepareStatement("select * FROM user WHERE idUser ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("role").equals("Freelancer")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isBusinessOwner(int id) {
        try {

            ps = conn.prepareStatement("select * FROM user WHERE idUser ='" + id + "' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("role").equals("BOwner")) {
                    return true;
                } else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
