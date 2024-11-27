/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Ventas;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author thyfa
 */
public class VentasMapper implements Mapper<Venta, VentasDTO> {

    @Override
    public VentasDTO toDTO(Venta ent) {
        return new VentasDTO(
                ent.getId(),
                ent.getFecha(),
                ent.getProductosVendidos(),
                ent.getSubtotal(),
                ent.getProductosVendidos(),
                ent.getTotal()
        );
    }

    @Override
    public Venta toEnt(VentasDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
