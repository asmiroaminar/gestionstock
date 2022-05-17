/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dbclasse.Produit;
import dbmanipulation.ProduitManipulation;
import java.awt.Toolkit;
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
public class Add_update_client extends javax.swing.JDialog {

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
        jprofil.setText("");
        jform_j.setText("");
        jphone.setText("");
        jadress.setText("");
        jfax.setText("");
        jnif.setText("");

    }

    private boolean isAllWriten() {
        boolean tr = true;
        if ((!jnam.getText().equals(""))
                && (!jprofil.getText().equals(""))
                && (!jform_j.getText().equals(""))
                && (!jphone.getText().equals(""))
                && (!jfax.getText().equals(""))) {
            tr = tr && true;
        } else {
            tr = tr && false;
        }
        return tr;
    }
//************* Add ****************************

    public Add_update_client(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
       
       
        jid.setText(ps.autoID());
        jid.setText(generateId());

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (isAllWriten()) {
//
//                    Produit produit = new Produit();
//                    produit.setId_p(jid.getText());
//                    produit.setNom(jnam.getText());
//                    produit.setDateAjoute(jdateAjout.getText());
//                    produit.setPrix_achat(Integer.parseInt(jpa.getText()));
//                    produit.setPrix_vent(Integer.parseInt(jpv.getText()));
//                    produit.setQte(Integer.parseInt(jqte.getText()));
//                    produit.setPosition(jposition.getText());
//
//                    try {
//                        ps.AddProduit(produit);
//                    } catch (SQLException | ClassNotFoundException ex) {
//                        Logger.getLogger(Add_update_client.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    JOptionPane.showMessageDialog(null, "Bien Eregistrer");
//                    clearAll();
//                    //  dispose();
//
//                    try {
//                        Gestion_Produit.AfficherProduits();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Add_update_client.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "S'il vous plaît vérifier les champs");
//                }

            }
        });
    }
//************* update ****************************

    public Add_update_client(Produit p) {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/rokgard-glam-ip5_2048x.png")));

//        jid.setText("" + p.getId_p());
//        jnam.setText(p.getNom());
//        jdateAjout.setText(p.getDateAjoute());
//        jpa.setText("" + p.getPrix_achat());
//        jpv.setText("" + p.getPrix_vent());
//        jqte.setText("" + p.getQte());
//        jposition.setText(p.getPosition());
//        jButton1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (isAllWriten()) {
//                    Produit produit = new Produit();
//                    produit.setId_p(jid.getText());
//                    produit.setNom(jnam.getText());
//                    produit.setDateAjoute(jdateAjout.getText());
//                    produit.setPrix_achat(Integer.parseInt(jpa.getText()));
//                    produit.setPrix_vent(Integer.parseInt(jpv.getText()));
//                    produit.setQte(Integer.parseInt(jqte.getText()));
//                    produit.setPosition(jposition.getText());
//
//                    try {
//                        ps.updateProduit(produit);
//                        JOptionPane.showMessageDialog(null, "Bien Eregistrer");
//                        clearAll();
//                        dispose();
//                        Gestion_Produit.AfficherProduits();
//                    } catch (ClassNotFoundException | SQLException ex) {
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "S'il vous plaît vérifier les champs");
//                }
//            }
//        });
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
        jLabel9 = new javax.swing.JLabel();
        jprofil = new javax.swing.JTextField();
        jform_j = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jphone = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jadress = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jfax = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jnif = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(7, 153, 146));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Customer_30px.png"))); // NOI18N
        jLabel7.setText("Fiche Clients");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 60));

        jPanel2.setBackground(new java.awt.Color(51, 204, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Matricule :");

        jid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jid.setText("Générer par le système");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("DOIT :");

        jnam.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Au profil :");

        jprofil.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jform_j.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jform_j.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Forme juridique :");

        jphone.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jphone.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Adress :");

        jadress.setColumns(20);
        jadress.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jadress.setRows(3);
        jScrollPane1.setViewportView(jadress);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Téléphone :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("NIF :");

        jfax.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jfax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("FAX :");

        jnif.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jnif.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jnam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                            .addComponent(jid, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jprofil, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jform_j, javax.swing.GroupLayout.Alignment.LEADING))
                        .addComponent(jphone, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jfax, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jnif, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
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
                    .addComponent(jprofil, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jform_j, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jphone, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jfax, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jnif, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 740, 520));

        jButton1.setBackground(new java.awt.Color(184, 233, 148));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Save_30px.png"))); // NOI18N
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 590, 80, -1));

        jButton2.setBackground(new java.awt.Color(235, 47, 6));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Delete_30px.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 590, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jadress;
    private javax.swing.JTextField jfax;
    private javax.swing.JTextField jform_j;
    private javax.swing.JLabel jid;
    private javax.swing.JTextField jnam;
    private javax.swing.JTextField jnif;
    private javax.swing.JTextField jphone;
    private javax.swing.JTextField jprofil;
    // End of variables declaration//GEN-END:variables
}
