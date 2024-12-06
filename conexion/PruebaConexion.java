/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fabia
 */
public class PruebaConexion {
    public static void main(String[] args) {

<<<<<<< HEAD

=======
>>>>>>> e22e99f (se realizan algunos cambios)
        String url ="jdbc:mysql://localhost:3306/tiendaelectronica";


        String usuario = "root";
        String contrasena = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("¡Conexión exitosa a la base de datos!");
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}
