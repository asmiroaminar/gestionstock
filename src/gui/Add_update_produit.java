/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dbclasse.Produit;
import dbmanipulation.ProduitManipulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Add_update_produit extends javax.swing.JDialog {

    /**
     * Creates new form Add_update_produit
     */
    static ProduitManipulation ps = new ProduitManipulation();

    public String getDate() {

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        return ft.format(dNow);
    }

    public String generateId() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
        return ft.format(dNow);
    }

    private void clearAll() {
        jid.setText(generateId());
        jnam.setText("");
        jnam_ar.setText("");
        junite_fr.setText("");
        junite_ar.setText("");
        jqte.setText("");
        jqte_ala.setText("");
        jtva.setText("");
        jpht.setText("");
        jprix_achat.setText("");
    }

    private boolean isAllWriten() {
        boolean tr = true;
        if ((!jnam.getText().equals(""))
                && (!jnam_ar.getText().equals(""))
                && (!junite_fr.getText().equals(""))
                && (!junite_ar.getText().equals(""))) {
            tr = tr && true;
        } else {
            tr = tr && false;
        }
        tr = tr && Pattern.matches("^\\d+$", jqte.getText());
        tr = tr && Pattern.matches("^\\d+$", jqte_ala.getText());
        tr = tr && Pattern.matches("^\\d+$", jtva.getText());
        tr = tr && Pattern.matches("^\\d+$", jpht.getText());
        tr = tr && Pattern.matches("^\\d+$", jprix_achat.getText());
        return tr;
    }
//************* Add ****************************

    public Add_update_produit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        try {
            //jid.setText(generateId());
            jid.setText(ps.autoID());
        } catch (SQLException ex) {
            Logger.getLogger(Add_update_produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAllWriten()) {

                    Produit produit = new Produit();
                    produit.setIdProduit(jid.getText());
                    produit.setDesignation(jnam_ar.getText());
                    produit.setDesignation_fr(jnam.getText());
                    produit.setQte(Integer.parseInt(jqte.getText()));
                    produit.setTva(Integer.parseInt(jtva.getText()));
                    produit.setPrixU_ht(Integer.parseInt(jpht.getText()));
                    produit.setPrix_achat(Integer.parseInt(jprix_achat.getText()));
                    try {
                        ps.AddProduit(produit);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Add_update_produit.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(null, "Bien Eregistrer");
                    clearAll();
                    //  dispose();

                    try {
                        Home_v2.AfficherProduit();
                        dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(Add_update_client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "S'il vous plaît vérifier les champs");
                }

            }
        });
    }
//************* update ****************************

    public Add_update_produit(java.awt.Frame parent, boolean modal,Produit p) {
        super(parent, modal);
        initComponents();

        jid.setText(p.getIdProduit());
        jnam.setText(p.getDesignation_fr());
        jnam_ar.setText(p.getDesignation());
        jqte.setText("" + p.getQte());
        jtva.setText("" + p.getTva());
        jpht.setText("" + p.getPrixU_ht());
        jprix_achat.setText("" + p.getPrix_achat());


        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAllWriten()) {
                    Produit produit = new Produit();
                    produit.setIdProduit(jid.getText());
                    produit.setDesignation_fr(jnam.getText());
                    produit.setDesignation(jnam_ar.getText());
                    produit.setQte(Integer.parseInt(jqte.getText()));
                    produit.setPrixU_ht(Integer.parseInt(jpht.getText()));
                    produit.setTva(Integer.parseInt(jtva.getText()));
                    produit.setPrix_achat(Integer.parseInt(jprix_achat.getText()));                

               
                    try {
                        ps.update_Produit(produit);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Add_update_produit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                        JOptionPane.showMessageDialog(null, "Bien Eregistrer");
                        dispose();
                        
                        
                    try {
                        Home_v2.AfficherProduit();
                    } catch (SQLException ex) {
                        Logger.getLogger(Add_update_produit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "S'il vous plaît vérifier les champs");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jid = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jnam = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jqte = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jpht = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jprix_achat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jnam_ar = new javax.swing.JTextField();
        junite_fr = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        junite_ar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jqte_ala = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(7, 153, 146));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(7, 153, 146));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/New Product_30px.png"))); // NOI18N
        jLabel7.setText("Informations de produit");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 60));

        jPanel2.setBackground(new java.awt.Color(56, 173, 169));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Matricule :");

        jid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jid.setText("Générer par le système");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nom de produit :");

        jnam.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Quantité en stok :");

        jqte.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jqte.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("TVA en % :");

        jtva.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtva.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Prix unitaire HT :");

        jpht.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jpht.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jpht.setText("0.00");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Prix d'achat :");

        jprix_achat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jprix_achat.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jprix_achat.setText("0.00");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Nom de produit en arabic :");

        jnam_ar.setEditable(false);
        jnam_ar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jnam_ar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jnam_ar.setText("/");

        junite_fr.setEditable(false);
        junite_fr.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        junite_fr.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        junite_fr.setText("KG");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Unité en FR :");

        junite_ar.setEditable(false);
        junite_ar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        junite_ar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        junite_ar.setText("كغ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Quantité d'alerte :");

        jqte_ala.setEditable(false);
        jqte_ala.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jqte_ala.setText("0");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Unité en AR :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jnam)
                    .addComponent(jnam_ar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(junite_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(junite_ar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtva, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jqte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jqte_ala, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jprix_achat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addComponent(jpht, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jid))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jnam, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jnam_ar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(junite_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(junite_ar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jqte, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jqte_ala, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtva, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpht, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jprix_achat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 740, -1));

        jButton1.setBackground(new java.awt.Color(184, 233, 148));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Save_30px.png"))); // NOI18N
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 550, 80, -1));

        jButton2.setBackground(new java.awt.Color(235, 47, 6));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Delete_30px.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 550, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jid;
    private javax.swing.JTextField jnam;
    private javax.swing.JTextField jnam_ar;
    private javax.swing.JTextField jpht;
    private javax.swing.JTextField jprix_achat;
    private javax.swing.JTextField jqte;
    private javax.swing.JTextField jqte_ala;
    private javax.swing.JTextField jtva;
    private javax.swing.JTextField junite_ar;
    private javax.swing.JTextField junite_fr;
    // End of variables declaration//GEN-END:variables
}
