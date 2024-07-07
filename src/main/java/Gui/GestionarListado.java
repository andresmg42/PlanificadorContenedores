/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package Gui;
import javax.swing.table.DefaultTableModel;
import logica.Contenedor1;
import Controlador.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.Comando;
import Controlador.ControladorEjecucion;
import com.mycompany.proyectoso.ProyectoSO;
import javax.swing.JFrame;
/**
 *
 * @author Jose Daniel
 */
public class GestionarListado extends javax.swing.JFrame {

    private int listado_id;
    private DefaultTableModel model = new DefaultTableModel();
    private ControladorEjecucion controlador;
    
    public GestionarListado(ControladorEjecucion controlador) {
        initComponents();
        this.controlador = controlador;
        String[] Titulos = new String[]{"Comando","Tiempo Inicial","Tiempo Estimado"};
        this.model.setColumnIdentifiers(Titulos);
        jtComandos.setModel(this.model);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void setListadoNombre(String nombre){
        jlListado.setText(nombre);
    }
    
    //OBJETOS DE OTRAS CLASES
    
    ControladorCL controladorCL = new ControladorCL();
    ControladorContenedor controladorContenedor = new ControladorContenedor();
    ControladorComando controladorComando = new ControladorComando();
    
//SETTER
    public void setListadoId(int id){
        this.listado_id = id;
    }
    
    //METODOS DE LA CLASE
    public void insertarFila(String comando,String tiempoInicial, String tiempoEstimado){
        Object[] nuevaFila = new Object[]{comando,tiempoInicial,tiempoEstimado}; 
        boolean existe = false;

        for (int i = 0; i < this.model.getRowCount(); i++){
            Object[] fila = new Object[this.model.getColumnCount()];
            for(int j = 0; j < this.model.getColumnCount(); j++){
                fila[j] = this.model.getValueAt(i, j);
            }
            
            if(java.util.Arrays.equals(fila, nuevaFila)){
                existe = true;
                break;
            }
        }
        
        if(existe){
            JOptionPane.showMessageDialog(this, "Ya existe un contenedor igual");
        }else{
            this.model.addRow(nuevaFila);
        }
        
        jtComandos.updateUI();
    }
    
    void llenarTablaC(ResultSet listado) {
        jtComandos.clearSelection();
        model.getDataVector().removeAllElements();
        try {
            while (listado.next()) {
                model.addRow(new Object[]{
                    listado.getString(3),
                    String.valueOf(listado.getDouble(4)),
                    String.valueOf(listado.getDouble(5))
                    });
            }
            jtComandos.updateUI();
            
        } catch (SQLException ex) {
            System.out.println("EStoy aqui"+ex.getMessage());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jbAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtfComando = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfTiempoInicial = new javax.swing.JTextField();
        jtfTiempoEstimado = new javax.swing.JTextField();
        jbInsertar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlListado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComandos = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbAtras.setText("Atras");
        jbAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtrasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel1.setText("Comando");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel2.setText("Tiempos Inicial:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel3.setText("Tiempo Estimado:");

        jbInsertar.setText("Insertar");
        jbInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInsertarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel4.setText("Listado:");

        jtComandos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Comando", "Tiempo Inicial", "Tiempo Estimado"
            }
        ));
        jScrollPane1.setViewportView(jtComandos);

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel8.setText("Lista de comandos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(jtfTiempoEstimado)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(31, 31, 31))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlListado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jtfComando)
                                    .addComponent(jtfTiempoInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jlListado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfComando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtfTiempoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtfTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbInsertar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtrasActionPerformed
        ProyectoSO.Glistados.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbAtrasActionPerformed

    private void Closing(java.awt.event.WindowEvent evt) {                         
        controlador.detenerContenedor();
        System.exit(0);
    } 
    
    private void jbInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsertarActionPerformed
        String nombreImagen = jtfComando.getText().replaceAll("\\s+","").trim();
        String comando = jtfComando.getText();
        //int contenedorId = Integer.parseInt(jtfId.getText());
        
        
        Contenedor1 c = new Contenedor1(nombreImagen,comando,Long.parseLong(jtfTiempoInicial.getText()),Long.parseLong(jtfTiempoEstimado.getText()));
        Comando com = new Comando(nombreImagen,comando);
        
        try {
            controladorComando.insertarComando(com);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        
        controladorContenedor.insertarContenedor(nombreImagen,comando,Long.parseLong(jtfTiempoInicial.getText()),Long.parseLong(jtfTiempoEstimado.getText()));
        
        controladorCL.insertarCL(this.listado_id);
        
        double tiempoLlegada = c.getTiempoLlegada();
        double tiempoEstimado = c.getTiempoEstimadoIngresado();
        insertarFila(jtfComando.getText(),String.valueOf(tiempoLlegada), String.valueOf(tiempoEstimado));
    }//GEN-LAST:event_jbInsertarActionPerformed

    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAtras;
    private javax.swing.JButton jbInsertar;
    private javax.swing.JLabel jlListado;
    private javax.swing.JTable jtComandos;
    private javax.swing.JTextField jtfComando;
    private javax.swing.JTextField jtfTiempoEstimado;
    private javax.swing.JTextField jtfTiempoInicial;
    // End of variables declaration//GEN-END:variables
}
