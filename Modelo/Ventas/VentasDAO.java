package Modelo.Ventas;

import Conexion.Conexion;
import Modelo.Cliente.ClienteDAO;
import Modelo.Dao.Dao;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentasDAO extends Dao<VentasDTO> {

    public VentasDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean agregar(VentasDTO dto) throws SQLException {
        if (dto == null || !validateFKClient(dto.getClienteId())) {
        return false;
    }
    String sql = "{CALL insertarVenta(?, ?, ?, ?, ?, ?)}";
    try (CallableStatement stmt = connection.prepareCall(sql)) {
        stmt.setTimestamp(1, new java.sql.Timestamp(dto.getFecha().getTime())); 
        stmt.setString(2, dto.getClienteId()); 
        stmt.setInt(3, dto.getProductosVendidos().size()); 
        
        
        stmt.setBigDecimal(4, BigDecimal.valueOf(dto.getSubtotal())); 
        stmt.setBigDecimal(5, BigDecimal.valueOf(dto.getImpuesto())); 
        stmt.setBigDecimal(6, BigDecimal.valueOf(dto.getTotal())); 
        
        return stmt.executeUpdate() > 0; 
    }
    }

    @Override
    public VentasDTO read(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return null;
        }
        String sql = "{CALL leerVentaPorID(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, (int) id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    List<productovendido> productosVendidos = new ArrayList<>();
                    return new VentasDTO(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getString("clienteId"), 
                        productosVendidos,
                        rs.getDouble("subtotal"),
                        rs.getDouble("impuesto"),
                        rs.getDouble("total")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<VentasDTO> readAll() throws SQLException {
        String sql = "{CALL obtenerTodasLasVentas()}";
        List<VentasDTO> ventas = new ArrayList<>();
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    List<productovendido> productosVendidos = new ArrayList<>();
                    ventas.add(new VentasDTO(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getString("clienteId"), 
                        productosVendidos,
                        rs.getDouble("subtotal"),
                        rs.getDouble("impuesto"),
                        rs.getDouble("total")
                    ));
                }
            }
        }
        return ventas;
    }

    @Override
    public boolean actualizar(VentasDTO dto) throws SQLException {
        String sql = "{CALL actualizarVenta(?, ?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, dto.getId());
            stmt.setDate(2, new java.sql.Date(dto.getFecha().getTime()));
            stmt.setString(3, dto.getClienteId()); 
            stmt.setDouble(4, dto.getSubtotal());
            stmt.setDouble(5, dto.getImpuesto());
            stmt.setDouble(6, dto.getTotal());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        if (id == null || String.valueOf(id).trim().isEmpty()) {
            return false;
        }
        String sql = "{CALL eliminarVenta(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, (int) id);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean validateFKClient(Object id) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        return clienteDAO.read(id) != null;
    }
}
