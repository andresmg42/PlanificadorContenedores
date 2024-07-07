/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controlador;

import Dao.DaoCL;

/**
 * Clase ControladorCL que actúa como controlador para gestionar la lógica relacionada con los elementos c_l.
 * Este controlador se comunica con la capa de datos a través de la clase DaoCL.
 * 
 * @autor Jose Daniel
 */
public class ControladorCL {

    // Constructor de la clase ControladorCL.
    public ControladorCL() {}

    // OBJETOS DE OTRAS CLASES
    DaoCL daoCL = new DaoCL();

    // METODOS

    /**
     * Método que inserta un elemento c_l en la base de datos.
     * 
     * @param listado_id El ID del listado en el que se insertará el elemento c_l.
     * @return Un entero que indica el resultado de la operación de inserción.
     */
    public int insertarCL(int listado_id) {
        return daoCL.DaoinsertarCL(listado_id);
    }
    
    /*
    public static void main(String[] args) {
        ControladorCL cl=new ControladorCL();
        
        
        cl.insertarCL(1,1);
        
        cl.insertarCL(2,1);
        
        cl.insertarCL(3,1 );
        
        cl.insertarCL(4,1 );
    }*/
}
