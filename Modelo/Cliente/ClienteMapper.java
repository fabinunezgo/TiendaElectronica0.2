/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author thyfa
 */
public class ClienteMapper implements Mapper<Cliente,ClienteDTO> {

    @Override
    public ClienteDTO toDTO(Cliente ent) {
       return new ClienteDTO(
                ent.getCedula(),
                ent.getNombreCompleto(),
                ent.getDireccion(),
                ent.getTelefono(),
                ent.getCorreo()
        );
    }

    @Override
    public Cliente toEnt(ClienteDTO dto) throws SQLException {
     
   return new Cliente(
               dto.getCedula(),
               dto.getNombreCompleto(),
               dto.getDireccion(),
               dto.getTelefono(),
               dto.getDireccion()
   );
    }
    
}
