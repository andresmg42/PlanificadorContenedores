/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Dao.DaoListado;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import logica.Listado;

/**
 * Clase ControladorListado que actúa como controlador para gestionar la lógica relacionada con los listados.
 * Este controlador se comunica con la capa de datos a través de la clase DaoListado.
 * 
 * @autor Jose Daniel
 */
public class ControladorListado {

    // Constructor de la clase ControladorListado.
    public ControladorListado() {
    }

    // OBJETOS DE OTRAS CLASES
    DaoListado daoListado = new DaoListado();

    /**
     * Método que lista todos los listados.
     * 
     * @return Un ResultSet que contiene todos los listados.
     */
    public ResultSet listarListados() {
        return daoListado.DaolistarListado();
    }

    /**
     * Método que crea un listado y llama a la función DaoInsertarListado con el listado que acaba de crear.
     * 
     * @param nombre Nombre del listado.
     * @param fecha Fecha de creación del listado.
     * @param hora Hora de creación del listado.
     * @return Un entero que indica el resultado de la operación de inserción.
     */
    public int insertarListado(String nombre, Date fecha, Time hora) {
        Listado l = new Listado(nombre, fecha, hora);
        return daoListado.DaoInsertarListado(l);
    }
}
