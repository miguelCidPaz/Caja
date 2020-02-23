/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Datos.GestionSQL;
import Datos.ProductosDatos;
import Datos.RegistroDatos;
import Domain.Producto;
import Domain.Registro;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class RegistroService {
    private Object[] datosfila;
    private DefaultTableModel modelo;

    public RegistroService() {
    }
    
    private void limpiartabla(JTable pantallaRegistros){
        modelo = (DefaultTableModel) pantallaRegistros.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
    }
    
    public void calculapreciofinal(JTextField texto, String dia) throws SQLException{
        double precio = 0;
        Connection conexion = null;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            RegistroDatos rd = new RegistroDatos();
            if(dia.length()==8){
                List<Registro>registros = rd.selectpordia(dia);
                for(int i = 0 ; i<registros.size() ; i++){
                    precio = precio + registros.get(i).getPreciototal();
                }
                String preciofinal = String.valueOf(precio);
                texto.setText(preciofinal);
            }else{
                List<Registro>registros = rd.selectgeneral();
                for(int i = 0 ; i<registros.size() ; i++){
                    precio = precio + registros.get(i).getPreciototal();
                }
                String preciofinal = String.valueOf(precio);
                texto.setText(preciofinal);
            }
            
            
            
        }catch(SQLException e){
            e.printStackTrace(System.out);
            System.out.println("Error en calculopreciofinal en registroservice");
            conexion.rollback();
        }finally{
            if(conexion!=null){
                conexion.close();
            }
        }
        
    }
    
    //Metodo que dependiendo de su string buscara en una u otra tabla segun el numero de ticket de la compra
    public DefaultTableModel registrosticket(JTable pantallaRegistros,int nticket, String diaticket)throws SQLException{
        Connection conexion = null;
        limpiartabla(pantallaRegistros);
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            DefaultTableModel modelo = (DefaultTableModel) pantallaRegistros.getModel();
            RegistroDatos registrodatos = new RegistroDatos();
            List<Registro>registro = registrodatos.selectticket(nticket, diaticket);
            datosfila = new Object [modelo.getColumnCount()];
            
            for(int i = 0; i<registro.size();i++){
                datosfila[0]=registro.get(i).getNticket();
                datosfila[1]=registro.get(i).getIdcliente();
                datosfila[2]=registro.get(i).getIdempleado();
                datosfila[3]=registro.get(i).getCantidad();
                datosfila[4]=registro.get(i).getDescripcion();
                datosfila[5]=registro.get(i).getPreciounidad();
                datosfila[6]=registro.get(i).getPreciototal();
                datosfila[7]=registro.get(i).getIdproducto();
                datosfila[8]=registro.get(i).getHoradeventa();
                modelo.addRow(datosfila);
            }
            
        }catch(SQLException ex){
            System.out.println("Error en listado de registros"+ex);
            try{
                conexion.rollback();
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        return modelo;
    }
    
    //Metodo que dependiendo de su string mostrara todas las ventas de X dia
    public DefaultTableModel registrosdia(JTable pantallaRegistros,String diaticket)throws SQLException{
        Connection conexion = null;
        limpiartabla(pantallaRegistros);
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            DefaultTableModel modelo = (DefaultTableModel) pantallaRegistros.getModel();
            RegistroDatos registrodatos = new RegistroDatos();
            List<Registro>registro = registrodatos.select(diaticket);
            datosfila = new Object [modelo.getColumnCount()];
            
            for(int i = 0; i<registro.size();i++){
                datosfila[0]=registro.get(i).getNticket();
                datosfila[1]=registro.get(i).getIdcliente();
                datosfila[2]=registro.get(i).getIdempleado();
                datosfila[3]=registro.get(i).getCantidad();
                datosfila[4]=registro.get(i).getDescripcion();
                datosfila[5]=registro.get(i).getPreciounidad();
                datosfila[6]=registro.get(i).getPreciototal();
                datosfila[7]=registro.get(i).getIdproducto();
                datosfila[8]=registro.get(i).getHoradeventa();
                modelo.addRow(datosfila);
            }
            
        }catch(SQLException ex){
            System.out.println("Error en listado de registros"+ex);
            try{
                conexion.rollback();
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        return modelo;
    }
    
    
    //Metodo que mostrara usando el string todas las compras de un cliente en X dia
    public DefaultTableModel registroscliente(JTable pantallaRegistros,int idcliente, String diaticket)throws SQLException{
        Connection conexion = null;
        limpiartabla(pantallaRegistros);
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            DefaultTableModel modelo = (DefaultTableModel) pantallaRegistros.getModel();
            RegistroDatos registrodatos = new RegistroDatos();
            List<Registro>registro = registrodatos.selectcliente(idcliente, diaticket);
            datosfila = new Object [modelo.getColumnCount()];
            
            for(int i = 0; i<registro.size();i++){
                datosfila[0]=registro.get(i).getNticket();
                datosfila[1]=registro.get(i).getIdcliente();
                datosfila[2]=registro.get(i).getIdempleado();
                datosfila[3]=registro.get(i).getCantidad();
                datosfila[4]=registro.get(i).getDescripcion();
                datosfila[5]=registro.get(i).getPreciounidad();
                datosfila[6]=registro.get(i).getPreciototal();
                datosfila[7]=registro.get(i).getIdproducto();
                datosfila[8]=registro.get(i).getHoradeventa();
                modelo.addRow(datosfila);
            }
            
        }catch(SQLException ex){
            System.out.println("Error en listado de registros"+ex);
            try{
                conexion.rollback();
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        return modelo;
    }
    
    //Metodo que mostrara usando el string todas las ventas de un empleado en X dia
    public DefaultTableModel registrosempleado(JTable pantallaRegistros,int idempleado, String diaticket)throws SQLException{
        Connection conexion = null;
        limpiartabla(pantallaRegistros);
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            DefaultTableModel modelo = (DefaultTableModel) pantallaRegistros.getModel();
            RegistroDatos registrodatos = new RegistroDatos();
            List<Registro>registro = registrodatos.selectempleado(idempleado, diaticket);
            datosfila = new Object [modelo.getColumnCount()];
            
            for(int i = 0; i<registro.size();i++){
                datosfila[0]=registro.get(i).getNticket();
                datosfila[1]=registro.get(i).getIdcliente();
                datosfila[2]=registro.get(i).getIdempleado();
                datosfila[3]=registro.get(i).getCantidad();
                datosfila[4]=registro.get(i).getDescripcion();
                datosfila[5]=registro.get(i).getPreciounidad();
                datosfila[6]=registro.get(i).getPreciototal();
                datosfila[7]=registro.get(i).getIdproducto();
                datosfila[8]=registro.get(i).getHoradeventa();
                modelo.addRow(datosfila);
            }
            
        }catch(SQLException ex){
            System.out.println("Error en listado de registros"+ex);
            try{
                conexion.rollback();
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        return modelo;
    }
    
    //Metodo que recogera el ultimo ticket del dia una vez iniciado el programa
    public int numerotickets(String diaTicket) throws SQLException{
        int numeroticket=0;
        Connection conexion = null;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            RegistroDatos rd = new RegistroDatos();
            numeroticket = (int) comprobarticket(rd.selectNumeroTickets(diaTicket));
        } catch (SQLException ex) {
            System.out.println("Error al obtener el numero de tickets : "+ex);
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        
        return numeroticket;
    }
    
    //Metodo que controlara lo que devuelve el metodo del ticket para que en ningun caso devuelva nulo
    public int comprobarticket(List numerosticket){
        int numeromayor=0;
        //Si la lista esta vacia o da nulo el primero (innecesario pero why not) el metodo retornara 1, el primer ticket del dia
        if(numerosticket.isEmpty()){
            return 1;
            //sino esta vacia devolvera el ultimo elemento de la lista sumandole uno y preparando el siguiente ticket
        }else{
            //Usando una expresion lambda para encontrar el valor mas alto y sumandole uno)
            numeromayor = (int) numerosticket.stream().max(Comparator.naturalOrder()).get()+1;
            System.out.println("NUMERO MAYOR : "+numeromayor);
            return numeromayor;
        }
        
    }
    
    //Esto nos dara el registro general con l
    public DefaultTableModel registrogeneral(JTable pantallaRegistros) throws SQLException{
        Connection conexion = null;
        limpiartabla(pantallaRegistros);
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            DefaultTableModel modelo = (DefaultTableModel)pantallaRegistros.getModel();
            RegistroDatos registrodatos = new RegistroDatos();
            List<Registro> registros = registrodatos.selectgeneral();
            datosfila = new Object[modelo.getColumnCount()];
            
            for(int i = 0 ; i<registros.size(); i++){
                datosfila[0]=registros.get(i).getDescripcion();
                datosfila[1]=registros.get(i).getPreciototal();
                modelo.addRow(datosfila);
            }
        }catch(SQLException e){
            System.out.println("Error en Registro service, registrogeneral, pantallaregistros : "+e);
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        return modelo;
    }
    
    public DefaultTableModel registropordia(JTable pantallaRegistros, String dia) throws SQLException{
        Connection conexion = null;
        limpiartabla(pantallaRegistros);
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            DefaultTableModel modelo = (DefaultTableModel)pantallaRegistros.getModel();
            RegistroDatos registrodatos = new RegistroDatos();
            List<Registro> registros = registrodatos.selectpordia(dia);
            datosfila = new Object[modelo.getColumnCount()];
            
            for(int i = 0 ; i<registros.size(); i++){
                datosfila[0]=registros.get(i).getDescripcion();
                datosfila[1]=registros.get(i).getPreciototal();
                modelo.addRow(datosfila);
            }
        }catch(SQLException e){
            System.out.println("Error en Registro service, registrogeneral, pantallaregistros : "+e);
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }
        return modelo;
    }
    //Metodo que comprobara la lista de fechas antes de realizar la accion de insert o update
    public void comprobarfechas(String dia) throws SQLException{
        Connection conexion = null;
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
        
        //Declaracion de factura para acumular las ventas, junto con el String del dia que recibe para localizarlas
            double factura = 0;
            String resultado = null;
            boolean comprobacion = true;
            Registro registro = null;
        //Objeto RegistroDatos que llamara las funciones
            RegistroDatos rd = new RegistroDatos();
        //La primera lista se llenara con registros completos desde la tabla del dia indicado o el dia en curso
        //La segunda recibira solo las fechas almacenadas en la tabla registroventapordia
            List<Registro>registrosdeldia = rd.select(dia);
            List<String>registrofechas = rd.selectdefechas();
        
        //Recorremos la primera lista almacenando y sumando todos los valores de getpreciototal almacenados en los registros
            for (int i = 0 ; i<registrosdeldia.size() ; i++){
                factura = factura + registrosdeldia.get(i).getPreciototal();
            }
        
        //Recorremos el registro de fechas para averiguar si la fecha que queremos agregar ya esta
            for(int i = 0 ; i<registrofechas.size() ; i++){
            //si ya esta realizamos un update a la factura indicando el dia gracias al parametro recibido
                if(dia.equals(registrofechas.get(i))){
                    rd.updatefecha(dia, factura);
                    comprobacion = false;
                    resultado = "Dia encontrado y modificado";
                }
            }
        //si por el contrario no esta, es decir, el for termino sin cambiar el valor de la comprobacion
        //realizamos un insert a nuestra tabla registroventapordia con la nueva fecha y la nueva facturacion
            if(comprobacion){
                registro = new Registro();
                registro.setDescripcion(dia);
                registro.setPreciototal(factura);
                rd.insertfecha(registro);
                resultado = "Dia insertado";
            }
            System.out.println("Comprobacion de fechas realizada, resultado : "+resultado);
            }catch(SQLException e){
                System.out.println("Error en comprobacion registroservice : "+e);
                conexion.rollback();
            }finally{
                if(conexion != null){
                    conexion.close();
                }
            }
        }
    
}
