package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.CaracteristicaTO;
import model.CategoriaTO;
import servicio.ServicioCaracteristica;
import servicio.ServicioCategoria;

@ManagedBean(name = "OtherController")
@ViewScoped
public class OtherController implements Serializable {

    private ServicioCategoria servicioCategoria = new ServicioCategoria();
    private ServicioCaracteristica servicioCaracteristica = new ServicioCaracteristica();

    private CategoriaTO categoriaTO, newCategoria;
    private CaracteristicaTO caracteristicaTO, newCaracteristica;

    List<CategoriaTO> listaCategorias = new ArrayList<CategoriaTO>();
    List<CaracteristicaTO> listaCaracteristicas = new ArrayList<CaracteristicaTO>();

    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    
    private int idCaracteristica;
    private String nombreCaracteristica;
    private String descripcionCaracteristica;
    private int cantidadCaracteristica;
    private int prioridadCaracteristica;
    private double precioCaracteristica;
    private int idCategoriaCaracteristica;

    ////////////////////////////////////////////////////////////////////////////
    
    public OtherController() {
    }
    
    @PostConstruct
    public void cargar() {
        try {
            this.listaCategorias = servicioCategoria.listaCategoriasBD();
            this.listaCaracteristicas = servicioCaracteristica.listaCaracteristicasBD();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
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

    /////////////////////////////////////////////////////////////////////////////
    
    
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

    public int getIdCategoriaCaracteristica() {
        return idCategoriaCaracteristica;
    }

    public void setIdCategoriaCaracteristica(int idCategoriaCaracteristica) {
        this.idCategoriaCaracteristica = idCategoriaCaracteristica;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    

    public CategoriaTO getNewCategoria() {
        return newCategoria;
    }

    public void setNewCategoria(CategoriaTO newCategoria) {
        this.newCategoria = newCategoria;
    }

    public CaracteristicaTO getNewCaracteristica() {
        return newCaracteristica;
    }

    public void setNewCaracteristica(CaracteristicaTO newCaracteristica) {
        this.newCaracteristica = newCaracteristica;
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public void openNewCategoria() {
        this.newCategoria = new CategoriaTO();
    }

    public void openNewCaracteristica() {
        this.newCaracteristica = new CaracteristicaTO();
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public void agregarCategoriaTO() {
        try {
            this.servicioCategoria.insertarCategoria(newCategoria);
            this.cargar();
        } catch (Exception e) {
            System.out.println("Quizas la categoria se encuentra nula?");
            System.out.println("Error agregando la categoria! " + e);
        }
    }

    public void actualizarCategoriaTO() {
        try {
            this.servicioCategoria.actualizarCategoria(newCategoria);
            this.cargar();
        } catch (Exception e) {
            System.out.println("Quizas la categoria se encuentra nula?");
            System.out.println("Error actualizando la categoria! " + e);
        }
    }

    public void eliminarCategoriaTO() {
        try {
            this.servicioCategoria.eliminarCategoria(newCategoria);
            this.cargar();
        } catch (Exception e) {
            System.out.println("Quizas la categoria se encuentra nula?");
            System.out.println("Error eliminando caegoria! " + e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    
    public void agregarCaracteristicaTO() {
        
        System.out.println("A: "+this.caracteristicaTO.getIdCategoriaCaracteristica());
        System.out.println("A: "+this.idCategoriaCaracteristica);
        try {
            
            if(newCaracteristica==null){
                System.out.println("A: "+this.caracteristicaTO.getIdCategoriaCaracteristica());
                System.out.println("A: "+this.idCategoriaCaracteristica);
                System.out.println("La caracterisitica se encuentra nula");
                return;
            }
            System.out.println("A: "+this.caracteristicaTO.getIdCategoriaCaracteristica());
            System.out.println("A: "+this.idCategoriaCaracteristica);
            this.servicioCaracteristica.insertarCaracteristica(newCaracteristica);
            cargar();
        } catch (Exception e) {
            System.out.println("A: "+this.caracteristicaTO.getIdCategoriaCaracteristica());
            System.out.println("A: "+this.idCategoriaCaracteristica);
            System.out.println("Error agregando caracteristica! " + e);
        }
    }

    public void actualizarCaracteristicaTO() {
        try {
            this.servicioCaracteristica.actualizarCaracteristica(newCaracteristica);
            cargar();
        } catch (Exception e) {
            System.out.println("Quizas la categoria se encuentra nula?");
            System.out.println("Error actualizando caracteristica! " + e);
        }
    }

    public void eliminarCaracteristicaTO() {
        try {
            this.servicioCaracteristica.eliminarCaracteristica(newCaracteristica);
            cargar();
        } catch (Exception e) {
            System.out.println("Quizas la categoria se encuentra nula?");
            System.out.println("Error eliminanddo caracteristica! " + e);
        }
    }

    public void cargarCaracteristicaTO() {
        try {
            this.listaCaracteristicas = this.servicioCaracteristica.listaCaracteristicasBD();

        } catch (Exception e) {
            System.out.println("Error cargando caracteristicas! " + e);

        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    
}
