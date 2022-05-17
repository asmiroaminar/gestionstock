/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import dbclasse.Produit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class ProduitManipulation {

    public void AddProduit(Produit p) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO 'produit'('id','designnation','qte','prix_u','tva','designnation_fr','prix_achat') VALUES"
                + " ('" + p.getIdProduit() + "','"
                + p.getDesignation() + "',"
                + p.getQte() + ","
                + p.getPrixU_ht() + ","
                + p.getTva() + ",'"
                + p.getDesignation_fr() + "',"
                + p.getPrix_achat() + ")";
        sqlConnection.executeSQLQuery(sql);
    }

    public Vector<Produit> getAllProduits() throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<Produit> prods = new Vector<>();
        String sql = "select * from produit";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            prods.addElement(new Produit(
                    rs.getString("id"),
                    rs.getString("designnation"),
                    rs.getString("designnation_fr"),
                    rs.getInt("qte"),
                    rs.getInt("prix_u"),
                    rs.getInt("tva"),
                    rs.getInt("prix_achat")));
        }
        c.close();
        return prods;
    }

    public Vector<String> getAllProduits_designnation_ar() throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<String> Produits = new Vector<>();
        String sql = "select * from produit";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Produits.addElement(rs.getString(2));
        }
        c.close();
        return Produits;
    }

    public Vector<String> getAllProduits_designnation_fr() throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<String> Produits = new Vector<>();
        String sql = "select * from produit";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Produits.addElement(rs.getString(6));
        }
        c.close();
        return Produits;
    }

    public int getProduit_pric(String designnation) throws SQLException {
        Connection c = sqlConnection.conector();
        int pric = 0;
        String sql = "select * from produit where designnation='" + designnation + "' OR designnation_fr='" + designnation + "'";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        pric = rs.getInt("prix_u");
        c.close();
        return pric;
    }

    public String autoID() throws SQLException {
        String id = "P001";

        Connection c = sqlConnection.conector();
        String sql = "SELECT max(id) from produit";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        if (rs.getString("max(id)") == null) {
            id = "P001";
        } else {
            Long idd = Long.parseLong(rs.getString("max(id)").substring(1, rs.getString("max(id)").length()));
            idd++;
            id = "P" + String.format("%03d", idd);
        }

        c.close();

        return id;
    }

    public void update_Produit(Produit p) throws ClassNotFoundException, SQLException {
        String sql = "update produit SET "
                + "designnation='" + p.getDesignation() + "',"
                + "qte=" + p.getQte() + ","
                + "prix_u=" + p.getPrix_achat() + ","
                + "tva=" + p.getTva() + ","
                + "designnation_fr=" + p.getDesignation_fr() + ","
                + "prix_achat=" + p.getPrix_achat() + " "
                + " where id= '" + p.getIdProduit() + "'";
        sqlConnection.executeSQLQuery(sql);
    }


    public void deleteProduit(String id) throws ClassNotFoundException, SQLException {
        String sql = "Delete  from produit where id ='" + id + "'";
        sqlConnection.executeSQLQuery(sql);
    }
}
