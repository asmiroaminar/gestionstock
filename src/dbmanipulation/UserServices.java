/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import dbclasse.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author IT-06
 */
public class UserServices {

    public void AddClient(User c) throws SQLException, ClassNotFoundException {
        String sql = "insert into client (nam,psw) values "
                + "('" + c.getNam() + "','"
                + c.getPsw() + "')";
        sqlConnection.executeSQLQuery(sql);
    }

    public Vector<User> getAllUsers() throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<User> Users = new Vector<>();
        String sql = "select * from client";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Users.addElement(new User(rs.getString("nam"), rs.getString("psw")));
        }
        c.close();
        return Users;
    }

    public User getOneUser(String n, String p) throws SQLException {
        Connection c = sqlConnection.conector();
        User u = new User();
        String sql = "select * from users "
                + "where nam='" + n + "'"
                + " AND psw ='" + p + "'";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {
            u.setNam(rs.getString("nam"));
            u.setPsw(rs.getString("psw"));
        } else {
            u = null;
        }

        c.close();
        return u;
    }

    public void deleteClient(String nam) throws ClassNotFoundException, SQLException {
        String sql = "Delete  from users where nam ='" + nam + "'";
        sqlConnection.executeSQLQuery(sql);
    }

    public void updateClient(User c,String opsw) throws ClassNotFoundException, SQLException {
        String sql = "update users SET "
                + "psw ='" + c.getPsw() + "'"
                + " where nam= '" + c.getNam() + "'" 
                + " AND psw ='" + opsw + "'";
        sqlConnection.executeSQLQuery(sql);
    }

}
