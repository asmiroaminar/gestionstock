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
public class Employeur {
    private String id, nom, salaire;

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }
    
}
