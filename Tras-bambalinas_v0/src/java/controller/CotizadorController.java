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

    List<Integer> listaIdCaracteristicas = new ArrayList<>();

    List<CaracteristicaTO> listaCaracteristicasParaCotizador = new ArrayList<>();
    List<CaracteristicaTO> listaCanastaCotizador = new ArrayList<>();

    List<CaracteristicaTO> listaCaracteristicasSeleccionada = new ArrayList<>();

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
    String listaDeCaracteristicas;
    Date fechaCotizacion;
    int clienteCotizacion;

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

    public void abrirEIngresarNewCotizacion() {
        this.newCotizacionTO = new CotizacionTO();
        try {

            if (this.listaCanastaCotizador.isEmpty()) {
                System.out.println("Error esta vacia");
                addMessage(FacesMessage.SEVERITY_ERROR, "Error de cotizacion!", "No hay items seleccionados!");
            }
            System.out.println("Se mando a cotizar");

            this.listaCanastaCotizador.forEach((caracTO) -> {
                listaIdCaracteristicas.add(caracTO.getIdCaracteristica());
            });
            newCotizacionTO.setListaDeCaracteristicas(listaIdCaracteristicas.stream().map(i -> i.toString()).collect(Collectors.joining(", ")));
            newCotizacionTO.setFechaCotizacion(Date.valueOf(LocalDate.now()));

            if (newCotizacionTO.getClienteCotizacion() != 0) {
                newCotizacionTO.setClienteCotizacion(14);
            }
            newCotizacionTO.setClienteCotizacion(0);

            this.servicioCotizacion.insertarCotizacion(newCotizacionTO);
            System.out.println("Se cotizo y se creo la nueva cotizacion.");

        } catch (Exception e) {
            System.out.println("Quizas la cotizacion se encuentra nula?");
            System.out.println("Error agregando cotizacion! " + e);
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
            servicioCotizacion.Cotizar(this.listaCanastaCotizador, idUser);
            this.listaCanastaCotizador.forEach((caracTO) -> {
                listaIdCaracteristicas.add(caracTO.getIdCaracteristica());
            });
            listaDeCaracteristicas = listaIdCaracteristicas.stream()
                    .map(i -> i.toString())
                    .collect(Collectors.joining(", "));

            this.fechaCotizacion = Date.valueOf(LocalDate.now());

            newCotizacionTO = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, idUser);
            System.out.println("Se cotizo y se creo la nueva cotizacion.");
            return true;
        } catch (Exception e) {
            System.out.println("Error al tratar de cotizar! " + e.getMessage());
        }
        return false;
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
            int test = 0;
            CaracteristicaTO auxiliar;
            List<CaracteristicaTO> listaAuxiliar = new ArrayList<>();
            if (this.listaCanastaCotizador.isEmpty()) {
                this.listaCanastaCotizador.add(caracteristicaSeleccionada);
                System.out.println("Agregando-> " + caracteristicaSeleccionada + "/" + caracteristicaSeleccionada.getIdCategoriaCaracteristica() + "/" + caracteristicaSeleccionada.getIdCaracteristica());
            } else {
                auxiliar = caracteristicaSeleccionada;
                System.out.println("Nuevo->" + auxiliar + "/" + auxiliar.getIdCategoriaCaracteristica() + "/" + auxiliar.getIdCaracteristica());
                this.listaCanastaCotizador.add(auxiliar);

                do {
                    if (this.listaCanastaCotizador.get(test).getIdCategoriaCaracteristica() == auxiliar.getIdCategoriaCaracteristica()
                            && this.listaCanastaCotizador.get(test).getIdCaracteristica() != auxiliar.getIdCaracteristica()) {

                        System.out.println("Remover->" + this.listaCanastaCotizador.get(test) + "/" + this.listaCanastaCotizador.get(test).getIdCategoriaCaracteristica() + "/" + this.listaCanastaCotizador.get(test).getIdCaracteristica());
                        this.listaCanastaCotizador.remove(this.listaCanastaCotizador.get(test));
                    }
                    test = test + 1;
                } while (test > this.listaCanastaCotizador.size());



            }
        } catch (Exception e) {
            System.out.println("Error seleccionando productos! " + e.getMessage());
        }
    }
//onsuccess="PF('accordionPanel').unselect(index)"

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
                this.listaCanastaCotizador.add(caracteristicaSeleccionada);
            }
        } catch (Exception e) {
            System.out.println("Error seleccionando productos! " + e.getLocalizedMessage() + " / " + e.getMessage());
        }
    }

    public void openNewCotizacion() {
        this.newCotizacionTO = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, clienteCotizacion);
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

    public String getListaDeCaracteristicas() {
        return listaDeCaracteristicas;
    }

    public void setListaDeCaracteristicas(String listaDeCaracteristicas) {
        this.listaDeCaracteristicas = listaDeCaracteristicas;
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

//    public List<CaracteristicaTO> getListaCaracteristicasSeleccionada() {
//        return listaCaracteristicasSeleccionada;
//    }
//
//    public void setListaCaracteristicasSeleccionada(List<CaracteristicaTO> listaCaracteristicasSeleccionada) {
//        this.listaCaracteristicasSeleccionada = listaCaracteristicasSeleccionada;
//    }
    public boolean esMultiple(CategoriaTO categoriaTO) {
        if (categoriaTO.getSeleccionCategoria().equals("Múltiple")) {
            return true;
        }
        return false;
    }

//    public void cargarCaracteristicasSelecionadas(int idCaracteristica) {
//        try {
//            if (this.listaCaracteristicasSeleccionada.contains(idCaracteristica)) {
//                this.listaCaracteristicasSeleccionada.remove(idCaracteristica);
//            } else {
//                this.listaCaracteristicasSeleccionada.add(this.servicioCaracteristica.cargarCaracteristicaSeleccionada(idCaracteristica));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}
