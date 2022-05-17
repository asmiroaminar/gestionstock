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

    public void AddVent(Vent v) throws SQLException, ClassNotFoundException {
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

    public Vector<Vent> getAllVents_ofclient_withdate(String idClient, String month, String year) throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<Vent> prods = new Vector<>();
        String sql = "SELECT  vent.id,vent.date_vent,client.profile,produit.designnation_fr,vent.qte,vent.prix_ht,vent.montant "
                + "FROM vent "
                + "INNER JOIN produit ON produit.id == vent.id_produit "
                + "INNER JOIN client ON client.id == vent.id_clinet "
                + "WHERE strftime('%m',vent.date_vent) = '" + month + "' AND strftime('%Y',vent.date_vent) = '" + year + "' "
                //+ "WHERE date(vent.date_vent) between date('"+start+"') and date('"+end+"') "
                + "AND vent.id_clinet = '" + idClient + "'";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            prods.addElement(new Vent(
                    rs.getString("id"),
                    rs.getString("date_vent"),
                    rs.getString("profile"),
                    rs.getString("designnation_fr"),
                    rs.getInt("qte"),
                    rs.getInt("prix_ht"),
                    rs.getInt("montant"))
            );
        }
        c.close();
        return prods;
    }

    public Vector<Vent> getVentsOfFacture(String n_facture) throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<Vent> prods = new Vector<>();
        String sql = "SELECT vent.id,vent.date_vent,produit.designnation_fr,vent.qte,vent.prix_ht,vent.montant  "
                + "FROM vent_fact "
                + "INNER JOIN vent ON   vent.id = vent_fact.id_vent "
                + "INNER JOIN produit ON   vent.id_produit = produit.id "
                + "WHERE vent_fact.id_facture = " + n_facture;
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            prods.addElement(new Vent(
                    rs.getString("id"),
                    rs.getString("date_vent"),
                    "Cxxx",//rs.getString("id_clinet"),
                    rs.getString("designnation_fr"),
                    rs.getInt("qte"),
                    rs.getInt("prix_ht"),
                    rs.getInt("montant"))
            );
        }
        c.close();
        return prods;
    }

    public Vector<Vent> getVents_costum(String id_client) throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<Vent> prods = new Vector<>();
        String sql = "SELECT vent.id,vent.date_vent,client.profile,produit.designnation_fr,vent.qte,vent.prix_ht,vent.montant  "
                + "FROM vent  "
                + "INNER JOIN client ON   client.id = vent.id_clinet "
                + "INNER JOIN produit ON  vent.id_produit = produit.id "
                + "WHERE vent.id_clinet = '" + id_client + "'";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            prods.addElement(new Vent(
                    rs.getString("id"),
                    rs.getString("date_vent"),
                    rs.getString("profile"),
                    rs.getString("designnation_fr"),
                    rs.getInt("qte"),
                    rs.getInt("prix_ht"),
                    rs.getInt("montant"))
            );
        }
        c.close();
        return prods;
    }

}
