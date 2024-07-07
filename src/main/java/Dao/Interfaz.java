/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Jose Daniel
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Interfaz {
    String url, usuario, password;
    Connection conexion = null;   
        
public Interfaz() {
        // Asegúrate de que el puerto 5432 esté mapeado al puerto 5432 en el contenedor
        url = "jdbc:postgresql://localhost:8000/mydatabase"; // Cambia la IP a localhost
        usuario = "myuser";
        password = "mypassword";
    }
    
            public Connection openConnection(){
            try {
            // Se carga el driver
            Class.forName("org.postgresql.Driver");
            //System.out.println( "Driver Cargado" );
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el driver.");
        }

            try{
                     //Crear el objeto de conexion a la base de datos
                     conexion = DriverManager.getConnection(url, usuario, password);
                     System.out.println( "Conexion Exitosa con la Base de datos" );
                     return conexion;
                  //Crear objeto Statement para realizar queries a la base de datos
             } catch( SQLException e ) {
                System.out.println( "No se pudo abrir la bd." );
                System.out.println(e.getMessage());
                return null;
             }

        }//end connectar

        public Connection getConnetion(){
            if (conexion == null) {
                return this.openConnection();
            }
            else{
                  return conexion;      
            }
            
        }
        
        public void closeConection(Connection c){
            try{
                if (conexion != null){
                    c.close();
                }
                 
            } catch( SQLException e ) {
                System.out.println( "No se pudo cerrar." );
            }
        }
        
        
        public static void main(String[] args) {
        Interfaz i=new Interfaz();
        i.openConnection();
    }
               
}

