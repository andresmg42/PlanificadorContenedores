/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase DaoCL que implementa métodos para interactuar con la base de datos en relación con la entidad c_l.
 * 
 * @author Jose Daniel
 */
public class DaoCL {
    
    // Atributos
    Interfaz interfaz; // Objeto para interactuar con la interfaz de la base de datos
    Connection conexion; // Conexión a la base de datos
    
    // Constructor
    public DaoCL(){
        interfaz = new Interfaz(); // Inicialización del objeto de interfaz
    }
    
    /**
     * Función que contiene la consulta para insertar un elemento en la relación c_l.
     * 
     * @param listado_id ID del listado a insertar en la relación c_l.
     * @return Un entero que indica el número de filas afectadas por la operación de inserción.
     */
    public int DaoinsertarCL(int listado_id){
        String insertar = "INSERT INTO c_l (listado_id) VALUES(?)"; // Consulta SQL para inserción
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(insertar); // Preparar la consulta
            
            ptm.setInt(1, listado_id); // Establecer el valor del parámetro
            
            return ptm.executeUpdate(); // Ejecutar la consulta de inserción y retornar el número de filas afectadas
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Manejar excepciones de SQL
        } catch (Exception e){
            System.out.println(e.getMessage()); // Manejar otras excepciones
        } finally {
            try {
                conexion.close(); // Cerrar la conexión a la base de datos en el bloque finally
            } catch (SQLException e) {
                System.out.println(e.getMessage()); // Manejar excepciones al cerrar la conexión
            }
        }
        return -1; // Retornar -1 en caso de error
    }
}
