package Modelo.Producto;

import Frames.FrmProductos;

public class Producto {

    private int codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidadDisponible;
    private String proveedor;

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Producto(int codigo, String nombre, String categoria, double precio, int cantidadDisponible, String proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.proveedor = proveedor;
    }
    
    
   

    
   

}
