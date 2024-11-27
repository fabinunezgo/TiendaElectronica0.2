/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Producto;

/**
 *
 * @author thyfa
 */
public class ProductoDTO {

    private final int codigo;
    private final String nombre;
    private final String categoria;
    private final int cantidadDisponible;
    private final double precio;
    private final String proveedor;

    public ProductoDTO(int codigo, String nombre, String categoria, int cantidadDisponible, double precio, String proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public double getPrecio() {
        return precio;
    }

    public String getProveedor() {
        return proveedor;
    }
}
