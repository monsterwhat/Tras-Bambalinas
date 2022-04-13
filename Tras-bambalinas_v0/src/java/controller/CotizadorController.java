package controller;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.CaracteristicaTO;
import model.CategoriaTO;
import model.CotizacionTO;
import servicio.ServicioCaracteristica;
import servicio.ServicioCategoria;
import servicio.ServicioCotizacion;

@ManagedBean(name = "cotizadorController")
@ViewScoped
public class CotizadorController implements Serializable {

    private ServicioCategoria servicioCategoria = new ServicioCategoria();
    private ServicioCaracteristica servicioCaracteristica = new ServicioCaracteristica();
    private ServicioCotizacion servicioCotizacion = new ServicioCotizacion();

    private CategoriaTO categoriaTO;
    private CaracteristicaTO caracteristicaTO;
    private CotizacionTO cotizacionTO, newCotizacionTO;

    List<String> listaDeCaracteristica = new ArrayList<>();
    List<Integer> listaIdCaracteristicas = new ArrayList<>();

    List<CaracteristicaTO> listaCaracteristicasParaCotizador = new ArrayList<>();
    List<CaracteristicaTO> listaCanastaCotizador = new ArrayList<>();

    List<CategoriaTO> listaCategoriaParaCotizar = new ArrayList<>();
    List<CotizacionTO> listaCotizacion = new ArrayList<>();

    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private String estadoCategoria;
    private String seleccionCategoria;

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
    Date fechaCotizacion;
    int clienteCotizacion;
    String anchoCotizacion;
    String largoCotizacion;
    double totalCotizacion = 0;

