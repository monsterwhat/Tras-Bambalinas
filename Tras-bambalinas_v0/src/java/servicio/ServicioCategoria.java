
package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.CategoriaTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import model.CategoriaTO;

public class ServicioCategoria extends Servicio{
    
    public CategoriaTO mostrarCategoria() {

        Statement statement = null;
        ResultSet resultSet = null;
        CategoriaTO categoriaTO = null;

        try {
            conectar();

            statement = conexion.createStatement();
            String sql = "SELECT * FROM categoria";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idCategoria = resultSet.getInt("idCategoria");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
         
                categoriaTO = new CategoriaTO(idCategoria, nombreCategoria, descripcionCategoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return categoriaTO;
    }
    public List<CategoriaTO> listaCategoriasBD() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<CategoriaTO> listaRetorno = new ArrayList<>();

        try {

            conectar();
            statement = conexion.createStatement();
            String sql = "SELECT * FROM categoria";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idCategoria = resultSet.getInt("idCategoria");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");

                CategoriaTO categoriaTO = new CategoriaTO(idCategoria, nombreCategoria, descripcionCategoria);
                listaRetorno.add(categoriaTO);
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
    
    public void insertarCategoria(CategoriaTO CategoriaTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "INSERT INTO categoria ( nombreCategoria, descripcionCategoria) VALUES (?,?)";
            preparedStatement = conexion.prepareStatement(sql);


            preparedStatement.setString(1, CategoriaTO.getNombreCategoria());
            preparedStatement.setString(2, CategoriaTO.getDescripcionCategoria());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void actualizarCategoria(CategoriaTO categoriaTO) {

        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE categoria SET nombreCategoria = ?, descripcionCategoria =?  WHERE idCategoria='"+categoriaTO.getIdCategoria()+"'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, categoriaTO.getNombreCategoria());
            preparedStatement.setString(2, categoriaTO.getDescripcionCategoria());
       
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }

    }
    
     public void eliminarCategoria(CategoriaTO categoriaTO) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "DELETE FROM categoria WHERE idCategoria='"+categoriaTO.getIdCategoria()+"'";
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
