/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import dbclasse.Facture;
import dbclasse.Vent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class Facture_manupulation {

    public void Add_facture(Facture f, Vector<Vent> v) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO 'main'.'facture'('n_facture','date_facture','id_client','mtht','tva','tva_p','mttc') VALUES ('"
                + f.getnFacture() + "' ,'"
                + f.getDate() + "','"
                + f.getIdClient() + "',"
                + f.getMTHT() + ","
                + f.getTva() + ","
                + f.getTva_p() + ","
                + f.getMttc() + ")";
        sqlConnection.executeSQLQuery(sql);
        for (int i = 0; i < v.size(); i++) {
            sql = "INSERT INTO 'main'.'vent_fact'('id_facture','id_vent') VALUES ('"
                    + f.getnFacture() + "','"
                    + v.get(i).getIdVent() + "')";
            sqlConnection.executeSQLQuery(sql);
        }

    }

    public String autoID() throws SQLException {
        String id = "0001";

        Connection c = sqlConnection.conector();
        Vector<Vent> prods = new Vector<>();
        String sql = "SELECT max(n_facture) from facture";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();//rs.getString("id"),

        if (rs.getString("max(n_facture)") == null) {
            id = "0001";
        } else {
            Long idd = Long.parseLong(rs.getString("max(n_facture)"));
            idd++;
            id = String.format("%04d", idd);
        }

        c.close();

        return id;
    }
}
