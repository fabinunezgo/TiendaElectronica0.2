/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataBase.DataBase;

import Model.Customer.CustomerMapper;
import Model.Mapper.Mapper;
import Modelo.Cliente.Cliente;
import Modelo.Cliente.ClienteDAO;
import Modelo.Cliente.ClienteDTO;
import Modelo.Cliente.ClienteMapper;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.text.View;

/**
 *
 * @author jprod
 */
public class ControllerProducto {

    private ClienteDAO dao;
    private final View view;
    private final ClienteMapper mapper;

    public ControllerProducto(View view) {
        this.view = view;
        mapper = new ClienteMapper();
        try {
            dao = new ClienteDAO(DataBase.getConnection());
        } catch (SQLException ex) {
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public void agregar (Cliente cliente {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.agregar(mapper.toDTO(cliente));
            view.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al guardar los datos: " + ex.getMessage());
        }
    }

    public void read() {

    }

    public void readAll() {
        try {
            List<ClienteDTO> dtoList = dao.readAll();
            List<Cliente> customerList;
            customerList = dtoList.stream()
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
            if (validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada no se encuentra registrada");
                return;
            }
            dao.actualizar(mapper.toDTO(cliente));
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
            if (validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada no ya se encuentra registrada");
                return;
            }
            dao.eliminar(cliente.getCedula());
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
            return dao.validatePK(id);
        } catch (SQLException ex) {
            return false;
        }
    }

}
