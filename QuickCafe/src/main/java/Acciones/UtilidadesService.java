/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Datos.GestionSQL;
import Datos.UtilidadesDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class UtilidadesService {
    
    public UtilidadesService(){
        
    }
    
    //Metodo que inserta ticket en el primer momento del dia
    public void insertTicket(int nticket) throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UtilidadesDatos ud = new UtilidadesDatos();
            ud.crearticket(nticket);
        }catch(SQLException e){
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Ya tienes una lista de tickets activa, para mas info, click en ayuda");
            try{
                conexion.rollback();
                System.out.println("Ejecutando rollback");
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
                System.out.println("Error en rollback idtickets");
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    //Metodo para recoger tickets de nuestra tabla
    public int selecTiket() throws SQLException{
        Connection conexion = null;
        int nticket=0;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UtilidadesDatos ud = new UtilidadesDatos();
            List <Integer> tickets = ud.selecticket();
            //de esta forma se selecciona el ultimo ticket de la lista,
            //dado que la tabla usa estos ids como primary key esto siempre nos devolvera
            //el valor mas alto
            if(tickets.size()>0){
                nticket =tickets.get(tickets.size()-1);
            }
            
        }catch(SQLException e){
            System.out.println("Error en el listado de tickets : "+e);
            try{
                conexion.rollback();
                System.out.println("Rollback en tickets");
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
                System.out.println("Error en rollback de tickets");
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        //retornamos el ticket mas alto de la tabla
        return nticket;
    }
    
    //Metodo que comprueba el nº del ticket y el de la caja, sumando al mas alto 1 y 
    //haciendo insert de este al mismo tiempo que lo devuelve para hacer uso de el en la caja
    public String comprobarticket(String nticket, int listaticket){
        int ticket = Integer.parseInt(nticket);
        if(ticket < listaticket){
            ticket = listaticket;
        }
            ticket=ticket+1;
            try {
                insertTicket(ticket);
            } catch (SQLException ex) {
                System.out.println("Error al insertar el nuevo ticket : "+ex);
            }
            String resultado = String.valueOf(ticket);
            return resultado;
        
    }
    
    //Metodo que copia desde la tabla de cesta compra a nuestra tabla diaria
    public void copiatabla() throws SQLException{
        Connection conexion = null;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UtilidadesDatos pd = new UtilidadesDatos();
            pd.copiaTabla();
            conexion.commit();
        }catch(SQLException ex){
            System.out.println("Error al copiar tabla: "+ex);
            try{
                conexion.rollback();
            }catch(SQLException ex1){
                ex1.printStackTrace();
            }finally{
            if(conexion != null){
               conexion.close(); 
            }}
        }
    }
    
    //Metodo que sirve para borrar nuestra tabla cestacompra y dejarla lista
    //para la proxima compra
    public void borradocesta() throws SQLException{
        Connection conexion = null;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UtilidadesDatos pd = new UtilidadesDatos();
            pd.borrarTabla();
            conexion.commit();
        }catch(SQLException e){
            e.printStackTrace();
            try{
                conexion.rollback();
            }catch(SQLException ex){
                e.printStackTrace(System.out);
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    public void creandotabla() throws SQLException{
        Connection conexion = null;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UtilidadesDatos pd = new UtilidadesDatos();
            pd.creartabla();
            conexion.commit();
        }catch(SQLException e){
            e.printStackTrace();
            try{
                conexion.rollback();
            }catch(SQLException ex){
                e.printStackTrace(System.out);
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    public void borradotickets() throws SQLException{
        Connection conexion = null;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UtilidadesDatos pd = new UtilidadesDatos();
            pd.borrartickets();
            conexion.commit();
        }catch(SQLException e){
            e.printStackTrace();
            try{
                conexion.rollback();
            }catch(SQLException ex){
                e.printStackTrace(System.out);
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
    }
}
