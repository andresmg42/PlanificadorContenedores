/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 * Clase que representa un comando asociado a una imagen Docker.
 * Contiene el nombre de la imagen y el nombre del comando.
 * 
 * @author andresuv
 */
public class Comando {
    private String nombre_imagen; // Nombre de la imagen Docker
    private String nombre_comando; // Nombre del comando asociado
    
    /**
     * Constructor vacío de la clase Comando.
     */
    public Comando() {
    }

    /**
     * Constructor de la clase Comando que inicializa los atributos.
     * 
     * @param nombre_imagen Nombre de la imagen Docker.
     * @param nombre_comando Nombre del comando asociado a la imagen.
     */
    public Comando(String nombre_imagen, String nombre_comando) {
        this.nombre_imagen = nombre_imagen;
        this.nombre_comando = nombre_comando;
    }

    /**
     * Método getter para obtener el nombre de la imagen Docker.
     * 
     * @return Nombre de la imagen Docker.
     */
    public String getNombre_imagen() {
        return nombre_imagen;
    }

    /**
     * Método setter para establecer el nombre de la imagen Docker.
     * 
     * @param nombre_imagen Nombre de la imagen Docker a establecer.
     */
    public void setNombre_imagen(String nombre_imagen) {
        this.nombre_imagen = nombre_imagen;
    }

    /**
     * Método getter para obtener el nombre del comando asociado.
     * 
     * @return Nombre del comando asociado.
     */
    public String getNombre_comando() {
        return nombre_comando;
    }

    /**
     * Método setter para establecer el nombre del comando asociado.
     * 
     * @param nombre_comando Nombre del comando a establecer.
     */
    public void setNombre_comando(String nombre_comando) {
        this.nombre_comando = nombre_comando;
    }
}
