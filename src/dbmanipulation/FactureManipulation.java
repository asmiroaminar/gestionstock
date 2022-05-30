/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmanipulation;

import dbclasse.Facture;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class FactureManipulation {

    public void AddVent(Facture f) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO 'main'.'facture'('id_facture','n_facture','date_facture','id_client','mtht','tva','tva_p','mttc') VALUES "
                //                + " ('" + v.getIdVent() + "','"
                + "(NULL,"
                + "'" + f.getnFacture() + "',"
                + "'" + f.getDate() + "',"
                + "'" + f.getIdClient() + "',"
                + f.getMTHT() + ","
                + f.getTva() + ","
                + f.getTva_p() + ","
                + f.getMttc() + ")";

        sqlConnection.executeSQLQuery(sql);
        
        
    }
}
