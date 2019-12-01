/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towergame;

import javax.swing.JScrollPane;
import javax.swing.ButtonModel;
import java.util.ArrayList;

/**
 *
 * @author Mark Masone
 */
public class TowerFrame extends javax.swing.JFrame {

    /**
     * Creates new form TowerFrame
     * @param gamePanel
     */
    public TowerFrame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        towerFrameListeners = new ArrayList();
        initComponents();
        System.out.println(gameScrollPane.getVerticalScrollBar().getValue());
        gameScrollPane.getVerticalScrollBar().setValue(4800 - (Floor.HEIGHT * 43) + 1);
        System.out.println(gameScrollPane.getVerticalScrollBar().getValue());
    }
    
    public void addTowerFrameListener(TowerFrameListener tfl) {
        towerFrameListeners.add(tfl);
    }
    
    public ButtonModel getSelectedRadioButtonModel() {
        return buttonGroup1.getSelection();
    }
    
    private void radioButtonSelectionChanged() {
        for(TowerFrameListener tfl : towerFrameListeners)
            tfl.radioButtonSelectionChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        gameScrollPane = new JScrollPane(gamePanel);
        condoRadioButton = new javax.swing.JRadioButton();
        officeRadioButton = new javax.swing.JRadioButton();
        standardElevatorRadioButton = new javax.swing.JRadioButton();
        hallRadioButton = new javax.swing.JRadioButton();
        lobbyRadioButton = new javax.swing.JRadioButton();
        expressElevatorRadioButton = new javax.swing.JRadioButton();
        demolishRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gameScrollPane.setMinimumSize(new java.awt.Dimension(100, 100));
        gameScrollPane.setPreferredSize(new java.awt.Dimension(800, 600));

        buttonGroup1.add(condoRadioButton);
        condoRadioButton.setText("Condo");
        condoRadioButton.setActionCommand("condo");
        condoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                condoRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(officeRadioButton);
        officeRadioButton.setText("Office");
        officeRadioButton.setActionCommand("office");
        officeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                officeRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(standardElevatorRadioButton);
        standardElevatorRadioButton.setText("Standard Elevator");
        standardElevatorRadioButton.setActionCommand("standard elevator");
        standardElevatorRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standardElevatorRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(hallRadioButton);
        hallRadioButton.setText("Hall");
        hallRadioButton.setActionCommand("hall");
        hallRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(lobbyRadioButton);
        lobbyRadioButton.setText("Lobby");
        lobbyRadioButton.setActionCommand("lobby");
        lobbyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lobbyRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(expressElevatorRadioButton);
        expressElevatorRadioButton.setText("Express Elevator");
        expressElevatorRadioButton.setActionCommand("express elevator");
        expressElevatorRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expressElevatorRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(demolishRadioButton);
        demolishRadioButton.setText("DEMOLISH");
        demolishRadioButton.setActionCommand("demolish");
        demolishRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demolishRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(condoRadioButton)
                    .addComponent(officeRadioButton)
                    .addComponent(hallRadioButton)
                    .addComponent(lobbyRadioButton)
                    .addComponent(expressElevatorRadioButton)
                    .addComponent(standardElevatorRadioButton)
                    .addComponent(demolishRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(condoRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(officeRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(standardElevatorRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hallRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lobbyRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expressElevatorRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(demolishRadioButton)
                        .addGap(0, 226, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void condoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_condoRadioButtonActionPerformed
        radioButtonSelectionChanged();
    }//GEN-LAST:event_condoRadioButtonActionPerformed

    private void officeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_officeRadioButtonActionPerformed
        radioButtonSelectionChanged();
    }//GEN-LAST:event_officeRadioButtonActionPerformed

    private void standardElevatorRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standardElevatorRadioButtonActionPerformed
        radioButtonSelectionChanged();
    }//GEN-LAST:event_standardElevatorRadioButtonActionPerformed

    private void hallRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallRadioButtonActionPerformed
        radioButtonSelectionChanged();
    }//GEN-LAST:event_hallRadioButtonActionPerformed

    private void lobbyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lobbyRadioButtonActionPerformed
        radioButtonSelectionChanged();
    }//GEN-LAST:event_lobbyRadioButtonActionPerformed

    private void expressElevatorRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expressElevatorRadioButtonActionPerformed
        radioButtonSelectionChanged();
    }//GEN-LAST:event_expressElevatorRadioButtonActionPerformed

    private void demolishRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demolishRadioButtonActionPerformed
        radioButtonSelectionChanged();
    }//GEN-LAST:event_demolishRadioButtonActionPerformed

    private final ArrayList<TowerFrameListener> towerFrameListeners;
    private final GamePanel gamePanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton condoRadioButton;
    private javax.swing.JRadioButton demolishRadioButton;
    private javax.swing.JRadioButton expressElevatorRadioButton;
    private javax.swing.JScrollPane gameScrollPane;
    private javax.swing.JRadioButton hallRadioButton;
    private javax.swing.JRadioButton lobbyRadioButton;
    private javax.swing.JRadioButton officeRadioButton;
    private javax.swing.JRadioButton standardElevatorRadioButton;
    // End of variables declaration//GEN-END:variables
}