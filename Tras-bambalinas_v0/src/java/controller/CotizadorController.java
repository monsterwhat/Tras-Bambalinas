package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.CaracteristicaTO;
import model.CategoriaTO;
import servicio.ServicioCaracteristica;
import servicio.ServicioCategoria;

@ManagedBean(name = "cotizadorController")
@ViewScoped
public class CotizadorController implements Serializable {

    private ServicioCategoria servicioCategoria = new ServicioCategoria();
    private ServicioCaracteristica servicioCaracteristica = new ServicioCaracteristica();

    private CategoriaTO categoriaTO;
    private CaracteristicaTO caracteristicaTO;

    HashMap<Integer, String> mapaCategoria = new HashMap<Integer, String>();
    List<Integer> listaNombreCategorias = new ArrayList<>();

    List<CaracteristicaTO> listaCaracteristicasParaCotizador = new ArrayList<CaracteristicaTO>();
    List<CategoriaTO> listaCategoriaParaCotizar = new ArrayList<CategoriaTO>();

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

    @PostConstruct
    public void cargar() {
        try {

            this.mapaCategoria = servicioCategoria.cargarCategoria();
//            this.listaCategoriaParaCotizar = servicioCategoria.listaCategoriaPorEstadoBD();
//            cargarListaCategoria();
            this.listaCaracteristicasParaCotizador = servicioCaracteristica.listaCaracteristicasPorIdCategoriaYEstadoBD(mapaCategoria);
          

        } catch (Exception e) {
            System.out.println(e);
        }

    }

//    public void cargarListaCaracteristicas() {
//        try {
//
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }

    ////////////////////////////////////////////////////////////////////////////
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

    ////////////////////////////////////////////////////////////////////////////
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

    ////////////////////////////////////////////////////////////////////////////
    public HashMap<Integer, String> getMapaCategoria() {
        return mapaCategoria;
    }

    public void setMapaCategoria(HashMap<Integer, String> mapaCategoria) {
        this.mapaCategoria = mapaCategoria;
    }

    public List<Integer> getListaNombreCategorias() {
        return listaNombreCategorias;
    }

    public void setListaNombreCategorias(List<Integer> listaNombreCategorias) {
        this.listaNombreCategorias = listaNombreCategorias;
    }

    ////////////////////////////////////////////////////////////////////////////
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

    ////////////////////////////////////////////////////////////////////////////
    public void setSeleccionCategoria(String seleccionCategoria) {
        this.seleccionCategoria = seleccionCategoria;
    }

    ////////////////////////////////////////////////////////////////////////////
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

//    public void cargarListaCategoria(){
//        for(int i : mapaCategoria.keySet()){
//            System.out.println("key: " + i + " value: " + mapaCategoria.get(i));
//            listaNombreCategorias.add(mapaCategoria.);
//        }
//    
//    }
//    
//    public void cargarListaCaracteristicas(){
//         
//        for(int i : mapaCategoria.keySet()){
//            System.out.println("key: " + mapaCategoria.keySet());
//            this.listaCaracteristicasParaCotizador =servicioCaracteristica.listaCaracteristicasPorIdCategoriaYEstadoBD(mapaCategoria.keySet());
//        }
//    
//    }
}
