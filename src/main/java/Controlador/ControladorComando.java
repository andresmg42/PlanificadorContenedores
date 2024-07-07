/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoComando;
import java.sql.SQLException;
import logica.Comando;

/**
 * Clase ControladorComando que actúa como controlador para gestionar la lógica relacionada con los comandos.
 * Este controlador se comunica con la capa de datos a través de la clase DaoComando.
 * 
 * @autor Jose Daniel
 */
public class ControladorComando {
    
    // Constructor de la clase ControladorComando.
    public ControladorComando() {}

    // OBJETOS DE OTRAS CLASES
    DaoComando daoComando = new DaoComando();

    /**
     * Método que verifica si una imagen existe en la base de datos y, si no existe, inserta el comando asociado.
     * 
     * @param c Objeto de la clase Comando que contiene los datos del comando a insertar.
     * @return Un entero que indica el resultado de la operación: 
     *         0 si la imagen ya existe, o el resultado de la inserción del comando si la imagen no existe.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    public int insertarComando(Comando c) throws SQLException {
        if (daoComando.DaoExisteImagen(c.getNombre_imagen())) {
            return 0;
        } else {
            return daoComando.DaoInsertarComando(c); 
        }          
    } 
}
