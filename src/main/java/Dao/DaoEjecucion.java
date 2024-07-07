/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logica.Ejecucion;

/**
 * Clase DaoEjecucion que implementa métodos para interactuar con la base de datos en relación con la entidad ejecucion.
 * 
 * @author andresuv
 */
public class DaoEjecucion {
    
    Interfaz interfaz; // Objeto para interactuar con la interfaz de la base de datos
    Connection conexion; // Conexión a la base de datos
    
    // Constructor
    public DaoEjecucion(){
        interfaz = new Interfaz(); // Inicialización del objeto de interfaz
    }
    
    /**
     * Método que contiene la consulta SQL para insertar un elemento en la tabla ejecucion.
     * 
     * @param e Objeto de la clase Ejecucion que contiene los datos a insertar.
     * @return Un entero que indica el número de filas afectadas por la operación de inserción.
     */
    public int DaoInsertarEjecucion(Ejecucion e){
    String sql_insertar="INSERT INTO ejecucion(listado_id,algoritmo,t_tornaround_timeP,t_response_timeP,fecha,hora) VALUES (?,?,?,?,?,?)";
        try {
            conexion=interfaz.openConnection();
            PreparedStatement ptm=conexion.prepareStatement(sql_insertar);
            ptm.setInt(1,e.getListado_id());
            ptm.setString(2,e.getAlgoritmo());
            ptm.setDouble(3,e.getTornaroundTimeP());
            ptm.setDouble(4,e.getResponseTimeP());
            ptm.setDate(5,e.getFecha());
            ptm.setTime(6,e.getTime());
            int result=ptm.executeUpdate();
            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return -1;
    }
    
    /**
     * Método que contiene la consulta SQL en la base de datos que lista todos los registros de la tabla ejecucion.
     * 
     * @return Un objeto ResultSet que contiene los resultados de la consulta SQL.
     */
    public ResultSet DaolistarEjecucion() {
        String sql_listar = "SELECT * FROM ejecucion";
        
        try {
            conexion = interfaz.openConnection(); // Obtener conexión a la base de datos
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
    
    public ResultSet consultarResultados(int listado_id) {
        String String_consultar = "SELECT DISTINCT cont.contenedor_id,\n"
                + "cont.nombre_imagen,cont.t_llegada,cont.t_estimado_ingresado,cont.t_inicial,\n"
                + "cont.t_final,cont.t_turnaround_time,cont.t_respose_time\n"
                + "FROM ejecucion e,c_l cl,contenedor cont\n"
                + "WHERE e.listado_id=cl.listado_id\n"
                + "AND cl.contenedor_id=cont.contenedor_id\n"
                + "AND e.listado_id=?;";
        try {
            conexion = interfaz.openConnection();
            PreparedStatement ptm=conexion.prepareStatement(String_consultar);
            ptm.setInt(1,listado_id);
            ResultSet result = ptm.executeQuery();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
