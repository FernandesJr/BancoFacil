package View;

import Controller.ControllerExtrato;
import Controller.ControllerMenuPrincipal;
import Model.Conta;
import Model.Usuario;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

public class Extrato extends javax.swing.JFrame {
    
    private final ControllerExtrato controlador;
    private ControllerMenuPrincipal telamenu;
    private Conta conta;
    public Usuario usuario;
    
    public Extrato() {
        initComponents();
        ControllerExtrato controladorl = new ControllerExtrato(this); ////Instaciando o controlador
        this.controlador = controladorl;
        
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
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabelExtrato = new javax.swing.JLabel();
        jButtonVoltarMenu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelSaldoR$ = new javax.swing.JLabel();
        jLabelValorSaldo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneExtrato = new javax.swing.JTextPane();
        jComboMes = new javax.swing.JComboBox<>();
        jLabelMes = new javax.swing.JLabel();
        jLabelAno = new javax.swing.JLabel();
        jComboAno = new javax.swing.JComboBox<>();
        jButtonPdf = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextField1.setText("jTextField1");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Banco Fácil"));

        jLabelExtrato.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelExtrato.setForeground(new java.awt.Color(0, 102, 102));
        jLabelExtrato.setText("Extrato");

        jButtonVoltarMenu.setText("Voltar ao Menu");
        jButtonVoltarMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 255), null));
        jButtonVoltarMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVoltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarMenuActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setForeground(new java.awt.Color(0, 153, 153));

        jLabelSaldoR$.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSaldoR$.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelSaldoR$.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSaldoR$.setText("Saldo  R$");

        jLabelValorSaldo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelValorSaldo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelValorSaldo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelValorSaldo.setText("999");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSaldoR$)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValorSaldo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSaldoR$)
                    .addComponent(jLabelValorSaldo))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTextPaneExtrato.setBackground(new java.awt.Color(0, 153, 153));
        jTextPaneExtrato.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextPaneExtrato.setForeground(new java.awt.Color(0, 0, 0));
        jTextPaneExtrato.setAutoscrolls(false);
        jTextPaneExtrato.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextPaneExtrato.setEnabled(false);
        jScrollPane2.setViewportView(jTextPaneExtrato);

        jComboMes.setBackground(new java.awt.Color(0, 153, 153));
        jComboMes.setForeground(new java.awt.Color(255, 255, 255));
        jComboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez" }));
        jComboMes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboMesActionPerformed(evt);
            }
        });

        jLabelMes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/magnifier.png"))); // NOI18N
        jLabelMes.setText("Selecione o mês");

        jLabelAno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/magnifier.png"))); // NOI18N
        jLabelAno.setText("Selecione o ano");

        jComboAno.setBackground(new java.awt.Color(0, 153, 153));
        jComboAno.setForeground(new java.awt.Color(255, 255, 255));
        jComboAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "2022", "2021", "2020" }));
        jComboAno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboAnoActionPerformed(evt);
            }
        });

        jButtonPdf.setBackground(new java.awt.Color(204, 204, 204));
        jButtonPdf.setForeground(new java.awt.Color(0, 102, 204));
        jButtonPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/icons/icon-pdf-32.png"))); // NOI18N
        jButtonPdf.setText("Salvar");
        jButtonPdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(27, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelAno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboAno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonPdf)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelExtrato)
                        .addGap(285, 285, 285))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonVoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelExtrato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelMes)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(jLabelAno)
                        .addGap(18, 18, 18)
                        .addComponent(jComboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jButtonVoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarMenuActionPerformed
        this.controlador.voltarMenu(telamenu);
    }//GEN-LAST:event_jButtonVoltarMenuActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.jLabelValorSaldo.setText(controlador.mostrarSaldo(conta)); //Label responsavel para mostrar o saldo
        try {
            this.controlador.mostrarExtrato(conta, usuario);
        } catch (SQLException ex) {
            Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jComboMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboMesActionPerformed
        try {
            this.controlador.selecaoPeriodo(jComboMes.getSelectedItem().toString(), jComboAno.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboMesActionPerformed

    private void jComboAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboAnoActionPerformed
        try {
            this.controlador.selecaoPeriodo(jComboMes.getSelectedItem().toString(), jComboAno.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboAnoActionPerformed

    private void jButtonPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPdfActionPerformed
        try {
            this.controlador.exportarPDFJasper();
            /*try {
            this.controlador.exportarPdf();
            } catch (DocumentException | IOException ex) {
            System.out.println("Erro: pdf"+ex);
            JOptionPane.showMessageDialog(this,"Feche o antigo relatório.");
            }*/
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, ex);
            Logger.getLogger(Extrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPdfActionPerformed

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
            java.util.logging.Logger.getLogger(Extrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Extrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Extrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Extrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Extrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPdf;
    private javax.swing.JButton jButtonVoltarMenu;
    private javax.swing.JComboBox<String> jComboAno;
    private javax.swing.JComboBox<String> jComboMes;
    private javax.swing.JLabel jLabelAno;
    private javax.swing.JLabel jLabelExtrato;
    private javax.swing.JLabel jLabelMes;
    private javax.swing.JLabel jLabelSaldoR$;
    private javax.swing.JLabel jLabelValorSaldo;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPaneExtrato;
    // End of variables declaration//GEN-END:variables
    
    public void trazerMenu(ControllerMenuPrincipal menu){
        this.telamenu = menu;
    }
    
    public void trazerConta(Conta conta){
        this.conta = conta;
    }
    
    public void trazerUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public javax.swing.JTextPane getjTextPaneExtrato() {
        return jTextPaneExtrato;
    }

    public void setjTextPaneExtrato(javax.swing.JTextPane jTextPaneExtrato) {
        this.jTextPaneExtrato = jTextPaneExtrato;
    }
    
    
}
