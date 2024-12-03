/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Producto;

import Conexion.Conexion;
import Modelo.Cliente.ClienteDTO;
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
    }

   

    public boolean agregar(ProductoDTO dto) throws SQLException {
       String sql = "{CALL insertarProducto(?, ?, ?, ?, ?, ?)}";  
         Connection con = Conexion.getConnection();
        if (con == null) {
            System.err.println("Error: La conexión es nula.");
            return false;
        }
        CallableStatement stmt = null;
        try {
            if (con.isValid(2)) {
                stmt = (CallableStatement) con.prepareCall(sql);
                stmt.setInt(1, dto.getCodigo());
                stmt.setString(2, dto.getNombre());
                stmt.setString(3, dto.getCategoria());
                stmt.setInt(4, dto.getCantidadDisponible());
                stmt.setDouble(5, dto.getPrecio());
                stmt.setString(6, dto.getProveedor());

                return stmt.executeUpdate() > 0; 
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
    String sql = "SELECT * FROM Producto WHERE codigo = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, codigo);  
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
      String sql = "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, cantidad_disponible = ?, proveedor = ? WHERE codigo = ?";
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