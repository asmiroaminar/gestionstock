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
   String idProduit, designation, qte, prixU, taxe;
   
   public Produit(){};
   
   public Produit(String idProduit, String designation, String qte, String prixU, String taxe){
       this.idProduit = idProduit;
       this.designation = designation;
       this.qte = qte; 
       this.prixU = prixU;
       this.taxe = taxe;
   }

    public String getIdProduit() {
        return idProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public String getQte() {
        return qte;
    }

    public String getPrixU() {
        return prixU;
    }

    public String getTaxe() {
        return taxe;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public void setPrixU(String prixU) {
        this.prixU = prixU;
    }

    public void setTaxe(String taxe) {
        this.taxe = taxe;
    }
   
   
   
   
}
