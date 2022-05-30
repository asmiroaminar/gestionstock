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
public class Vent {

    private String idVent, date_vent, idClinet, idProduit;
    private int  qte, prixU, montant;

    public Vent() {
    }

    public Vent(String idVent, String date_vent, String idClinet, String idProduit, int qte, int prixU, int montant) {
        this.idVent = idVent;
        this.date_vent = date_vent;
        this.idClinet = idClinet;
        this.idProduit = idProduit;
        this.qte = qte;
        this.prixU = prixU;
        this.montant = montant;
    }

    public String getIdVent() {
        return idVent;
    }

    public String getDate_vent() {
        return date_vent;
    }

    public String getIdClinet() {
        return idClinet;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public int getQte() {
        return qte;
    }

    public int getPrixU() {
        return prixU;
    }

    public int getMontant() {
        return montant;
    }

    public void setIdVent(String idVent) {
        this.idVent = idVent;
    }

    public void setDate_vent(String date_vent) {
        this.date_vent = date_vent;
    }

    public void setIdClinet(String idClinet) {
        this.idClinet = idClinet;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setPrixU(int prixU) {
        this.prixU = prixU;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    
    
    

}
