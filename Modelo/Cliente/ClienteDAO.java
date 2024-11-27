/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;

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
import Modelo.Dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends Dao<ClienteDTO> {

    public ClienteDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean agregar(ClienteDTO dto) throws SQLException {
        String sql = "INSERT INTO Cliente (cedula, nombreCompleto, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dto.getCedula());
            statement.setString(2, dto.getNombreCompleto());
            statement.setString(3, dto.getDireccion());
            statement.setString(4, dto.getTelefono());
            statement.setString(5, dto.getCorreo());
            return statement.executeUpdate() > 0; // Devuelve true si se insertó al menos un registro
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
        String sql = "SELECT * FROM Cliente";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                clientes.add(new ClienteDTO(
                        resultSet.getString("cedula"),
                        resultSet.getString("nombreCompleto"),
                        resultSet.getString("direccion"),
                        resultSet.getString("telefono"),
                        resultSet.getString("correo")
                ));
            }
        }
        return clientes;
    }

    @Override
    public boolean actualizar(ClienteDTO dto) throws SQLException {
        String sql = "UPDATE Cliente SET nombreCompleto = ?, direccion = ?, telefono = ?, correo = ? WHERE cedula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dto.getNombreCompleto());
            statement.setString(2, dto.getDireccion());
            statement.setString(3, dto.getTelefono());
            statement.setString(4, dto.getCorreo());
            statement.setString(5, dto.getCedula());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE cedula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, (String) id);
            return statement.executeUpdate() > 0;
        }
    }
}
