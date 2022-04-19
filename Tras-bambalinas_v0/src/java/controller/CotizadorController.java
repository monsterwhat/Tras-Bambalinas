package controller;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.CaracteristicaTO;
import model.CategoriaTO;
import model.CotizacionTO;
import model.UsuarioTO;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import servicio.ServicioCaracteristica;
import servicio.ServicioCategoria;
import servicio.ServicioCotizacion;

@ManagedBean(name = "cotizadorController")
@ViewScoped

public class CotizadorController implements Serializable {

    private ServicioCategoria servicioCategoria = new ServicioCategoria();
    private ServicioCaracteristica servicioCaracteristica = new ServicioCaracteristica();
    private ServicioCotizacion servicioCotizacion = new ServicioCotizacion();

    private UsuarioTO usuarioTO;
    private CategoriaTO categoriaTO;
    private CaracteristicaTO caracteristicaTO, caracteristicasSeleccionada;
    private CotizacionTO cotizacionTO, newCotizacionTO, mostrarCotizacionNueva;

    List<String> listaDeCaracteristica = new ArrayList<>();
    List<Integer> listaIdCaracteristicas = new ArrayList<>();

    List<Double> listaAncho = new ArrayList<>();
    List<Double> listaLargo = new ArrayList<>();

    List<CaracteristicaTO> listaCaracteristicasParaCotizador = new ArrayList<>();
    List<CaracteristicaTO> listaCanastaCotizador = new ArrayList<>();

    List<CategoriaTO> listaCategoriaParaCotizarCliente = new ArrayList<>();
    List<CategoriaTO> listaCategoriaParaCotizarAdmin = new ArrayList<>();
    List<CotizacionTO> listaCotizacion = new ArrayList<>();

    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private String estadoCategoria;
    private String seleccionCategoria;
    private String visibilidadCategoria;

    private int idCaracteristica;
    private int idCategoriaCaracteristica;
    private String imagenCaracteristica;
    private String nombreCaracteristica;
    private String descripcionCaracteristica;
    private String estadoCaracteristica;
    private double precioCaracteristica;
    private String colorCaracteristica;
    private int prioridadCaracteristica;

    int numeroCotizacion;
    private String listaDeCaracteristicas;
    String fechaCotizacion;
    int clienteCotizacion;
    String anchoCotizacion;
    String largoCotizacion;
    double totalCotizacion;

    private double ancho;
    private double largo;

    List<CaracteristicaTO> listaCaracteristicas = new ArrayList<>();
    List<CaracteristicaTO> listaCaracteristicasCotizacion = null;

