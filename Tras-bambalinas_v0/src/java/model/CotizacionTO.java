package model;

import java.util.ArrayList;
import java.util.List;

public class CotizacionTO {

    private int numeroCotizacion;
    private String listaDeCaracteristicas;
    private String fechaCotizacion;
    private int clienteCotizacion;
    private double totalCotizacion;
    private String estadoCotizacion;

    List<CaracteristicaTO> listaCaracteristicas = new ArrayList<>();

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, int clienteCotizacion, String estadoCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.estadoCotizacion = estadoCotizacion;
    }

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, int clienteCotizacion, double totalCotizacion, String estadoCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.totalCotizacion = totalCotizacion;
        this.estadoCotizacion = estadoCotizacion;
    }

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, int clienteCotizacion, Double totalCotizacion, String estadoCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.totalCotizacion = totalCotizacion;
        this.estadoCotizacion = estadoCotizacion;
    }

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, int clienteCotizacion, List<CaracteristicaTO> listaCaracteristicas, Double totalCotizacion, String estadoCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.listaCaracteristicas = listaCaracteristicas;
        this.totalCotizacion = totalCotizacion;
        this.estadoCotizacion = estadoCotizacion;
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

    public String getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(String estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }

}
