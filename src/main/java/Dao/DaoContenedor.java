/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import logica.Contenedor1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DaoContenedor que implementa métodos para interactuar con la base de datos en relación con la entidad contenedor.
 * 
 * @author andresuv
 */
public class DaoContenedor {

    // Atributos
    Interfaz interfaz; // Objeto para interactuar con la interfaz de la base de datos
    Connection conexion; // Conexión a la base de datos
    
    // Constructor
    public DaoContenedor() {
        interfaz = new Interfaz(); // Inicialización del objeto de interfaz
    }
    
    /**
     * Método que contiene la consulta SQL para insertar un elemento en la relación contenedor.
     * 
     * @param c Objeto de la clase Contenedor1 que contiene los datos a insertar.
     * @return Un entero que indica el número de filas afectadas por la operación de inserción.
     */
    public int DaoInsertarContenedor(Contenedor1 c) {
        String insertar_sql = "INSERT INTO contenedor (nombre_imagen, t_llegada, t_estimado_ingresado) VALUES (?, ?, ?)";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(insertar_sql); // Preparar la consulta
            
            ptm.setString(1, c.getNombreI()); // Establecer el primer parámetro
            ptm.setDouble(2, c.getTiempoLlegada()); // Establecer el segundo parámetro
            ptm.setDouble(3, c.getTiempoEstimadoIngresado()); // Establecer el tercer parámetro
            
            int result = ptm.executeUpdate(); // Ejecutar la consulta de inserción
            return result; // Retornar el número de filas afectadas
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Manejar excepciones de SQL
        } catch (Exception e) {
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
     * Método que contiene la consulta a la base de datos que, dado un contenedor ya existente, actualiza los datos nulos de ese contenedor.
     * 
     * @param c Objeto de la clase Contenedor1 que contiene los datos a actualizar.
     * @return Un entero que indica el número de filas afectadas por la operación de actualización.
     */
    public int DaoActualizarContenedor(Contenedor1 c) {
        String actualizar_sql = "UPDATE contenedor SET t_inicial = ?, t_real_estimado = ?, t_final = ?, t_turnaround_time = ?, t_respose_time = ? WHERE contenedor_id = ?";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(actualizar_sql); // Preparar la consulta
            
            ptm.setDouble(1, c.getTiempoInicio()); // Establecer el primer parámetro
            ptm.setDouble(2, c.getTiempoEstimadoReal()); // Establecer el segundo parámetro
            ptm.setDouble(3, c.getTiempoFinal()); // Establecer el tercer parámetro
            ptm.setDouble(4, c.getTornaroundTime()); // Establecer el cuarto parámetro
            ptm.setDouble(5, c.getResponseTime()); // Establecer el quinto parámetro
            ptm.setInt(6, c.getContenedor_id()); // Establecer el sexto parámetro
            
            int result = ptm.executeUpdate(); // Ejecutar la consulta de actualización
            return result; // Retornar el número de filas afectadas
            
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Manejar excepciones de SQL
        } catch (Exception e) {
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
    * Actualiza un objeto Contenedor3 en la base de datos.
    *
    * @param c El objeto Contenedor3 que contiene los datos actualizados.
    * @return Un entero que indica el resultado de la operación:
    *         - Si es mayor que 0, indica el número de filas afectadas.
    *         - Si ocurre un error, devuelve -1.
    **/
    /*
    public int DaoActualizarContenedor3(Contenedor3 c) {
        String actualizar_sql = "UPDATE contenedor SET t_inicial=?, t_real_estimado=?,t_final=?,t_turnaround_time=?,t_respose_time=? WHERE contenedor_id=?";

        try {
            conexion = interfaz.openConnection();
            PreparedStatement ptm = conexion.prepareStatement(actualizar_sql);
            ptm.setDouble(1, c.getTiempoInicio());
            ptm.setDouble(2, c.getTiempoEstimadoReal());
            ptm.setDouble(3, c.getTiempoFinal());
            ptm.setDouble(4, c.getTornaroundTime());
            ptm.setDouble(5, c.getResponseTime());
            ptm.setInt(6, c.getContenedor_id());

            int result = ptm.executeUpdate();
            return result;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;

    }*/
    
    /**
     * Método que contiene la consulta SQL en la base de datos que, dado un id de listado, retorna una tabla (ResultSet) con todos los atributos de los contenedores asociados a ese listado.
     * 
     * @param listado_id ID del listado para el cual se desean listar los contenedores.
     * @return Un objeto ResultSet que contiene los resultados de la consulta SQL.
     */
    public ResultSet DaoListarContenedores(int listado_id) {
        String sql_listar = "SELECT cont.contenedor_id, com.nombre_imagen, com.nombre_comando, cont.t_llegada, cont.t_estimado_ingresado "
                + "FROM contenedor cont, c_l, comando com "
                + "WHERE cont.contenedor_id = c_l.contenedor_id "
                + "AND cont.nombre_imagen = com.nombre_imagen "
                + "AND c_l.listado_id = ?";
        
        try {
            conexion = interfaz.openConnection(); // Abrir conexión a la base de datos
            PreparedStatement ptm = conexion.prepareStatement(sql_listar); // Preparar la consulta
            
            ptm.setInt(1, listado_id); // Establecer el parámetro de ID de listado
            
            ResultSet result = ptm.executeQuery(); // Ejecutar la consulta
            
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
    
    /**
     * Método que, dado un id de listado, retorna una lista de elementos de tipo Contenedor1 con los contenedores creados como objetos seteados con los datos obtenidos de los contenedores de la base de datos.
     * 
     * @param listado_id ID del listado para el cual se desean obtener los contenedores.
     * @return Una lista de objetos Contenedor1 que representan los contenedores asociados al listado.
     */
    public List<Contenedor1> DaobtenerConetenedoresListado(int listado_id) {
        List<Contenedor1> listaC = new ArrayList<>(); // Crear una nueva lista para almacenar los contenedores
        
        ResultSet con = DaoListarContenedores(listado_id); // Obtener el ResultSet de contenedores
        
        try {
            while (con.next()) { // Iterar sobre los resultados del ResultSet
                Contenedor1 c = new Contenedor1( // Crear un nuevo objeto Contenedor1 con los datos obtenidos
                    con.getInt(1), // ID de contenedor
                    con.getString(2).trim(), // Nombre de imagen
                    con.getString(3).trim(), // Nombre de comando
                    con.getLong(4), // Tiempo de llegada
                    con.getLong(5) // Tiempo estimado ingresado
                );
                listaC.add(c); // Agregar el contenedor a la lista
            }
            return listaC; // Retornar la lista de contenedores
            
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
    
    /*
    * Obtiene una lista de objetos Contenedor3 a partir de un listado identificado por su ID.
    *
    * @param listado_id El ID del listado de contenedores a obtener.
    * @return Una lista de objetos Contenedor3 obtenida del listado identificado por listado_id,
    *         o null si ocurre un error de SQL.
    */
    /*
    public List<Contenedor3> DaobtenerConetenedoresListado3(int listado_id) {
    List<Contenedor3> listaC=new ArrayList<>();
    ResultSet con=DaoListarContenedores(listado_id);
    
        try {
         while(con.next()){
        Contenedor3 c = new Contenedor3(con.getInt(1),con.getString(2).trim(),con.getString(3).trim(),con.getInt(4),con.getInt(5));
        listaC.add(c);
    }
    return listaC;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return null;
    
    }*/
}
