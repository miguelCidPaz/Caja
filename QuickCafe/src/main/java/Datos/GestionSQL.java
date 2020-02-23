/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/*
*@author: Miguel
*Desde aqui controlaremos el inicio y final de todas las conexiones a SQL de nuestra aplicacion
*/
public class GestionSQL {
    private static final String JDBC_URL = "jdbc:mysql://localhost/QuickCafe?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER ="root";
    private static final String JDBC_PASS ="matanomos12";
    private static BasicDataSource ds = null;
    private Connection connection = null;
    
    //Preparamos nuestro pool de conexiones
    public static DataSource executeSQL(){
        if(ds == null){
            ds = new BasicDataSource();
            //hacemos set con los datos imprescindibles
            ds.setUrl(JDBC_URL);
            ds.setUsername(JDBC_USER);
            ds.setPassword(JDBC_PASS);
            //damos un tamaño inicial de 50
            ds.setInitialSize(50);
            ds.setMaxIdle(10);
            ds.setMaxTotal(20);
            //decimos cuanto tiene que esperar para cerrar el hilo
            ds.setMaxWaitMillis(5000);
        }
        
        return ds;
    }
    
    //Metodo publico statico que nos dara la conexion con el pool
    public static Connection openConnection()throws SQLException{
        return executeSQL().getConnection();
    }
    
    
    //Metodos close para la conexion a la BD, ResultSet, PreparedStatement y Connection
    public static void closeConnection(ResultSet rs){
        try{
            rs.close();
        }catch(SQLException ex){
            System.out.println("Error en ConexionSQL: "+ex);
            ex.printStackTrace(System.err);
        }
    }
    public static void closeConnection(PreparedStatement stmt){
        try{
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Error en ConexionSQL: "+ex);
            ex.printStackTrace(System.err);
        }
    }
    public static void closeConnection(Connection conn){
        try{
            conn.close();
        }catch(SQLException ex){
            System.out.println("Error en conexionSQL: "+ex);
        }
    }
}
