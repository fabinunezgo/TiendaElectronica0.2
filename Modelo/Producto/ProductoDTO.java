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

    public ProductoDTO(int codigo, String nombre, String categoria, int cantidadDisponible, double precio, String proveedor) {
    }
    public class Producto {
    private final int codigo;
    private final String nombre;
    private final String categoria;
    private final double precio;
    private final int cantidadDisponible;
    private final String proveedor;

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
       
      

        public Producto(int codigo, String nombre, String categoria, double precio, int cantidadDisponible, String proveedor) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.categoria = categoria;
            this.precio = precio;
            this.cantidadDisponible = cantidadDisponible;
            this.proveedor = proveedor;
        }
  
    }
}
