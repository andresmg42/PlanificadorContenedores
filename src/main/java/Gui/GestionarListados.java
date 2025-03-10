/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Gui;
import Controlador.ControladorContenedor;
import Controlador.ControladorEjecucion;
import Controlador.ControladorListado;
import Dao.DaoContenedor;
import com.mycompany.proyectoso.ProyectoSO;
import java.time.LocalDate;
import java.time.LocalTime;
import logica.Listado;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Daniel
 */
public class GestionarListados extends javax.swing.JFrame {

    private DefaultTableModel model = new DefaultTableModel();
    private ControladorEjecucion controlador;
    /**
     * Creates new form GestionarListados
     */
    
    public static Ejecuciones Ejecu;
    
    public GestionarListados(ControladorEjecucion controlador) {
        initComponents();

        this.controlador = controlador;
        String[] Titulos = new String[]{"Id_listado","Nombre","Fecha","Hora"};
        this.model.setColumnIdentifiers(Titulos);
        this.jtListados.setModel(this.model);
        llenarTablaC(controladorListado.listarListados());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    void llenarTablaC(ResultSet listado) {
        jtListados.clearSelection();
        model.getDataVector().removeAllElements();
        try {
            while (listado.next()) {
                model.addRow(new Object[]{
                    String.valueOf(listado.getInt(1)),
                    listado.getString(2),
                    convertirFecha(listado.getDate(3)),
                    convertirHora(listado.getTime(4))  
                    });
            }
            jtListados.updateUI();
            
        } catch (SQLException ex) {
            System.out.println("EStoy aqui"+ex.getMessage());
        }
    }
        

//OBJETOS DE OTRAS CLASES
    ControladorListado controladorListado = new ControladorListado();
    ControladorContenedor controladorContenedor = new ControladorContenedor();
    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    
//METODOS DE LA CLASE
    public void insertarFila(String id,String nombreLista, String fecha, String hora){
        this.model.addRow(new Object[]{
            id,
            nombreLista,
            fecha,
            hora
            });
        jtListados.updateUI();
    }

    public void seleccionarFila(){
        
    }
            
    public String convertirFecha(Date fecha){
        return dateFormatter.format(fecha);
    }
    
    public String convertirHora(Time hora){
        return timeFormatter.format(hora);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlNombreListado = new javax.swing.JLabel();
        jtNombreListado = new javax.swing.JTextField();
        jbInsertarListado = new javax.swing.JButton();
        jbEliminar = new javax.swing.JButton();
        jbAgregarComandos = new javax.swing.JButton();
        jbMostar = new javax.swing.JButton();
        jbEjecutar = new javax.swing.JButton();
        jcbAlgoritmos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Closing(evt);
            }
        });

        jlNombreListado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jlNombreListado.setText("Nombre Listado");

        jtNombreListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNombreListadoActionPerformed(evt);
            }
        });
        jtNombreListado.setEditable(true);

        jbInsertarListado.setText("Insertar Listado");
        jbInsertarListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInsertarListadoActionPerformed(evt);
            }
        });

        jbEliminar.setText("Eliminar Listado");

        jbAgregarComandos.setText("Agregar Comandos");
        jbAgregarComandos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAgregarComandosActionPerformed(evt);
            }
        });

        jbMostar.setText("Mostrar Ejecuciones");
        jbMostar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMostarActionPerformed(evt);
            }
        });

        jbEjecutar.setText("Ejecutar");
        jbEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEjecutarActionPerformed(evt);
            }
        });

        jcbAlgoritmos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIFO", "Round Robin", "SPN", "SRT" ,"HRRN"}));
        jcbAlgoritmos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAlgoritmosActionPerformed(evt);
            }
        });

        jtListados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Id_Listado", "Nombre", "Fecha", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtListados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlNombreListado, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNombreListado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbAgregarComandos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbInsertarListado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbEjecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbMostar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jcbAlgoritmos, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNombreListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNombreListado)
                    .addComponent(jbInsertarListado)
                    .addComponent(jbMostar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEliminar)
                    .addComponent(jbEjecutar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAgregarComandos)
                    .addComponent(jcbAlgoritmos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtNombreListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNombreListadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNombreListadoActionPerformed

    private void jcbAlgoritmosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAlgoritmosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbAlgoritmosActionPerformed

    private void jbAgregarComandosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAgregarComandosActionPerformed
        GestionarListado Glistado = new GestionarListado(this.controlador);
        int fila = jtListados.getSelectedRow();
        ResultSet  contenedores;
        if (fila != -1){
            int listado_id = Integer.parseInt((String) jtListados.getValueAt(fila,0));
            String nombreListado = (String) jtListados.getValueAt(fila,1);
            contenedores =  controladorContenedor.listarContenedores(listado_id);
            Glistado.llenarTablaC(contenedores);
            Glistado.setListadoId(listado_id);
            Glistado.setListadoNombre(nombreListado);
            Glistado.setVisible(true);
            ProyectoSO.Glistados.setVisible(true);
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(this, "!Seleccione un listado primero!");
        }
        
    }//GEN-LAST:event_jbAgregarComandosActionPerformed

    private void jbMostarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMostarActionPerformed
        Ejecu = new Ejecuciones(this.controlador);        
        Ejecu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbMostarActionPerformed

    private void jbEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEjecutarActionPerformed
        try {
            Resultados Resultado = new Resultados(this.controlador);
            
            int fila = jtListados.getSelectedRow();
            
            String algoritmo = String.valueOf(jcbAlgoritmos.getSelectedItem());
            
            int listadoId = Integer.parseInt((String) jtListados.getValueAt(fila,0));
            
            String resultado = controlador.EjecutarAlgoritmo(algoritmo, listadoId);
            
            Resultado.setResultado(resultado);
            
            Resultado.setVisible(true);
            this.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestionarListados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbEjecutarActionPerformed
    
    private void jbInsertarListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsertarListadoActionPerformed
        LocalDate Fecha = LocalDate.now();
        
        Date fechaConvertida = Date.valueOf(Fecha);
        
        LocalTime Hora = LocalTime.now();
        
        Time horaConvertida = Time.valueOf(Hora);
        String nombreListado = jtNombreListado.getText();
        
        if(nombreListado.equals("")){
             JOptionPane.showMessageDialog(this,"Escriba un nombre");
             
        }else{           
            controladorListado.insertarListado(nombreListado,fechaConvertida,horaConvertida);
            
            llenarTablaC(controladorListado.listarListados());
        }       
                      
    }//GEN-LAST:event_jbInsertarListadoActionPerformed

    private void Closing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Closing
        controlador.detenerContenedor();
        System.exit(0);
    }//GEN-LAST:event_Closing

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAgregarComandos;
    private javax.swing.JButton jbEjecutar;
    private javax.swing.JButton jbEliminar;
    private javax.swing.JButton jbInsertarListado;
    private javax.swing.JButton jbMostar;
    private javax.swing.JComboBox<String> jcbAlgoritmos;
    private javax.swing.JLabel jlNombreListado;
    private javax.swing.JTable jtListados;
    private javax.swing.JTextField jtNombreListado;
    // End of variables declaration//GEN-END:variables
}
