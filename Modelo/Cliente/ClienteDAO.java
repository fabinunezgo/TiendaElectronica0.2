/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;

import Conexion.Conexion;
import Modelo.Dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author thyfa
 */



public class ClienteDAO extends Dao<ClienteDTO> {

    public ClienteDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean agregar(ClienteDTO dto) throws SQLException {
    CallableStatement stmt = null;
    try {
        String sql = "{CALL insertarCliente(?, ?, ?, ?, ?)}"; 
        stmt = connection.prepareCall(sql);
        stmt.setString(1, dto.getCedula());
        stmt.setString(2, dto.getNombreCompleto());
        stmt.setString(3, dto.getDireccion());
        stmt.setString(4, dto.getTelefono());
        stmt.setString(5, dto.getCorreo());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; 
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al agregar el cliente: " + e.getMessage(), e);
    } finally {
        if (stmt != null) {
            stmt.close();
        }
    }
}

    @Override
    public ClienteDTO read(Object id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE cedula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, (String) id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ClienteDTO(
                            resultSet.getString("cedula"),
                            resultSet.getString("nombreCompleto"),
                            resultSet.getString("direccion"),
                            resultSet.getString("telefono"),
                            resultSet.getString("correo")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<ClienteDTO> readAll() throws SQLException {
        List<ClienteDTO> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente"; 
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                ClienteDTO cliente = new ClienteDTO(
                        rs.getString("cedula"),
                        rs.getString("nombreCompleto"), 
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    @Override
    public boolean actualizar(ClienteDTO dto) throws SQLException {
        CallableStatement stmt = null;
        try {
            String sql = "{CALL actualizarCliente(?, ?, ?, ?, ?)}"; 
            stmt = connection.prepareCall(sql);
            stmt.setString(1, dto.getCedula());
            stmt.setString(2, dto.getNombreCompleto());
            stmt.setString(3, dto.getDireccion());
            stmt.setString(4, dto.getTelefono());
            stmt.setString(5, dto.getCorreo());
            return stmt.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        } finally {
            if (stmt != null) stmt.close(); 
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        CallableStatement stmt = null;
        try {
            String sql = "{CALL eliminarCliente(?)}"; 
            stmt = connection.prepareCall(sql);
            stmt.setString(1, (String) id); 
            return stmt.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        } finally {
            if (stmt != null) stmt.close(); 
        }
    }

    public boolean validatePK(String id) throws SQLException {
        return read(id) == null;
    }
    
    public boolean existeCliente(String cedula) throws SQLException {
    ClienteDTO cliente = read(cedula);
    return cliente != null;  
}
}
