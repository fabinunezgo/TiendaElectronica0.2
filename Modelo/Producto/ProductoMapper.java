/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Producto;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author thyfa
 */
public class ProductoMapper implements Mapper<Producto, ProductoDTO>{

    @Override
    public ProductoDTO toDTO(Producto ent) {
       return new ProductoDTO(
               ent.getCodigo(),
               ent.getNombre(),
               ent.getCategoria(), 
               (int) ent.getPrecio(),
               ent.getCantidadDisponible(),
               ent.getProveedor()
       );
    }
    @Override
    public Producto toEnt(ProductoDTO dto) throws SQLException {


       return new Producto(
               dto.getCodigo(),
               dto.getNombre(),
               dto.getCategoria(),
               dto.getPrecio(),
               dto.getCantidadDisponible(),
               dto.getProveedor()
       );
}
}
