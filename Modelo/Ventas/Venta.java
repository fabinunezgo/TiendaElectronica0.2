package Modelo.Ventas;


import java.util.Date;
import java.util.List;


public class Venta {
    private int id;
    private Date fecha;
    private int clienteId;
    private List<productovendido> productosVendidos;
    private double subtotal;
    private double impuesto;
    private double total;

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
    

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setProductosVendidos(List<productovendido> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Venta(int id, Date fecha, int clienteId, List<productovendido> productosVendidos, double subtotal, double impuesto, double total) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.productosVendidos = productosVendidos;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }
 
    
}

 