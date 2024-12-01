/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Producto;

import Modelo.Cliente.ClienteDTO;
import Modelo.Dao.Dao;
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

    public ProductoDAO(Connection connection) {
        super(connection);
    }
    
    private Connection connection;

    public boolean agregar(ProductoDTO dto) throws SQLException {
        String sql = "INSERT INTO Producto (codigo, nombre, categoria, cantidadDisponible, precio, proveedor) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dto.getCodigo());
            statement.setString(2, dto.getNombre());
            statement.setString(3, dto.getCategoria());
            statement.setInt(4, dto.getCantidadDisponible());
            statement.setDouble(5, dto.getPrecio());
            statement.setString(6, dto.getProveedor());
            return statement.executeUpdate() > 0;
        }
    }
    public ProductoDTO buscarPorCodigo(int codigo) throws SQLException {
    String sql = "SELECT * FROM Producto WHERE codigo = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, codigo);  // Establecer el código del producto
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
        String sql = "SELECT * FROM Producto";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
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
        String sql = "UPDATE Producto SET nombre = ?, categoria = ?, cantidadDisponible = ?, precio = ?, proveedor = ? WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dto.getNombre());
            statement.setString(2, dto.getCategoria());
            statement.setInt(3, dto.getCantidadDisponible());
            statement.setDouble(4, dto.getPrecio());
            statement.setString(5, dto.getProveedor());
            statement.setInt(6, dto.getCodigo());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        String sql = "DELETE FROM Producto WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (int) id);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean agregar(ClienteDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}