package model;

import java.sql.Date;
import java.util.List;


public class CotizacionTO {

    int numeroCotizacion;
    String listaDeCaracteristicas;
    Date fechaCotizacion;
    int clienteCotizacion;
    List<CaracteristicaTO> listaCaracteristicas = null;

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, Date fechaCotizacion, int clienteCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
    }

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, Date fechaCotizacion, int clienteCotizacion, List<CaracteristicaTO> listaCaracteristica){
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.listaCaracteristicas = listaCaracteristica;
    }

    public CotizacionTO() {
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

    public Date getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(Date fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public int getClienteCotizacion() {
        return clienteCotizacion;
    }

    public void setClienteCotizacion(int clienteCotizacion) {
        this.clienteCotizacion = clienteCotizacion;
    }
    
    
}
