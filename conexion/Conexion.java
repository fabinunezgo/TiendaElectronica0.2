/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fabia
 */
public class Conexion {
    private static Conexion instancia; 
    private Connection conexion;
    private final String URL = "jdbc:mysql://localhost:3306/TiendaElectronica"; 
    private final String USUARIO = "root"; 
    private final String CONTRASENA = ""; 


    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}

