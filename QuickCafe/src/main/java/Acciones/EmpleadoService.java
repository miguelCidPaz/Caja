/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Datos.GestionSQL;
import Datos.EmpleadosDatos;
import Domain.Empleado;
import java.awt.Choice;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class EmpleadoService {
    private Object[] datosfila;
    private DefaultTableModel modelo;
    
    public EmpleadoService(){
    
}
    
    //Metodo para limpiar tabla completamente y evitar que se mezclen registros
    public void limpiartabla(JTable listaempleados){
        modelo = (DefaultTableModel) listaempleados.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
    }
    
    //Metodo que forma nuestra tablaempleados usando el select recogido en ProductosDatos
    public DefaultTableModel tablaempleados(JTable listaempleados)throws SQLException{
        Connection conexion = null;
        limpiartabla(listaempleados);
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            DefaultTableModel modelo = (DefaultTableModel) listaempleados.getModel();
            EmpleadosDatos empleadodatos = new EmpleadosDatos();
            List<Empleado>empleados = empleadodatos.select();
            datosfila = new Object[modelo.getColumnCount()];
            
            //La tabla de se llena mediante un FOR usando la lista de objectos empleado traida desde metodo select
            for(int i = 0; i<empleados.size(); i++){
                datosfila[0]=empleados.get(i).getIdempleado();
                datosfila[1]=empleados.get(i).getNombre();
                datosfila[2]=empleados.get(i).getDNI();
                datosfila[3]=empleados.get(i).getTelefono();
                datosfila[4]=empleados.get(i).getEmail();
                modelo.addRow(datosfila);
            }
        }catch(SQLException e){
            System.out.println("Error en listadoempleados: "+e);
            System.out.println("Entramos al rollback ¿Whynot?");
            try{
                conexion.rollback();
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println("Error en rollback");
            }finally{
                if(conexion != null){
                    conexion.close();
                }
            }
        }
        //retornamos el modelo que es el aspecto y los datos que le daremos a la tabla
        return modelo;
    }
    
    //Metodo para actualizar la seleccion de empleados usando la id de estos. 
    //Como no existe el empleado random el "0" no se añadio esta vez
    public void actualizadorchoiceempleado(Choice choice)throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            EmpleadosDatos empleadodatos = new EmpleadosDatos();
            List<Empleado>listaempleados = empleadodatos.select();
            for(int i = 0 ; i<listaempleados.size();i++){
                int auxiliar = listaempleados.get(i).getIdempleado();
                String segundoauxiliar = Integer.toString(auxiliar);
                choice.add(segundoauxiliar);
            }
        }catch(SQLException e){
            System.out.println("Error en Choice empleado: "+e);
            try{
                conexion.rollback();
            }catch(SQLException ex){
                System.out.println("Error en rollback choice empleado: "+ex);
            }
        }
    }
    
    //Metodo que añade empleados a la BD usando para ello el objeto empleado
    //creado en la ventana AdministracionEmpleados
    public void añadirEmpleado(Empleado empleado)throws SQLException{
        Connection conexion = null;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            EmpleadosDatos empleadodatos = new EmpleadosDatos(conexion);
            //realiza el insert de empleado usando el objeto empleadodatos reciencreado
            empleadodatos.insert(empleado);
            //realiza el commit
            conexion.commit();
            System.out.println("Empleado insertado con exito");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error, entramos al rollback");
            try{
                //se llama a rollback en caso de que ocurriese la excepcion
                conexion.rollback();
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println("Error en rollback de insertado empleado");
            }
        }finally{
            //si hubiese alguna conexion diferente a null esto la cerraria
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    //Metodo para modificar el email y/o telefono de los empleados usando su id
    public void modificarempleado(Empleado empleado)throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            EmpleadosDatos empleadodatos = new EmpleadosDatos();
            empleadodatos.update(empleado);
            conexion.commit();
            System.out.println("Empleado modificado con exito");
        }catch(SQLException e){
            System.out.println("Error en modificacion de empleado");
            e.printStackTrace();
            try{
                conexion.rollback();
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println("Error en rollback, vamos a morir todos");
                ex.printStackTrace();
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
    };
    
    //Metodo para borrar empleado de la BD usando su id
    public void borrarempleado(Empleado empleado)throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            EmpleadosDatos empleadodatos = new EmpleadosDatos();
            empleadodatos.delete(empleado);
            conexion.commit();
            System.out.println("Empleado borrado");
        }catch(SQLException e){
            System.out.println("Error en borrado, entramos a rollback");
            try{
                conexion.rollback();
            }catch(SQLException ex){
                System.out.println("Error en rollback");
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
    }
}
