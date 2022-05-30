/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class sqlConnection {

    Connection con = null;

    public static Connection conector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
            return con;
            
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//            String msAccDB = "dbstocke.accdb";
//            String dbURL = "jdbc:ucanaccess://" + msAccDB; 
//            Connection con = DriverManager.getConnection(dbURL); 
//            
//            return con;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static void executeSQLQuery(String sql) throws ClassNotFoundException, SQLException {
        Connection con = conector();
        Statement st = con.createStatement();
        int n = st.executeUpdate(sql);
        // System.out.println(n);
        st.close();
        con.close();
    }

}
