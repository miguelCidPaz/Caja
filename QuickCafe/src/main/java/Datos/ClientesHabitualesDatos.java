/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Domain.ClienteHabitual;
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
public class ClientesHabitualesDatos {
    //Declaracion de nuestra conexion para transacciones
    private Connection conexionTransaccional;
    //Sentencias para interactuar con la BD
    private static final String SQL_SELECT="SELECT * FROM clienteshabituales";
    private static final String SQL_INSERT="INSERT INTO clienteshabituales(NombreCliente, DNICliente, EmailCliente, TelefonoCliente) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE="UPDATE clienteshabituales SET EmailCliente=?, TelefonoCliente=? WHERE idCliente = ?";
    private static final String SQL_DELETE="DELETE FROM clienteshabituales WHERE idcliente=?";
    
    //Constructores uno vacio y otro con nuestra conexion para transacciones
    public ClientesHabitualesDatos(){
    }
    public ClientesHabitualesDatos(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    //Metodo Select para Clientes habituales, devuelve una lista de objetos cliente
    public List <ClienteHabitual>select()throws SQLException{
        //iniciamos todo a null y preparamos nuestra lista
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteHabitual clientehabitual = null;
        List <ClienteHabitual> clientes = new ArrayList<ClienteHabitual>();
        
        try{
            //creamos nuestra conexion mediante un ternario que administrara el pool.
            conn = this.conexionTransaccional !=null ? this.conexionTransaccional : GestionSQL.openConnection();
            //creamos nuestro objeto preparestatement con la sentencia SQL_SELECT
            stmt = conn.prepareStatement(SQL_SELECT);
            //preparamos nuestro objeto resultset para que nos devuelva los valores
            rs = stmt.executeQuery();
            //Mientras existan valores
            while(rs.next()){
                
                //asignamos estos a "auxiliares"
                int id = rs.getInt("idcliente");
                String NombreCliente = rs.getString("NombreCliente");
                String DNICliente = rs.getString("DNICliente");
                String EmailCliente = rs.getString("EmailCliente");
                String TelefonoCliente = rs.getString("TelefonoCliente");
                
                //y damos forma a nuestro cliente usando estos auxiliares
                clientehabitual = new ClienteHabitual();
                clientehabitual.setIdCliente(id);
                clientehabitual.setNombre(NombreCliente);
                clientehabitual.setDNI(DNICliente);
                clientehabitual.setEmail(EmailCliente);
                clientehabitual.setTelefono(TelefonoCliente);
                
                //agregamos a la lista
                clientes.add(clientehabitual);
            }
        }catch(SQLException ex){
            System.out.println("Error en Select()ClienteshabitualesDatos : "+ex);
            ex.printStackTrace(System.err);
        }finally{
            //en el finally que se ejecutara siempre si o si cerramos todas las conexiones
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
        }
        //devolvemos la lista 
        return clientes;
    }
    
    //Metodo que inserta el cliente habitual en la tabla de la BD, recibe su cliente desde la clase clienteshabitualesservice
    public int insert(ClienteHabitual clientehabitual)throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.conexionTransaccional !=null ? this.conexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(2, clientehabitual.getNombre());
            stmt.setString(1, clientehabitual.getDNI());
            stmt.setString(3, clientehabitual.getEmail());
            stmt.setString(4, clientehabitual.getTelefono());
            
            System.out.print("Ejecutando salvado: "+SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Nº registros modificados: "+rows);
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
        }
        return rows;
    }
    
    //Metodo Update que permite modificar email y telefono de nuestro cliente usando su id
    public int update(ClienteHabitual clientehabitual)throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            System.out.println("Ejecutando salvado: "+SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, clientehabitual.getEmail());
            stmt.setString(2, clientehabitual.getTelefono());
            stmt.setInt(3, clientehabitual.getIdCliente());
            
            rows = stmt.executeUpdate();
            System.out.println("NºRegistros Afectados: "+rows);
        }finally{
            if(this.conexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
            GestionSQL.closeConnection(stmt);
        }
        return rows;
    }
    
    //Metodo delete que borrara al cliente de la BD usando su id para localizarlo
    public int delete(ClienteHabitual clientehabitual)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            System.out.println("Ejecutando salvado: "+rows);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, clientehabitual.getIdCliente());
            rows = stmt.executeUpdate();
            System.out.println("N! registros afectados: "+rows);
        }finally{
            if(this.conexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
            GestionSQL.closeConnection(stmt);
        }
        return rows;
    } 
}
