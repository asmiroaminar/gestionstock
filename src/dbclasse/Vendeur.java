/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbclasse;

/**
 *
 * @author User
 */
public class Vendeur {
    private String idVendeur, typeActivity, adress, nrc, nif, nis, fax, tel, nrib, cpa, datNRC;
    
    public Vendeur (){};
    
    public Vendeur (String idVendeur, String typeActivity, String adress, String nrc, 
            String nif, String nis, String fax, String tel, String nrib, String cpa, String datNRC){
        
        this.idVendeur = idVendeur;
        this.typeActivity = typeActivity;
        this.adress = adress; 
        this.nrc = nrc;
        this.nif = nif;
        this.nis = nis; 
        this.fax = fax; 
        this.tel = tel; 
        this.nrib = nrib;
        this.cpa = cpa; 
        this.datNRC = datNRC;
    };

    public String getIdVendeur() {
        return idVendeur;
    }

    public String getTypeActivity() {
        return typeActivity;
    }

    public String getAdress() {
        return adress;
    }

    public String getNrc() {
        return nrc;
    }

    public String getNif() {
        return nif;
    }

    public String getNis() {
        return nis;
    }

    public String getFax() {
        return fax;
    }

    public String getTel() {
        return tel;
    }

    public String getNrib() {
        return nrib;
    }

    public String getCpa() {
        return cpa;
    }

    public String getDatNRC() {
        return datNRC;
    }

    public void setIdVendeur(String idVendeur) {
        this.idVendeur = idVendeur;
    }

    public void setTypeActivity(String typeActivity) {
        this.typeActivity = typeActivity;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setNrib(String nrib) {
        this.nrib = nrib;
    }

    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

    public void setDatNRC(String datNRC) {
        this.datNRC = datNRC;
    }
    
    
}
