/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dbclasse.*;
import dbmanipulation.ClientManipulation;
import dbmanipulation.Facture_manupulation;
import dbmanipulation.ProduitManipulation;
import dbmanipulation.VentManipulation;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import pdfgenerator.Ar_factur_generate;
import pdfgenerator.Fr_factur_generate;

/**
 *
 * @author IT-06
 */
public class Generate_facture_frame extends javax.swing.JDialog {

    Client selected_client = new Client();
    Vector<Client> v_clients = new Vector<>();
    static Vector<Vent> v_vents = new Vector<>();
    static Vector<Vent> v_selected_vents = new Vector<>();

    static ClientManipulation cm = new ClientManipulation();
    static ProduitManipulation pm = new ProduitManipulation();
    static VentManipulation vm = new VentManipulation();
    Facture_manupulation fm = new Facture_manupulation();

    DocumentListener calculeTotalwithTAX_listener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            calcul_tva();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calcul_tva();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calcul_tva();
        }
    };

    Fr_factur_generate fr_factur_generate = new Fr_factur_generate();
    Ar_factur_generate ar_factur_generate = new Ar_factur_generate();

    public final void AfficherClients_Profil() throws SQLException {
//        Vector<String> clients = cm.getAllClients_Profil();
//        clients.add(0, " / ");
//        final DefaultComboBoxModel model = new DefaultComboBoxModel(clients);
//        client_list.setModel(model);
        //-----------------------------
        v_clients = cm.getAllClients();
        Vector<String> clients = new Vector<>();
        clients.add(" / ");
        for (int i = 0; i < v_clients.size(); i++) {
            Client c = v_clients.get(i);
            clients.add(c.getProfil());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(clients);
        client_list.setModel(model);

        //-----------------------------
    }

    static boolean isDuplicated(Vector<String> v, String pro) {
        boolean tr = false;
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).equals(pro)) {
                tr = true;
                break;
            }
        }
        return tr;
    }

    // afficher tout les Vent  en Vent table
    public static void AfficherVents(String idClient, String month, String year) throws SQLException {
        // TODO Auto-generated method stub
        DefaultTableModel model = (DefaultTableModel) vent_table.getModel();
        model.setRowCount(0);
        DefaultTableModel temp_model2 = (DefaultTableModel) fact_table.getModel();
        temp_model2.setRowCount(0);
        v_selected_vents.clear();
        //****** liste des produits sons repetision
        Vector<String> listproduit = new Stack<String>();
        listproduit.add(" / ");
        //----------------------------
//        Vector<Vent> parants = vm.getAllVents_ofclient_withdate(idClient, month, year);
        v_vents = vm.getAllVents_ofclient_withdate(idClient, month, year);
        // afficher les vent dan la table vent_table
        // et afficher les nom des roduit exictant dans les vent
        for (int i = 0; i < v_vents.size(); i++) {
            Vent v = (Vent) v_vents.get(i);
            Object[] row = new Object[]{
                v.getDate_vent(),
                v.getIdProduit(),
                v.getQte(),
                v.getPrixU(),
                v.getMontant()};
            model.addRow(row);
            //------------
            if (!isDuplicated(listproduit, v.getIdProduit())) {
                listproduit.add(v.getIdProduit());
            }
        }
        //--------------------------------------
        final DefaultComboBoxModel model2 = new DefaultComboBoxModel(listproduit);
        produit_list.setModel(model2);
        //----------------------------------
    }

    private void pick_client(int index)/*String profile) throws SQLException */ {
//        selected_client = cm.getClient_byprofile(profile); 
        //----------------------------------------
        if (index == 0) {
            selected_client = new Client();
        } else {
            index--;
            selected_client = v_clients.get(index);
            System.out.println("" + selected_client.getIdClient());
        }
        //----------------------------------------

    }

    //---------------------------------------------
    void calc_Tot() {
//        DefaultTableModel model = (DefaultTableModel) fact_table.getModel();
        float somme = 0;
        for (int i = 0; i < v_selected_vents.size(); i++) {
            somme += v_selected_vents.get(i).getMontant();
        }
        jTextField32.setText("" + somme);

        calcul_tva();

    }

    void calcul_tva() {
        if (!jTextField1.getText().isEmpty()) {
            float mtht = Float.parseFloat(jTextField32.getText());
            int ptva = Integer.parseInt(jTextField1.getText());

            float tva = (float) mtht * ptva / 100;
            jTextField33.setText("" + tva);

            float mtttc = mtht + tva;
            jTextField34.setText("" + mtttc);

        }
    }

    void generat_N_fact() throws SQLException {
        String last_num = fm.autoID();
        jNo_fact.setText(last_num);
    }

    /**
     * Creates new form Generate_facture_frame
     */
    public Generate_facture_frame(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        AfficherClients_Profil();
        generat_N_fact();
        jTextField1.getDocument().addDocumentListener(calculeTotalwithTAX_listener);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        client_list = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        print_btn = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        vent_table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        produit_list = new javax.swing.JComboBox<>();
        btn_add_fact = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        fact_table = new javax.swing.JTable();
        jMonthChooser2 = new com.toedter.calendar.JMonthChooser();
        jYearChooser2 = new com.toedter.calendar.JYearChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jNo_fact = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Les vents de mois :");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Les vents de mois :");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(275, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Client :");

        client_list.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        client_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "/", "Item 2", "Item 3", "Item 4" }));
        client_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                client_listActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("montant total hors taxes :");

        jTextField32.setEditable(false);
        jTextField32.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField32.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField32.setText("0");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("taxe sur la valeur ajoutee :");

        jTextField33.setEditable(false);
        jTextField33.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField33.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField33.setText("0");

        jTextField34.setEditable(false);
        jTextField34.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField34.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField34.setText("0");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("montant total en tout taxes comprises :");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField1.setText("0");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("%");

        print_btn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        print_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8_print_30px_1.png"))); // NOI18N
        print_btn.setLabel("Imprimer");
        print_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_btnActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox1.setText("facture detailleé (pour un seul produit)");
        jCheckBox1.setEnabled(false);

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox2.setText("facture en arabe");
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(527, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2))
                        .addGap(88, 88, 88)
                        .addComponent(print_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(print_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Trouver Les Commande");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        vent_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        vent_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Produit", "Qte", "Prix", "montant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vent_table.setRowHeight(26);
        vent_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(vent_table);
        if (vent_table.getColumnModel().getColumnCount() > 0) {
            vent_table.getColumnModel().getColumn(0).setPreferredWidth(50);
            vent_table.getColumnModel().getColumn(1).setPreferredWidth(150);
            vent_table.getColumnModel().getColumn(2).setPreferredWidth(10);
            vent_table.getColumnModel().getColumn(3).setPreferredWidth(10);
            vent_table.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        jPanel4.setLayout(new java.awt.BorderLayout());

        produit_list.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        produit_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "/" }));
        produit_list.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        produit_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produit_listActionPerformed(evt);
            }
        });

        btn_add_fact.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_add_fact.setText("Ajouter");
        btn_add_fact.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_add_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_factActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Choisissez un produit:");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Qte:");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.setText("0");
        jTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.setText("0");
        jTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Prix HT:");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setText("0");
        jTextField4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Montant:");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Vider la liste");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(produit_list, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_add_fact))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(produit_list, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_add_fact)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.NORTH);

        fact_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fact_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Produit", "Qte", "Prix", "montant"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fact_table.setRowHeight(26);
        fact_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(fact_table);
        if (fact_table.getColumnModel().getColumnCount() > 0) {
            fact_table.getColumnModel().getColumn(0).setPreferredWidth(50);
            fact_table.getColumnModel().getColumn(1).setPreferredWidth(150);
            fact_table.getColumnModel().getColumn(2).setPreferredWidth(10);
            fact_table.getColumnModel().getColumn(3).setPreferredWidth(10);
            fact_table.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jPanel4.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        jMonthChooser2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jMonthChooser2.setYearChooser(jYearChooser2);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Facture du Mois :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Facture N°:");

        jNo_fact.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(client_list, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jMonthChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jNo_fact, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(client_list, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNo_fact, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jYearChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jMonthChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE)
                || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        // get month and year from inteface !!
        int mi = jMonthChooser2.getMonth() + 1;
        String month = "" + mi;
        String year = "" + jYearChooser2.getYear();
        if (mi < 10) {
            month = "0" + mi;
        }
        System.out.println(month + "-" + year);
        //-----------------------
        try {
//            AfficherVents(selected_client.getIdClient(),
//                    ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText(),
//                    ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText());
            AfficherVents(selected_client.getIdClient(),
                    month,
                    year);
        } catch (SQLException ex) {
            Logger.getLogger(Generate_facture_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void client_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_client_listActionPerformed

        pick_client(client_list.getSelectedIndex());

        DefaultTableModel model = (DefaultTableModel) fact_table.getModel();
        model.setRowCount(0);
        v_selected_vents.clear();
        calc_Tot();

        DefaultTableModel model2 = (DefaultTableModel) vent_table.getModel();
        model2.setRowCount(0);
        v_vents.clear();

        produit_list.setSelectedIndex(0);
    }//GEN-LAST:event_client_listActionPerformed

    private void produit_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produit_listActionPerformed
        // TODO add your handling code here:
        /**
         * Calcoler la sommes des Qte et montant de produit selectioner
         *
         * seulement pour l affichaget dans TextFields
         */
        int t_qte = 0, t_prix = 0, t_montant = 0;
        DefaultTableModel model = (DefaultTableModel) vent_table.getModel();
        if (vent_table.getRowCount() != 0) {
            for (int i = 0; i < model.getRowCount(); i++) {
                if (produit_list.getSelectedItem().toString().equals(vent_table.getValueAt(i, 1).toString())) {
                    t_qte += Integer.parseInt(vent_table.getValueAt(i, 2).toString());
                    t_prix = Integer.parseInt(vent_table.getValueAt(i, 3).toString());
                    t_montant += Integer.parseInt(vent_table.getValueAt(i, 4).toString());
                }
            }
        }

        jTextField2.setText("" + t_qte);
        jTextField3.setText("" + t_prix);
        jTextField4.setText("" + t_montant);


    }//GEN-LAST:event_produit_listActionPerformed

    private void btn_add_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_factActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) fact_table.getModel();
        /**
         * tester Si le produit est dejas exicte dans la facture
         */
        boolean tr = false;
        for (int i = 0; i < v_selected_vents.size(); i++) {//fact_table.getRowCount()
            if (produit_list.getSelectedItem().equals(v_selected_vents.get(i).getIdProduit())) {
                tr = true;
                break;
            }
        }
//        if (!produit_list.getSelectedItem().equals(" / ") && !tr) {
//            int counter = model.getRowCount();
//            Object[] row = new Object[]{
//                counter + 1, produit_list.getSelectedItem(), jTextField2.getText(), jTextField3.getText(), jTextField4.getText()};
//            model.addRow(row);
//            fact_table.setModel(model);
//
//            calc_Tot();
//        }
        //----------------------------------------------
        int t_qte = 0, t_prix = 0, t_montant = 0;
        /**
         * ajouter le produit au tableau du facture et V_selected_vents RQ:
         * getIdProduit() contient le nom de produit
         */
        if (!produit_list.getSelectedItem().equals(" / ") && !tr) {
            for (int i = 0; i < v_vents.size(); i++) {
                String temp = produit_list.getSelectedItem().toString();
                if (temp.equals(v_vents.get(i).getIdProduit())) {
                    Vent v = v_vents.get(i);
                    v_selected_vents.add(v);
                    t_qte += v.getQte();
                    t_prix = v.getPrixU();
                    t_montant += v.getMontant();
                }
            }
            int counter = model.getRowCount();
            Object[] row = new Object[]{
                counter + 1, produit_list.getSelectedItem(), t_qte, t_prix, t_montant};
            model.addRow(row);
            fact_table.setModel(model);

            calc_Tot();
        }
        //----------------------------------------------

    }//GEN-LAST:event_btn_add_factActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) fact_table.getModel();
        model.setRowCount(0);
        v_selected_vents.clear();
        calc_Tot();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void print_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_btnActionPerformed
        String date = "";
        int monthe = jMonthChooser2.getMonth() + 1;
        date = monthe + "-" + jYearChooser2.getYear();
        DefaultTableModel model = (DefaultTableModel) fact_table.getModel();

        if (!jCheckBox2.isSelected()) {
            //   en francais *******************
            try {
                // TODO add your handling code here:
                /**
                 * selected_client
                 *
                 * jMonthChooser2.getMonth() jYearChooser2.getYear()
                 *
                 * jNo_fact.gettext();
                 *
                 * v_selected_vents
                 *
                 * jTextField32 jTextField1 jTextField33 jTextField34
                 */
                fr_factur_generate.generate_fr_factur(
                        selected_client,
                        date,
                        jNo_fact.getText(),
                        v_selected_vents,
                        model,
                        Float.parseFloat(jTextField32.getText()),
                        Integer.parseInt(jTextField1.getText()),
                        Float.parseFloat(jTextField33.getText()),
                        Float.parseFloat(jTextField34.getText()));
            } catch (MalformedURLException | FileNotFoundException ex) {
                Logger.getLogger(Generate_facture_frame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Generate_facture_frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // En Arabe ******************************
            try {
                ar_factur_generate.generate_ar_factur(
                        selected_client,
                        date,
                        jNo_fact.getText(),
                        v_selected_vents,
                        model,
                        Float.parseFloat(jTextField32.getText()),
                        Integer.parseInt(jTextField1.getText()),
                        Float.parseFloat(jTextField33.getText()),
                        Float.parseFloat(jTextField34.getText()),
                        jCheckBox1.isSelected());
            } catch (Exception ex) {
                Logger.getLogger(Generate_facture_frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //add Facture to Data base
        Facture f = new Facture();
        f.setIdFacture(0);
        f.setnFacture(jNo_fact.getText());
        f.setDate(date);
        f.setIdClient(selected_client.getIdClient());
        f.setMTHT(Integer.parseInt(jTextField32.getText().substring(0, jTextField32.getText().length() - 2)));
        f.setTva(Integer.parseInt(jTextField1.getText()));
        f.setTva_p(Float.parseFloat(jTextField33.getText()));
        f.setMttc(Float.parseFloat(jTextField34.getText()));

        try {
            fm.Add_facture(f, v_selected_vents);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Generate_facture_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Home_v2.AfficherFactures();
        } catch (SQLException ex) {
            Logger.getLogger(Generate_facture_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_print_btnActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        // TODO add your handling code here:
        if (!jCheckBox2.isSelected()) {
            jCheckBox1.setEnabled(false);
            jCheckBox1.setSelected(false);
        } else {
            jCheckBox1.setEnabled(true);
            jCheckBox1.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Generate_facture_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Generate_facture_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Generate_facture_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Generate_facture_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Generate_facture_frame dialog;
                try {
                    dialog = new Generate_facture_frame(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Generate_facture_frame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_fact;
    private javax.swing.JComboBox<String> client_list;
    private static javax.swing.JTable fact_table;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private static javax.swing.JCheckBox jCheckBox1;
    private static javax.swing.JCheckBox jCheckBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private com.toedter.calendar.JMonthChooser jMonthChooser2;
    private javax.swing.JTextField jNo_fact;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField4;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private com.toedter.calendar.JYearChooser jYearChooser2;
    private javax.swing.JButton print_btn;
    private static javax.swing.JComboBox<String> produit_list;
    private static javax.swing.JTable vent_table;
    // End of variables declaration//GEN-END:variables
}
