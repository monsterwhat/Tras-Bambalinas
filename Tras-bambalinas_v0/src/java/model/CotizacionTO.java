package model;

import java.util.List;


public class CotizacionTO {
    
    
    private int numeroCotizacion;
    private String listaDeCaracteristicas;
    private String fechaCotizacion;
    private int clienteCotizacion;
    private String anchoCotizacion;
    private String largoCotizacion;
    private double totalCotizacion;
    
    List<CaracteristicaTO> listaCaracteristicas = null;
    

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, int clienteCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
    }

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, int clienteCotizacion, String anchoCotizacion, String largoCotizacion, Double totalCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.anchoCotizacion = anchoCotizacion;
        this.largoCotizacion = largoCotizacion;
        this.totalCotizacion = totalCotizacion;
    }
    
    

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, int clienteCotizacion, List<CaracteristicaTO> listaCaracteristicas, String anchoCotizacion, String largoCotizacion, Double totalCotizacion){
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.listaCaracteristicas = listaCaracteristicas;
        this.anchoCotizacion = anchoCotizacion;
        this.largoCotizacion = largoCotizacion;
        this.totalCotizacion = totalCotizacion;
    }

    public CotizacionTO() {
    }
    
    
    public List<CaracteristicaTO> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public void setListaCaracteristicas(List<CaracteristicaTO> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
    }

    public double getTotalCotizacion() {
        return totalCotizacion;
    }

    public void setTotalCotizacion(double totalCotizacion) {
        this.totalCotizacion = totalCotizacion;
    }

    public int getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(int numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public String getListaDeCaracteristicas() {
        return listaDeCaracteristicas;
    }

    public void setListaDeCaracteristicas(String listaDeCaracteristicas) {
        this.listaDeCaracteristicas = listaDeCaracteristicas;
    }

    public String getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(String fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public int getClienteCotizacion() {
        return clienteCotizacion;
    }

    public void setClienteCotizacion(int clienteCotizacion) {
        this.clienteCotizacion = clienteCotizacion;
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

   

    
    
}
