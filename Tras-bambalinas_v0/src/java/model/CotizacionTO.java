package model;

import java.util.List;


public class CotizacionTO {

    int numeroCotizacion;
    String listaDeCaracteristicas;
    String fechaCotizacion;
    String clienteCotizacion;
    List<CaracteristicaTO> listaCaracteristicas = null;

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, String clienteCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
    }

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, String fechaCotizacion, String clienteCotizacion, List<CaracteristicaTO> listaCaracteristica){
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.listaCaracteristicas = listaCaracteristica;
    }
    
    public List<CaracteristicaTO> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public void setListaCaracteristicas(List<CaracteristicaTO> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
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

    public String getClienteCotizacion() {
        return clienteCotizacion;
    }

    public void setClienteCotizacion(String clienteCotizacion) {
        this.clienteCotizacion = clienteCotizacion;
    }
    
    
}
