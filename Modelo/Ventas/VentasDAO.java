/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Ventas;

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
public class VentasDAO extends Dao<VentasDTO> {

    public VentasDAO(Connection connection) {
        super(connection);
    }

 
    public boolean agregar(VentasDTO dto) throws SQLException {
        String sql = "{CALL insertarVenta(?, ?, ?, ?, ?)}";  
        Connection con = Conexion.getInstancia().getConexion();

        if (con == null) {
            System.err.println("Error: La conexión es nula.");
            return false;
        }

        CallableStatement stmt = null;
        try {
            if (con.isValid(2)) {
                stmt = (CallableStatement) con.prepareCall(sql);
                stmt.setDate(1, new java.sql.Date(dto.getFecha().getTime()));
                stmt.setInt(2, dto.getClienteId());
                stmt.setDouble(3, dto.getSubtotal());
                stmt.setDouble(4, dto.getImpuesto());
                stmt.setDouble(5, dto.getTotal());
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
