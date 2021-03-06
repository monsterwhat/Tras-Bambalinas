package servicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import model.CategoriaTO;

public class ServicioCategoria extends Servicio {

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
                String estadoCategoria = resultSet.getString("estadoCategoria");
                String seleccionCategoria = resultSet.getString("seleccionCategoria");
                String visibilidadCategoria = resultSet.getString("visibilidadCategoria");
                String medidasCategoria = resultSet.getString("medidasCategoria");
                categoriaTO = new CategoriaTO(idCategoria, nombreCategoria, descripcionCategoria, estadoCategoria, seleccionCategoria,visibilidadCategoria,medidasCategoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las categorias! " + e);
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
                String estadoCategoria = resultSet.getString("estadoCategoria");
                String seleccionCategoria = resultSet.getString("seleccionCategoria");
                String visibilidadCategoria = resultSet.getString("visibilidadCategoria");
                String medidasCategoria = resultSet.getString("medidasCategoria");
                CategoriaTO categoriaTO = new CategoriaTO(idCategoria, nombreCategoria, descripcionCategoria, estadoCategoria, seleccionCategoria,visibilidadCategoria,medidasCategoria);
                listaRetorno.add(categoriaTO);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar la lista de categorias! " + e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return listaRetorno;
    }
     public List<CategoriaTO> listaCategoriaParaCotizadorAdmin() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<CategoriaTO> listaRetorno = new ArrayList<>();

        try {

            conectar();
            statement = conexion.createStatement();
            String sql = "SELECT * FROM categoria WHERE estadoCategoria = 'Disponible'";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idCategoria = resultSet.getInt("idCategoria");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
                String estadoCategoria = resultSet.getString("estadoCategoria");
                String seleccionCategoria = resultSet.getString("seleccionCategoria");
                String visibilidadCategoria = resultSet.getString("visibilidadCategoria");
                String medidasCategoria = resultSet.getString("medidasCategoria");
                CategoriaTO categoriaTO = new CategoriaTO(idCategoria, nombreCategoria, descripcionCategoria, estadoCategoria, seleccionCategoria,visibilidadCategoria,medidasCategoria);

                listaRetorno.add(categoriaTO);
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
    
  
    public List<CategoriaTO> listaCategoriaParaCotizadorCliente() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<CategoriaTO> listaRetorno = new ArrayList<>();

        try {

            conectar();
            statement = conexion.createStatement();
            String sql = "SELECT * FROM categoria WHERE estadoCategoria = 'Disponible' AND visibilidadCategoria='Para Todos'";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idCategoria = resultSet.getInt("idCategoria");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
                String estadoCategoria = resultSet.getString("estadoCategoria");
                String seleccionCategoria = resultSet.getString("seleccionCategoria");
                String visibilidadCategoria = resultSet.getString("visibilidadCategoria");
                String medidasCategoria = resultSet.getString("medidasCategoria");
                CategoriaTO categoriaTO = new CategoriaTO(idCategoria, nombreCategoria, descripcionCategoria, estadoCategoria, seleccionCategoria,visibilidadCategoria,medidasCategoria);

                listaRetorno.add(categoriaTO);
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

    public void insertarCategoria(CategoriaTO CategoriaTO) {

        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "INSERT INTO categoria ( nombreCategoria, descripcionCategoria, estadoCategoria, seleccionCategoria,visibilidadCategoria, medidasCategoria) VALUES (?,?,?,?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, CategoriaTO.getNombreCategoria());
            preparedStatement.setString(2, CategoriaTO.getDescripcionCategoria());
            preparedStatement.setString(3, CategoriaTO.getEstadoCategoria());
            preparedStatement.setString(4, CategoriaTO.getSeleccionCategoria());
            preparedStatement.setString(5, CategoriaTO.getVisibilidadCategoria());
            preparedStatement.setString(6, CategoriaTO.getMedidasCategoria());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar categoria! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void actualizarCategoria(CategoriaTO categoriaTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE categoria SET nombreCategoria=?, descripcionCategoria=?, estadoCategoria=?, seleccionCategoria=?, visibilidadCategoria=?, medidasCategoria=? WHERE idCategoria='" + categoriaTO.getIdCategoria() + "'";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, categoriaTO.getNombreCategoria());
            preparedStatement.setString(2, categoriaTO.getDescripcionCategoria());
            preparedStatement.setString(3, categoriaTO.getEstadoCategoria());
            preparedStatement.setString(4, categoriaTO.getSeleccionCategoria());
            preparedStatement.setString(5, categoriaTO.getVisibilidadCategoria());
            preparedStatement.setString(6, categoriaTO.getMedidasCategoria());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la categoria! " + e);
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
            String sql = "DELETE FROM categoria WHERE idCategoria='" + categoriaTO.getIdCategoria() + "'";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar la categoria! " + e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void eliminarPorEstadoCategoria(CategoriaTO categoriaTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE categoria SET estadoCategoria='No Disponible' WHERE idCategoria='" + categoriaTO.getIdCategoria() + "' AND estadoCategoria='Disponible' ";
            preparedStatement = conexion.prepareStatement(sql);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la categoria ! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }

    }

//    public HashMap<Integer, String> cargarCategoria() {
//
//        HashMap<Integer, String> mapa = new HashMap<Integer, String>();
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            conectar();
//            statement = conexion.createStatement();
//            String sql = "SELECT * FROM categoria WHERE estadoCategoria = 'Disponible'";
//            resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                mapa.put(resultSet.getInt("idCategoria"), resultSet.getString("nombreCategoria"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al cargar las categorias disponibles! " + e);
//        } finally {
//            cerrarResultSet(resultSet);
//            cerrarStatement(statement);
//            desconectar();
//        }
//
//        return mapa;
//    }

}
