
package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.UsuarioTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class ServicioUsuario extends Servicio {
    
    public UsuarioTO existeUsuario(String correoUsua, String clave){
       
        Statement statement = null;
        ResultSet resultSet = null;
        UsuarioTO usuarioTO = null;
        
        try{
            conectar();
            
            statement = conexion.createStatement();
            String sql = "SELECT * FROM usuarios WHERE correoUsuario = '"+correoUsua+"' AND contrasenaUsuario = '"+clave+"'";
            resultSet =statement.executeQuery(sql);

            if(resultSet.next()){
                int cedula = resultSet.getInt("cedula");
                String nombreUsuario = resultSet.getString("nombreUsuario");
                int telefonoUsuario = resultSet.getInt("telefonoUsuario");
                String correoUsuario = resultSet.getString("correoUsuario");
                String contrasenaUsuario = resultSet.getString("contrasenaUsuario");
                String tipoUsuario = resultSet.getString("tipoUsuario");
                usuarioTO = new UsuarioTO(cedula,nombreUsuario,telefonoUsuario,correoUsuario,contrasenaUsuario,tipoUsuario);     

            }
                  
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }   
  
       return usuarioTO;
    }
   
    public List<UsuarioTO> listaUsuariosBD(){
        Statement statement = null;
        ResultSet resultSet = null;
        List<UsuarioTO> listaRetorno = new ArrayList<>();
        
        try{
            
            conectar();
            statement = conexion.createStatement();            
            String sql = "SELECT * FROM usuarios";
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                int cedula = resultSet.getInt("cedula");
                String nombreUsuario = resultSet.getString("nombreUsuario");
                int telefonoUsuario = resultSet.getInt("telefonoUsuario");
                String correoUsuario = resultSet.getString("correoUsuario");
                String contrasenaUsuario = resultSet.getString("contrasenaUsuario");
                String tipoUsuario = resultSet.getString("tipoUsuario");
                 
                UsuarioTO usuarioTO = new UsuarioTO(cedula,nombreUsuario,telefonoUsuario,correoUsuario,contrasenaUsuario,tipoUsuario);    
                listaRetorno.add(usuarioTO);
            }   
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarResultSet(resultSet);
            cerrarStatement(statement);
            desconectar();
        }
        return listaRetorno;
    }
    
    
    public void insertarUser(UsuarioTO usuarioTO){
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "INSERT INTO usuarios (cedula, nombreUsuario, telefonoUsuario,correoUsuario,contrasenaUsuario,tipoUsuario) VALUES (?,?,?,?,?,?)";
            preparedStatement = conexion.prepareStatement(sql);
            
            preparedStatement.setInt(1, usuarioTO.getCedula());
            preparedStatement.setString(2, usuarioTO.getNombreUsuario());
            preparedStatement.setInt(3, usuarioTO.getTelefonoUsuario());
            preparedStatement.setString(4, usuarioTO.getCorreoUsuario());
            preparedStatement.setString(5, usuarioTO.getContrasenaUsuario());
            preparedStatement.setString(6, usuarioTO.getTipoUsuario());
            
            
            preparedStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally{    
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }
    
    public void actualizarUser(UsuarioTO usuarioTO){
        
        PreparedStatement preparedStatement = null;
 
        try {
            conectar();
            String sql = "UPDATE usuarios SET nombreUsuario = ?, telefonoUsuario =?, correoUsuario=?, contrasenaUsuario=?, tipoUsuario=?  WHERE cedula=?";
            preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, usuarioTO.getNombreUsuario());
            preparedStatement.setInt(2, usuarioTO.getTelefonoUsuario());
            preparedStatement.setString(3, usuarioTO.getCorreoUsuario());
            preparedStatement.setString(4, usuarioTO.getContrasenaUsuario());
            preparedStatement.setString(5, usuarioTO.getTipoUsuario());
            preparedStatement.setInt(6, usuarioTO.getCedula());
            preparedStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }

    }
    
    
    public void actualizarContrasena(String correoUsua, String clave, String claveNueva){
        
        PreparedStatement preparedStatement = null;
 
        try {
            conectar();
            String sql = "UPDATE usuarios SET contrasenaUsuario=? WHERE correoUsuario = '"+correoUsua+"' AND contrasenaUsuario = '"+clave+"'";
            preparedStatement = conexion.prepareStatement(sql);

           
            preparedStatement.setString(1, claveNueva);
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }

    }
    
    
    
    
    
    
    public void eliminarUser(UsuarioTO usuarioTO){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            conectar();
            String sql = "DELETE FROM usuarios WHERE cedula=?";
            preparedStatement = conexion.prepareStatement(sql);
            
            preparedStatement.setInt(1, usuarioTO.getCedula());

            preparedStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarResultSet(resultSet);
            cerrarPreparedStatement(preparedStatement);
            desconectar();
        }
    }
    
    
    
}



