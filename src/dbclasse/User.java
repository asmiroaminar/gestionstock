/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbclasse;

/**
 *
 * @author IT-06
 */
public class User {
   private String nam,psw;

    public User() {
    }
   
       public User(String nam, String psw) {
        this.nam = nam;
        this.psw = psw;
    }

    public String getNam() {
        return nam;
    }

    public String getPsw() {
        return psw;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
       
    
   
   
}
