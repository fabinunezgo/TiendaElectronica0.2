/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Conexion.Conexion;
import Modelo.Usuario.UsuarioDAO;
import Modelo.Usuario.UsuarioDTO;
import View.View;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public class ControllerUsuario {
    private UsuarioDAO dao;
    private final View view;

    public ControllerUsuario(View view) {
        this.view = view;
        try {
            this.dao = new UsuarioDAO(Conexion.getConnection());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public boolean agregar(UsuarioDTO usuario) {
        if (usuario == null || !validateRequired(usuario)) {
            view.showError("Faltan datos requeridos");
            return false;
        }
        try (Connection connection = Conexion.getConnection()) {
            String query = "{CALL añadirUsuario(?, ?, ?, ?)}";
            try (CallableStatement stmt = connection.prepareCall(query)) {
                stmt.setString(1, usuario.getNombre());
                stmt.setString(2, usuario.getUsername());
                stmt.setString(3, usuario.getPassword());
                stmt.setString(4, usuario.getRol());
                stmt.executeUpdate();
                view.showMessage("Usuario registrado correctamente");
                return true;
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al agregar el usuario: " + ex.getMessage());
            return false;
        }
    }

    public void readAll() {
        try {
            List<UsuarioDTO> usuarios = dao.readAll();
            view.showAll(usuarios);
        } catch (SQLException ex) {
            view.showError("Error al cargar los usuarios: " + ex.getMessage());
        }
    }

    public void update(UsuarioDTO usuario) {
        if (usuario == null || !validateRequired(usuario)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try (Connection connection = Conexion.getConnection()) {
            String query = "{CALL actualizarUsuario(?, ?, ?, ?, ?)}";
            try (CallableStatement stmt = connection.prepareCall(query)) {
                stmt.setInt(1, usuario.getId());
                stmt.setString(2, usuario.getNombre());
                stmt.setString(3, usuario.getUsername());
                stmt.setString(4, usuario.getPassword());
                stmt.setString(5, usuario.getRol());
                stmt.executeUpdate();
                view.showMessage("Usuario actualizado correctamente");
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar el usuario: " + ex.getMessage());
        }
    }

    public void delete(UsuarioDTO usuario) {
        if (usuario == null || usuario.getId() <= 0) {
            view.showError("No se ha seleccionado ningún usuario para eliminar");
            return;
        }
        try (Connection connection = Conexion.getConnection()) {
            String query = "{CALL eliminarUsuario(?)}";
            try (CallableStatement stmt = connection.prepareCall(query)) {
                stmt.setInt(1, usuario.getId());
                stmt.executeUpdate();
                view.showMessage("Usuario eliminado correctamente");
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar el usuario: " + ex.getMessage());
        }
    }

    private boolean validateRequired(UsuarioDTO usuario) {
        return usuario != null &&
               usuario.getNombre() != null && !usuario.getNombre().isEmpty() &&
               usuario.getUsername() != null && !usuario.getUsername().isEmpty() &&
               usuario.getPassword() != null && !usuario.getPassword().isEmpty() &&
               usuario.getRol() != null && !usuario.getRol().isEmpty();
    }
}
