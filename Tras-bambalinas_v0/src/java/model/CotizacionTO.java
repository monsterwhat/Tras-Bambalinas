package model;

import java.sql.Date;
import java.util.List;


public class CotizacionTO {

    private int numeroCotizacion;
    private String listaDeCaracteristicas;
    private Date fechaCotizacion;
    private int clienteCotizacion;
    private String anchoCotizacion;
    private String largoCotizacion;
    
    List<CaracteristicaTO> listaCaracteristicas = null;

//    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, Date fechaCotizacion, int clienteCotizacion) {
//        this.numeroCotizacion = numeroCotizacion;
//        this.listaDeCaracteristicas = listaDeCaracteristicas;
//        this.fechaCotizacion = fechaCotizacion;
//        this.clienteCotizacion = clienteCotizacion;
//    }

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, Date fechaCotizacion, int clienteCotizacion, String anchoCotizacion, String largoCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.anchoCotizacion = anchoCotizacion;
        this.largoCotizacion = largoCotizacion;
    }
    
    

    public CotizacionTO(int numeroCotizacion, String listaDeCaracteristicas, Date fechaCotizacion, int clienteCotizacion, List<CaracteristicaTO> listaCaracteristica,String anchoCotizacion, String largoCotizacion){
        this.numeroCotizacion = numeroCotizacion;
        this.listaDeCaracteristicas = listaDeCaracteristicas;
        this.fechaCotizacion = fechaCotizacion;
        this.clienteCotizacion = clienteCotizacion;
        this.listaCaracteristicas = listaCaracteristica;
        this.anchoCotizacion = anchoCotizacion;
        this.largoCotizacion = largoCotizacion;

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
