package model;

import java.io.Serializable;

public class CaracteristicaTO implements Serializable {

    private int idCaracteristica;
    private int idCategoriaCaracteristica;
    private String imagenCaracteristica = "porDefecto.png";
    private String nombreCaracteristica;
    private String descripcionCaracteristica;
    private String estadoCaracteristica;
    private double precioCaracteristica;
    private String colorCaracteristica;
    private int prioridadCaracteristica;


    public CaracteristicaTO() {
    }
    
    public CaracteristicaTO(int idCaracteristica, int idCategoriaCaracteristica, String imagenCaracteristica, String nombreCaracteristica, String descripcionCaracteristica, String estadoCaracteristica, double precioCaracteristica, String colorCaracteristica, int prioridadCaracteristica) {
        this.idCaracteristica = idCaracteristica;
        this.idCategoriaCaracteristica = idCategoriaCaracteristica;
        this.imagenCaracteristica = imagenCaracteristica;
        this.nombreCaracteristica = nombreCaracteristica;
        this.descripcionCaracteristica = descripcionCaracteristica;
        this.estadoCaracteristica = estadoCaracteristica;
        this.precioCaracteristica = precioCaracteristica;
        this.colorCaracteristica = colorCaracteristica;
        this.prioridadCaracteristica = prioridadCaracteristica;
     
    }

    public CaracteristicaTO(int idCategoriaCaracteristica, String imagenCaracteristica, String nombreCaracteristica, String descripcionCaracteristica, String estadoCaracteristica, double precioCaracteristica, String colorCaracteristica, int prioridadCaracteristica) {
        this.idCategoriaCaracteristica = idCategoriaCaracteristica;
        this.imagenCaracteristica = imagenCaracteristica;
        this.nombreCaracteristica = nombreCaracteristica;
        this.descripcionCaracteristica = descripcionCaracteristica;
        this.estadoCaracteristica = estadoCaracteristica;
        this.precioCaracteristica = precioCaracteristica;
        this.colorCaracteristica = colorCaracteristica;
        this.prioridadCaracteristica = prioridadCaracteristica;
      
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



    
}
