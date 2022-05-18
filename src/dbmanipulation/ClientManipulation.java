/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import dbclasse.Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class ClientManipulation {

    public void AddClient(Client c) throws SQLException, ClassNotFoundException {
        String sql = "insert into client (id,doit,profile,form_juridique,adress,tel,fax,nif) values "
                + "('" + c.getIdClient() + "','"
                + c.getDoit() + "','"
                + c.getProfil() + "','"
                + c.getFormeJuridique() + "','"
                + c.getAdr() + "','"
                + c.getTel() + "','"
                + c.getFax() + "','"
                + c.getNif() + "')";
        sqlConnection.executeSQLQuery(sql);
    }

    public Vector<Client> getAllClients() throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<Client> clients = new Vector<>();
        String sql = "select * from client";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            clients.addElement(new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }
        c.close();
        return clients;
    }

 public Vector<String> getAllClients_Profil() throws SQLException {
        Connection c = sqlConnection.conector();
        Vector<String> clients = new Vector<>();
        String sql = "select * from client";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            clients.addElement(rs.getString(3));
        }
        c.close();
        return clients;
    }

    public Client getOneClient(String id) throws SQLException {
        Connection c = sqlConnection.conector();
        Client cl = new Client();
        String sql = "select * from client where id='" + id + "'";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();

        cl.setIdClient(rs.getString("id"));
        cl.setDoit(rs.getString("doit"));
        cl.setProfil(rs.getString("profile"));
        cl.setFormeJuridique(rs.getString("form_juridique"));
        cl.setAdr(rs.getString("adress"));
        cl.setTel(rs.getString("tel"));
        cl.setFax(rs.getString("fax"));
        cl.setNif(rs.getString("nif"));

        c.close();
        return cl;
    }

    public void deleteClient(String id) throws ClassNotFoundException, SQLException {
        String sql = "Delete  from client where id='" + id + "'";
        sqlConnection.executeSQLQuery(sql);
    }

    public void updateClient(Client c) throws ClassNotFoundException, SQLException {
        String sql = "update client SET "
                + "doit='" + c.getDoit() + "',"
                + "profile='" + c.getProfil()+ "',"
                + "form_juridique='" + c.getFormeJuridique()+ "',"
                + "adress='" + c.getAdr()+ "',"
                + "tel='" + c.getTel()+ "',"
                + "fax='" + c.getFax()+ "',"
                + "nif='" + c.getNif()+ "'"
                + " where id= '" + c.getIdClient()+"'";
        sqlConnection.executeSQLQuery(sql);
    }

    public Client getClient_byprofile(String profile) throws SQLException {
        Connection c = sqlConnection.conector();
        Client cl = new Client();
        String sql = "select * from client where profile = '" + profile + "'";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            cl = new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
        }
        c.close();
        return cl;
    }

    public String autoID() throws SQLException {
        String id = "C001";

        Connection c = sqlConnection.conector();
        String sql = "SELECT max(id) from client";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        if (rs.getString("max(id)") == null) {
            id = "C001";
        } else {
            Long idd = Long.parseLong(rs.getString("max(id)").substring(1, rs.getString("max(id)").length()));
            idd++;
            id = "C" + String.format("%03d", idd);
        }

        c.close();

        return id;
    }

}
