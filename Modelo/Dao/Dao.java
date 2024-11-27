/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo.Dao;

import Modelo.Cliente.ClienteDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author thyfa
 */
public abstract class Dao<Dto> {
    protected Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }
    
    public abstract boolean agregar (   ClienteDTO dto)throws SQLException;
    public abstract Dto read (Object id)throws SQLException;
    public abstract List<Dto> readAll ()throws SQLException;
    public abstract boolean actualizar (Dto dto)throws SQLException;
    public abstract boolean eliminar (Object id)throws SQLException;
}
