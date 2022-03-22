
package model;

import java.io.Serializable;


public class UsuarioTO implements Serializable{
    private int idusuarios;
    private String correoUsuario;
    private String contrasenaUsuario;
    private String tipoUsuario;
    private String contrasenaUsuarioDecifrada;
    private String nombreUsuario;
    private String direccionUsuario;
    private int telefonoUsuario;
    private int numeroContratoUsuario;
    private String descripcionTrabajoUsuario;

    public UsuarioTO(int idusuarios, String correoUsuario, String contrasenaUsuario, String tipoUsuario, String nombreUsuario, String direccionUsuario, int telefonoUsuario, int numeroContratoUsuario, String descripcionTrabajoUsuario) {
        this.idusuarios = idusuarios;
        this.correoUsuario = correoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.tipoUsuario = tipoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.direccionUsuario = direccionUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.numeroContratoUsuario = numeroContratoUsuario;
        this.descripcionTrabajoUsuario = descripcionTrabajoUsuario;
    }

    public UsuarioTO(){
    
    }

    public UsuarioTO(String correoUsuario, String contrasenaUsuario, String tipoUsuario, String nombreUsuario, String direccionUsuario, int telefonoUsuario) {
        this.correoUsuario = correoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.tipoUsuario = tipoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.direccionUsuario = direccionUsuario;
        this.telefonoUsuario = telefonoUsuario;
    }
    
 
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public int getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(int telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public int getNumeroContratoUsuario() {
        return numeroContratoUsuario;
    }

    public void setNumeroContratoUsuario(int numeroContratoUsuario) {
        this.numeroContratoUsuario = numeroContratoUsuario;
    }

    public String getDescripcionTrabajoUsuario() {
        return descripcionTrabajoUsuario;
    }

    public void setDescripcionTrabajoUsuario(String descripcionTrabajoUsuario) {
        this.descripcionTrabajoUsuario = descripcionTrabajoUsuario;
    }

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setContrasenaUsuarioDecifrada(String contrasenaUsuarioDecifrada) {
        this.contrasenaUsuarioDecifrada = servicio.ServicioCifrar.decrypt(contrasenaUsuario);
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }
    
    public String getContrasenaUsuarioDecifrada(){
        contrasenaUsuarioDecifrada = servicio.ServicioCifrar.decrypt(contrasenaUsuario);
        return contrasenaUsuarioDecifrada;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
