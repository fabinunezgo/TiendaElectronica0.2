/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Conexion.Conexion;
import Modelo.Proveedor.Proveedor;
import Modelo.Proveedor.ProveedorDAO;
import Modelo.Proveedor.ProveedorDTO;
import Modelo.Proveedor.ProveedorMapper;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author user
 */
public class ControllerProveedor {

    private ProveedorDAO dao;
    private final View view;
    private final ProveedorMapper mapper;

    public ControllerProveedor(View view) {
        this.view = view;
        this.mapper = new ProveedorMapper();
        try {
            this.dao = new ProveedorDAO(Conexion.getConnection());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public boolean agregar(Proveedor proveedor) {
        if (proveedor == null || !validateRequired(proveedor)) {
            view.showError("Faltan datos requeridos");
            return false;
        }
        try {
            if (existeProveedor(proveedor.getId())) {
                view.showError("El ID del proveedor ya está registrado");
                return false;
            }

            dao.agregar(mapper.toDTO(proveedor));
            view.showMessage("Proveedor registrado correctamente");
            return true;

        } catch (SQLException ex) {
            view.showError("Ocurrió un error al agregar el proveedor: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public void readAll() {
        try {
            List<ProveedorDTO> dtoList = dao.readAll();
        } catch (SQLException ex) {
            view.showError("Error al cargar los proveedores: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void update(Proveedor proveedor) {
        if (proveedor == null || !validateRequired(proveedor)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(proveedor.getId())) {
                view.showError("El ID del proveedor no está registrado");
                return;
            }

            dao.actualizar(mapper.toDTO(proveedor));
            view.showMessage("Proveedor actualizado correctamente");

        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar el proveedor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void delete(Proveedor proveedor) {
        if (proveedor == null || proveedor.getId() <= 0) {
            view.showError("No se ha seleccionado ningún proveedor para eliminar");
            return;
        }
        try {
            if (!validatePK(proveedor.getId())) {
                view.showError("El ID del proveedor no está registrado");
                return;
            }

            dao.eliminar(proveedor.getId());
            view.showMessage("Proveedor eliminado correctamente");

        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar el proveedor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean validateRequired(Proveedor proveedor) {
        return proveedor != null
                && proveedor.getNombre() != null && !proveedor.getNombre().trim().isEmpty()
                && proveedor.getContacto() != null && !proveedor.getContacto().trim().isEmpty()
                && proveedor.getDireccion() != null && !proveedor.getDireccion().trim().isEmpty();
    }

    public boolean validatePK(int id) {
        try {
            return dao.read(id) != null;
        } catch (SQLException ex) {
            view.showError("Error al validar el ID del proveedor: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean existeProveedor(int id) {
        try {
            ProveedorDTO proveedorDTO = dao.read(id);
            return proveedorDTO != null;
        } catch (SQLException ex) {
            view.showError("Error al verificar la existencia del proveedor: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
