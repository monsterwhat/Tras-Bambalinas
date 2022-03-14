
package model;

import java.io.Serializable;


public class UsuarioTO implements Serializable{
    private int idusuarios;
    private String correoUsuario;
    private String contrasenaUsuario;
    private String tipoUsuario;
    private String contrasenaUsuarioDecifrada;

    public UsuarioTO(int idusuarios, String correoUsuario, String contrasenaUsuario, String tipoUsuario) {
        this.idusuarios = idusuarios;
        this.correoUsuario = correoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.tipoUsuario = tipoUsuario;
    }
    public UsuarioTO(){
    
    }

    public UsuarioTO(String correoUsuario, String contrasenaUsuario, String tipoUsuario) {
        this.correoUsuario = correoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setContrasenaUsuarioDecifrada(String contrasenaUsuarioDecifrada) {
        this.contrasenaUsuario = contrasenaUsuarioDecifrada;
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
