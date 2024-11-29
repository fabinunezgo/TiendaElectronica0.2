/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuario;

/**
 *
 * @author thyfa
 */
public class UsuarioDTO {
    private final int id;
    private final String nombre;
    private final String username;
    private final String password;
    private final String rol; // Puede ser "Administrador" o "Vendedor"

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public UsuarioDTO(int id, String nombre, String username, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }
}
