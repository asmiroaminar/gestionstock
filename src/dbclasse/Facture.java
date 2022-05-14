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

    private int idFacture, MTHT, tva;
    private String idClient, Date, nFacture;
    private float tva_p, mttc;

    public Facture() {
    }

    ;

    public Facture(
            int idFacture,
            String nFacture,
            String Date,
            String idClient,
            int MTHT,
            int tva,
            float tva_p,
            float mttc) {
        this.idFacture = idFacture;
        this.nFacture = nFacture;
        this.MTHT = MTHT;
        this.tva = tva;
        this.idClient = idClient;
        this.Date = Date;
        this.tva_p = tva_p;
        this.mttc = mttc;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public String getnFacture() {
        return nFacture;
    }

    public int getMTHT() {
        return MTHT;
    }

    public int getTva() {
        return tva;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getDate() {
        return Date;
    }

    public float getTva_p() {
        return tva_p;
    }

    public float getMttc() {
        return mttc;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setnFacture(String nFacture) {
        this.nFacture = nFacture;
    }

    public void setMTHT(int MTHT) {
        this.MTHT = MTHT;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setTva_p(float tva_p) {
        this.tva_p = tva_p;
    }

    public void setMttc(float mttc) {
        this.mttc = mttc;
    }

}
