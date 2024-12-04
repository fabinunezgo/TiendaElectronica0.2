/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Modelo.Producto.Producto;
import Modelo.Producto.ProductoDAO;
import Modelo.Producto.ProductoDTO;
import Modelo.Producto.ProductoMapper;
import View.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author users
 */
public class ControllerProducto {
    private ProductoDAO dao;
    private final View view;
    private final ProductoMapper mapper;

    public ControllerProducto(View view) {
        this.view = view;
        this.mapper = new ProductoMapper();
        try {
            dao = new ProductoDAO(DataBase.getConnection());
        } catch (SQLException ex) {
            view.showError("Error al conectar con la Base de Datos");
        }
    }

    public void create(Producto producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(producto.getCodigo())) {
                view.showError("El código ingresado ya se encuentra registrado");
                return;
            }
            dao.agregar(mapper.toDTO(producto));
            view.showMessage("Producto guardado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al guardar los datos: " + ex.getMessage());
        }
    }

    public void read(int codigo) {
        try {
            ProductoDTO dto = dao.read(codigo);
            if (dto != null) {
                Producto producto = mapper.toEnt(dto);
                view.showOne(producto);
            } else {
                view.showError("Producto no encontrado");
            }
        } catch (SQLException ex) {
            view.showError("Error al cargar el producto: " + ex.getMessage());
        }
    }

    public void readAll() {
        try {
            List<ProductoDTO> dtoList = dao.readAll();
            List<Producto> productoList = dtoList.stream()
                    .map(mapper::toEnt)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            view.showAll(productoList);
        } catch (SQLException ex) {
            view.showError("Error al cargar los productos: " + ex.getMessage());
        }
    }

    public void update(Producto producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (validatePK(producto.getCodigo())) {
                view.showError("El código ingresado no se encuentra registrado");
                return;
            }
            dao.actualizar(mapper.toDTO(producto));
            view.showMessage("Producto actualizado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
        }
    }

    public void delete(Producto producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("No hay ningún producto cargado actualmente");
            return;
        }
        try {
            if (validatePK(producto.getCodigo())) {
                view.showError("El código ingresado no se encuentra registrado");
                return;
            }
            dao.eliminar(producto.getCodigo());
            view.showMessage("Producto eliminado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar los datos: " + ex.getMessage());
        }
    }

    public boolean validateRequired(Producto producto) {
        return producto.getCodigo() > 0 &&
                !producto.getNombre().trim().isEmpty() &&
                !producto.getCategoria().trim().isEmpty() &&
                producto.getPrecio() > 0 &&
                producto.getCantidadDisponible() >= 0 &&
                !producto.getProveedor().trim().isEmpty();
    }

    public boolean validatePK(int codigo) {
        try {
            return dao.read(codigo) != null;
        } catch (SQLException ex) {
            return false;
        }
    }
}
