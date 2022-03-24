package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.CaracteristicaTO;
import model.CategoriaTO;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import servicio.ServicioCaracteristica;
import servicio.ServicioCategoria;

@ManagedBean(name = "OtherController")
@ViewScoped
public class OtherController implements Serializable {

    private ServicioCategoria servicioCategoria = new ServicioCategoria();
    private ServicioCaracteristica servicioCaracteristica = new ServicioCaracteristica();

    private CategoriaTO categoriaTO, newCategoria;
    private CaracteristicaTO caracteristicaTO, newCaracteristica;
    
    HashMap<Integer,String> mapaCaracterisita, mapaCategoria = new HashMap<Integer, String>();
    List<CategoriaTO> listaCategorias = new ArrayList<CategoriaTO>();
    List<CaracteristicaTO> listaCaracteristicas = new ArrayList<CaracteristicaTO>();
    
    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private String estadoCategoria;
    private String seleccionCategoria;

    
    ///////////////////////////////////////////////////////////////////////////
    private int idCaracteristica;
    private int idCategoriaCaracteristica;
    private String imagenCaracteristica;
    private String nombreCaracteristica;
    private String descripcionCaracteristica;
    private String estadoCaracteristica;
    private double precioCaracteristica;
    private String colorCaracteristica;
    private int prioridadCaracteristica;
    
   

    ////////////////////////////////////////////////////////////////////////////

    public OtherController() {
    }

    @PostConstruct
    public void cargar() {
        try {
            this.listaCategorias = servicioCategoria.listaCategoriasBD();
            this.listaCaracteristicas = servicioCaracteristica.listaCaracteristicasBD();
            this.mapaCaracterisita = servicioCaracteristica.cargarCaracterisitca();
            this.mapaCategoria = servicioCategoria.cargarCategoria();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

     public HashMap<Integer, String> getMapaCaracterisita() {
        return mapaCaracterisita;
    }

    public void setMapaCaracterisita(HashMap<Integer, String> mapaCaracterisita) {
        this.mapaCaracterisita = mapaCaracterisita;
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
    
    /////////////////////////////////////////////////////////////////////////////

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

    public void openNewCategoria() {
        this.newCategoria = new CategoriaTO();
    }

    public void openNewCaracteristica() {
            this.newCaracteristica = new CaracteristicaTO();
    }

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
            cargar();
        } catch (Exception e) {
            System.out.println("Quizas la categoria se encuentra nula?");
            System.out.println("Error actualizando caracteristica! " + e);
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

   
    public void eliminarCategoriaTOEstado() {
        try {
            this.servicioCategoria.eliminarPorEstadoCategoria(newCategoria);
            this.cargar();
        } catch (Exception e) {
            System.out.println("Quizas la categoria se encuentra nula?");
            System.out.println("Error eliminando caegoria! " + e);
        }
    }


    public void agregarCaracteristicaTO() {
        try {
            this.servicioCaracteristica.insertarCaracteristica(newCaracteristica);
            cargar();
        } catch (Exception e) {
            System.out.println("Quizas la categoria se encuentra nula?");
            System.out.println("Error actualizando caracteristica! " + e);
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

    public void eliminarCaracteristicaTOPorEstado() {
        try {
            this.servicioCaracteristica.eliminarPorEstadoCaracteristica(newCaracteristica);
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
    
    public void seleccionarImagenASubir(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        try {
            InputStream in = event.getFile().getInputStream();
            OutputStream out = new FileOutputStream(new File("C:\\Imagenes\\" + uploadedFile.getFileName()));
            this.newCaracteristica.setImagenCaracteristica(uploadedFile.getFileName());
            
            System.out.println(this.newCaracteristica.getImagenCaracteristica());
            
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void seleccionarImagenPorDefecto(){
        this.newCaracteristica.setImagenCaracteristica("porDefecto.png");
    }

    private static class Updated {

        public Updated() {
        }
    }
    
    

}
