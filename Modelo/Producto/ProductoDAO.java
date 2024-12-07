/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Producto;

import Conexion.Conexion;
import Modelo.Dao.Dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thyfa
 */
public class ProductoDAO extends Dao<ProductoDTO> {

    private Connection connection;

    public ProductoDAO(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    @Override
    public boolean agregar(ProductoDTO dto) throws SQLException {
        String sql = "{CALL insertarProducto(?, ?, ?, ?, ?, ?)}";  
        CallableStatement stmt = null;

        try {
            if (connection != null && connection.isValid(2)) {
                stmt = connection.prepareCall(sql);
                stmt.setInt(1, dto.getCodigo());
                stmt.setString(2, dto.getNombre());
                stmt.setString(3, dto.getCategoria());
                stmt.setInt(4, dto.getCantidadDisponible());
                stmt.setDouble(5, dto.getPrecio());
                stmt.setString(6, dto.getProveedor());

                int rowsAffected = stmt.executeUpdate(); 
                if (rowsAffected > 0) {
                    System.out.println("Producto agregado correctamente");
                    return true;
                } else {
                    System.err.println("No se pudo agregar el producto");
                    return false;
                }
            } else {
                System.err.println("Error: La conexión no es válida.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;         
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public ProductoDTO buscarPorCodigo(int codigo) throws SQLException {
        String sql = "{CALL buscarProductoPorCodigo(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, codigo);  
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new ProductoDTO(
                    resultSet.getInt("codigo"),
                    resultSet.getString("nombre"),
                    resultSet.getString("categoria"),
                    resultSet.getInt("cantidadDisponible"),
                    resultSet.getDouble("precio"),
                    resultSet.getString("proveedor")
                );
            }
        }
        return null;  
    }
    @Override
    public ProductoDTO read(Object id) throws SQLException {
        String sql = "SELECT * FROM Producto WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (int) id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ProductoDTO(
                    resultSet.getInt("codigo"),
                    resultSet.getString("nombre"),
                    resultSet.getString("categoria"),
                    resultSet.getInt("cantidadDisponible"),
                    resultSet.getDouble("precio"),
                    resultSet.getString("proveedor")
                );
            }
            return null;
        }
    }

    @Override
    public List<ProductoDTO> readAll() throws SQLException {
        List<ProductoDTO> productos = new ArrayList<>();
        String sql = "{CALL obtenerTodosLosProductos()}";  // Procedimiento almacenado
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                ProductoDTO producto = new ProductoDTO(
                    resultSet.getInt("codigo"),
                    resultSet.getString("nombre"),
                    resultSet.getString("categoria"),
                    resultSet.getInt("cantidadDisponible"),
                    resultSet.getDouble("precio"),
                    resultSet.getString("proveedor")
                );
                productos.add(producto);
            }
        }
        return productos;
    }

    @Override
     public boolean actualizar(ProductoDTO dto) throws SQLException {
        String sql = "{CALL actualizarProducto(?, ?, ?, ?, ?, ?)}";  
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, dto.getCodigo());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getCategoria());
            stmt.setInt(4, dto.getCantidadDisponible());
            stmt.setDouble(5, dto.getPrecio());
            stmt.setString(6, dto.getProveedor());
            return stmt.executeUpdate() > 0;
        }
    }
     
    @Override
    public boolean eliminar(Object id) throws SQLException {
        String sql = "{CALL eliminarProducto(?)}";  // Procedimiento almacenado
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, (int) id);
            return stmt.executeUpdate() > 0;
        }
    } 
}