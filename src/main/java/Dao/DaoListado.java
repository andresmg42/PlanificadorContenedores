/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import logica.Listado;

/**
 * Clase DaoListado que implementa métodos para interactuar con la base de datos en relación con la entidad listado.
 * 
 * @author andresuv
 */
public class DaoListado {
    Interfaz interfaz; // Objeto para interactuar con la interfaz de la base de datos
    Connection conexion; // Conexión a la base de datos
    
    // Constructor
    public DaoListado(){
        interfaz = new Interfaz(); // Inicialización del objeto de interfaz
    }
    
    /**
     * Método que contiene la consulta SQL para insertar un elemento en la tabla listado.
     * 
     * @param l Objeto de la clase Listado que contiene los datos a insertar.
     * @return Un entero que indica el número de filas afectadas por la operación de inserción.
     */
    public int DaoInsertarListado(Listado l){
        String sql_insertar = "INSERT INTO listado (nombre, fecha, hora) VALUES (?, ?, ?)";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(sql_insertar); // Preparar la consulta
            
            ptm.setString(1, l.getNombre()); // Establecer el primer parámetro
            ptm.setDate(2, l.getFecha()); // Establecer el segundo parámetro
            ptm.setTime(3, l.getHora()); // Establecer el tercer parámetro
            
            int result = ptm.executeUpdate(); // Ejecutar la consulta de inserción
            return result; // Retornar el número de filas afectadas
            
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
    
    /**
     * Método que contiene la consulta SQL para eliminar un listado por su ID.
     * 
     * @param listado_id ID del listado a eliminar.
     * @return Un entero que indica el número de filas afectadas por la operación de eliminación.
     */
    public int DaoEliminarListado(int listado_id){
        String sql_eliminar = "DELETE FROM listado WHERE listado_id=?";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(sql_eliminar); // Preparar la consulta
            
            ptm.setInt(1, listado_id); // Establecer el parámetro del ID del listado
            
            int result = ptm.executeUpdate(); // Ejecutar la consulta de eliminación
            return result; // Retornar el número de filas afectadas
            
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
    
    /**
     * Método que contiene la consulta SQL en la base de datos que lista todos los registros de la tabla listado.
     * 
     * @return Un objeto ResultSet que contiene los resultados de la consulta SQL.
     */
    public ResultSet DaolistarListado() {
        String sql_listar = "SELECT * FROM listado";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            Statement sentencia = conexion.createStatement(); // Crear una sentencia SQL
            ResultSet result = sentencia.executeQuery(sql_listar); // Ejecutar la consulta
            
            return result; // Retornar el resultado de la consulta
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Manejar excepciones de SQL
        } finally {
            try {
                conexion.close(); // Cerrar la conexión a la base de datos en el bloque finally
            } catch (SQLException e) {
                System.out.println(e.getMessage()); // Manejar excepciones al cerrar la conexión
            }
        }
        return null; // Retornar null si hay algún error
    }
}
