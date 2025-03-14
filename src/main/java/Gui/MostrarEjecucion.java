/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Gui;

import Controlador.ControladorEjecucion;
import javax.swing.JFrame;

/**
 *
 * @author Jose Daniel
 */
public class MostrarEjecucion extends javax.swing.JFrame {
    
    private ControladorEjecucion controlador;

    /**
     * Creates new form MostrarEjecucion
     */
    public MostrarEjecucion(ControladorEjecucion controlador) {
        initComponents();
        this.controlador = controlador;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    //METODOS DE LA CLASE
    public void setResultado(String resultado){
        this.jtfResultados.setText(resultado);
    }
    
    private void Closing(java.awt.event.WindowEvent evt) {                         
        controlador.detenerContenedor();
        System.exit(0);
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbAtras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtfResultados = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbAtras.setText("Atras");
        jbAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtrasActionPerformed(evt);
            }
        });

        jtfResultados.setColumns(20);
        jtfResultados.setRows(5);
        jScrollPane1.setViewportView(jtfResultados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbAtras))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtrasActionPerformed
        GestionarListados.Ejecu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jbAtrasActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAtras;
    private javax.swing.JTextArea jtfResultados;
    // End of variables declaration//GEN-END:variables
}
