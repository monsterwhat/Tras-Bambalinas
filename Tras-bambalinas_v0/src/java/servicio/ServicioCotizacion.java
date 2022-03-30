/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.CotizacionTO;
import model.CaracteristicaTO;

public class ServicioCotizacion extends Servicio {

    ServicioCaracteristica servicioCaracteristica = new ServicioCaracteristica();

    public List<CotizacionTO> listaLargaCotizaciones() {

        List<CotizacionTO> lista = new ArrayList<>();
        List<CotizacionTO> listaLarga = new ArrayList<>();
        lista = listaCotizaciones();
        if (lista == null) {
            System.out.println("La lista a cargar(Larga) se encuentra vacia!");
            return null;
        }

        for (CotizacionTO cotizacionTO : lista) {
            int numeroCotizacion = cotizacionTO.getNumeroCotizacion();
            String listaCotizacion = cotizacionTO.getListaDeCaracteristicas();
            String fechaCotizacion = cotizacionTO.getFechaCotizacion();
            String clienteCotizacion = cotizacionTO.getClienteCotizacion();
            List<CaracteristicaTO> listaCotizacionLarga = listaCotizacion(listaCotizacion);
            CotizacionTO cotizacionTO1 = new CotizacionTO(numeroCotizacion, listaCotizacion, fechaCotizacion, clienteCotizacion, listaCotizacionLarga);
            listaLarga.add(cotizacionTO1);
        }
        return listaLarga;

    }

    public List<CaracteristicaTO> listaCotizacion(String lista) {

        List<CaracteristicaTO> listaCaracteristicaTO = null;

        String[] array = lista.split(",");
        int ArraySize = array.length;
        for (int i = 0; i < ArraySize; i++) {
            listaCaracteristicaTO.add(servicioCaracteristica.cargarCaracteristicaTO(array[i]));
        }
        return listaCaracteristicaTO;
    }

    public List<CotizacionTO> listaCotizaciones() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<CotizacionTO> listaRetorno = new ArrayList<>();

        try {

            conectar();
            statement = conexion.createStatement();
            String sql = "SELECT * FROM cotizacion";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int numeroCotizacion = resultSet.getInt("NumeroCotizacion");
                String listaDeCaracteristicas = resultSet.getString("ListaIDCaracterista");
                String fechaCotizacion = resultSet.getString("FechaCotizacion");
                String clienteCotizacion = resultSet.getString("Cliente");
                CotizacionTO cotizacionTO = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, clienteCotizacion);

                listaRetorno.add(cotizacionTO);
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo de cotizaciones! " + e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return listaRetorno;
    }

    public void Cotizar(List<CaracteristicaTO> listaCotizar, int idUsuario) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "INSERT INTO cotizacion listaIDCaracteristicaCotizacion,fechaCotizacion,clienteCotizacion = ?,?,?";
            preparedStatement = conexion.prepareStatement(sql);
            List<Integer> ListaIDCaracteristicas = null;
            for (CaracteristicaTO caracteristicaTO : listaCotizar) {
                ListaIDCaracteristicas.add(caracteristicaTO.getIdCaracteristica());
            }
            String lista = ListaIDCaracteristicas.stream()
                    .map(i -> i.toString())
                    .collect(Collectors.joining(", "));

            preparedStatement.setString(1, lista);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.MAX));
            if(idUsuario!=0){
                preparedStatement.setInt(3,idUsuario);
            }
            preparedStatement.setInt(3, 0);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al cotizar! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }
}
