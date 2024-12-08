/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Controller;

import Conexion.Conexion;
import Modelo.Producto.ProductoDAO;
import Modelo.Producto.ProductoDTO;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControllerProducto {

    private ProductoDAO dao;
    private final View view;

    public ControllerProducto(View view) {
        this.view = view;
        try {
            this.dao = new ProductoDAO(Conexion.getConnection());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(); // Imprime el seguimiento completo del error
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public boolean agregar(ProductoDTO producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("Faltan datos requeridos");
            return false;
        }
        try {
            if (existeProducto(producto.getCodigo())) {
                view.showError("El código del producto ya está registrado");
                return false;
            }

            dao.agregar(producto);
            view.showMessage("Producto registrado correctamente");
            return true;
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al agregar el producto: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public void readAll() {
        try {
            List<ProductoDTO> productos = dao.readAll();
            List<ProductoDTO> productList = productos.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            view.showAll(productList);
        } catch (SQLException ex) {
            // Manejo de errores al cargar los productos
            view.showError("Error al cargar los productos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void update(ProductoDTO producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("Faltan datos requeridos");
            return;
        }  
        try {
            if (!validatePK(producto.getCodigo())) {
                view.showError("El código del producto no está registrado");
                return;
            }

            dao.actualizar(producto);
            view.showMessage("Producto actualizado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar el producto: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void delete(ProductoDTO producto) {
        if (producto == null || producto.getCodigo() <= 0) {
            view.showError("No se ha seleccionado ningún producto para eliminar");
            return;
        }
        try {
            if (!validatePK(producto.getCodigo())) {
                view.showError("El código del producto no está registrado");
                return;
            }
            dao.eliminar(producto.getCodigo());
            view.showMessage("Producto eliminado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar el producto: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private boolean validateRequired(ProductoDTO producto) {
        return producto != null 
            && producto.getCodigo() > 0 
            && producto.getNombre() != null 
            && !producto.getNombre().trim().isEmpty()
            && producto.getPrecio() > 0;
    }

    private boolean validatePK(int codigo) {
        try {
            return dao.read(codigo) != null;
        } catch (SQLException ex) {
            view.showError("Error al validar el código: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean existeProducto(int codigo) {
        try {
            ProductoDTO productoDTO = dao.read(codigo);
            return productoDTO != null;
        } catch (SQLException ex) {
            view.showError("Error al verificar la existencia del producto: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
