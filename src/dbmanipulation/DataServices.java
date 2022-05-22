/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author IT-06
 */
public class DataServices {

    public String getFolderPath() throws SQLException {
        Connection c = sqlConnection.conector();
        String path;
        String sql = "select * from data where id=1";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        path = rs.getString("path");

        c.close();
        return path;
    }

    public void updatFolderPath(String path) throws ClassNotFoundException, SQLException {
        String sql = "update data SET "
                + "path ='" + path + "'"
                + " where id = 1";
        sqlConnection.executeSQLQuery(sql);
    }
}
