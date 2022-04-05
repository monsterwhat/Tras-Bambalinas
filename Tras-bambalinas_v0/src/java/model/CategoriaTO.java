
package model;

import java.io.Serializable;


public class CategoriaTO implements Serializable {
    
    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private String estadoCategoria;
    private String seleccionCategoria;


    public CategoriaTO(int idCategoria, String nombreCategoria, String descripcionCategoria, String estadoCategoria, String seleccionCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.estadoCategoria=estadoCategoria;
        this.seleccionCategoria=seleccionCategoria;
    }
    public CategoriaTO(){
    }

    public CategoriaTO(String nombreCategoria, String descripcionCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
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
    
    
    
}
