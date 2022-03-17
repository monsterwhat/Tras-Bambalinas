package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.UsuarioTO;
import servicio.ServicioUsuario;
import javax.servlet.http.HttpServletRequest;
import servicio.ServicioCifrar;

@ManagedBean(name = "loginController")
@SessionScoped

public class LoginController implements Serializable {

    private String correo;
    private String clave;
    private String claveNueva, verificarClave;
    private String nombreUsuario;
    private String direccionUsuario;
    private int telefonoUsuario;

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

    public String getClavecifrada() {
        return ServicioCifrar.encrypt(clave);
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
        try {
            System.out.println("El valor digitado por el usuario (Correo) es: " + this.getCorreo());
            System.out.println("El valor digitado por el usuario (password) es: " + this.getClave());

            if (this.getCorreo() == null || "".equals(this.getCorreo())) {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos invalidos", "El correo electronico es incorrecto"));
            } else {
                usuarioTO = servicioUsuario.existeUsuario(this.getCorreo(), this.getClave());

                switch (usuarioTO.getTipoUsuario()) {
                    case "admin":
                        this.listaUsuarios = servicioUsuario.listaUsuariosBD();
                        this.redireccionar("/faces/adminMenu.xhtml");
                        break;
                    case "cliente":
                        this.redireccionar("/faces/clienteMenu.xhtml");
                        break;
                    default:
                        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autenticacion", "Las credenciales son invalidas"));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al ingresar el usuario! " + e);
        }

    }

    public void cambiarContrasena() {
        try {
            System.out.println("El valor digitado por el usuario (Correo) es: " + this.getCorreo());
            System.out.println("El valor digitado por el usuario (password) es: " + this.getClave());
            System.out.println("El valor digitado por el usuario (passwordNuevo) es: " + this.getClaveNueva());

            if (this.getCorreo() == null || "".equals(this.getCorreo())) {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos invalidos", "El correo electronico es incorrecto"));
            } else if (this.claveNueva == null ? this.verificarClave == null : this.claveNueva.equals(this.verificarClave)) {
                this.servicioUsuario.actualizarContrasena(this.getCorreo(), this.getClave(), this.getClaveNueva());
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "La Contraseña se guardo con exito"));
            }
        } catch (Exception e) {
            System.out.println("Error al cambiar contrasena! " + e);
        }
    }

    public void registrarUsuario() {
        try {
            System.out.println("El valor digitado por el usuario (Correo) es: " + this.getCorreo());
            System.out.println("El valor digitado por el usuario (password) es: " + this.getClaveNueva());
            System.out.println("El valor digitado por el usuario (Nombre) es: " + this.getNombreUsuario());
            System.out.println("El valor digitado por el usuario (Direccion) es: " + this.getDireccionUsuario());
            System.out.println("El valor digitado por el usuario (Telefono) es: " + this.getTelefonoUsuario());

            if (this.getCorreo() == null || "".equals(this.getCorreo())) {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos invalidos", "El correo electronico es incorrecto"));
            } else if (this.claveNueva == null) {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos invalidos", "La contrasena no puede estar vacia"));
            }
            UsuarioTO nuevoUsuario = new UsuarioTO(this.getCorreo(), this.getClaveNueva(), "admin",this.getNombreUsuario(),this.getDireccionUsuario(),this.getTelefonoUsuario());
            this.servicioUsuario.insertarUser(nuevoUsuario);

        } catch (Exception e) {
            System.out.println("Error al registrar Usuario! " + e);
        }
    }

    public void redireccionar(String ruta) {
        try {
            HttpServletRequest request;
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (IOException e) {
            System.out.println("Error redireccionando. " + e);
        }
    }

    public void salir() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()
                    + "/faces/index.xhtml?faces-redirect=true");
        } catch (IOException e) {
            System.out.println("Error al salir. " + e);
        }

    }

    public void testCategoria() {
        this.redireccionar("/faces/adminCategoria.xhtml");
    }

}
