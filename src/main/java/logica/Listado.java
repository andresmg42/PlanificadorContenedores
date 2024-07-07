/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.sql.Date;
import java.sql.Time;

/**
 * Clase que representa un listado de ejecuciones.
 * Contiene datos como el ID del listado, nombre del listado,
 * fecha y hora del listado.
 * 
 * @author andresuv
 */
public class Listado {
    private int listado_id; // ID del listado
    private String nombre; // Nombre del listado
    private Date fecha; // Fecha del listado
    private Time hora; // Hora del listado
    
    /**
     * Constructor vacío de la clase Listado.
     */
    public Listado() {
    }

    /**
     * Constructor de la clase Listado que inicializa los atributos.
     * 
     * @param nombre Nombre del listado.
     * @param fecha Fecha del listado.
     * @param hora Hora del listado.
     */
    public Listado(String nombre, Date fecha, Time hora) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
    }

    /**
     * Método getter para obtener el ID del listado.
     * 
     * @return ID del listado.
     */
    public int getListado_id() {
        return listado_id;
    }

    /**
     * Método setter para establecer el ID del listado.
     * 
     * @param listado_id ID del listado a establecer.
     */
    public void setListado_id(int listado_id) {
        this.listado_id = listado_id;
    }

    /**
     * Método getter para obtener el nombre del listado.
     * 
     * @return Nombre del listado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método setter para establecer el nombre del listado.
     * 
     * @param nombre Nombre del listado a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método getter para obtener la fecha del listado.
     * 
     * @return Fecha del listado.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Método setter para establecer la fecha del listado.
     * 
     * @param fecha Fecha del listado a establecer.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Método getter para obtener la hora del listado.
     * 
     * @return Hora del listado.
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Método setter para establecer la hora del listado.
     * 
     * @param hora Hora del listado a establecer.
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }  
}
