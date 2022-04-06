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
import model.UsuarioTO;

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
            Date fechaCotizacion = cotizacionTO.getFechaCotizacion();
            int clienteCotizacion = cotizacionTO.getClienteCotizacion();
            List<CaracteristicaTO> listaCotizacionLarga = listaCotizacion(listaCotizacion);
            String anchoCotizacion = cotizacionTO.getAnchoCotizacion();
            String largoCotizacion = cotizacionTO.getLargoCotizacion();
            Double totalCotizacion = cotizacionTO.getTotalCotizacion();
            CotizacionTO cotizacionTO1 = new CotizacionTO(numeroCotizacion, listaCotizacion, fechaCotizacion, clienteCotizacion, listaCotizacionLarga, anchoCotizacion, largoCotizacion, totalCotizacion);
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
                int numeroCotizacion = resultSet.getInt("numeroCotizacion");
                String listaDeCaracteristicas = resultSet.getString("listaIDCaracteristicaCotizacion");
                Date fechaCotizacion = resultSet.getDate("fechaCotizacion");
                int clienteCotizacion = resultSet.getInt("clienteCotizacion");
                String anchoCotizacion = resultSet.getString("anchoCotizacion");
                String largoCotizacion = resultSet.getString("largoCotizacion");
                Double totalCotizacion = resultSet.getDouble("totalCotizacion");
                CotizacionTO cotizacionTO = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, clienteCotizacion, anchoCotizacion, largoCotizacion, totalCotizacion);

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

    public CotizacionTO CargarCotizacion(int idCotizacion) {
        Statement statement = null;
        ResultSet resultSet = null;
        CotizacionTO cotizacion = new CotizacionTO();

        try {
            conectar();
            statement = conexion.createStatement();
            String sql = "Select * FROM cotizacion where numeroCotizacion='" + idCotizacion + "'";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int numeroCotizacion = resultSet.getInt("numeroCotizacion");
                String listaDeCaracteristicas = resultSet.getString("listaIDCaracteristicaCotizacion");
                Date fechaCotizacion = resultSet.getDate("fechaCotizacion");
                int clienteCotizacion = resultSet.getInt("clienteCotizacion");
                String anchoCotizacion = resultSet.getString("anchoCotizacion");
                String largoCotizacion = resultSet.getString("largoCotizacion");
                double totalCotizacion = resultSet.getDouble("totalCotizacion");
                cotizacion = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, clienteCotizacion, anchoCotizacion, largoCotizacion, totalCotizacion);

                return cotizacion;
            }
            return cotizacion;

        } catch (SQLException e) {
            System.out.println("Error cargando Cotizacion! " + e.getMessage());
        }
        return cotizacion;
    }

    public void Cotizar(List<CaracteristicaTO> listaCotizar, int idUsuario, String ancho, String largo) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "INSERT INTO cotizacion (listaIDCaracteristicaCotizacion,fechaCotizacion,clienteCotizacion,anchoCotizacion,largoCotizacion,totalCotizacion) VALUES (?,?,?,?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);
            List<Integer> ListaIDCaracteristicas = null;
            double totalCotizacion = 0;

            for (CaracteristicaTO caracteristicaTO : listaCotizar) {
                ListaIDCaracteristicas.add(caracteristicaTO.getIdCaracteristica());
                totalCotizacion = totalCotizacion + caracteristicaTO.getPrecioCaracteristica();
            }
            String lista = ListaIDCaracteristicas.stream()
                    .map(i -> i.toString())
                    .collect(Collectors.joining(", "));

            preparedStatement.setString(1, lista);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            if (idUsuario != 0) {
                preparedStatement.setInt(3, idUsuario);
            } else {
                preparedStatement.setInt(3, 0);
            }
            preparedStatement.setString(4, ancho);
            preparedStatement.setString(5, largo);
            preparedStatement.setDouble(6, totalCotizacion);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al cotizar! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void insertarCotizacion(CotizacionTO cotizacionTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "INSERT INTO cotizacion (listaIDCaracteristicaCotizacion,fechaCotizacion,clienteCotizacion,anchoCotizacion,largoCotizacion,totalCotizacion) VALUES (?,?,?,?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, cotizacionTO.getListaDeCaracteristicas());
            preparedStatement.setDate(2, cotizacionTO.getFechaCotizacion());
            preparedStatement.setInt(3, cotizacionTO.getClienteCotizacion());
            preparedStatement.setString(4, cotizacionTO.getAnchoCotizacion());
            preparedStatement.setString(5, cotizacionTO.getLargoCotizacion());
            preparedStatement.setDouble(6, cotizacionTO.getTotalCotizacion());

            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al cotizar! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void eliminarCotizacion(CotizacionTO cotizacionTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "DELETE FROM cotizacion WHERE numeroCotizacion='" + cotizacionTO.getNumeroCotizacion() + "'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar la cotizacion! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

}
