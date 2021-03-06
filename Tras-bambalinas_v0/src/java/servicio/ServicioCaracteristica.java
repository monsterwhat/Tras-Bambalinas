package servicio;

import java.sql.PreparedStatement;
import model.CaracteristicaTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class ServicioCaracteristica extends Servicio {

    public CaracteristicaTO cargarCaracteristicaTO(String id) {
        Statement statement = null;
        ResultSet resultSet = null;
        CaracteristicaTO caracteristicaTO = null;

        try {
            conectar();
            statement = conexion.createStatement();

            String sql = "SELECT * FROM caracteristica WHERE idCaracteristica='" + id + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int idCaracteristica = resultSet.getInt("idCaracteristica");
                int idCategoriaCaracteristicas = resultSet.getInt("idCategoriaCaracteristica");
                String imagenCaracteristica = resultSet.getString("imagenCaracteristica");
                String nombreCaracteristica = resultSet.getString("nombreCaracteristica");
                String descripcionCaracteristica = resultSet.getString("descripcionCaracteristica");
                String estadoCaracteristica = resultSet.getString("estadoCaracteristica");
                double precioCaracteristica = resultSet.getDouble("precioCaracteristica");
                String colorCaracteristica = resultSet.getString("colorCaracteristica");
                int prioridadCaracteristica = resultSet.getInt("prioridadCaracteristica");
               
                caracteristicaTO = new CaracteristicaTO(idCaracteristica, idCategoriaCaracteristicas, imagenCaracteristica, nombreCaracteristica, descripcionCaracteristica,
                        estadoCaracteristica, precioCaracteristica, colorCaracteristica, prioridadCaracteristica);
            }

        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo de Caracteristicas! " + e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }

        return caracteristicaTO;
    }
    
    public CaracteristicaTO cargarCaracteristicaSeleccionada(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        CaracteristicaTO caracteristicaTO = new CaracteristicaTO();

        try {
            conectar();
            statement = conexion.createStatement();

            String sql = "SELECT * FROM caracteristica WHERE idCaracteristica='"+id+"';";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int idCaracteristica = resultSet.getInt("idCaracteristica");
                int idCategoriaCaracteristicas = resultSet.getInt("idCategoriaCaracteristica");
                String imagenCaracteristica = resultSet.getString("imagenCaracteristica");
                String nombreCaracteristica = resultSet.getString("nombreCaracteristica");
                String descripcionCaracteristica = resultSet.getString("descripcionCaracteristica");
                String estadoCaracteristica = resultSet.getString("estadoCaracteristica");
                double precioCaracteristica = resultSet.getDouble("precioCaracteristica");
                String colorCaracteristica = resultSet.getString("colorCaracteristica");
                int prioridadCaracteristica = resultSet.getInt("prioridadCaracteristica");
               
                caracteristicaTO = new CaracteristicaTO(idCaracteristica, idCategoriaCaracteristicas, imagenCaracteristica, nombreCaracteristica, descripcionCaracteristica,
                        estadoCaracteristica, precioCaracteristica, colorCaracteristica, prioridadCaracteristica);
            }

        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo de Caracteristicas! " + e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }

        return caracteristicaTO;
    }
    

    public List<CaracteristicaTO> listaCaracteristicasParaCotizador(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        List<CaracteristicaTO> listaRetorno = new ArrayList<>();

        try {

            conectar();
            statement = conexion.createStatement();

            String sql = "SELECT * FROM caracteristica WHERE estadoCaracteristica ='Disponible' AND idCategoriaCaracteristica='" + id + "'  ORDER BY prioridadCaracteristica ASC;";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idCaracteristica = resultSet.getInt("idCaracteristica");
                int idCategoriaCaracteristicas = resultSet.getInt("idCategoriaCaracteristica");
                String imagenCaracteristica = resultSet.getString("imagenCaracteristica");
                String nombreCaracteristica = resultSet.getString("nombreCaracteristica");
                String descripcionCaracteristica = resultSet.getString("descripcionCaracteristica");
                String estadoCaracteristica = resultSet.getString("estadoCaracteristica");
                double precioCaracteristica = resultSet.getDouble("precioCaracteristica");
                String colorCaracteristica = resultSet.getString("colorCaracteristica");
                int prioridadCaracteristica = resultSet.getInt("prioridadCaracteristica");
                

                CaracteristicaTO caracteristicasTO = new CaracteristicaTO(idCaracteristica, idCategoriaCaracteristicas, imagenCaracteristica, nombreCaracteristica, descripcionCaracteristica,
                        estadoCaracteristica, precioCaracteristica, colorCaracteristica, prioridadCaracteristica);
                listaRetorno.add(caracteristicasTO);
            }
        } catch (SQLException e) {

            System.out.println("Error al seleccionar todo de Caracteristicas! " + e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return listaRetorno;
    }

    public List<CaracteristicaTO> listaCaracteristicasBD() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<CaracteristicaTO> listaRetorno = new ArrayList<>();

        try {

            conectar();
            statement = conexion.createStatement();
            String sql = "SELECT * FROM caracteristica";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idCaracteristica = resultSet.getInt("idCaracteristica");
                int idCategoriaCaracteristica = resultSet.getInt("idCategoriaCaracteristica");
                String imagenCaracteristica = resultSet.getString("imagenCaracteristica");
                String nombreCaracteristica = resultSet.getString("nombreCaracteristica");
                String descripcionCaracteristica = resultSet.getString("descripcionCaracteristica");
                String estadoCaracteristica = resultSet.getString("estadoCaracteristica");
                double precioCaracteristica = resultSet.getDouble("precioCaracteristica");
                String colorCaracteristica = resultSet.getString("colorCaracteristica");
                int prioridadCaracteristica = resultSet.getInt("prioridadCaracteristica");
            

                CaracteristicaTO caracteristicasTO = new CaracteristicaTO(idCaracteristica, idCategoriaCaracteristica, imagenCaracteristica, nombreCaracteristica, descripcionCaracteristica,
                        estadoCaracteristica, precioCaracteristica, colorCaracteristica, prioridadCaracteristica);
                listaRetorno.add(caracteristicasTO);
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo de Caracteristicas! " + e.getMessage());
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return listaRetorno;
    }

    public void insertarCaracteristica(CaracteristicaTO caracteristicaTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "INSERT INTO caracteristica (idCategoriaCaracteristica, imagenCaracteristica, nombreCaracteristica, descripcionCaracteristica, estadoCaracteristica, precioCaracteristica,colorCaracteristica,prioridadCaracteristica) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, caracteristicaTO.getIdCategoriaCaracteristica());
            preparedStatement.setString(2, caracteristicaTO.getImagenCaracteristica());
            preparedStatement.setString(3, caracteristicaTO.getNombreCaracteristica());
            preparedStatement.setString(4, caracteristicaTO.getDescripcionCaracteristica());
            preparedStatement.setString(5, caracteristicaTO.getEstadoCaracteristica());
            preparedStatement.setDouble(6, caracteristicaTO.getPrecioCaracteristica());
            preparedStatement.setString(7, caracteristicaTO.getColorCaracteristica());
            preparedStatement.setInt(8, caracteristicaTO.getPrioridadCaracteristica());
          
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar caracteristica! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void actualizarCaracteristica(CaracteristicaTO caracteristicaTO) {

        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE caracteristica SET idCategoriaCaracteristica=?, imagenCaracteristica=?, nombreCaracteristica=?, descripcionCaracteristica=?, estadoCaracteristica=?, precioCaracteristica=?,colorCaracteristica=?,prioridadCaracteristica=? WHERE idCaracteristica='" + caracteristicaTO.getIdCaracteristica() + "'";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, caracteristicaTO.getIdCategoriaCaracteristica());
            preparedStatement.setString(2, caracteristicaTO.getImagenCaracteristica());
            preparedStatement.setString(3, caracteristicaTO.getNombreCaracteristica());
            preparedStatement.setString(4, caracteristicaTO.getDescripcionCaracteristica());
            preparedStatement.setString(5, caracteristicaTO.getEstadoCaracteristica());
            preparedStatement.setDouble(6, caracteristicaTO.getPrecioCaracteristica());
            preparedStatement.setString(7, caracteristicaTO.getColorCaracteristica());
            preparedStatement.setInt(8, caracteristicaTO.getPrioridadCaracteristica());
         

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar caracteristica! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void eliminarCaracteristica(CaracteristicaTO caracteristicaTO) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "DELETE FROM caracteristica WHERE idCaracteristica='" + caracteristicaTO.getIdCaracteristica() + "'";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar caracteristica! " + e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void eliminarPorEstadoCaracteristica(CaracteristicaTO caracteristicaTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE caracteristica SET estadoCaracteristica='No Disponible' WHERE idCaracteristica='" + caracteristicaTO.getIdCaracteristica() + "' AND estadoCaracteristica='Disponible' ";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar caracteristica! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }

    }

}
