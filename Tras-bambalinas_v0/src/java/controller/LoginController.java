package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.UsuarioTO;
import servicio.ServicioUsuario;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String correo;
    private String clave;
    private String claveNueva, verificarClave;

    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private UsuarioTO usuarioTO = null;
    List<UsuarioTO> listaUsuarios = new ArrayList<UsuarioTO>();

    public LoginController() {
    }

    @PostConstruct
    public void cargar() {
        this.listaUsuarios = servicioUsuario.listaUsuariosBD();
    }

    public String getVerificarClave() {
        return verificarClave;
    }

    public void setVerificarClave(String verificarClave) {
        this.verificarClave = verificarClave;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public UsuarioTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public List<UsuarioTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void ingresar() {
        System.out.println("El valor digitado por el usuario (Correo) es: " + this.getCorreo());
        System.out.println("El valor digitado por el usuario (password) es: " + this.getClave());

        if (this.getCorreo() == null || "".equals(this.getCorreo())) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos invalidos", "El correo electronico es incorrecto"));
        } else {
            usuarioTO = servicioUsuario.existeUsuario(this.getCorreo(), this.getClave());

            if (usuarioTO.getTipoUsuario().equals("admin")) {
                this.listaUsuarios = servicioUsuario.listaUsuariosBD();
                this.redireccionar("/faces/adminMenu.xhtml");
            } else if (usuarioTO.getTipoUsuario().equals("cliente")) {
                this.redireccionar("/faces/clienteMenu.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autenticacion", "Las credenciales son invalidas"));

            }
        }
    }

    public void cambiarContrasena() {
        System.out.println("El valor digitado por el usuario (Correo) es: " + this.getCorreo());
        System.out.println("El valor digitado por el usuario (password) es: " + this.getClave());
        System.out.println("El valor digitado por el usuario (passwordNuevo) es: " + this.getClaveNueva());

        if (this.getCorreo() == null || "".equals(this.getCorreo())) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos invalidos", "El correo electronico es incorrecto"));
        } else if (this.claveNueva == null ? this.verificarClave == null : this.claveNueva.equals(this.verificarClave)) {
            this.servicioUsuario.actualizarContrasena(this.getCorreo(), this.getClave(), this.getClaveNueva());
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "La Contraseña se guardo con exito"));
        }

    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salir() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()
                    + "/faces/index.xhtml?faces-redirect=true");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testRedireccion() {
        this.redireccionar("/faces/IngresarUsuario.xhtml");
    }

}
