package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.UsuarioTO;
import servicio.ServicioUsuario;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;

@ManagedBean(name = "userController")
@ViewScoped
public class UserController implements Serializable {

    private int idUser;

    public ServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }
    //private String nombreUser;
    private String correoElectronico;
    private String passwordUser;

    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private UsuarioTO usuarioTO = null;
    List<UsuarioTO> listaUserBD = new ArrayList<UsuarioTO>();
    private UsuarioTO newUser = null;

    public UserController() {

    }

    @PostConstruct
    public void cargar() {
        try {
            this.listaUserBD = servicioUsuario.listaUsuariosBD();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public UsuarioTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public List<UsuarioTO> getListaUserBD() {
        return listaUserBD;
    }

    public void setListaUserBD(List<UsuarioTO> listaUserBD) {
        this.listaUserBD = listaUserBD;
    }

    public UsuarioTO getNewUser() {
        return newUser;
    }

    public void setNewUser(UsuarioTO newUser) {
        this.newUser = newUser;
    }

    public void openNewUser() {
        this.newUser = new UsuarioTO();
    }

    public void agregarUsuarioTO() {
        try {
            this.servicioUsuario.insertarUser(newUser);
            this.cargar();
        } catch (Exception e) {
            System.out.println("Error agregando usuario! " + e);
        }
    }

    public void actualizarUsuarioTO() {
        try {
            this.servicioUsuario.actualizarUser(newUser);
            this.cargar();
        } catch (Exception e) {
            System.out.println("Error actualizando usuario! " + e);
        }
    }

    public void eliminarUsuarioTO() {
        try {
            this.servicioUsuario.eliminarUser(newUser);
            this.cargar();
        } catch (Exception e) {
            System.out.println("Error elimando usuario! " + e);
        }
    }

}
