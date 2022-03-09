package controller;
//Controller Categoria /Caracteristicas

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import model.CaracteristicaTO;
import model.CategoriaTO;
import servicio.ServicioCaracteristica;
import servicio.ServicioCategoria;

@ManagedBean(name = "userController")

public class OtherController implements Serializable {

    private ServicioCategoria servicioCategoria = new ServicioCategoria();
    private ServicioCaracteristica servicioCaracteristica = new ServicioCaracteristica();

    private CategoriaTO categoriaTO;
    private CaracteristicaTO caracteristicaTO;

    private CategoriaTO newCategoria;
    private CaracteristicaTO newCaracteristica;

    List<CategoriaTO> listaCategorias = new ArrayList<>();
    List<CaracteristicaTO> listaCaracteristicas = new ArrayList<>();

    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private int idCaracteristica;
    private String nombreCaracteristica;
    private String descripcionCaracteristica;
    private int cantidadCaracteristica;
    private int prioridadCaracteristica;
    private double precioCaracteristica;

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

    public OtherController() {
    }

    @PostConstruct
    public void cargarCategoria() {
        try {
            this.listaCategorias = servicioCategoria.listaCategoriasBD();
        } catch (Exception e) {
            System.out.println(e);
        }
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

    public List<CategoriaTO> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaTO> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public List<CaracteristicaTO> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public void setListaCaracteristicas(List<CaracteristicaTO> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
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

    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
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

    public int getCantidadCaracteristica() {
        return cantidadCaracteristica;
    }

    public void setCantidadCaracteristica(int cantidadCaracteristica) {
        this.cantidadCaracteristica = cantidadCaracteristica;
    }

    public int getPrioridadCaracteristica() {
        return prioridadCaracteristica;
    }

    public void setPrioridadCaracteristica(int prioridadCaracteristica) {
        this.prioridadCaracteristica = prioridadCaracteristica;
    }

    public double getPrecioCaracteristica() {
        return precioCaracteristica;
    }

    public void setPrecioCaracteristica(double precioCaracteristica) {
        this.precioCaracteristica = precioCaracteristica;
    }

    public CategoriaTO getNewCategoria() {
        return newCategoria;
    }

    public void setNewCategoria(CategoriaTO newCategoria) {
        this.newCategoria = newCategoria;
    }

    public CaracteristicaTO getNewcCaracteristica() {
        return newCaracteristica;
    }

    public void setNewCaracteristica(CaracteristicaTO newcCaracteristica) {
        this.newCaracteristica = newcCaracteristica;
    }

    public void openNewCategoria() {
        this.newCategoria = new CategoriaTO();
    }

    public void openNewCaracteristica() {
        this.newCaracteristica = new CaracteristicaTO();
    }

    public void agregarCategoriaTO() {
        this.servicioCategoria.insertarCategoria(newCategoria);
        this.cargarCategoria();
    }

    public void actualizarCategoriaTO() {
        this.servicioCategoria.actualizarCategoria(newCategoria);
        this.cargarCategoria();

    }

    public void eliminarCategoriaTO() {
        this.servicioCategoria.eliminarCategoria(newCategoria);
        this.cargarCategoria();

    }

    public void agregarCaracteristicaTO() {
        this.servicioCaracteristica.insertarCaracteristica(newCaracteristica);
        cargarCaracteristicaTO();
    }

    public void actualizarCaracteristicaTO() {
        this.servicioCaracteristica.actualizarCaracteristica(newCaracteristica);
        cargarCaracteristicaTO();
    }

    public void eliminarCaracteristicaTO() {
        this.servicioCaracteristica.eliminarCaracteristica(newCaracteristica);
        cargarCaracteristicaTO();
    }

    public void cargarCaracteristicaTO() {
        this.listaCaracteristicas = this.servicioCaracteristica.listaCaracteristicasBD();

    }

}
