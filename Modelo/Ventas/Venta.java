package Modelo.Ventas;


import java.util.Date;
import java.util.List;


public class Venta {
    private int id;
    private Date fecha;
    private int clienteId;
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

    public int getClienteId() {
        return clienteId;
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
        setFecha(fecha);
    }


    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }


    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Venta(int id, Date fecha, int clienteId, int productoId, int cantidad, double precio, double subtotal, double impuesto, double total) {
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

    public Venta(int id, Date fecha, int clienteId, int productoId, int cantidad, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fecha=" + fecha + ", clienteId=" + clienteId + ", productoId=" + productoId + ", cantidad=" + cantidad + ", precio=" + precio + ", subtotal=" + subtotal + ", impuesto=" + impuesto + ", total=" + total + '}';
    }   
}

 