package model;

import java.io.Serializable;

public class CategoriaTO implements Serializable {

    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private String estadoCategoria;
    private String seleccionCategoria;
    private String visibilidadCategoria;
    private String medidasCategoria;

    public CategoriaTO(int idCategoria, String nombreCategoria, String descripcionCategoria, String estadoCategoria, String seleccionCategoria, String visibilidadCategoria, String medidasCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.estadoCategoria = estadoCategoria;
        this.seleccionCategoria = seleccionCategoria;
        this.visibilidadCategoria = visibilidadCategoria;
        this.medidasCategoria = medidasCategoria;
    }

    public CategoriaTO() {
    }

    public CategoriaTO(String nombreCategoria, String descripcionCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
    }

    public String getMedidasCategoria() {
        return medidasCategoria;
    }

    public void setMedidasCategoria(String medidasCategoria) {
        this.medidasCategoria = medidasCategoria;
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

    public String getVisibilidadCategoria() {
        return visibilidadCategoria;
    }

    public void setVisibilidadCategoria(String visibilidadCategoria) {
        this.visibilidadCategoria = visibilidadCategoria;
    }

}
