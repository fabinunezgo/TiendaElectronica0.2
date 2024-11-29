/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Ventas;

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
public class VentasDAO extends Dao<VentasDTO> {

    public VentasDAO(Connection connection) {
        super(connection);
    }

 
    public boolean agregar(VentasDTO dto) throws SQLException {
        String sql = "INSERT INTO Ventas (fecha, clienteId, subtotal, impuesto, total) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(dto.getFecha().getTime()));
            statement.setInt(2, dto.getClienteId());
            statement.setDouble(3, dto.getSubtotal());
            statement.setDouble(4, dto.getImpuesto());
            statement.setDouble(5, dto.getTotal());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public VentasDTO read(Object id) throws SQLException {
        String sql = "SELECT * FROM Ventas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer) id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    List<productovendido> productosVendidos = new ArrayList<>();

                    return new VentasDTO(
                            rs.getInt("id"),
                            rs.getDate("fecha"),
                            rs.getInt("clienteId"),
                            productosVendidos,
                            rs.getDouble("subtotal"),
                            rs.getDouble("impuesto"),
                            rs.getDouble("total")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<VentasDTO> readAll() throws SQLException {
        List<VentasDTO> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Ventas";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                List<productovendido> productosVendidos = new ArrayList<>();
                ventas.add(new VentasDTO(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getInt("clienteId"),
                        productosVendidos, 
                        rs.getDouble("subtotal"),
                        rs.getDouble("impuesto"),
                        rs.getDouble("total")
                ));
            }
        }
        return ventas;
    }

    @Override
    public boolean actualizar(VentasDTO dto) throws SQLException {
        String sql = "UPDATE Ventas SET fecha = ?, clienteId = ?, subtotal = ?, impuesto = ?, total = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(dto.getFecha().getTime()));
            statement.setInt(2, dto.getClienteId());
            statement.setDouble(3, dto.getSubtotal());
            statement.setDouble(4, dto.getImpuesto());
            statement.setDouble(5, dto.getTotal());
            statement.setInt(6, dto.getId());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        String sql = "DELETE FROM Ventas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer) id);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean agregar(ClienteDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
