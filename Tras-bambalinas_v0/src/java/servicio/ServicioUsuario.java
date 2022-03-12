package servicio;

import java.sql.PreparedStatement;
import model.UsuarioTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class ServicioUsuario extends Servicio {

    public UsuarioTO existeUsuario(String correoUsua, String clave) {

        Statement statement = null;
        ResultSet resultSet = null;
        UsuarioTO usuarioTO = null;

        try {
            conectar();

            statement = conexion.createStatement();
            String sql = "SELECT * FROM usuarios WHERE correoUsuario = '" + correoUsua + "' AND contrasenaUsuario = '" + ServicioCifrar.encrypt(clave) + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                 int idUsuario = resultSet.getInt("idusuarios");
                String correoUsuario = resultSet.getString("correoUsuario");
                String contrasenaUsuario = resultSet.getString("contrasenaUsuario");
                String tipoUsuario = resultSet.getString("tipoUsuario");

                usuarioTO = new UsuarioTO(idUsuario, correoUsuario, contrasenaUsuario, tipoUsuario);
            }
        } catch (SQLException e) {
             System.out.println("Error tratando de cargar datos de usuario (conectando!)! "+e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return usuarioTO;
    }

    public List<UsuarioTO> listaUsuariosBD() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<UsuarioTO> listaRetorno = new ArrayList<>();

        try {

            conectar();
            statement = conexion.createStatement();
            String sql = "SELECT * FROM usuarios";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("idusuarios");
                String correoUsuario = resultSet.getString("correoUsuario");
                String contrasenaUsuario = resultSet.getString("contrasenaUsuario");
                String tipoUsuario = resultSet.getString("tipoUsuario");

                UsuarioTO usuarioTO = new UsuarioTO(idUsuario, correoUsuario, contrasenaUsuario, tipoUsuario);
                listaRetorno.add(usuarioTO);
            }
        } catch (SQLException e) {
             System.out.println("Error al cargar la lista de usuarios! " + e);
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return listaRetorno;
    }

    public void insertarUser(UsuarioTO usuarioTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "INSERT INTO usuarios (correoUsuario,contrasenaUsuario,tipoUsuario) VALUES (?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, usuarioTO.getCorreoUsuario());
            preparedStatement.setString(2, ServicioCifrar.encrypt(usuarioTO.getContrasenaUsuario()));
            preparedStatement.setString(3, usuarioTO.getTipoUsuario());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void actualizarUser(UsuarioTO usuarioTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE usuarios SET correoUsuario=?, contrasenaUsuario=?, tipoUsuario=?  WHERE idusuarios='"+usuarioTO.getIdusuarios()+"'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, usuarioTO.getCorreoUsuario());
            preparedStatement.setString(2, ServicioCifrar.encrypt(usuarioTO.getContrasenaUsuario()));
            preparedStatement.setString(3, usuarioTO.getTipoUsuario());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
           System.out.println("Error al actualizar el usuario! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void actualizarContrasena(String correoUsua, String clave, String claveNueva) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE usuarios SET contrasenaUsuario=? WHERE correoUsuario = '" + correoUsua + "' AND contrasenaUsuario = '" + clave + "'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, ServicioCifrar.encrypt(claveNueva));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar la contrasena! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }

    }

    public void eliminarUser(UsuarioTO usuarioTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "DELETE FROM usuarios WHERE idusuarios='"+usuarioTO.getIdusuarios()+"'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario! " + e);
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

}
