/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controller;

import Conexion.Conexion;
import Modelo.Ventas.VentasDAO;
import Modelo.Ventas.VentasDTO;
import Modelo.Ventas.productovendido;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControllerVentas {
  private VentasDAO dao;
    private final View view;

    public ControllerVentas(View view) {
        this.view = view;
        try {
            this.dao = new VentasDAO(Conexion.getConnection());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(); // Imprime el seguimiento completo del error
            view.showError("Error al conectar con la Base de Datos");
        }
    }
    
public boolean agregar(VentasDTO venta) {
        if (venta == null || !validateRequired(venta)) {
            view.showError("Faltan datos requeridos");
            return false;
        }
        try {
            dao.agregar(venta);
            view.showMessage("Venta registrada correctamente");
            return true;
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al agregar la venta: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

  public void readAll() {
        try {
            List<VentasDTO> ventas = dao.readAll();
            List<VentasDTO> ventasList = ventas.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            view.showAll(ventasList);
        } catch (SQLException ex) {
            view.showError("Error al cargar las ventas: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void update(VentasDTO venta) {
        if (venta == null || !validateRequired(venta)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(venta.getId())) {
                view.showError("El ID de la venta no está registrado");
                return;
            }

       dao.actualizar(venta);
            view.showMessage("Venta actualizada correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar la venta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void delete(VentasDTO venta) {
        if (venta == null || venta.getId() <= 0) {
            view.showError("No se ha seleccionado ninguna venta para eliminar");
            return;
        }
        try {
            if (!validatePK(venta.getId())) {
                view.showError("El ID de la venta no está registrado");
                return;
            }
            dao.eliminar(venta.getId());
            view.showMessage("Venta eliminada correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar la venta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
 private boolean validateRequired(VentasDTO venta) {
        return venta != null && venta.getId() > 0
                && venta.getClienteId() > 0
                && venta.getFecha() != null
                && venta.getTotal() > 0;
    }

    private boolean validatePK(int id) {
        try {
            return dao.read(id) != null;
        } catch (SQLException ex) {
            view.showError("Error al validar el ID: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}