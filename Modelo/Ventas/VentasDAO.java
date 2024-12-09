package Modelo.Ventas;

import Conexion.Conexion;
import Modelo.Cliente.ClienteDAO;
import Modelo.Dao.Dao;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VentasDAO extends Dao<VentasDTO> {

    public VentasDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean agregar(VentasDTO dto) throws SQLException {

        CallableStatement stmt = null;

        try {
            String sql = "{CALL insertarVenta(?, ?, ?, ?, ?, ?)}";
            stmt = connection.prepareCall(sql);
            stmt.setString(1, String.valueOf(dto.getId()));
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(dto.getFecha()));
            stmt.setString(3, dto.getClienteId());
            stmt.setString(4, String.valueOf(dto.getProductoId()));
            stmt.setString(5, String.valueOf(dto.getCantidad()));
            stmt.setBigDecimal(6, BigDecimal.valueOf(dto.getPrecio()));

            return stmt.executeUpdate() > 0;
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
                            rs.getString("idCliente"),
                            rs.getInt("idProducto"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio")
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
                            rs.getString("idCliente"),
                            rs.getInt("idProducto"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio")
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
            stmt.setString(1, String.valueOf(dto.getId()));
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(dto.getFecha()));
            stmt.setString(3, dto.getClienteId());
            stmt.setString(4, String.valueOf(dto.getProductoId()));
            stmt.setString(5, String.valueOf(dto.getCantidad()));
            stmt.setBigDecimal(6, BigDecimal.valueOf(dto.getPrecio()));

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
