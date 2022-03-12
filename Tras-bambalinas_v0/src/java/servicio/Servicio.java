
package servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Servicio {
    
    protected Connection conexion = null;
    private String host = "localhost";
    private String puerto = "3306";
    private String nombreBD = "trasbambalinas";
    private String usuario = "Telon";
    private String clave = "admin";
    
    
    public void conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://"+
                        host+":"+puerto+"/"+nombreBD+"?serverTimezone=UTC",usuario,clave);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectarse a la base de datos! " + e);
            
        }
    }
    
    public void cerrarStatement(Statement statement){
        try{
            if(statement != null && !statement.isClosed()){
                statement.close();
                statement = null;
            }
        } catch(SQLException e){
            System.out.println("Error cerrando el Statement! " + e);   
        }
    }
    
    public void cerrarResultSet(ResultSet resultSet){
        try{
            if(resultSet != null && !resultSet.isClosed()){
                resultSet.close();
                resultSet = null;
            }
        } catch(SQLException e){
            System.out.println("Error cerrando el ResultSet! " + e);      
        }
    }
    
    public void cerrarPreparedStatement(PreparedStatement preparedStatement){
        try{
            if(preparedStatement != null && !preparedStatement.isClosed()){
                preparedStatement.close();
                preparedStatement = null;
            }
        } catch(SQLException e){
            System.out.println("Error al cerrar el preparedStatement! " + e);
        }
    }
    
    public void desconectar(){
        try{
            if(conexion != null && !conexion.isClosed()){
                conexion.close();
                conexion = null;
            }
        } catch(SQLException e){
            System.out.println("Error al tratar de cerrar la conecion! " + e);   
        } 
    }
    
}
