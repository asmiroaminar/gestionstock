/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import dbclasse.Employeur;
import dbclasse.Produit;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class EmployeurManipulation {
    public void AddEmpl(Employeur e) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO 'Employeur'('id','Nom','salaire') VALUES"
                + " ('" + e.getId() + "','"
                + e.getNom() + "',"
                + e.getSalaire() +")";
        sqlConnection.executeSQLQuery(sql);
    }
}
