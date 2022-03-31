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
public class Facture {
    private String idFacture, idVendeur, idClient, nFacture, Date, MTHT, tva, mttc;
    public Facture(){};
    public Facture(String idFacture, String idVendeur, String idClient, 
            String nFacture, String Date, String MTHT, String tva, String mttc){
        this.idFacture = idFacture;
        this.idVendeur = idVendeur; 
        this.idClient = idClient;
        this.nFacture = nFacture;
        this.Date = Date;
        this.MTHT = MTHT;
        this.tva = tva;
        this.mttc = mttc;
    };

    public String getIdFacture() {
        return idFacture;
    }

    public String getIdVendeur() {
        return idVendeur;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getnFacture() {
        return nFacture;
    }

    public String getDate() {
        return Date;
    }

    public String getMTHT() {
        return MTHT;
    }

    public String getTva() {
        return tva;
    }

    public String getMttc() {
        return mttc;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public void setIdVendeur(String idVendeur) {
        this.idVendeur = idVendeur;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setnFacture(String nFacture) {
        this.nFacture = nFacture;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setMTHT(String MTHT) {
        this.MTHT = MTHT;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public void setMttc(String mttc) {
        this.mttc = mttc;
    }
    
}