    @PostConstruct
    public void cargar() {
        try {

            this.listaCategoriaParaCotizarAdmin = servicioCategoria.listaCategoriaParaCotizadorAdmin();
            this.listaCategoriaParaCotizarCliente = servicioCategoria.listaCategoriaParaCotizadorCliente();
            this.listaCotizacion = servicioCotizacion.listaCotizaciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void noTieneMedidas() {
        this.listaLargo.add(0.0);
        this.listaAncho.add(0.0);
    }

    public boolean esAdmin(String tipo) {
        System.out.println("Tipo: " + tipo);
        if (tipo.equals("admin")) {
            return true;
        }
        return false;
    }

    public boolean esMultiple(CategoriaTO categoriaTO) {
        if (categoriaTO.getSeleccionCategoria().equals("Múltiple")) {
            return true;
        }
        return false;
    }

    public boolean tieneMedidas(CategoriaTO categoriaTO) {
        if (categoriaTO.getMedidasCategoria().equals("Tiene medidas")) {
            return true;
        }
        return false;
    }

    public void eliminarCotizacionTO() {
        try {
            this.servicioCotizacion.eliminarCotizacion(newCotizacionTO);
            this.cargar();
        } catch (Exception e) {
            System.out.println("Error elimando cotizacion! " + e);
        }
    }

    public List<CaracteristicaTO> cargarListaCaracteristicas(int id) {
        try {
            this.listaCaracteristicasParaCotizador = this.servicioCaracteristica.listaCaracteristicasParaCotizador(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return listaCaracteristicasParaCotizador;
    }

    public List<CaracteristicaTO> cargarImagenCaracteristica(String listaDeIds) {
        listaCaracteristicasCotizacion = new ArrayList<>();
        System.out.println("String: " + listaDeIds);

        String[] lista = listaDeIds.split(", ");

        for (String i : lista) {
            this.listaCaracteristicasCotizacion.add(this.servicioCaracteristica.cargarCaracteristicaTO(i));
            System.out.println("Id: " + i);
        }

        for (CaracteristicaTO a : listaCaracteristicasCotizacion) {
            System.out.println("Lista TO:" + a.getNombreCaracteristica());
        }

        return this.listaCaracteristicasCotizacion;
    }

    public void openNewCotizacion() {
        this.newCotizacionTO = new CotizacionTO();
    }

    public void abrirEIngresarNewCotizacion(int id) {

        double suma = 0;
        double multiplicacionMedidas = 0;
        double ancho = 0;
        double largo = 0;
        openNewCotizacion();
        String ListaCotizador = "";

        try {

            if (this.listaCanastaCotizador.isEmpty()) {
                System.out.println("Error esta vacia");
                addMessage(FacesMessage.SEVERITY_ERROR, "Error de cotizacion!", "No hay nada seleccionado!");
            }

            this.listaCanastaCotizador.forEach((caracTO) -> {
                listaIdCaracteristicas.add(caracTO.getIdCaracteristica());
                listaDeCaracteristica.add(caracTO.getNombreCaracteristica());
            });

            for (CaracteristicaTO caracTO : listaCanastaCotizador) {
                suma = suma + caracTO.getPrecioCaracteristica();
            }

            for (int i = 0; i < listaCanastaCotizador.size(); i++) {
                ListaCotizador = ListaCotizador.trim() + listaDeCaracteristica.get(i);
                ListaCotizador = ListaCotizador + " - " + listaAncho.get(i).toString();
                ListaCotizador = ListaCotizador + " cm x " + listaLargo.get(i).toString();
                ListaCotizador = ListaCotizador + " cm ( " + listaCanastaCotizador.get(i).getPrecioCaracteristica();
                ListaCotizador = ListaCotizador + " ) | ";
            }
            System.out.println(ListaCotizador);

            this.newCotizacionTO.setListaDeCaracteristicas(listaIdCaracteristicas.stream().map(i -> i.toString()).collect(Collectors.joining(", ")));
            System.out.println("Caracteristicas->" + this.newCotizacionTO.getListaDeCaracteristicas());

            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            this.newCotizacionTO.setFechaCotizacion(fechaActual.format(formatoFecha));
            System.out.println("Fecha->" + this.newCotizacionTO.getFechaCotizacion());
            this.newCotizacionTO.setNumeroCotizacion((numeroCotizacion));
            System.out.println("Numero Cotizacion->" + this.newCotizacionTO.getNumeroCotizacion());

            if (id != 0) {
                this.newCotizacionTO.setClienteCotizacion(id);
                System.out.println("Cliente->" + this.newCotizacionTO.getClienteCotizacion());
            } else {
                this.newCotizacionTO.setClienteCotizacion(0);
                System.out.println("Cliente->" + this.newCotizacionTO.getClienteCotizacion());
            }

            this.newCotizacionTO.setListaCaracteristicas(this.listaCanastaCotizador);
            this.newCotizacionTO.setAnchoCotizacion(this.anchoCotizacion);
            System.out.println("Ancho->" + this.newCotizacionTO.getAnchoCotizacion());

            this.newCotizacionTO.setLargoCotizacion(this.largoCotizacion);
            System.out.println("Largo->" + this.newCotizacionTO.getLargoCotizacion());

            ancho = Integer.parseInt(this.newCotizacionTO.getAnchoCotizacion());
            largo = Integer.parseInt(this.newCotizacionTO.getLargoCotizacion());
            multiplicacionMedidas = largo * ancho;

            this.totalCotizacion = suma * multiplicacionMedidas;

            this.newCotizacionTO.setTotalCotizacion(this.totalCotizacion);
            System.out.println("Monto->" + this.newCotizacionTO.getTotalCotizacion());

            this.servicioCotizacion.insertarCotizacion(this.newCotizacionTO);
            System.out.println("Se cotizo y se creo la nueva cotizacion.");

            this.mostrarCotizacionNueva = this.servicioCotizacion.cotizacionNuevaExistente(this.newCotizacionTO.getFechaCotizacion());

        } catch (Exception e) {
            System.out.println("Quizas la cotizacion se encuentra nula?");
            System.out.println("Error agregando cotizacion! " + e.getMessage());
        }

    }

    public void SeleccionadorUnica(CaracteristicaTO caracteristicaSeleccionada) {
        System.out.println("Largo: "+this.getLargo());
        System.out.println("Ancho: "+this.getAncho());
        System.out.println("Caracteristica "+caracteristicaSeleccionada);
        
        try {
            int test;
            CaracteristicaTO auxiliar;

            if (this.listaCanastaCotizador.isEmpty() || this.listaAncho.isEmpty() || this.listaLargo.isEmpty()) {
                this.listaCanastaCotizador.add(caracteristicaSeleccionada);
                System.out.println("Agregando-> " + caracteristicaSeleccionada + "/" + caracteristicaSeleccionada.getIdCategoriaCaracteristica() + "/" + caracteristicaSeleccionada.getIdCaracteristica());
            } else {
                test = 0;
                auxiliar = caracteristicaSeleccionada;
                System.out.println("Nuevo->" + auxiliar + "/" + auxiliar.getIdCategoriaCaracteristica() + "/" + auxiliar.getIdCaracteristica());
                this.listaCanastaCotizador.add(auxiliar);
                do {
                    if (this.listaCanastaCotizador.get(test).getIdCategoriaCaracteristica() == auxiliar.getIdCategoriaCaracteristica()
                            && this.listaCanastaCotizador.get(test).getIdCaracteristica() != auxiliar.getIdCaracteristica()) {

                        System.out.println("Remover Anterior->" + this.listaCanastaCotizador.get(test) + "/" + this.listaCanastaCotizador.get(test).getIdCategoriaCaracteristica() + "/" + this.listaCanastaCotizador.get(test).getIdCaracteristica());
                        this.listaCanastaCotizador.remove(this.listaCanastaCotizador.get(test));
                    }
                    test++;
                } while (test < this.listaCanastaCotizador.size());

                for (CaracteristicaTO i : this.listaCanastaCotizador) {
                    System.out.println("Lista->" + i + "/" + i.getIdCategoriaCaracteristica() + "/" + i.getIdCaracteristica());
                }

            }
        } catch (Exception e) {
            System.out.println("Error seleccionando productos! " + e.getMessage());
        }
    }

    public void SeleccionadorMultiple(CaracteristicaTO caracteristicaSeleccionada) {
        try {
            if (this.listaCanastaCotizador.isEmpty()) {
                this.listaCanastaCotizador.add(caracteristicaSeleccionada);
                System.out.println("Agregando-> " + caracteristicaSeleccionada + "/" + caracteristicaSeleccionada.getIdCategoriaCaracteristica() + "/" + caracteristicaSeleccionada.getIdCaracteristica());
            } else {
                System.out.println("Nuevo-> " + caracteristicaSeleccionada + "/" + caracteristicaSeleccionada.getIdCategoriaCaracteristica() + "/" + caracteristicaSeleccionada.getIdCaracteristica());
                CaracteristicaTO auxiliar = caracteristicaSeleccionada;
                for (CaracteristicaTO i : this.listaCanastaCotizador) {
                    System.out.println("Lista->" + i + "/" + i.getIdCategoriaCaracteristica() + "/" + i.getIdCaracteristica());
                    if (i.getIdCaracteristica() == auxiliar.getIdCaracteristica()) {
                        CaracteristicaTO a = i;
                        System.out.println("Remover->" + a + "/" + a.getIdCategoriaCaracteristica() + "/" + a.getIdCaracteristica());
                        this.listaCanastaCotizador.remove(a);
                    }
                }
            }
            this.listaCanastaCotizador.add(caracteristicaSeleccionada);

        } catch (Exception e) {
            System.out.println("Error seleccionando productos! " + e.getLocalizedMessage() + " / " + e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////Servicios
    public ServicioCategoria getServicioCategoria() {
        return servicioCategoria;
    }

    public void setServicioCategoria(ServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    public ServicioCaracteristica getServicioCaracteristica() {
        return servicioCaracteristica;
    }

    public void setServicioCaracteristica(ServicioCaracteristica servicioCaracteristica) {
        this.servicioCaracteristica = servicioCaracteristica;
    }

    public ServicioCotizacion getServicioCotizacion() {
        return servicioCotizacion;
    }

    public void setServicioCotizacion(ServicioCotizacion servicioCotizacion) {
        this.servicioCotizacion = servicioCotizacion;
    }

    ////////////////////////////////////////////////////////////////////////////ModeloTO
    public CotizacionTO getNewCotizacionTO() {
        return newCotizacionTO;
    }

    public CaracteristicaTO getCaracteristicasSeleccionada() {
        return caracteristicasSeleccionada;
    }

    public void setCaracteristicasSeleccionada(CaracteristicaTO caracteristicasSeleccionada) {
        this.caracteristicasSeleccionada = caracteristicasSeleccionada;
    }

    public void setNewCotizacionTO(CotizacionTO newCotizacionTO) {
        this.newCotizacionTO = newCotizacionTO;
    }

    public CotizacionTO getCotizacionTO() {
        return cotizacionTO;
    }

    public CotizacionTO getMostrarCotizacionNueva() {
        return mostrarCotizacionNueva;
    }

    public void setMostrarCotizacionNueva(CotizacionTO mostrarCotizacionNueva) {
        this.mostrarCotizacionNueva = mostrarCotizacionNueva;
    }

    public CategoriaTO getCategoriaTO() {
        return categoriaTO;
    }

    public void setCategoriaTO(CategoriaTO categoriaTO) {
        this.categoriaTO = categoriaTO;
    }

    public CaracteristicaTO getCaracteristicaTO() {
        return caracteristicaTO;
    }

    public void setCaracteristicaTO(CaracteristicaTO caracteristicaTO) {
        this.caracteristicaTO = caracteristicaTO;
    }

    public UsuarioTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    ////////////////////////////////////////////////////////////////////////////Listas
    public List<CaracteristicaTO> getListaCanastaCotizador() {
        return listaCanastaCotizador;
    }

    public void setListaCanastaCotizador(List<CaracteristicaTO> listaCanastaCotizador) {
        this.listaCanastaCotizador = listaCanastaCotizador;
    }

    public List<CotizacionTO> getListaCotizacion() {
        return listaCotizacion;
    }

    public void setListaCotizacion(List<CotizacionTO> listaCotizacion) {
        this.listaCotizacion = listaCotizacion;
    }

    public List<CaracteristicaTO> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public void setListaCaracteristicas(List<CaracteristicaTO> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
    }

    public List<CaracteristicaTO> getListaCaracteristicasCotizacion() {
        return listaCaracteristicasCotizacion;
    }

    public void setListaCaracteristicasCotizacion(List<CaracteristicaTO> listaCaracteristicasCotizacion) {
        this.listaCaracteristicasCotizacion = listaCaracteristicasCotizacion;
    }

    public List<CaracteristicaTO> getListaCaracteristicasParaCotizador() {
        return listaCaracteristicasParaCotizador;
    }

    public void setListaCaracteristicasParaCotizador(List<CaracteristicaTO> listaCaracteristicasParaCotizador) {
        this.listaCaracteristicasParaCotizador = listaCaracteristicasParaCotizador;
    }

    public List<CategoriaTO> getListaCategoriaParaCotizarCliente() {
        return listaCategoriaParaCotizarCliente;
    }

    public void setListaCategoriaParaCotizarCliente(List<CategoriaTO> listaCategoriaParaCotizarCliente) {
        this.listaCategoriaParaCotizarCliente = listaCategoriaParaCotizarCliente;
    }

    public List<CategoriaTO> getListaCategoriaParaCotizarAdmin() {
        return listaCategoriaParaCotizarAdmin;
    }

    public void setListaCategoriaParaCotizarAdmin(List<CategoriaTO> listaCategoriaParaCotizarAdmin) {
        this.listaCategoriaParaCotizarAdmin = listaCategoriaParaCotizarAdmin;
    }

    ////////////////////////////////////////////////////////////////////////////Cotizacion
    public int getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(int numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public String getListaDeCaracteristicas() {
        return listaDeCaracteristicas;
    }

    public void setListaDeCaracteristicas(String listaDeCaracteristicas) {
        this.listaDeCaracteristicas = listaDeCaracteristicas;
    }

    public List<Integer> getListaIdCaracteristicas() {
        return listaIdCaracteristicas;
    }

    public void setListaIdCaracteristicas(List<Integer> listaIdCaracteristicas) {
        this.listaIdCaracteristicas = listaIdCaracteristicas;
    }

    public List<String> getListaDeCaracteristica() {
        return listaDeCaracteristica;
    }

    public void setListaDeCaracteristica(List<String> listaDeCaracteristica) {
        this.listaDeCaracteristica = listaDeCaracteristica;
    }

    public String getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(String fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public int getClienteCotizacion() {
        return clienteCotizacion;
    }

    public void setClienteCotizacion(int clienteCotizacion) {
        this.clienteCotizacion = clienteCotizacion;
    }

    public String getAnchoCotizacion() {
        return anchoCotizacion;
    }

    public void setAnchoCotizacion(String anchoCotizacion) {
        this.anchoCotizacion = anchoCotizacion;
    }

    public String getLargoCotizacion() {
        return largoCotizacion;
    }

    public void setLargoCotizacion(String largoCotizacion) {
        this.largoCotizacion = largoCotizacion;
    }

    public double getTotalCotizacion() {
        return totalCotizacion;
    }

    public void setTotalCotizacion(double totalCotizacion) {
        this.totalCotizacion = totalCotizacion;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public List<Double> getListaAncho() {
        return listaAncho;
    }

    public void setListaAncho(List<Double> listaAncho) {
        this.listaAncho = listaAncho;
    }

    public List<Double> getListaLargo() {
        return listaLargo;
    }

    public void setListaLargo(List<Double> listaLargo) {
        this.listaLargo = listaLargo;
    }

    ////////////////////////////////////////////////////////////////////////////Categorias
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public String getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(String estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }

    public String getSeleccionCategoria() {
        return seleccionCategoria;
    }

    public void setSeleccionCategoria(String seleccionCategoria) {
        this.seleccionCategoria = seleccionCategoria;
    }

    public String getVisibilidadCategoria() {
        return visibilidadCategoria;
    }

    public void setVisibilidadCategoria(String visibilidadCategoria) {
        this.visibilidadCategoria = visibilidadCategoria;
    }

    ////////////////////////////////////////////////////////////////////////////Caracteristicas
    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public int getIdCategoriaCaracteristica() {
        return idCategoriaCaracteristica;
    }

    public void setIdCategoriaCaracteristica(int idCategoriaCaracteristica) {
        this.idCategoriaCaracteristica = idCategoriaCaracteristica;
    }

    public String getImagenCaracteristica() {
        return imagenCaracteristica;
    }

    public void setImagenCaracteristica(String imagenCaracteristica) {
        this.imagenCaracteristica = imagenCaracteristica;
    }

    public String getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    public void setNombreCaracteristica(String nombreCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public String getDescripcionCaracteristica() {
        return descripcionCaracteristica;
    }

    public void setDescripcionCaracteristica(String descripcionCaracteristica) {
        this.descripcionCaracteristica = descripcionCaracteristica;
    }

    public String getEstadoCaracteristica() {
        return estadoCaracteristica;
    }

    public void setEstadoCaracteristica(String estadoCaracteristica) {
        this.estadoCaracteristica = estadoCaracteristica;
    }

    public double getPrecioCaracteristica() {
        return precioCaracteristica;
    }

    public void setPrecioCaracteristica(double precioCaracteristica) {
        this.precioCaracteristica = precioCaracteristica;
    }

    public String getColorCaracteristica() {
        return colorCaracteristica;
    }

    public void setColorCaracteristica(String colorCaracteristica) {
        this.colorCaracteristica = colorCaracteristica;
    }

    public int getPrioridadCaracteristica() {
        return prioridadCaracteristica;
    }

    public void setPrioridadCaracteristica(int prioridadCaracteristica) {
        this.prioridadCaracteristica = prioridadCaracteristica;
    }

}
