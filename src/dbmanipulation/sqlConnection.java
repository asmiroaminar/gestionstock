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
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/cobraPro?useUnicode=yes&characterEncoding=UTF-8","root","");//localhost
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:sql_database\\database.db");
            return con;
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
