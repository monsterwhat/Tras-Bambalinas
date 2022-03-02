/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import model.ProductoTO;
import servicio.ServicioProducto;

@ManagedBean(name = "userController")
@ViewScoped

/**
 *
 * @author User
 */
public class ProductController implements Serializable{
    
    
    private ServicioProducto servicioProducto = new ServicioProducto();
    private ProductoTO productoTO = null;
    List<ProductoTO> listaUserBD = new ArrayList<>();
    private ProductoTO newProducto = null;
}
