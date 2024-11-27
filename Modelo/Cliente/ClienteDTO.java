/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;

/**
 *
 * @author thyfa
 */
public class ClienteDTO {

    public ClienteDTO(String cedula, String nombreCompleto, String direccion, String telefono, String correo) {
    }
    public class Cliente {
    private String cedula;
    private String nombreCompleto;
    private String direccion;
    private String telefono;
    private String correo;

        public String getCedula() {
            return cedula;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public String getDireccion() {
            return direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public String getCorreo() {
            return correo;
        }

    
        public Cliente(String cedula, String nombreCompleto, String direccion, String telefono, String correo) {
            this.cedula = cedula;
            this.nombreCompleto = nombreCompleto;
            this.direccion = direccion;
            this.telefono = telefono;
            this.correo = correo;
        }
        
        

    }
}
