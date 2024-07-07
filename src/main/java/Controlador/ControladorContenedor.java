/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import logica.Contenedor1;
import Dao.DaoComando;
import Dao.DaoContenedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import logica.Comando;

/**
 * Clase ControladorContenedor que actúa como controlador para gestionar la lógica relacionada con los contenedores.
 * Este controlador se comunica con la capa de datos a través de las clases DaoContenedor y DaoComando.
 * 
 * @autor Jose Daniel
 */
public class ControladorContenedor {
    
    // Instancias de clases Dao
    DaoContenedor daoC = new DaoContenedor();
    DaoComando daoCom = new DaoComando();
    
    // Constructor de la clase ControladorContenedor.
    public ControladorContenedor() {}
    
    // OBJETOS DE OTRAS CLASES
    DaoContenedor daoContenedor = new DaoContenedor();
    
    // METODOS
    
    /**
     * Método que verifica si la imagen de un contenedor ya fue creada, y la inserta en la
     * base de datos si no ha sido creada. Además, inserta el contenedor.
     * 
     * @param nombreI Nombre de la imagen del contenedor.
     * @param comando Comando asociado al contenedor.
     * @param t_llegada Tiempo de llegada del contenedor.
     * @param t_estimado_ingresado Tiempo estimado ingresado para el contenedor.
     * @return Un entero que indica el resultado de la operación de inserción del contenedor.
     */
    public int insertarContenedor(String nombreI, String comando, long t_llegada, long t_estimado_ingresado) {
        Contenedor1 c = new Contenedor1(nombreI, comando, t_llegada, t_estimado_ingresado);
        
        if (daoCom.DaoBuscarComando(c.getNombreI()).getNombre_imagen() == null) {
            Comando com = new Comando(c.getNombreI(), c.getComando());
            daoCom.DaoInsertarComando(com);
        }
        return daoContenedor.DaoInsertarContenedor(c);
    }
    
    /**
     * Método que, dada la ID de un listado, retorna todos los contenedores asociados a ese listado.
     * 
     * @param listadoId ID del listado cuyos contenedores se desean obtener.
     * @return Un ResultSet que contiene todos los contenedores asociados al listado.
     */
    public ResultSet listarContenedores(int listadoId) {
        return daoContenedor.DaoListarContenedores(listadoId);
    }
}
