package Modelo.Proveedor;

public class Proveedor {
    private String id;
    private String nombre;
    private String contacto;
    private String direccion;
   
     public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
 
    
    

    public Proveedor(String id, String nombre, String contacto, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + ", contacto=" + contacto + ", direccion=" + direccion + '}';
    }
}