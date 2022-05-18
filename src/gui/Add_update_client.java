/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dbclasse.Client;
import dbmanipulation.ClientManipulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Add_update_client extends javax.swing.JDialog {

    static ClientManipulation cm = new ClientManipulation();

//    public String getDate() {
//
//        Date dNow = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
//        return ft.format(dNow);
//    }
//    public String generateId() {
//
//        Date dNow = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
//        return ft.format(dNow);
//    }
    private void clearAll() throws SQLException {
        jid.setText(cm.autoID());
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

    public Add_update_client(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        try {
            jid.setText(cm.autoID());
        } catch (SQLException ex) {
            Logger.getLogger(Add_update_client.class.getName()).log(Level.SEVERE, null, ex);
        }

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAllWriten()) {

                    Client c = new Client();
                    c.setIdClient(jid.getText());
                    c.setDoit(jnam.getText());
                    c.setProfil(jprofil.getText());
                    c.setFormeJuridique(jform_j.getText());
                    c.setAdr(jadress.getText());
                    c.setTel(jphone.getText());
                    c.setFax(jfax.getText());
                    c.setNif(jnif.getText());
                    try {
                        cm.AddClient(c);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Add_update_client.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(null, "Bien Eregistrer");

                    try {
                        Home_v2.AfficherClients();
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

    public Add_update_client(java.awt.Frame parent, boolean modal, Client cl) {
        super(parent, modal);
        initComponents();

        jid.setText(cl.getIdClient());
        jnam.setText(cl.getDoit());
        jprofil.setText(cl.getProfil());
        jform_j.setText(cl.getFormeJuridique());
        jadress.setText(cl.getAdr());
        jphone.setText(cl.getTel());
        jfax.setText(cl.getFax());
        jnif.setText(cl.getNif());

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAllWriten()) {
                    Client c = new Client();
                    c.setIdClient(jid.getText());
                    c.setDoit(jnam.getText());
                    c.setProfil(jprofil.getText());
                    c.setFormeJuridique(jform_j.getText());
                    c.setAdr(jadress.getText());
                    c.setTel(jphone.getText());
                    c.setFax(jfax.getText());
                    c.setNif(jnif.getText());

                    try {
                        cm.updateClient(c);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Add_update_client.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(null, "Bien Eregistrer");

                    try {
                        Home_v2.AfficherClients();
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
        saveBTN = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(7, 153, 146));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Customer_30px.png"))); // NOI18N
        jLabel7.setText("Fiche Clients");

        jPanel2.setBackground(new java.awt.Color(51, 204, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Matricule :");

        jid.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
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
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jform_j)
                        .addComponent(jScrollPane1)
                        .addComponent(jphone)
                        .addComponent(jfax)
                        .addComponent(jnif))
                    .addComponent(jnam)
                    .addComponent(jprofil))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jid)
                .addGap(391, 391, 391))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jnam, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jprofil, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap())
        );

        saveBTN.setBackground(new java.awt.Color(184, 233, 148));
        saveBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Save_30px.png"))); // NOI18N

        jButton2.setBackground(new java.awt.Color(235, 47, 6));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Delete_30px.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(652, 652, 652)
                .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveBTN, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JButton saveBTN;
    // End of variables declaration//GEN-END:variables
}
