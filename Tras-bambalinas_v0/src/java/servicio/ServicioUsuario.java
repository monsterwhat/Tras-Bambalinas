package servicio;

import java.sql.PreparedStatement;
import model.UsuarioTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;


public class ServicioUsuario extends Servicio {

    public boolean verificarCorreo(String correoUsuario){
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            conectar();
            
            statement = conexion.createStatement();
            String sql = "SELECT * FROM usuarios WHERE correoUsuario='" + correoUsuario + "'";
            resultset = statement.executeQuery(sql);
            if(resultset.next()){
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Error verificando correo electronico! + " + e.getMessage());
        } finally{
            cerrarResultSet(resultset);
            cerrarStatement(statement);
            desconectar();
        }
        return true;
    }
    
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
                String nombreUsuario = resultSet.getString("nombreUsuario");
                String direccionUsuario = resultSet.getString("direccionUsuario");
                int telefonoUsuario = resultSet.getInt("telefonoUsuario");
                int numeroContratoUsuario = resultSet.getInt("numeroContratoUsuario");
                String descripcionTrabajoUsuario = resultSet.getString("descripcionTrabajoUsuario");

                usuarioTO = new UsuarioTO(idUsuario, correoUsuario, contrasenaUsuario, tipoUsuario, nombreUsuario, direccionUsuario, telefonoUsuario, numeroContratoUsuario, descripcionTrabajoUsuario);
            }
        } catch (SQLException e) {
            System.out.println("Error tratando de cargar datos de usuario (conectando!)! " + e.getMessage());
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return usuarioTO;
    }
    
    public boolean existeUsuarioB(String correoUsua, String clave) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conectar();
            statement = conexion.createStatement();
            String sql = "SELECT * FROM usuarios WHERE correoUsuario = '" + correoUsua + "' AND contrasenaUsuario = '" + ServicioCifrar.encrypt(clave) + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error tratando de cargar datos de usuario (conectando!)! " + e.getMessage());
        } finally {
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return false;
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
                String nombreUsuario = resultSet.getString("nombreUsuario");
                String direccionUsuario = resultSet.getString("direccionUsuario");
                int telefonoUsuario = resultSet.getInt("telefonoUsuario");
                int numeroContratoUsuario = resultSet.getInt("numeroContratoUsuario");
                String descripcionTrabajoUsuario = resultSet.getString("descripcionTrabajoUsuario");

                UsuarioTO usuarioTO = new UsuarioTO(idUsuario, correoUsuario, contrasenaUsuario, tipoUsuario, nombreUsuario, direccionUsuario, telefonoUsuario, numeroContratoUsuario, descripcionTrabajoUsuario);

                listaRetorno.add(usuarioTO);

            }
        } catch (SQLException e) {
            System.out.println("Error al cargar la lista de usuarios! " + e.getMessage());
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
            String sql = "INSERT INTO usuarios (correoUsuario,tipoUsuario,nombreUsuario,contrasenaUsuario,direccionUsuario,telefonoUsuario,numeroContratoUsuario,descripcionTrabajoUsuario) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);
            
            
            preparedStatement.setString(1, usuarioTO.getCorreoUsuario());
            preparedStatement.setString(2, usuarioTO.getTipoUsuario());
            preparedStatement.setString(3, usuarioTO.getNombreUsuario());
            preparedStatement.setString(4, usuarioTO.getContrasenaUsuario());
            preparedStatement.setString(5, usuarioTO.getDireccionUsuario());
            preparedStatement.setInt(6, usuarioTO.getTelefonoUsuario());
            preparedStatement.setInt(7,usuarioTO.getNumeroContratoUsuario());
            preparedStatement.setString(8,usuarioTO.getDescripcionTrabajoUsuario());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void actualizarUser(UsuarioTO usuarioTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE usuarios SET correoUsuario=?, tipoUsuario=?,nombreUsuario=?,direccionUsuario=?,telefonoUsuario=?,numeroContratoUsuario=?,descripcionTrabajoUsuario=? WHERE idusuarios='" + usuarioTO.getIdusuarios() + "'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, usuarioTO.getCorreoUsuario());
            preparedStatement.setString(2, usuarioTO.getTipoUsuario());
            preparedStatement.setString(3, usuarioTO.getNombreUsuario());
            preparedStatement.setString(4, usuarioTO.getDireccionUsuario());
            preparedStatement.setInt(5, usuarioTO.getTelefonoUsuario());
            preparedStatement.setInt(6,usuarioTO.getNumeroContratoUsuario());
            preparedStatement.setString(7,usuarioTO.getDescripcionTrabajoUsuario());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

    public void actualizarContrasena(String correoUsua, String clave, String claveNueva) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "UPDATE usuarios SET contrasenaUsuario=? WHERE correoUsuario = '" + correoUsua + "'";
            preparedStatement = conexion.prepareStatement(sql);

            String claveCifrada = ServicioCifrar.encrypt(claveNueva);
            preparedStatement.setString(1, claveCifrada);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar la contrasena! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }

    }

    public void eliminarUser(UsuarioTO usuarioTO) {
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "DELETE FROM usuarios WHERE idusuarios='" + usuarioTO.getIdusuarios() + "'";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario! " + e.getMessage());
        } finally {
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }

}