    @PostConstruct
    public void cargar() {
        try {
            this.listaCategoriaParaCotizar = servicioCategoria.listaCategoriaPorEstadoBD();
            this.listaCotizacion = servicioCotizacion.listaCotizaciones();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void cargarImagenCaracteristica(int id) {

    }

    public void abrirEIngresarNewCotizacion(int id) {

        double suma = 0;
        double multiplicacionMedidas = 0;
        double ancho = 0;
        double largo = 0;
        openNewCotizacion();
        try {
            if (this.listaCanastaCotizador.isEmpty()) {
                System.out.println("Error esta vacia");
                addMessage(FacesMessage.SEVERITY_ERROR, "Error de cotizacion!", "No hay nada seleccionado!");
            }

            this.listaCanastaCotizador.forEach((caracTO) -> {
                //listaIdCaracteristicas.add(caracTO.getIdCaracteristica());
                listaDeCaracteristica.add(caracTO.getNombreCaracteristica());
            });
            for (CaracteristicaTO caracTO : listaCanastaCotizador) {
                suma = suma + caracTO.getPrecioCaracteristica();
            }
            
            this.newCotizacionTO.setListaDeCaracteristicas(listaDeCaracteristica.stream().map(i -> i.toString()).collect(Collectors.joining(", ")));
            System.out.println("Caracteristicas->" + this.newCotizacionTO.getListaDeCaracteristicas());
            this.newCotizacionTO.setFechaCotizacion(Date.valueOf(LocalDate.now()));
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

        } catch (Exception e) {
            System.out.println("Quizas la cotizacion se encuentra nula?");
            System.out.println("Error agregando cotizacion! " + e);
        }

    }

    public List<CaracteristicaTO> cargarListaCaracteristicas(int id) {
        try {
            this.listaCaracteristicasParaCotizador = this.servicioCaracteristica.listaCaracteristicasPorIdCategoriaYEstado(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return listaCaracteristicasParaCotizador;
    }

    public List<CaracteristicaTO> getListaCanastaCotizador() {
        return listaCanastaCotizador;
    }

    public void setListaCanastaCotizador(CaracteristicaTO caracteristica) {
        SeleccionadorUnica(caracteristica);
    }

    public void SeleccionadorUnica(CaracteristicaTO caracteristicaSeleccionada) {
        try {
            int test;
            CaracteristicaTO auxiliar;

            if (this.listaCanastaCotizador.isEmpty()) {
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

    public void openNewCotizacion() {
        this.newCotizacionTO = new CotizacionTO();
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

    public CotizacionTO getNewCotizacionTO() {
        return newCotizacionTO;
    }

    public void setNewCotizacionTO(CotizacionTO newCotizacionTO) {
        this.newCotizacionTO = newCotizacionTO;
    }

    public CotizacionTO getCotizacionTO() {
        return cotizacionTO;
    }

    public void setCotizacionTO(CotizacionTO cotizacionTO) {
        this.cotizacionTO = cotizacionTO;
    }

    public ServicioCotizacion getServicioCotizacion() {
        return servicioCotizacion;
    }

    public void setServicioCotizacion(ServicioCotizacion servicioCotizacion) {
        this.servicioCotizacion = servicioCotizacion;
    }

    public List<CotizacionTO> getListaCotizacion() {
        return listaCotizacion;
    }

    public void setListaCotizacion(List<CotizacionTO> listaCotizacion) {
        this.listaCotizacion = listaCotizacion;
    }

    public int getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(int numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
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

    public Date getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(Date fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public int getClienteCotizacion() {
        return clienteCotizacion;
    }

    public void setClienteCotizacion(int clienteCotizacion) {
        this.clienteCotizacion = clienteCotizacion;
    }

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

    public List<CaracteristicaTO> getListaCaracteristicasParaCotizador() {
        return listaCaracteristicasParaCotizador;
    }

    public void setListaCaracteristicasParaCotizador(List<CaracteristicaTO> listaCaracteristicasParaCotizador) {
        this.listaCaracteristicasParaCotizador = listaCaracteristicasParaCotizador;
    }

    public List<CategoriaTO> getListaCategoriaParaCotizar() {
        return listaCategoriaParaCotizar;
    }

    public String getListaDeCaracteristicas() {
        return listaDeCaracteristicas;
    }

    public void setListaDeCaracteristicas(String listaDeCaracteristicas) {
        this.listaDeCaracteristicas = listaDeCaracteristicas;
    }

    public void setListaCategoriaParaCotizar(List<CategoriaTO> listaCategoriaParaCotizar) {
        this.listaCategoriaParaCotizar = listaCategoriaParaCotizar;
    }

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

    public boolean esMultiple(CategoriaTO categoriaTO) {
        if (categoriaTO.getSeleccionCategoria().equals("Múltiple")) {
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

    public boolean Cotizar(int idUser) {
//        xhtml(87)=<!--actionListener="#{cotizadorController.Cotizar(userController.idUser)}" onclick="#{cotizadorController.openNewCotizacion()}"--> required="true"
        try {
            if (this.listaCanastaCotizador.isEmpty()) {
                System.out.println("Error esta vacia");
                addMessage(FacesMessage.SEVERITY_ERROR, "Error de cotizacion!", "No hay items seleccionados!");
                return false;
            }
            System.out.println("Se mando a cotizar");
            this.fechaCotizacion = Date.valueOf(LocalDate.now());

            servicioCotizacion.Cotizar(this.listaCanastaCotizador, idUser, this.anchoCotizacion, this.largoCotizacion);
            this.listaCanastaCotizador.forEach((caracTO) -> {
                //listaIdCaracteristicas.add(caracTO.getIdCaracteristica());
                listaDeCaracteristica.add(caracTO.getNombreCaracteristica());

            });
            listaDeCaracteristicas = listaDeCaracteristica.stream()
                    .map(i -> i.toString())
                    .collect(Collectors.joining(", "));

            System.out.println("Se cotizo y se creo la nueva cotizacion.");
            return true;
        } catch (Exception e) {
            System.out.println("Error al tratar de cotizar! " + e.getMessage());
        }
        return false;
    }

}
