/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dbclasse.Client;
import dbclasse.Facture;
import dbclasse.Produit;
import dbclasse.Vent;
import dbmanipulation.ClientManipulation;
import dbmanipulation.Facture_manupulation;
import dbmanipulation.ProduitManipulation;
import dbmanipulation.VentManipulation;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class Home_v2 extends javax.swing.JFrame {

    Vector<Client> v_clients = new Vector<>();

    static ProduitManipulation pm = new ProduitManipulation();
    static ClientManipulation cm = new ClientManipulation();
    static VentManipulation vm = new VentManipulation();
    static Facture_manupulation fm = new Facture_manupulation();

    /**
     * Creates new form Home_v2
     */
    //------------------------------------------------------------
    // afficher tout les Produit en produit table
    public static void AfficherProduit() throws SQLException {
        // TODO Auto-generated method stub
        DefaultTableModel model = (DefaultTableModel) produit_table.getModel();
        model.setRowCount(0);
        Vector<Produit> parants = pm.getAllProduits();
        for (int i = 0; i < parants.size(); i++) {
            Produit p = (Produit) parants.get(i);
            Object[] row = new Object[]{
                p.getIdProduit(),
                p.getDesignation(),
                p.getDesignation_fr(),
                p.getQte(),
                p.getPrixU_ht(),
                p.getPrix_achat()};
            model.addRow(row);
        }
    }

    // afficher tout les Clients en Clients table
    public static void AfficherClients() throws SQLException {
        // TODO Auto-generated method stub
        DefaultTableModel model = (DefaultTableModel) clients_table.getModel();
        model.setRowCount(0);
        Vector<Client> parants = cm.getAllClients();
        for (int i = 0; i < parants.size(); i++) {
            Client p = (Client) parants.get(i);
            Object[] row = new Object[]{
                p.getIdClient(),
                p.getDoit(),
                p.getProfil(),
                p.getFormeJuridique(),
                p.getAdr(),
                p.getTel(),
                p.getFax(),
                p.getNif()};
            model.addRow(row);
        }
    }

    // afficher tout les Vent  en Vent table
    public static void AfficherVents() throws SQLException {
        // TODO Auto-generated method stub
        DefaultTableModel model = (DefaultTableModel) vent_table.getModel();
        model.setRowCount(0);
        Vector<Vent> parants = vm.getAllVents();
        for (int i = 0; i < parants.size(); i++) {
            Vent v = (Vent) parants.get(i);
            Object[] row = new Object[]{
                v.getIdVent(),
                v.getDate_vent(),
                v.getIdClinet(),
                v.getIdProduit(),
                v.getQte(),
                v.getPrixU(),
                v.getMontant()};
            model.addRow(row);
        }
    }

    // afficher tout les Facture  en Facture table
    public static void AfficherFactures() throws SQLException {
        // TODO Auto-generated method stub
        DefaultTableModel model = (DefaultTableModel) facture_table.getModel();
        model.setRowCount(0);
        Vector<Facture> parants = fm.getAllFactures();
        for (int i = 0; i < parants.size(); i++) {
            Facture v = (Facture) parants.get(i);
            Object[] row = new Object[]{
                v.getnFacture(),
                v.getDate(),
                v.getIdClient(),
                v.getMTHT(),
                v.getTva(),
                v.getMttc()};
            model.addRow(row);
        }
    }

    public final void AfficherClients_Profil_factPanel() throws SQLException {

        v_clients = cm.getAllClients();
        Vector<String> clients = new Vector<>();
        clients.add("tous les clients");
        for (int i = 0; i < v_clients.size(); i++) {
            Client c = v_clients.get(i);
            clients.add(c.getProfil());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(clients);
        jComboBox2.setModel(model);
        jComboBox1.setModel(model);

    }

    public static void AfficherVents_byNfacture_factPanel() throws SQLException {

        // TODO Auto-generated method stub
        DefaultTableModel model = (DefaultTableModel) fact_vent.getModel();
        model.setRowCount(0);
        Vector<Facture> parants = fm.getAllFactures();
        for (int i = 0; i < parants.size(); i++) {
            Facture v = (Facture) parants.get(i);
            Object[] row = new Object[]{
                v.getnFacture(),
                v.getDate(),
                v.getIdClient(),
                v.getMTHT(),
                v.getTva(),
                v.getMttc()};
            model.addRow(row);
        }

    }
    //------------------------------------------------------------

    public Home_v2() throws SQLException {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        AfficherProduit();
        AfficherClients();
        AfficherVents();

        AfficherFactures();
        AfficherClients_Profil_factPanel();

        facture_table.getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (facture_table.getSelectedRow() != -1) {
                            try {
                                DefaultTableModel mod = (DefaultTableModel) facture_table.getModel();
                                DefaultTableModel model = (DefaultTableModel) fact_vent.getModel();
                                model.setRowCount(0);
                                Vector<Vent> parants = vm.getVentsOfFacture(mod.getValueAt(facture_table.getSelectedRow(), 0).toString());
                                for (int i = 0; i < parants.size(); i++) {
                                    Vent v = (Vent) parants.get(i);
                                    Object[] row = new Object[]{
                                        v.getIdVent(),
                                        v.getDate_vent(),
                                        //v.getIdClinet(),
                                        v.getIdProduit(),
                                        v.getQte(),
                                        v.getPrixU(),
                                        v.getMontant()};
                                    model.addRow(row);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(Home_v2.class.getName()).log(Level.SEVERE, null, ex);
                            }
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

        gestion_client_panel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        accueil = new javax.swing.JPanel();
        jButton28 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        employees = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        stock = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        clients = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        sceen = new javax.swing.JPanel();
        home_panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        commandes_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton27 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        vent_table = new javax.swing.JTable();
        gestion_employ_panel = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        gestion_client_panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        clients_table = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        gestion_produit_panel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        produit_table = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        facture_panel = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        facture_table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        fact_vent = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setText("jTextField1");

        jButton6.setText("Nouveau");

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("Rechercher");

        jButton8.setText("Modifier");

        jButton9.setText("Supprimer");

        jButton10.setText("Imprimer");

        javax.swing.GroupLayout gestion_client_panel2Layout = new javax.swing.GroupLayout(gestion_client_panel2);
        gestion_client_panel2.setLayout(gestion_client_panel2Layout);
        gestion_client_panel2Layout.setHorizontalGroup(
            gestion_client_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestion_client_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gestion_client_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gestion_client_panel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(gestion_client_panel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(gestion_client_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gestion_client_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING)))))
        );
        gestion_client_panel2Layout.setVerticalGroup(
            gestion_client_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestion_client_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gestion_client_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gestion_client_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(gestion_client_panel2Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addGap(35, 35, 35)
                        .addComponent(jButton10)
                        .addGap(96, 96, 96)
                        .addComponent(jButton9)
                        .addGap(0, 109, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-accueil-48.png"))); // NOI18N
        jButton28.setText("Accueil");
        jButton28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton28.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-ajouter-le-fichier-48.png"))); // NOI18N
        jButton25.setText("Ajouter commande");
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-ordre-d'achat-48.png"))); // NOI18N
        jButton26.setText("Commandes");
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-ajouter-une-propriété-48.png"))); // NOI18N
        jButton30.setText("Crée Facture");
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton30.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-liste-48.png"))); // NOI18N
        jButton31.setText("Liste des Facture");
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout accueilLayout = new javax.swing.GroupLayout(accueil);
        accueil.setLayout(accueilLayout);
        accueilLayout.setHorizontalGroup(
            accueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accueilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton31)
                .addContainerGap(382, Short.MAX_VALUE))
        );
        accueilLayout.setVerticalGroup(
            accueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accueilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(accueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton30)
                        .addComponent(jButton31))
                    .addComponent(jButton28))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Accueil", accueil);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-emp-48.png"))); // NOI18N
        jButton4.setText("Gestion des employées");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout employeesLayout = new javax.swing.GroupLayout(employees);
        employees.setLayout(employeesLayout);
        employeesLayout.setHorizontalGroup(
            employeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap(853, Short.MAX_VALUE))
        );
        employeesLayout.setVerticalGroup(
            employeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Employées", employees);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-produit-48.png"))); // NOI18N
        jButton1.setText("Produits");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-entrepôt-48.png"))); // NOI18N
        jButton2.setText("Inventaire");
        jButton2.setEnabled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-haute-priorité-48.png"))); // NOI18N
        jButton3.setText("Alerte de stock");
        jButton3.setEnabled(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-ajouter-48.png"))); // NOI18N
        jButton21.setText("Ajouter stock rapide");
        jButton21.setEnabled(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout stockLayout = new javax.swing.GroupLayout(stock);
        stock.setLayout(stockLayout);
        stockLayout.setHorizontalGroup(
            stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21)
                .addContainerGap(552, Short.MAX_VALUE))
        );
        stockLayout.setVerticalGroup(
            stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton21))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Stock", stock);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-gestion-des-clients-48.png"))); // NOI18N
        jButton5.setText("Gestion des clients");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-graphique-combiné-48.png"))); // NOI18N
        jButton29.setText("Statistiques des clients");
        jButton29.setEnabled(false);
        jButton29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton29.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clientsLayout = new javax.swing.GroupLayout(clients);
        clients.setLayout(clientsLayout);
        clientsLayout.setHorizontalGroup(
            clientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29)
                .addContainerGap(708, Short.MAX_VALUE))
        );
        clientsLayout.setVerticalGroup(
            clientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Clients", clients);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        sceen.setBackground(new java.awt.Color(255, 255, 204));
        sceen.setLayout(new java.awt.CardLayout());

        home_panel.setBackground(new java.awt.Color(255, 255, 204));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/icons8-accueil-150.png"))); // NOI18N
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout home_panelLayout = new javax.swing.GroupLayout(home_panel);
        home_panel.setLayout(home_panelLayout);
        home_panelLayout.setHorizontalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_panelLayout.createSequentialGroup()
                .addGap(417, 417, 417)
                .addComponent(jLabel6)
                .addContainerGap(472, Short.MAX_VALUE))
        );
        home_panelLayout.setVerticalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_panelLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel6)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        sceen.add(home_panel, "card6");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Par Client :");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton27.setText("Tous commandes");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        vent_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        vent_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° vent", "Date", "Client", "Produit", "Qte", "Prix", "montant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vent_table.setRowHeight(26);
        vent_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(vent_table);

        javax.swing.GroupLayout commandes_panelLayout = new javax.swing.GroupLayout(commandes_panel);
        commandes_panel.setLayout(commandes_panelLayout);
        commandes_panelLayout.setHorizontalGroup(
            commandes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commandes_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(commandes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
                    .addGroup(commandes_panelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        commandes_panelLayout.setVerticalGroup(
            commandes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commandes_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(commandes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        sceen.add(commandes_panel, "card4");

        gestion_employ_panel.setBackground(new java.awt.Color(153, 153, 255));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.setText("Employees");

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton11.setText("Rechercher");

        jButton12.setText("Nouveau");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Modifier");

        jButton14.setText("Supprimer");

        jButton15.setText("Imprimer");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout gestion_employ_panelLayout = new javax.swing.GroupLayout(gestion_employ_panel);
        gestion_employ_panel.setLayout(gestion_employ_panelLayout);
        gestion_employ_panelLayout.setHorizontalGroup(
            gestion_employ_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestion_employ_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gestion_employ_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(gestion_employ_panelLayout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(gestion_employ_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(gestion_employ_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        gestion_employ_panelLayout.setVerticalGroup(
            gestion_employ_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestion_employ_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gestion_employ_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addGap(4, 4, 4)
                .addGroup(gestion_employ_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gestion_employ_panelLayout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                .addContainerGap())
        );

        sceen.add(gestion_employ_panel, "card3");

        gestion_client_panel.setBackground(new java.awt.Color(255, 153, 153));

        clients_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        clients_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Client", "DOIT", "Profile", "Form_juridique", "Adress", "Tel", "Fax", "NIF"
            }
        ));
        clients_table.setRowHeight(26);
        clients_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(clients_table);

        jButton17.setText("Modifier");

        jButton18.setText("Supprimer");

        jButton19.setText("Imprimer");

        jButton20.setText("Nouveau");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconse/Customer_30px.png"))); // NOI18N
        jLabel1.setText("Gestion des clients");

        javax.swing.GroupLayout gestion_client_panelLayout = new javax.swing.GroupLayout(gestion_client_panel);
        gestion_client_panel.setLayout(gestion_client_panelLayout);
        gestion_client_panelLayout.setHorizontalGroup(
            gestion_client_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gestion_client_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gestion_client_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gestion_client_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        gestion_client_panelLayout.setVerticalGroup(
            gestion_client_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestion_client_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gestion_client_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(gestion_client_panelLayout.createSequentialGroup()
                        .addComponent(jButton20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19)
                        .addGap(0, 235, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sceen.add(gestion_client_panel, "card2");

        gestion_produit_panel.setBackground(new java.awt.Color(153, 255, 153));

        produit_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        produit_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID produit", "designnation", "designnation fr", "stock", "Prix UN HT", "Prix achat"
            }
        ));
        produit_table.setRowHeight(26);
        produit_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(produit_table);

        jButton16.setText("Nouveau");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton22.setText("Modifier");

        jButton23.setText("Supprimer");

        jButton24.setText("Imprimer");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Produits");

        javax.swing.GroupLayout gestion_produit_panelLayout = new javax.swing.GroupLayout(gestion_produit_panel);
        gestion_produit_panel.setLayout(gestion_produit_panelLayout);
        gestion_produit_panelLayout.setHorizontalGroup(
            gestion_produit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gestion_produit_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gestion_produit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gestion_produit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        gestion_produit_panelLayout.setVerticalGroup(
            gestion_produit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestion_produit_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gestion_produit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gestion_produit_panelLayout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );

        sceen.add(gestion_produit_panel, "card5");

        facture_panel.setBackground(new java.awt.Color(204, 255, 204));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "tous les clients", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Par Client :");

        jButton32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton32.setText("Filtrer");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        facture_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        facture_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° Facture", "Date", "Client", "Montant HT", "TVA", "Montabt TTC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        facture_table.setRowHeight(26);
        facture_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        facture_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(facture_table);
        if (facture_table.getColumnModel().getColumnCount() > 0) {
            facture_table.getColumnModel().getColumn(0).setPreferredWidth(20);
            facture_table.getColumnModel().getColumn(1).setPreferredWidth(20);
            facture_table.getColumnModel().getColumn(2).setPreferredWidth(300);
            facture_table.getColumnModel().getColumn(3).setPreferredWidth(50);
            facture_table.getColumnModel().getColumn(4).setPreferredWidth(20);
            facture_table.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Les articlles de facture :");

        fact_vent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fact_vent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° Vent", "Date", "Produit", "Qte", "Prix U", "Montant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fact_vent.setRowHeight(26);
        jScrollPane8.setViewportView(fact_vent);
        if (fact_vent.getColumnModel().getColumnCount() > 0) {
            fact_vent.getColumnModel().getColumn(2).setMinWidth(150);
            fact_vent.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        javax.swing.GroupLayout facture_panelLayout = new javax.swing.GroupLayout(facture_panel);
        facture_panel.setLayout(facture_panelLayout);
        facture_panelLayout.setHorizontalGroup(
            facture_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facture_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(facture_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane7)
                    .addGroup(facture_panelLayout.createSequentialGroup()
                        .addGroup(facture_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(facture_panelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 509, Short.MAX_VALUE)))
                .addContainerGap())
        );
        facture_panelLayout.setVerticalGroup(
            facture_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facture_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(facture_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(facture_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );

        sceen.add(facture_panel, "card7");

        getContentPane().add(sceen, java.awt.BorderLayout.CENTER);

        jMenuBar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Configurations");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem1.setText("Configuration Login et Psw");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Configuration facture");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Configuration fiche vendeur");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Utilisateurs");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        sceen.removeAll();
        sceen.add(gestion_client_panel);
        sceen.repaint();
        sceen.revalidate();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        sceen.removeAll();
        sceen.add(gestion_employ_panel);
        sceen.repaint();
        sceen.revalidate();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        Add_update_client dialog = new Add_update_client(null, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        // new Add_update_produit().setVisible(true);
        Add_update_produit dialog = new Add_update_produit(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        sceen.removeAll();
        sceen.add(gestion_produit_panel);
        sceen.repaint();
        sceen.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        sceen.removeAll();
        sceen.add(commandes_panel);
        sceen.repaint();
        sceen.revalidate();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        try {
            //new Ajouter_commande_frame().setVisible(true);
            Ajouter_commande_frame dialog = new Ajouter_commande_frame(this, true);
            dialog.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Home_v2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        sceen.removeAll();
        sceen.add(home_panel);
        sceen.repaint();
        sceen.revalidate();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Configurer_Vendeur dialog;
        dialog = new Configurer_Vendeur(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // jComboBox1
        try {
            if (jComboBox1.getSelectedIndex() == 0) {
                AfficherVents();
            } else {
                DefaultTableModel model = (DefaultTableModel) vent_table.getModel();
                model.setRowCount(0);
                Vector<Vent> parants = vm.getVents_costum(v_clients.get(jComboBox1.getSelectedIndex() - 1).getIdClient());
                for (int i = 0; i < parants.size(); i++) {
                    Vent v = (Vent) parants.get(i);
                    Object[] row = new Object[]{
                        v.getIdVent(),
                        v.getDate_vent(),
                        v.getIdClinet(),
                        v.getIdProduit(),
                        v.getQte(),
                        v.getPrixU(),
                        v.getMontant()};
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home_v2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        try {
            // TODO add your handling code here:

            Generate_facture_frame dialog = new Generate_facture_frame(this, true);
            dialog.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Home_v2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton30ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Configurer_facture dialog;
        dialog = new Configurer_facture(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        sceen.removeAll();
        sceen.add(facture_panel);
        sceen.repaint();
        sceen.revalidate();
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        try {
            DefaultTableModel mod = (DefaultTableModel) fact_vent.getModel();
            mod.setRowCount(0);
            // Affucher les facture par client selctionee 
            if (jComboBox2.getSelectedIndex() == 0) {
                AfficherFactures();

            } else {
                DefaultTableModel model = (DefaultTableModel) facture_table.getModel();
                model.setRowCount(0);
                Vector<Facture> parants = fm.getFactures_ByClientID(v_clients.get(jComboBox2.getSelectedIndex() - 1).getIdClient());
                for (int i = 0; i < parants.size(); i++) {
                    Facture v = (Facture) parants.get(i);
                    Object[] row = new Object[]{
                        v.getnFacture(),
                        v.getDate(),
                        v.getIdClient(),
                        v.getMTHT(),
                        v.getTva(),
                        v.getMttc()};
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home_v2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton32ActionPerformed

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
            java.util.logging.Logger.getLogger(Home_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Home_v2().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Home_v2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accueil;
    private javax.swing.JPanel clients;
    private static javax.swing.JTable clients_table;
    private javax.swing.JPanel commandes_panel;
    private javax.swing.JPanel employees;
    private static javax.swing.JTable fact_vent;
    private javax.swing.JPanel facture_panel;
    private static javax.swing.JTable facture_table;
    private javax.swing.JPanel gestion_client_panel;
    private javax.swing.JPanel gestion_client_panel2;
    private javax.swing.JPanel gestion_employ_panel;
    private javax.swing.JPanel gestion_produit_panel;
    private javax.swing.JPanel home_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private static javax.swing.JTable produit_table;
    private javax.swing.JPanel sceen;
    private javax.swing.JPanel stock;
    private static javax.swing.JTable vent_table;
    // End of variables declaration//GEN-END:variables
}
