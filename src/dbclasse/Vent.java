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
    private String idVent, idProduit, qte, prixU, montant;
    public Vent(){};
    public Vent(String idVent, String idProduit, String qte, String prixU, String montant){
        this.idVent = idVent;
        this.idProduit = idProduit;
        this.qte = qte; 
        this.prixU = prixU; 
        this.montant = montant;
    };

    public String getIdVent() {
        return idVent;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public String getQte() {
        return qte;
    }

    public String getPrixU() {
        return prixU;
    }

    public String getMontant() {
        return montant;
    }

    public void setIdVent(String idVent) {
        this.idVent = idVent;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public void setPrixU(String prixU) {
        this.prixU = prixU;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
    
}
