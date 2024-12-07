/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Conexion.Conexion;
import Modelo.Producto.ProductoDAO;
import Modelo.Producto.ProductoDTO;
import View.View;
import java.sql.SQLException;
import java.util.List;

public class ControllerProducto {

    private ProductoDAO dao;
    private final View view;

    public ControllerProducto(View view) {
        this.view = view;
        try {
            this.dao = new ProductoDAO(Conexion.getConnection());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public boolean agregar(ProductoDTO producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("Faltan datos requeridos");
            return false;
        }
        try {
            if (dao.agregar(producto)) {
                view.showMessage("Producto registrado correctamente");
                return true;
            } else {
                view.showError("No se pudo registrar el producto");
                return false;
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al agregar el producto: " + ex.getMessage());
            return false;
        }
    }

    public void readAll() {
        try {
            List<ProductoDTO> productos = dao.readAll();
            view.showAll(productos);
        } catch (SQLException ex) {
            // Manejo de errores al cargar los productos
            view.showError("Error al cargar los productos: " + ex.getMessage());
        }
    }
    public void update(ProductoDTO producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("Faltan datos requeridos");
            return;
        }  
        try {
            if (dao.actualizar(producto)) {
                view.showMessage("Producto actualizado correctamente");
            } else {
                view.showError("No se pudo actualizar el producto");
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar el producto: " + ex.getMessage());
        }
    }

    public void delete(ProductoDTO producto) {
        if (producto == null || producto.getCodigo() <= 0) {
            view.showError("No se ha seleccionado ningún producto para eliminar");
            return;
        }
        try {
            if (dao.eliminar(producto.getCodigo())) {
                view.showMessage("Producto eliminado correctamente");
            } else {
                view.showError("No se pudo eliminar el producto");
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar el producto: " + ex.getMessage());
        }
    }

      private boolean validateRequired(ProductoDTO producto) {
        return producto != null 
            && producto.getCodigo() > 0 
            && producto.getNombre() != null 
            && !producto.getNombre().isEmpty()
            && producto.getPrecio() > 0;
    }
}
