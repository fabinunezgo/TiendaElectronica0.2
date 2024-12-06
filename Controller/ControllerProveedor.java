/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Conexion.Conexion;
import Modelo.Proveedor.ProveedorDAO;
import Modelo.Proveedor.ProveedorDTO;
import View.View;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public class ControllerProveedor {
    
    private ProveedorDAO dao;
    private final View view;

    public ControllerProveedor(View view) {
        this.view = view;
        try {
            this.dao = new ProveedorDAO(Conexion.getConnection());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public boolean agregar(ProveedorDTO proveedor) {
        if (proveedor == null || !validateRequired(proveedor)) {
            view.showError("Faltan datos requeridos");
            return false;
        }
        try {
            if (dao.agregar(proveedor)) {
                view.showMessage("Proveedor registrado correctamente");
                return true;
            } else {
                view.showError("No se pudo registrar el proveedor");
                return false;
            }
          } catch (SQLException ex) {
            view.showError("Ocurrió un error al agregar el proveedor: " + ex.getMessage());
            return false;
        }
    }

    public void readAll() {
        try {
            List<ProveedorDTO> proveedores = dao.readAll();
            view.showAll(proveedores);
        } catch (SQLException ex) {
            view.showError("Error al cargar los proveedores: " + ex.getMessage());
        }
    }

    public void update(ProveedorDTO proveedor) {
        if (proveedor == null || !validateRequired(proveedor)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (dao.actualizar(proveedor)) {
                view.showMessage("Proveedor actualizado correctamente");
            } else {
                view.showError("No se pudo actualizar el proveedor");
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar el proveedor: " + ex.getMessage());
        }
    }

    public void delete(ProveedorDTO proveedor) {
        if (proveedor == null || proveedor.getId() <= 0) {
            view.showError("No se ha seleccionado ningún proveedor para eliminar");
            return;
        }
        try {
            if (dao.eliminar(proveedor.getId())) {
                view.showMessage("Proveedor eliminado correctamente");
            } else {
                view.showError("No se pudo eliminar el proveedor");
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar el proveedor: " + ex.getMessage());
        }
    }

    private boolean validateRequired(ProveedorDTO proveedor) {
        return proveedor != null && proveedor.getNombre() != null && !proveedor.getNombre().isEmpty()
                && proveedor.getContacto() != null && !proveedor.getContacto().isEmpty()
                && proveedor.getDireccion() != null && !proveedor.getDireccion().isEmpty();
    }
    
}
