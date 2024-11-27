/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuario;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author thyfa
 */
public class UsuarioMapper implements Mapper<UsuarioDTO,Usuario> {

    @Override
    public Usuario toDTO(UsuarioDTO ent) {
       return new Usuario(
        ent.getId(),
        ent.getNombre(),
        ent.getUsername(),
        ent.getPassword(),
        ent.getRol()
       );
    }

    @Override
    public UsuarioDTO toEnt(Usuario dto) throws SQLException {
        
        
    }

}