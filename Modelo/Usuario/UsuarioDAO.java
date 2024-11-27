/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuario;

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
public class UsuarioDAO extends Dao<UsuarioDTO> {

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean agregar(UsuarioDTO dto) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, username, password, rol) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dto.getNombre());
            statement.setString(2, dto.getUsername());
            statement.setString(3, dto.getPassword());
            statement.setString(4, dto.getRol());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public UsuarioDTO read(Object id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer) id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioDTO(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("rol")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<UsuarioDTO> readAll() throws SQLException {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new UsuarioDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("rol")
                ));
            }
        }
        return usuarios;
    }

    @Override
    public boolean actualizar(UsuarioDTO dto) throws SQLException {
        String sql = "UPDATE Usuario SET nombre = ?, username = ?, password = ?, rol = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dto.getNombre());
            statement.setString(2, dto.getUsername());
            statement.setString(3, dto.getPassword());
            statement.setString(4, dto.getRol());
            statement.setInt(5, dto.getId());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer) id);
            return statement.executeUpdate() > 0;
        }
    }
}
