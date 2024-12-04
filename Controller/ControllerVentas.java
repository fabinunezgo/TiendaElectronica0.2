package Controller;

import Conexion.Conexion;
import Modelo.Cliente.ClienteDAO;
import Modelo.Ventas.VentasDAO;
import Modelo.Ventas.VentasDTO;
import Modelo.Ventas.productovendido;
import View.View;
import java.sql.SQLException;
import java.util.List;

public class ControllerVentas {

    private VentasDAO dao;
    private final View view;

    public ControllerVentas(View view) {
        this.view = view;
        try {
            this.dao = new VentasDAO(Conexion.getConnection());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public boolean agregar(VentasDTO venta) {
        if (venta == null || !validateRequired(venta)) {
            view.showError("Faltan datos requeridos");
            return false;
        }
        try {
            if (!validateFKCliente(venta.getClienteId())) {
                view.showError("El cliente no está registrado");
                return false;
            }
            dao.agregar(venta);
            view.showMessage("Venta registrada correctamente");
            return true;
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al agregar la venta: " + ex.getMessage());
            return false;
        }
    }

    public void readAll() {
        try {
            List<VentasDTO> ventas = dao.readAll();
            view.showAll(ventas);
        } catch (SQLException ex) {
            view.showError("Error al cargar las ventas: " + ex.getMessage());
        }
    }

    public void update(VentasDTO venta) {
        if (venta == null || !validateRequired(venta)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validateFKCliente(venta.getClienteId())) {
                view.showError("El cliente no está registrado");
                return;
            }
            dao.actualizar(venta);
            view.showMessage("Venta actualizada correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar la venta: " + ex.getMessage());
        }
    }

    public void delete(VentasDTO venta) {
        if (venta == null || venta.getId() <= 0) {
            view.showError("No se ha seleccionado ninguna venta para eliminar");
            return;
        }
        try {
            dao.eliminar(venta.getId());
            view.showMessage("Venta eliminada correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar la venta: " + ex.getMessage());
        }
    }

    private boolean validateRequired(VentasDTO venta) {
        return venta != null && venta.getClienteId() > 0 && venta.getFecha() != null;
    }

    private boolean validateFKCliente(int clienteId) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO(Conexion.getConnection());
            return clienteDAO.read(clienteId) != null;
        } catch (SQLException ex) {
            view.showError("Error al validar el cliente: " + ex.getMessage());
            return false;
        }
    }
}
