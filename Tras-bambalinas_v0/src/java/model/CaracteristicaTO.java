
package model;

import java.io.Serializable;


public class CaracteristicaTO implements Serializable {
    private int idCaracteristica;
    private String nombreCaracteristica;
    private String descripcionCaracteristica;
    private int cantidadCaracteristica;
    private int prioridadCaracteristica;
    private double precioCaracteristica;

    public CaracteristicaTO(int idCaracteristica, String nombreCaracteristica, String descripcionCaracteristica, int cantidadCaracteristica, int prioridadCaracteristica, double precioCaracteristica) {
        this.idCaracteristica = idCaracteristica;
        this.nombreCaracteristica = nombreCaracteristica;
        this.descripcionCaracteristica = descripcionCaracteristica;
        this.cantidadCaracteristica = cantidadCaracteristica;
        this.prioridadCaracteristica = prioridadCaracteristica;
        this.precioCaracteristica = precioCaracteristica;
    }

    public CaracteristicaTO(String nombreCaracteristica, String descripcionCaracteristica, int cantidadCaracteristica, int prioridadCaracteristica, double precioCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
        this.descripcionCaracteristica = descripcionCaracteristica;
        this.cantidadCaracteristica = cantidadCaracteristica;
        this.prioridadCaracteristica = prioridadCaracteristica;
        this.precioCaracteristica = precioCaracteristica;
    }

    public CaracteristicaTO(){
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

  
    
}
