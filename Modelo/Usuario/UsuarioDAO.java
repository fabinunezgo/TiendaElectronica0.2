/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuario;

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
public class UsuarioDAO extends Dao<UsuarioDTO> {

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    
    public boolean agregar(UsuarioDTO dto) throws SQLException {
       String sql = "{CALL insertarUsuario(?, ?, ?, ?)}";  
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
                stmt.setString(2, dto.getUsername());
                stmt.setString(3, dto.getPassword());
                stmt.setString(4, dto.getRol());

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
    public UsuarioDTO read(Object id) throws SQLException {
        String sql = "{CALL leerUsuarioPorID(?)}";
        Connection con = Conexion.getInstancia().getConexion();
        CallableStatement stmt = null;
    try {
        stmt = con.prepareCall(sql);
        stmt.setInt(1, (int) id);
        ResultSet rs = stmt.executeQuery();
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
    } finally {
        if (stmt != null) {
            stmt.close();
        }
    }
    }

    @Override
    public List<UsuarioDTO> readAll() throws SQLException {
        String sql = "{CALL obtenerTodosLosUsuarios()}";
        List<UsuarioDTO> usuarios = new ArrayList<>();
        Connection con = Conexion.getInstancia().getConexion();
        CallableStatement stmt = null;
        try {
        stmt = con.prepareCall(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            usuarios.add(new UsuarioDTO(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("rol")
            ));
        }
    } finally {
        if (stmt != null) {
            stmt.close();
        }
    }
    return usuarios;
    }

    @Override
    public boolean actualizar(UsuarioDTO dto) throws SQLException {
        String sql = "{CALL actualizarUsuario(?, ?, ?, ?, ?)}";
        Connection con = Conexion.getInstancia().getConexion();
        CallableStatement stmt = null;
        try {
        stmt = con.prepareCall(sql);
        stmt.setInt(1, dto.getId());
        stmt.setString(2, dto.getNombre());
        stmt.setString(3, dto.getUsername());
        stmt.setString(4, dto.getPassword());
        stmt.setString(5, dto.getRol());
        return stmt.executeUpdate() > 0;
        } finally {
            if (stmt != null) {
            stmt.close();
            }
        }
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        String sql = "{CALL eliminarUsuario(?)}";
        Connection con = Conexion.getInstancia().getConexion();
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

    @Override
    public boolean agregar(ClienteDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
