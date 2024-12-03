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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thyfa
 */
import Modelo.Dao.Dao;
import com.mysql.cj.jdbc.CallableStatement;
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
        Connection con = Conexion.getInstancia().getConexion();
         CallableStatement stmt = null;

        try {
            String sql = "{CALL insertar_cliente(?, ?, ?, ?, ?)}";
            stmt = (CallableStatement) con.prepareCall(sql);
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
            if (con != null) con.close();
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
        String query = "SELECT * FROM cliente"; // Cambia "cliente" al nombre de tu tabla.
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
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

    public boolean validatePK(String id) throws SQLException {
       return read(id) == null;
    }
}
