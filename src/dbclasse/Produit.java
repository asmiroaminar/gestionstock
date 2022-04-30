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
public class Produit {

    String idProduit, designation, designation_fr;
    int qte, prixU_ht, tva, prix_achat;

    public Produit() {
    };

    public Produit(String idProduit, String designation, String designation_fr, int qte, int prixU_ht, int tva, int prix_achat) {
        this.idProduit = idProduit;
        this.designation = designation;
        this.designation_fr = designation_fr;
        this.qte = qte;
        this.prixU_ht = prixU_ht;
        this.tva = tva;
        this.prix_achat = prix_achat;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDesignation_fr() {
        return designation_fr;
    }

    public int getQte() {
        return qte;
    }

    public int getPrixU_ht() {
        return prixU_ht;
    }

    public int getTva() {
        return tva;
    }

    public int getPrix_achat() {
        return prix_achat;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDesignation_fr(String designation_fr) {
        this.designation_fr = designation_fr;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setPrixU_ht(int prixU_ht) {
        this.prixU_ht = prixU_ht;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public void setPrix_achat(int prix_achat) {
        this.prix_achat = prix_achat;
    }

   

    

}
