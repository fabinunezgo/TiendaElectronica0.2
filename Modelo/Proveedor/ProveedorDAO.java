/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Proveedor;

import Conexion.Conexion;
import Modelo.Cliente.ClienteDTO;
import Modelo.Dao.Dao;
import com.mysql.cj.jdbc.CallableStatement;
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

public class ProveedorDAO extends Dao<ProveedorDTO> {

    public ProveedorDAO(Connection connection) {
        super(connection);
    }

    
    public boolean agregar(ProveedorDTO dto) throws SQLException {
        String sql = "{CALL insertar_proveedor(?, ?, ?)}";  // Procedimiento almacenado
        Connection con = Conexion.getInstancia().getConexion();
        if (con == null) {
            System.err.println("Error: La conexión es nula.");
            return false;
        }
        CallableStatement stmt = null;
        try {
            if (con.isValid(2)) {
                stmt = (CallableStatement) con.prepareCall(sql);
                stmt.setString(1, dto.getNombre());
                stmt.setString(2, dto.getContacto());
                stmt.setString(3, dto.getDireccion());
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

    @Override
    public ProveedorDTO read(Object id) throws SQLException {
        String sql = "SELECT * FROM Proveedor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (int) id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ProveedorDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("contacto"),
                    resultSet.getString("direccion")
                );
            }
            return null;
        }
    }

    @Override
    public List<ProveedorDTO> readAll() throws SQLException {
        List<ProveedorDTO> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProveedorDTO proveedor = new ProveedorDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("contacto"),
                    resultSet.getString("direccion")
                );
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }

    @Override
    public boolean actualizar(ProveedorDTO dto) throws SQLException {
        String sql = "UPDATE Proveedor SET nombre = ?, contacto = ?, direccion = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dto.getNombre());
            statement.setString(2, dto.getContacto());
            statement.setString(3, dto.getDireccion());
            statement.setInt(4, dto.getId());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        String sql = "DELETE FROM Proveedor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (int) id);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean agregar(ClienteDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

//public class ProveedorDAO extends Dao<ProveedorDTO>{
//
//    public ProveedorDAO(Connection connection) {
//        super(connection);
//    }
//
//    @Override
//    public boolean agregar(ProveedorDTO dto) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ProveedorDTO read(Object id) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public List<ProveedorDTO> readAll() throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean actualizar(ProveedorDTO dto) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean eliminar(Object id) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
//}

