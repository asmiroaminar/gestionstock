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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public Vector<Facture> getAllFactures() throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<Facture> fs = new Vector<>();
        String sql = "SELECT id_facture,n_facture,date_facture,profile,mtht,tva,tva_p,mttc "
                + "FROM main.facture "
                + "INNER JOIN client ON client.id = facture.id_client";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            //clients.addElement(new Facture(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            fs.addElement(new Facture(
                    rs.getInt("id_facture"),
                    rs.getString("n_facture"),
                    rs.getString("date_facture"),
                    rs.getString("profile"),
                    rs.getInt("mtht"),
                    rs.getInt("tva"),
                    rs.getFloat("tva_p"),
                    rs.getFloat("mttc")
            ));
        }
        c.close();
        return fs;
    }

    public Vector<Facture> getFactures_ByClientID(String id) throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<Facture> fs = new Vector<>();
        String sql = "SELECT id_facture,n_facture,date_facture,profile,mtht,tva,tva_p,mttc "
                + "FROM main.facture "
                + "INNER JOIN client ON client.id = facture.id_client "
                + "WHERE facture.id_client = '" + id + "'";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            //clients.addElement(new Facture(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            fs.addElement(new Facture(
                    rs.getInt("id_facture"),
                    rs.getString("n_facture"),
                    rs.getString("date_facture"),
                    rs.getString("profile"),
                    rs.getInt("mtht"),
                    rs.getInt("tva"),
                    rs.getFloat("tva_p"),
                    rs.getFloat("mttc")
            ));
        }
        c.close();
        return fs;
    }

    public String autoID() throws SQLException {
        String id = "001";

        Connection c = sqlConnection.conector();
        Vector<Vent> prods = new Vector<>();
        String sql = "SELECT max(n_facture) from facture";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();//rs.getString("id"),

        if (rs.getString("max(n_facture)") == null) {
            id = "001";
        } else {
            Long idd = Long.parseLong(rs.getString("max(n_facture)"));
            idd++;
            id = String.format("%03d", idd);
        }

        c.close();

        return id;
    }

    public void deleteFacture(int n) throws ClassNotFoundException, SQLException {
        System.out.println("delete facture function");
        String sql = "delete from facture where n_facture = " + n;
        sqlConnection.executeSQLQuery(sql);

        sql = "delete from vent_fact where id_facture = " + n;
        sqlConnection.executeSQLQuery(sql);
//        

//        try {
//            Connection c = sqlConnection.conector();
//            
//            Statement st = c.createStatement();
//            
//            String sql = "select * from facture where n_facture = " + n;
//            ResultSet rs = st.executeQuery(sql);
//            String fc = rs.getString(1);
//            String sqldeleteFact = "delete from facture where n_facture = " + fc;
//            st.executeUpdate(sqldeleteFact);
//            
//            sql = "select * from vent_fact where id_facture = " + n;
//            String sqldeleteVentFact = "delete from vent_fact where id_facture = " + n;
//            rs = st.executeQuery(sql);
//            System.out.println("s = " + sql);
//            while (rs.next()) {
//                System.out.println("index" + rs.getString(1));
//            st.executeUpdate(sqldeleteVentFact);
//            }
//            
//            /*while (rs.next()) {
//            //clients.addElement(new Facture(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
//            System.out.println(rs.getString(1)+ " " +
//                    //rs.getString(1)+ " "+
//                            rs.getString(2)+ " "+
//                                    rs.getString(3)+ " "+
//                                            rs.getString(4)+ " "+
//                                                    rs.getString(5)+ " "+
//                                                   rs.getString(6)+ " "+
//                                                     rs.getString(7)+ " "+
//                                                            rs.getString(8)+ " ");
//        }*/
//        c.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Facture_manupulation.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
