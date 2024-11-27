/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;

import Modelo.Dao.Dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;

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
       String sql = "INSERT INTO Cliente (cedula, nombreCompleto, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, Cliente());
        statement.setString(2, dto.getNombreCompleto());
        statement.setString(3, dto.getDireccion());
        statement.setString(4, dto.getTelefono());
        statement.setString(5, dto.getCorreo());
        return statement.executeUpdate() > 0; // Devuelve true si se insertó al menos un registro
    }
    }

    @Override
    public ClienteDTO read(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ClienteDTO> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(ClienteDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
