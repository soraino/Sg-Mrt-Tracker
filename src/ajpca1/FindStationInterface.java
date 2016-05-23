/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajpca1;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javafx.scene.input.KeyCode;
import javax.swing.JOptionPane;

/**
 *
 * @author Darren
 */
public class FindStationInterface extends javax.swing.JFrame {

    /**
     * Creates new form FindStationInterface
     */
    public FindStationInterface() {
        initComponents();
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
        TxtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BtnSearch = new javax.swing.JButton();
        LblHome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Please enter the the station name / code");

        TxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtSearchKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Station / code :");

        BtnSearch.setText("Search");
        BtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSearchActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblHome)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(78, 78, 78)
                .addComponent(BtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(LblHome)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 /**
  * this is to give an effect when user is hovering over the label to create the feel that it is interactable
  * @param evt 
  */
    private void LblHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblHomeMouseEntered
        LblHome.setForeground(Color.MAGENTA);
    }//GEN-LAST:event_LblHomeMouseEntered
/**
 * this is when mouse gets out of the label
 * @param evt 
 */
    private void LblHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblHomeMouseExited
        LblHome.setForeground(Color.black);
    }//GEN-LAST:event_LblHomeMouseExited
/**
 * this is when the user clicks the label and it will take them back to th3e home page
 * @param evt 
 */
    private void LblHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblHomeMousePressed
        AskDirInterface AD = new AskDirInterface();
        AD.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LblHomeMousePressed

    private void TxtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            
            if(TxtSearch.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Invalid input","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
            BtnSearch.doClick();
            }
        }
    }//GEN-LAST:event_TxtSearchKeyPressed

    private void BtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSearchActionPerformed
        Search s = new Search();
        if(s.SearchTrain(TxtSearch.getText())){
            JOptionPane.showMessageDialog(null,"Line Name : " + s.GetLine()+"\nStation Code : " +s.GetCode()+"\nStation Name : "+s.GetStationName()
                    ,"Train Information",JOptionPane.PLAIN_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null,"Error couldn't find your station", "Error",JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(FindStationInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindStationInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindStationInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindStationInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FindStationInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSearch;
    private javax.swing.JLabel LblHome;
    private javax.swing.JTextField TxtSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
