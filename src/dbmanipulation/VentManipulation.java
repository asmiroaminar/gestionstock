/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import dbclasse.Vent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author IT-06
 */
public class VentManipulation {

    public void AddProduit(Vent v) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO 'vent'('date_vent','id_clinet','id_produit','qte','prix_ht','montant') VALUES "
                //                + " ('" + v.getIdVent() + "','"
                + " ('" + v.getDate_vent() + "','"
                + v.getIdClinet() + "','"
                + v.getIdProduit() + "',"
                + v.getQte() + ","
                + v.getPrixU() + ","
                + v.getMontant() + ")";
        sqlConnection.executeSQLQuery(sql);
    }

    public Vector<Vent> getAllVents() throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<Vent> prods = new Vector<>();
        String sql = "select * from vent";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            prods.addElement(new Vent(
                    rs.getString("id"),
                    rs.getString("date_vent"),
                    rs.getString("id_clinet"),
                    rs.getString("id_produit"),
                    rs.getInt("qte"),
                    rs.getInt("prix_ht"),
                    rs.getInt("montant"))
            );
        }
        c.close();
        return prods;
    }
}
