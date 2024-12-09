/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Ventas;

import java.util.Date;
import java.util.List;

/**
 *
 * @author thyfa
 */
public class VentasDTO {

    private int id;
    private Date fecha;
    private String clienteId;
    private int productoId;
    private int cantidad;
    private double precio;
    private double subtotal;
    private double impuesto;
    private double total;

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getClienteId() {
        return clienteId;
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

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

    public VentasDTO(int id, Date fecha, String clienteId, int productoId, int cantidad, double precio, double subtotal, double impuesto, double total) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public VentasDTO(int id, Date fecha, String clienteId, int productoId, int cantidad, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
   
