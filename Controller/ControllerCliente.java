/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataBase.DataBase;
import Modelo.Cliente.Cliente;
import Modelo.Cliente.ClienteDAO;
import Modelo.Cliente.ClienteDTO;
import Modelo.Cliente.ClienteMapper;
import View.View;
import com.sun.jdi.connect.spi.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author jprod
 */
public class ControllerCliente {

        private ClienteDAO dao;
    private final View view;
    private final ClienteMapper mapper;


    public ControllerCliente(View view) {
        this.view = view;
        mapper = new ClienteMapper();
        try {
            dao = new ClienteDAO(Connection.
        } catch (SQLException ex) {
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public void agregar(Cliente cliente) {  
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.agregar(mapper.toDTO(cliente));
            view.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al guardar los datos: " + ex.getMessage());
        }
    }

    public void readAll() {
        try {
            List<ClienteDTO> dtoList = dao.readAll();
            List<Cliente> customerList = dtoList.stream()
                    .map(mapper::toEnt)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            view.showAll(customerList);
        } catch (SQLException ex) {
            view.showError("Error al cargar los datos: " + ex.getMessage());
        }
    }

    public void update(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada no se encuentra registrada");
                return;
            }
            dao.actualizar(mapper.toDTO(cliente));
            view.showMessage("Datos actualizados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al actualizar los datos: " + ex.getMessage());
        }
    }

    public void delete(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("No hay ningun cliente cargado actualmente");
            return;
        }
        try {
            if (!validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada no ya se encuentra registrada");
                return;
            }
            dao.eliminar(cliente.getCedula());
            view.showMessage("Cliente eliminado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al eliminar los datos: " + ex.getMessage());
        }
    }

    public boolean validateRequired(Cliente cliente) {
        return !cliente.getCedula().trim().isEmpty()
                && !cliente.getNombreCompleto().trim().isEmpty()
                && !cliente.getDireccion().trim().isEmpty()
                && !cliente.getTelefono().trim().isEmpty()
                && !cliente.getCorreo().trim().isEmpty();
    }

    public boolean validatePK(String id) {
        try {
            return dao.read(id) != null;  
        } catch (SQLException ex) {
            return false;
        }
    }

}
