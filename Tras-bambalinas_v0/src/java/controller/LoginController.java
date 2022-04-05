package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.UsuarioTO;
import servicio.ServicioUsuario;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import servicio.ServicioCifrar;

@ManagedBean(name = "loginController")
@SessionScoped

public class LoginController implements Serializable {

    private int idUser;
    private String correo;
    private String clave;
    private String claveNueva, verificarClave;
    private String nombreUsuario;
    private String direccionUsuario;
    private int telefonoUsuario;

    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private UsuarioTO usuarioTO;
    List<UsuarioTO> listaUsuarios = new ArrayList<UsuarioTO>();

    public LoginController() {
    }

    @PostConstruct
    public void cargar() {
        this.listaUsuarios = servicioUsuario.listaUsuariosBD();
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

            if (this.getCorreo() == null || "".equals(this.getCorreo())) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Campos invalidos", "El correo electronico es incorrecto");
            } else {
                System.out.println("El valor digitado por el usuario (Correo) es: " + this.getCorreo());
                System.out.println("El valor digitado por el usuario (password) es: " + this.getClave());

                if (!servicioUsuario.verificarCorreo(this.getCorreo())) {

                    if (servicioUsuario.existeUsuarioB(this.getCorreo(), this.getClave())) {
                        this.usuarioTO = servicioUsuario.existeUsuario(this.getCorreo(), this.getClave());
                        this.idUser = usuarioTO.getIdusuarios();

                        switch (this.usuarioTO.getTipoUsuario()) {
                            case "admin":
                                this.listaUsuarios = servicioUsuario.listaUsuariosBD();
                                this.redireccionar("/faces/adminMenu.xhtml");
                                break;
                            case "cliente":
                                this.redireccionar("/faces/clienteMenu.xhtml");
                                
                                break;
                            default:
                                addMessage(FacesMessage.SEVERITY_ERROR, "Autenticacion", "Las credenciales son invalidas");
                                break;
                        }
                    } else {
                        addMessage(FacesMessage.SEVERITY_ERROR, "Error de informacion de usuario!", "Uno de los valores digitados es incorrecto!");
                    }
                } else {
                    addMessage(FacesMessage.SEVERITY_WARN, "Correo Incorrecto!", "El correo digitado no se encuentra registrado!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al ingresar el usuario! " + e);
        }
    }

    public void cambiarContrasena() {
        try {
            if (this.getClave() == null || this.getClaveNueva() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error de cambio de contrasena", "Una de las contrasenas no puede estar vacia");
            }
            if (this.getCorreo() == null || "".equals(this.getCorreo())) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Campos invalidos", "El correo electronico es incorrecto");
            } else if (this.claveNueva == null ? this.verificarClave == null : this.claveNueva.equals(this.verificarClave)) {
                this.servicioUsuario.actualizarContrasena(this.getCorreo(), this.getClave(), this.getClaveNueva());
                addMessage(FacesMessage.SEVERITY_INFO, "Exito", "La Contraseña se guardo con exito");
            }
        } catch (Exception e) {
            System.out.println("Error al cambiar contrasena! " + e);
        }
    }

    public void registrarUsuario() {
        try {
            Pattern pattern = Pattern.compile("^.+@.+\\..+$");
            Matcher matcher = pattern.matcher(this.getCorreo());

            if (this.getCorreo() == null || "".equals(this.getCorreo())) {
                addMessage(FacesMessage.SEVERITY_INFO, "Campos invalidos", "El correo electronico es incorrecto");

            } else if (this.claveNueva == null) {
                addMessage(FacesMessage.SEVERITY_INFO, "Campos invalidos", "La contrasena no puede estar vacia");
            }
            if (matcher.matches()) {
                if (this.servicioUsuario.verificarCorreo(this.getCorreo())) {
                    System.out.println("Los valores son validos!");
                    System.out.println("El valor digitado por el usuario (Correo) es: " + this.getCorreo());
                    System.out.println("El valor digitado por el usuario (password) es: " + this.getClaveNueva());
                    System.out.println("El valor digitado por el usuario (Nombre) es: " + this.getNombreUsuario());
                    System.out.println("El valor digitado por el usuario (Direccion) es: " + this.getDireccionUsuario());
                    System.out.println("El valor digitado por el usuario (Telefono) es: " + this.getTelefonoUsuario());
                    UsuarioTO nuevoUsuario = new UsuarioTO(this.getCorreo(), ServicioCifrar.encrypt(this.getClaveNueva()), "admin", this.getNombreUsuario(), this.getDireccionUsuario(), this.getTelefonoUsuario());
                    this.servicioUsuario.insertarUser(nuevoUsuario);
                    addMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso!", "Se registro de manera exitosa al usuario!");
                } else {
                    addMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El correo ya se encuentra registrado");
                }
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El correo es invalido!");
            }
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
