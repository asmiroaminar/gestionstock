
import dbclasse.Client;
import dbmanipulation.ClientManipulation;
import dbmanipulation.DBConnection;
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Test {
    public static void main(String[] args) {
        
        //DBConnection dbcon = new DBConnection();
        for(int i = 5000; i<50000; i++){
        Client c = new Client(i+"","12","36","25","21","69","145","ml");
        ClientManipulation cm = new ClientManipulation();
        //cm.AjouterClient(c);
        System.out.println(i);
        }
    }
}
