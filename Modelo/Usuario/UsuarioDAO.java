/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuario;

import Modelo.Dao.Dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author thyfa
 */
public class UsuarioDAO extends Dao<UsuarioDTO>{

    public UsuarioDAO(Connection connection) {
        super(connection);
    }


    
    
}
