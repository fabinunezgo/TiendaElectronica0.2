/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.SQLException;

/**
 *
 * @author fabia
 */
public class PruebaConexion {
    public static void main(String[] args) throws SQLException {
        Conexion conexion = Conexion.getInstancia();
        if (conexion.getConexion() != null) {
            System.out.println("Conexión exitosa a la base de datos");
        } else {
            System.out.println("Error al conectar a la base de datos");
        }
    }
}
