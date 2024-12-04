/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;



import Conexion.Conexion;
import Modelo.Cliente.Cliente;
import Modelo.Cliente.ClienteDAO;
import Modelo.Cliente.ClienteDTO;
import Modelo.Cliente.ClienteMapper;
import View.View;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author thyfa
 */
public class ControllerCliente {

    private ClienteDAO dao;
    private final View view;
    private final ClienteMapper mapper;

    public ControllerCliente(View view) {
        this.view = view;
        this.mapper = new ClienteMapper();
        try {
            this.dao = new ClienteDAO(Conexion.getConnection());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(); // Imprime el seguimiento completo del error
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public boolean agregar(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return false;  
        }
        try {
            if (validatePK(cliente.getCedula())) {
                view.showError("La cédula ingresada ya se encuentra registrada");
                return false;  
            }
            dao.agregar(mapper.toDTO(cliente));
            view.showMessage("Cliente registrado correctamente");
            return true;  
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al guardar los datos: " + ex.getMessage());
            ex.printStackTrace(); // Imprime el seguimiento completo del error
            return false;  
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
            ex.printStackTrace(); // Imprime el seguimiento completo del error
        }
    }

    public void update(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(cliente.getCedula())) {
                view.showError("La cédula ingresada no se encuentra registrada");
                return;
            }
            dao.actualizar(mapper.toDTO(cliente));
            view.showMessage("Datos actualizados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
            ex.printStackTrace(); // Imprime el seguimiento completo del error
        }
    }

    public void delete(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("No hay ningún cliente cargado actualmente");
            return;
        }
        try {
            if (!validatePK(cliente.getCedula())) {
                view.showError("La cédula ingresada no se encuentra registrada");
                return;
            }
            dao.eliminar(cliente.getCedula());
            view.showMessage("Cliente eliminado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar los datos: " + ex.getMessage());
            ex.printStackTrace(); // Imprime el seguimiento completo del error
        }
    }

    public boolean validateRequired(Cliente cliente) {
        return cliente != null
                && !cliente.getCedula().trim().isEmpty()
                && !cliente.getNombreCompleto().trim().isEmpty()
                && !cliente.getDireccion().trim().isEmpty()
                && !cliente.getTelefono().trim().isEmpty()
                && !cliente.getCorreo().trim().isEmpty();
    }

    public boolean validatePK(String cedula) {
        try {
            return dao.read(cedula) != null;
        } catch (SQLException ex) {
            view.showError("Error al validar la cédula: " + ex.getMessage());
            ex.printStackTrace(); // Imprime el seguimiento completo del error
            return false;
        }
    }
}