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

    public CotizacionTO cotizacionNuevaExistente(String fecha) {

        Statement statement = null;
        ResultSet resultSet = null;
        CotizacionTO cotizacionTORetorno = null;
        try {
            conectar();

            statement = conexion.createStatement();
            String sql = "Select * FROM cotizacion where fechaCotizacion='" + fecha + "';";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int numeroCotizacion = resultSet.getInt("numeroCotizacion");
                String listaDeCaracteristicas = resultSet.getString("listaIDCaracteristicaCotizacion");
                String fechaCotizacion = resultSet.getString("fechaCotizacion");
                int clienteCotizacion = resultSet.getInt("clienteCotizacion");
                Double totalCotizacion = resultSet.getDouble("totalCotizacion");
                String estadoCotizacion = resultSet.getString("estadoCotizacion");
                String codigoCotizacion = resultSet.getString("codigoCotizacion");
                cotizacionTORetorno = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, clienteCotizacion, totalCotizacion, estadoCotizacion,codigoCotizacion);
            }
        } catch (SQLException e) {
            System.out.println("Error tratando de cargar datos de usuario (conectando!)! " + e.getMessage());
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return cotizacionTORetorno;
    }

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
            int clienteCotizacion = cotizacionTO.getClienteCotizacion();
            List<CaracteristicaTO> listaCotizacionLarga = listaCotizacion(listaCotizacion);
            Double totalCotizacion = cotizacionTO.getTotalCotizacion();
            String estadoCotizacion = cotizacionTO.getEstadoCotizacion();
            String codigoCotizacion = cotizacionTO.getCodigoCotizacion();
            CotizacionTO cotizacionTO1 = new CotizacionTO(numeroCotizacion, listaCotizacion, fechaCotizacion, clienteCotizacion, listaCotizacionLarga, totalCotizacion, estadoCotizacion,codigoCotizacion);
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
                String fechaCotizacion = resultSet.getString("fechaCotizacion");
                int clienteCotizacion = resultSet.getInt("clienteCotizacion");
                Double totalCotizacion = resultSet.getDouble("totalCotizacion");
                String estadoCotizacion = resultSet.getString("estadoCotizacion");
                String codigoCotizacion = resultSet.getString("codigoCotizacion");
                CotizacionTO cotizacionTO = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, clienteCotizacion, totalCotizacion, estadoCotizacion,codigoCotizacion);

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
                String fechaCotizacion = resultSet.getString("fechaCotizacion");
                int clienteCotizacion = resultSet.getInt("clienteCotizacion");
                double totalCotizacion = resultSet.getDouble("totalCotizacion");
                String estadoCotizacion = resultSet.getString("estadoCotizacion");
                String codigoCotizacion = resultSet.getString("codigoCotizacion");
                cotizacion = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, clienteCotizacion, totalCotizacion, estadoCotizacion,codigoCotizacion);

                return cotizacion;
            }
            return cotizacion;

        } catch (SQLException e) {
            System.out.println("Error cargando Cotizacion! " + e.getMessage());
        }
        return cotizacion;
    }

    public List<CotizacionTO> listaCotizacionesCliente(int idCliente) {
        Statement statement = null;
        ResultSet resultSet = null;
        List<CotizacionTO> listaRetorno = new ArrayList<>();

        try {

            conectar();
            statement = conexion.createStatement();
            String sql = "Select * FROM cotizacion where clienteCotizacion='" + idCliente + "'";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int numeroCotizacion = resultSet.getInt("numeroCotizacion");
                String listaDeCaracteristicas = resultSet.getString("listaIDCaracteristicaCotizacion");
                String fechaCotizacion = resultSet.getString("fechaCotizacion");
                int clienteCotizacion = resultSet.getInt("clienteCotizacion");
                Double totalCotizacion = resultSet.getDouble("totalCotizacion");
                String estadoCotizacion = resultSet.getString("estadoCotizacion");
                String codigoCotizacion = resultSet.getString("codigoCotizacion");
                CotizacionTO cotizacionTO = new CotizacionTO(numeroCotizacion, listaDeCaracteristicas, fechaCotizacion, clienteCotizacion, totalCotizacion, estadoCotizacion,codigoCotizacion);

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
            String sql = "INSERT INTO cotizacion (listaIDCaracteristicaCotizacion,fechaCotizacion,clienteCotizacion,totalCotizacion,estadoCotizacion,codigoCotizacion) VALUES (?,?,?,?,?,?)";
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
            preparedStatement.setDouble(4, totalCotizacion);
            preparedStatement.setString(5, "Pre-Cotizado");
            preparedStatement.setString(6, "Codigo");

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
            String sql = "INSERT INTO cotizacion (listaIDCaracteristicaCotizacion,fechaCotizacion,clienteCotizacion,totalCotizacion,estadoCotizacion,codigoCotizacion) VALUES (?,?,?,?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, cotizacionTO.getListaDeCaracteristicas());
            preparedStatement.setString(2, cotizacionTO.getFechaCotizacion());
            preparedStatement.setInt(3, cotizacionTO.getClienteCotizacion());
            preparedStatement.setDouble(4, cotizacionTO.getTotalCotizacion());
            preparedStatement.setString(5, "Pre-Cotizado");
            preparedStatement.setString(6, cotizacionTO.getCodigoCotizacion());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al cotizar! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void cambiarEstadoACotizado(CotizacionTO cotizacionTO) {

        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE cotizacion SET estadoCotizacion='Cotizado' WHERE numeroCotizacion='" + cotizacionTO.getNumeroCotizacion() + "'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado de Cotizacion a Cotizado! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void cambiarEstadoACancelado(CotizacionTO cotizacionTO) {

        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE cotizacion SET estadoCotizacion='Verificada' WHERE numeroCotizacion='" + cotizacionTO.getNumeroCotizacion() + "'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado de Cotizacion a Cancelado! " + e);
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
    
    public void cambiarClienteCotizacion(int idUser ,int numCotizacion, String codCotizacion) {

        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE cotizacion SET clienteCotizacion=?  WHERE numeroCotizacion='" + numCotizacion + "' AND codigoCotizacion = '"+codCotizacion+"'";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado de Cotizacion a Cancelado! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

}
