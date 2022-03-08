
package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.CaracteristicaTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class ServicioCaracteristica extends Servicio {
    
        public CaracteristicaTO mostrarCaracteristicas(int idCategoria) {

        Statement statement = null;
        ResultSet resultSet = null;
        CaracteristicaTO caracteristicasTO = null;

        try {
            conectar();

            statement = conexion.createStatement();
            String sql = "SELECT * FROM caracteristica Where idCategoria = '"+idCategoria+"'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idCaracteristica = resultSet.getInt("idCaracteristica");
                String nombreCaracteristica = resultSet.getString("nombreCaracteristica");
                String descripcionCaracteristica = resultSet.getString("descripcionCaracteristica");
                int cantidadCaracteristica = resultSet.getInt("cantidadCaracteristica");
                int prioridadCaracteristica = resultSet.getInt("prioridadCaracteristica");
                double precioCaracteristica = resultSet.getDouble("precioCaracteristica");
                
                
                caracteristicasTO = new CaracteristicaTO(idCaracteristica,nombreCaracteristica,descripcionCaracteristica,
                        cantidadCaracteristica,prioridadCaracteristica,precioCaracteristica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return caracteristicasTO;
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
                String nombreCaracteristica = resultSet.getString("nombreCaracteristica");
                String descripcionCaracteristica = resultSet.getString("descripcionCaracteristica");
                int cantidadCaracteristica = resultSet.getInt("cantidadCaracteristica");
                int prioridadCaracteristica = resultSet.getInt("prioridadCaracteristica");
                double precioCaracteristica = resultSet.getDouble("precioCaracteristica");

                CaracteristicaTO caracteristicasTO = new CaracteristicaTO(idCaracteristica, nombreCaracteristica, descripcionCaracteristica, cantidadCaracteristica,prioridadCaracteristica,precioCaracteristica);
                listaRetorno.add(caracteristicasTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            String sql = "INSERT INTO caracteristica ( nombreCaracteristica, descripcionCaracteristica, cantidadCaracteristica, prioridadCaracteristica, precioCaracteristica) VALUES (?,?,?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, caracteristicaTO.getNombreCaracteristica());
            preparedStatement.setString(2, caracteristicaTO.getDescripcionCaracteristica());
            preparedStatement.setInt(3, caracteristicaTO.getCantidadCaracteristica());
            preparedStatement.setInt(4, caracteristicaTO.getPrioridadCaracteristica());
            preparedStatement.setDouble(5, caracteristicaTO.getPrecioCaracteristica());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void actualizarCaracteristica(CaracteristicaTO caracteristicaTO) {

        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE caracteristica SET nombreCaracteristica = ?, descripcionCaracteristica =? cantidadCaracteristica=?, prioridadCaracteristica=?, precioCaracteristica=? WHERE idCaracteristica='"+caracteristicaTO.getIdCaracteristica()+"'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, caracteristicaTO.getNombreCaracteristica());
            preparedStatement.setString(2, caracteristicaTO.getDescripcionCaracteristica());
            preparedStatement.setInt(3, caracteristicaTO.getCantidadCaracteristica());
            preparedStatement.setInt(4, caracteristicaTO.getPrioridadCaracteristica());
            preparedStatement.setDouble(5, caracteristicaTO.getPrecioCaracteristica());
       
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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
            String sql = "DELETE FROM caracteristica WHERE idCaracteristica='"+caracteristicaTO.getIdCaracteristica()+"'";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(resultSet);
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }
    
    
}
