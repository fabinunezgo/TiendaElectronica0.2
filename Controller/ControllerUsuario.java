/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Conexion.Conexion;
import Modelo.Usuario.UsuarioDAO;
import Modelo.Usuario.UsuarioDTO;
import View.View;
import java.util.List;
import java.sql.SQLException;

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
        try {
            if (dao.agregar(usuario)) {
                view.showMessage("Usuario registrado correctamente");
                return true;
            } else {
                view.showError("No se pudo registrar el usuario");
                return false;
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
        try {
            if (dao.actualizar(usuario)) {
                view.showMessage("Usuario actualizado correctamente");
            } else {
                view.showError("No se pudo actualizar el usuario");
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
        try {
            if (dao.eliminar(usuario.getId())) {
                view.showMessage("Usuario eliminado correctamente");
            } else {
                view.showError("No se pudo eliminar el usuario");
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
