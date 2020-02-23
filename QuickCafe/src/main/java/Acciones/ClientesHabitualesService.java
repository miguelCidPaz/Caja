/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;
import Datos.GestionSQL;
import Datos.ClientesHabitualesDatos;
import Domain.ClienteHabitual;
import java.awt.Choice;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author miguel
 * @version 1.0
 */


/*
Esta clase servira de comunicacion entre la interfaz y su clase datos para los objetos ClienteHabitual
*/
public class ClientesHabitualesService {
    private Object [] datosfila;
    private DefaultTableModel modelo;
    
    public ClientesHabitualesService(){
    }
    
    //Metodo para limpiar tabla por completo
    public void limpiartabla(JTable pantallaclientes){
        modelo = (DefaultTableModel) pantallaclientes.getModel();
        while(modelo.getRowCount()>0) modelo.removeRow(0);
    }
    
    //construccion de la tabla de Clientes en la ventana de Administracion de clientes
    public DefaultTableModel tablaclientes(JTable pantallaclientes)throws SQLException{
        Connection conexion = null;
        limpiartabla(pantallaclientes);
        
        try{
            conexion = GestionSQL.openConnection();
            //si el autocommit esta activado lo desactiva para controlar desde el programa los commit
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            //Declaramos nuestra DefaultTableModel modelo tomando de base nuestra tabla de clientes
            DefaultTableModel modelo = (DefaultTableModel) pantallaclientes.getModel();
            ClientesHabitualesDatos clientedatos = new ClientesHabitualesDatos();
            List<ClienteHabitual> listaclientes = clientedatos.select();
            datosfila = new Object[modelo.getColumnCount()];
            
            //Mediante el bucle for procedemos a implementar todos los datos en sus respectivos lugares
            for(int i = 0 ; i<listaclientes.size(); i++){
                datosfila[0]=listaclientes.get(i).getIdCliente();
                datosfila[1]=listaclientes.get(i).getNombre();
                datosfila[2]=listaclientes.get(i).getDNI();
                datosfila[3]=listaclientes.get(i).getTelefono();
                datosfila[4]=listaclientes.get(i).getEmail();
                modelo.addRow(datosfila);
            }
        }catch(SQLException ex){
            //Tratamiento estandar de las excepciones mediante informacion en pantalla y el rollback
            //para evitar errores mayores
            System.out.println("Error en pantallaclientes de clientesservice");
            JOptionPane.showMessageDialog(null, "Error en el listado de clientes");
            System.out.println("Entramos al rollback");
            try{
                conexion.rollback();
            }catch(SQLException ex1){
                ex1.printStackTrace();
                System.out.println("Eror en cerrar conexion de listadoclientes");
            }
        }finally{
            //Cierra la conexion en el supuesto caso de que halla algo abierto, no es necesario
            //pero me dieron problemas las conexiones con este programa
            if(conexion != null){
                conexion.close();
            }
        }
        return modelo;
    }
    
    
    //Actualizador de las listas de los clientes, funciona mediante el choice de estos en la Ventana Principal
    //Imprescindible pues es lo que nos dara que cliente realiza la compra
    public void actualizarchoicecliente(Choice choice) throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            //Mediante un for agregamos los ids obtenidos al choice.
            //En ultimo lugar agregamos el "0" como cliente no registrado
            ClientesHabitualesDatos clientedatos = new ClientesHabitualesDatos(conexion);
            List<ClienteHabitual> listaclientes = clientedatos.select();
            for(int i = 0; i<listaclientes.size();i++){
                int auxiliar = listaclientes.get(i).getIdCliente();
                String segundoauxiliar = Integer.toString(auxiliar);
                choice.add(segundoauxiliar);
            }
            choice.add("0");
            
        }catch(SQLException e){
            //Por si llegase a ocurrir algun error en nuestro select a los ids
            System.out.println("Error en ChoiceCliente: "+e);
            try{
                conexion.rollback();
            }catch(SQLException ex){
                System.out.println("Error en rollback: "+ex);
            }
        }
    }
    
    //Metodo para agregar cliente a la BD gracias al objeto cliente pasado
    public void añadircliente(ClienteHabitual cliente) throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            ClientesHabitualesDatos clientesdatos = new ClientesHabitualesDatos(conexion);
            clientesdatos.insert(cliente);
            conexion.commit();
            System.out.println("Cliente ingresado con exito");
            
        }catch(SQLException ex){
            System.out.println("Error al insertar "+ex);
            try{
                //Tratamos la excepcion y se agega un rollback para evitar males mayores en la BD
                conexion.rollback();
                System.out.println("Procediendo a rollback");
            }catch(SQLException ex1){
                System.out.println("Error en rollback de insertado "+ex1);
            }
        }finally{
            if (conexion != null){
                conexion.close();
            }
        }
    }
    
    //Metodo para modificar Telefono y Email de un cliente usando su id
    public void modificarcliente(ClienteHabitual cliente)throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            ClientesHabitualesDatos clientesdatos = new ClientesHabitualesDatos(conexion);
            clientesdatos.update(cliente);
            conexion.commit();
            System.out.println("Cliente modificado con exito");
        }catch(SQLException e){
            System.out.println("Error en modificacion de cliente "+e);
            try{
                conexion.rollback();
            }catch(SQLException ex){
                System.out.println("Error en rollback de modificacion cliente "+ex);
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    //Metodo para borrar al cliente de la BD usando su id
    public void borrarcliente(ClienteHabitual cliente)throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            ClientesHabitualesDatos clientesdatos = new ClientesHabitualesDatos();
            clientesdatos.delete(cliente);
            conexion.commit();
            System.out.println("Cliente borrado con exito");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error en borrado");
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
    
    //Metodo que encuentra si el DNI del cliente agregado coincide con alguno ya registrado
    public void comprobarclientes(JTable pantallaclientes, ClienteHabitual cliente) throws SQLException{
        //Boolean auxiliar que detiene o permite la ejecucion del metodo
        boolean enmarcha = true;
        //Recorremos la tabla con un for recogiendo con el String auxiliar "comparador" el valor de las celdas de DNI
        for(int i = 0; i<pantallaclientes.getRowCount();i++){
            String comparador = pantallaclientes.getValueAt(i, 1).toString();
            //Si este encuentra que ese DNI ya esta se mostrara la informacion cambiando el Boolean a false y evitando que el metodo siga
            if(comparador.equals(cliente.getDNI())){
                JOptionPane.showMessageDialog(null, "Este DNI ya se encuentra en nuestra BD");
                String nombre = pantallaclientes.getValueAt(i, 2).toString();
                System.out.println("El cliente ya existe");
                System.out.println("Bajo el nombre: "+nombre);
                enmarcha=false;
            }
        }
        //Si no se encuentra el DNI en la tabla el programa procedera a añadir al Cliente
        if(enmarcha){
            try{
                añadircliente(cliente);
            }catch(SQLException e){
                System.out.println("Error al añadir tras la comprobacion: "+e);
            }
        }
    }
}




