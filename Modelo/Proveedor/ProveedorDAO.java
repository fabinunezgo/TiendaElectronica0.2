/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Proveedor;

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

public class ProveedorDAO extends Dao<ProveedorDTO> {

    public ProveedorDAO(Connection connection) {
        super(connection);
    }

    
    public boolean agregar(ProveedorDTO dto) throws SQLException {
        String sql = "{CALL insertarProveedor(?, ?, ?)}";  
        Connection con = Conexion.getConnection();
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
        String sql = "{CALL leerProveedorPorID(?)}"; 
        Connection con = Conexion.getConnection();
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall(sql);
            stmt.setInt(1, (int) id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new ProveedorDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("contacto"),
                    resultSet.getString("direccion")
                );
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return null;
    }

    @Override
    public List<ProveedorDTO> readAll() throws SQLException {
        String sql = "{CALL obtenerTodosLosProveedores()}";  
        List<ProveedorDTO> proveedores = new ArrayList<>();
        Connection con = Conexion.getConnection();
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                ProveedorDTO proveedor = new ProveedorDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("contacto"),
                    resultSet.getString("direccion")
                );
                proveedores.add(proveedor);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return proveedores;
    }

    @Override
    public boolean actualizar(ProveedorDTO dto) throws SQLException {
        String sql = "{CALL actualizarProveedor(?, ?, ?, ?)}";  
        Connection con = Conexion.getConnection();
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall(sql);
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getContacto());
            stmt.setString(4, dto.getDireccion());
            return stmt.executeUpdate() > 0;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        String sql = "{CALL eliminarProveedor(?)}"; 
        Connection con = Conexion.getConnection();
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall(sql);
            stmt.setInt(1, (int) id);
            return stmt.executeUpdate() > 0;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

   
}



