/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import java.sql.*;
/**
 *
 * @author User
 */
public class DBConnection {
        private Statement st = null;
        private Connection conn = null;
       public DBConnection(){
        try{
            String url = "jdbc:ucanaccess://C:\\Users\\User\\Documents\\NetBeansProjects\\gestions\\src\\db\\dbstocke.accdb";
            conn = DriverManager.getConnection(url);
            st = conn.createStatement();
            /*String sql = "Select * from client";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.println("\n" + rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
            }*/
            
        }catch(Exception e){
            e.printStackTrace();
        }
}

    public Statement getSt() {
        return st;
    }

    public Connection getConn() {
        return conn;
    }
    
       
       
       
}
