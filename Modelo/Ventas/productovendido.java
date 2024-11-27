/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Ventas;

public class productovendido {
    private int productoId;
    private int cantidad;
    private double precio;

    public productovendido(int productoId, int cantidad, double precio) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
}

