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
public class VentFacture {
    String idVentFacture, idFacture, idVent;
    public VentFacture(){};
    public VentFacture(String idVentFacture, String idFacture, String idVent){
        this.idVentFacture = idVentFacture;
        this.idFacture = idFacture;
        this.idVent = idVent;
    };

    public String getIdVentFacture() {
        return idVentFacture;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public String getIdVent() {
        return idVent;
    }

    public void setIdVentFacture(String idVentFacture) {
        this.idVentFacture = idVentFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public void setIdVent(String idVent) {
        this.idVent = idVent;
    }
    
}
