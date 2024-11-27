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
<<<<<<< HEAD
=======

>>>>>>> ba8fdca6e6d2030f7cb6a8ce0ff334b9327ad25e
    private final String cedula;
    private final String nombreCompleto;
    private final String direccion;
    private final String telefono;
    private final String correo;

    public ClienteDTO(String cedula, String nombreCompleto, String direccion, String telefono, String correo) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

<<<<<<< HEAD
    public String getTelefono() {
        return telefono;
    }
=======
        public String getCorreo() {
            return correo;
        }

    public ClienteDTO(String cedula, String nombreCompleto, String direccion, String telefono, String correo) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

       
>>>>>>> ba8fdca6e6d2030f7cb6a8ce0ff334b9327ad25e

    public String getCorreo() {
        return correo;
    }

