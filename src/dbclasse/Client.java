/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbclasse;

import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Client {
    private String idClient, doit, profil, formeJuridique, adr, tel, fax, ni;
    
    public Client(){};
    
    public Client(String idClient, String doit, String profil, String formeJuridique, 
            String adr, String tel, String fax, String ni){
        this.idClient = idClient;
        this.doit = doit;
        this.profil = profil; 
        this.formeJuridique = formeJuridique; 
        this.adr = adr;
        this.tel = tel;
        this.fax = fax; 
        this.ni = ni;
    };

    public String getIdClient() {
        return idClient;
    }

    public String getDoit() {
        return doit;
    }

    public String getProfil() {
        return profil;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public String getAdr() {
        return adr;
    }

    public String getTel() {
        return tel;
    }

    public String getFax() {
        return fax;
    }

    public String getNi() {
        return ni;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setDoit(String doit) {
        this.doit = doit;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setNi(String ni) {
        this.ni = ni;
    }
    
}
