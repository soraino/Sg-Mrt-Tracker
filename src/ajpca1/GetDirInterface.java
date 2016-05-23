package ajpca1;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.regex.Pattern;
import javafx.scene.input.KeyCode;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Darren
 */
public class GetDirInterface extends javax.swing.JFrame {

   

    /**
     * Creates new form GetDirInterface
     */
    public GetDirInterface() {
        initComponents();
        Trains t = new Trains();
        t.SetHashTable();
        AutoSuggestor autoSuggestor = new AutoSuggestor(TxtBoard, this, null, Color.WHITE.brighter(), Color.RED.darker(), Color.RED.brighter(), 0.75f) {
            @Override
            boolean wordTyped(String typedWord) {

               


                setDictionary(t.GetTrainName());
                //addToDictionary("bye");//adds a single word

                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };
        AutoSuggestor autoSuggestor2 = new AutoSuggestor(TxtAlight, this, null, Color.WHITE.brighter(), Color.RED.darker(), Color.RED.brighter(), 0.75f) {
            @Override
            boolean wordTyped(String typedWord) {

               


                setDictionary(t.GetTrainName());
                //addToDictionary("bye");//adds a single word

                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };
        
    }

    public void something() {
        
    }

    public void something2() {
        //jLabel1.setText("this works2");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        BtnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LblHome = new javax.swing.JLabel();
        TxtAlight = new javax.swing.JTextField();
        TxtBoard = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Please enter Boarding and Alighting Stations");

        BtnSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BtnSearch.setText("Search");
        BtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Boarding Station :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Alighting Station :");

        LblHome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LblHome.setText("Return back to Home page");
        LblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LblHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LblHomeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LblHomeMousePressed(evt);
            }
        });

        TxtAlight.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtBoard.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(BtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TxtAlight, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 397, Short.MAX_VALUE)
                .addComponent(LblHome))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(134, 134, 134)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtAlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addComponent(BtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(LblHome))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LblHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblHomeMouseEntered
        LblHome.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_LblHomeMouseEntered

    private void LblHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblHomeMouseExited
        LblHome.setForeground(Color.black);
    }//GEN-LAST:event_LblHomeMouseExited

    private void LblHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblHomeMousePressed
        AskDirInterface AD = new AskDirInterface();
        AD.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LblHomeMousePressed

    private void BtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSearchActionPerformed
        CalculateRoute cr = new CalculateRoute(TxtBoard.getText().trim(), TxtAlight.getText().trim());
        boolean l = cr.Getroutes();
        if (TxtBoard.getText().equalsIgnoreCase(TxtAlight.getText()) != true) {
            if (l == true) {
                JOptionPane.showMessageDialog(null, cr.GetDestination(), "SMRT", JOptionPane.PLAIN_MESSAGE);
            } else if (l == false) {
                JOptionPane.showMessageDialog(null, "Error train inputed does not exist", "Error", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (TxtAlight.getText().isEmpty() || TxtBoard.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter trains", "Error", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cannot input 2 same trains station on boarding and aligthing", "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_BtnSearchActionPerformed

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
            java.util.logging.Logger.getLogger(GetDirInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetDirInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetDirInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetDirInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetDirInterface().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSearch;
    private javax.swing.JLabel LblHome;
    private javax.swing.JTextField TxtAlight;
    private javax.swing.JTextField TxtBoard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
