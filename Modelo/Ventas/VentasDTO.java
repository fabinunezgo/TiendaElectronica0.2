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
    private final int id;
    private final Date fecha;
    private final int clienteId;
    private final List<productovendido> productosVendidos;
    private final double subtotal;
    private final double impuesto;
    private final double total;

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getClienteId() {
        return clienteId;
    }

    public List<productovendido> getProductosVendidos() {
        return productosVendidos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public VentasDTO(int id, Date fecha, int clienteId, List<productovendido> productosVendidos, double subtotal, double impuesto, double total) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.productosVendidos = productosVendidos;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }
    
    
    
 
    
}

   
