/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Domain.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author migue
 */
public class EmpleadosDatos {
    //Declaracion de nuestra conexion para transacciones
    private Connection ConexionTransaccional;
    //Sentencias para interactuar con la BD
    private static String SQL_SELECT="SELECT * FROM empleados";
    private static String SQL_INSERT="INSERT INTO empleados (nombre, DNI, telefono, email) VALUES (?, ?, ?, ?)";
    private static String SQL_UPDATE ="UPDATE empleados SET telefono=?, email=? WHERE idempleado=?";
    private static String SQL_DELETE="DELETE FROM empleados WHERE idempleado=?";
    
    public EmpleadosDatos(){
        
    }
    //Constructor vacio y con la conexion  transaccional previamente creada
    public EmpleadosDatos(Connection conexionTransaccional){
        this.ConexionTransaccional=conexionTransaccional;
    }
    
    //Metodo Select para Empleados, devuelve una lista de objetos empleado
    public List <Empleado>select()throws SQLException{
        //iniciamos todo a null y preparamos nuestra lista
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleado empleado = null;
        List <Empleado> empleados = new ArrayList <Empleado>();
        
        try{
            //creamos nuestra conexion mediante un ternario que administrara el pool.
            conn = this.ConexionTransaccional !=null ? this.ConexionTransaccional : GestionSQL.openConnection();
            //creamos nuestro objeto preparestatement con la sentencia SQL_SELECT
            stmt = conn.prepareStatement(SQL_SELECT);
            //preparamos nuestro objeto resultset para que nos devuelva los valores
            rs = stmt.executeQuery();
            //Mientras existan valores
            while(rs.next()){
                //asignamos estos a "auxiliares"
                int idempleado = rs.getInt("idempleado");
                String nombre = rs.getString("nombre");
                String DNI = rs.getString("DNI");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                
                //y damos forma a nuestro empleado usando estos auxiliares
                empleado = new Empleado();
                empleado.setIdempleado(idempleado);
                empleado.setNombre(nombre);
                empleado.setDNI(DNI);
                empleado.setTelefono(telefono);
                empleado.setEmail(email);
                
                //agregamos a la lista el objeto empleado
                empleados.add(empleado);
            }
        }finally{
            //finalmente se cierran conexiones
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
        }
        //devolvemos la lista empleados
        return empleados;
    }
    
    //Metodo que inserta el empleado en la tabla de la BD, recibe su empleado desde la clase empleadosservice
    public int insert(Empleado empleado)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.ConexionTransaccional != null ?this.ConexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getDNI());
            stmt.setString(3, empleado.getTelefono());
            stmt.setString(4, empleado.getEmail());
            
            System.out.println("Ejecutando salvado: "+SQL_INSERT);
            rows =stmt.executeUpdate();
            System.out.println("Nº registros modificados: "+rows);
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
        }
        return rows;
    }
    
    //Metodo Update que permite modificar email y telefono de nuestro empleado usando su id
    public int update(Empleado empleado)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            System.out.println("Ejecutando salvado: "+SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, empleado.getTelefono());
            stmt.setString(2, empleado.getEmail());
            stmt.setInt(3, empleado.getIdempleado());
            
            rows = stmt.executeUpdate();
            System.out.println("Nº registros afectados: "+rows);
        }finally{
            if(this.ConexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
            GestionSQL.closeConnection(stmt);
        }
        return rows;
    }
    
    //Metodo delete que borrara al empleado de la BD usando su id para localizarlo
    public int delete(Empleado empleado)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            System.out.println("Ejecutando salvado: "+ SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, empleado.getIdempleado());
            rows = stmt.executeUpdate();
            System.out.println("Nº registros afectados: "+rows);
        }finally{
            if(this.ConexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
            GestionSQL.closeConnection(stmt);
        }
        return rows;
    }
}
