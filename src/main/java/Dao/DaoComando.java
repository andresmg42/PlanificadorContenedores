/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Comando;

/**
 * Clase DaoComando que implementa métodos para interactuar con la base de datos en relación con la entidad comando.
 * 
 * @author andresuv
 */
public class DaoComando {
    
    // Atributos
    Interfaz interfaz; // Objeto para interactuar con la interfaz de la base de datos
    Connection conexion; // Conexión a la base de datos
    
    // Constructor
    public DaoComando(){
        interfaz = new Interfaz(); // Inicialización del objeto de interfaz
    }
    
    /**
     * Función que contiene la consulta para insertar un elemento en la tabla comando.
     * 
     * @param c Objeto de la clase Comando que contiene los datos a insertar.
     * @return Un entero que indica el número de filas afectadas por la operación de inserción.
     */
    public int DaoInsertarComando(Comando c){
        String insertar_sql = "INSERT INTO comando VALUES (?, ?)";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(insertar_sql); // Preparar la consulta
            
            ptm.setString(1, c.getNombre_imagen()); // Establecer el primer parámetro
            ptm.setString(2, c.getNombre_comando()); // Establecer el segundo parámetro
            
            int result = ptm.executeUpdate(); // Ejecutar la consulta de inserción
            return result; // Retornar el número de filas afectadas
         
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Manejar excepciones de SQL
        } catch(Exception e){
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
     * Función que busca un comando por el nombre de imagen en la base de datos.
     * 
     * @param nombreI Nombre de la imagen a buscar.
     * @return Un objeto de la clase Comando si se encuentra la imagen, o null si no se encuentra.
     */
    public Comando DaoBuscarComando(String nombreI){
        Comando com = new Comando(); // Crear objeto Comando
        
        String sql_buscar = "SELECT * FROM comando WHERE nombre_imagen=?";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(sql_buscar); // Preparar la consulta
            
            ptm.setString(1, nombreI); // Establecer el parámetro de búsqueda
            
            ResultSet result = ptm.executeQuery(); // Ejecutar la consulta
            
            while(result.next()){ // Recorrer el resultado
                com.setNombre_imagen(result.getString(1)); // Establecer nombre de imagen
                com.setNombre_comando(result.getString(2)); // Establecer nombre de comando
            }
            
            return com; // Retornar el objeto Comando encontrado
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Manejar excepciones de SQL
        } finally {
            try {
                conexion.close(); // Cerrar la conexión a la base de datos en el bloque finally
            } catch (SQLException e) {
                System.out.println(e.getMessage()); // Manejar excepciones al cerrar la conexión
            }
        }
        
        return null; // Retornar null si no se encuentra la imagen
    }
    
    /**
     * Función que verifica si una imagen existe en la base de datos.
     * 
     * @param nombre_imagen Nombre de la imagen a verificar.
     * @return true si la imagen existe, false si no existe.
     * @throws SQLException Si ocurre un error de SQL durante la ejecución.
     */
    public boolean DaoExisteImagen(String nombre_imagen) throws SQLException {
        String sql_buscar = "SELECT nombre_imagen FROM comando WHERE nombre_imagen = ?";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(sql_buscar); // Preparar la consulta
            
            ptm.setString(1, nombre_imagen); // Establecer el parámetro de búsqueda
            
            ResultSet resultado = ptm.executeQuery(); // Ejecutar la consulta
            
            return resultado.isBeforeFirst(); // Verificar si hay resultados antes del primer registro
        
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Manejar excepciones de SQL
        } finally {
            try {
                conexion.close(); // Cerrar la conexión a la base de datos en el bloque finally
            } catch (SQLException e) {
                System.out.println(e.getMessage()); // Manejar excepciones al cerrar la conexión
            }
        }
        
        return false; // Retornar false si ocurre algún error
    }
}
