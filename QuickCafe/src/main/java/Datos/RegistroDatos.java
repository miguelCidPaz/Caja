/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Domain.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ventanas.VentanaPrincipal;

/**

 *Nticket, idcliente, idempleado, cantidad, descripcion, preciounidad, preciototal, idproducto, horadeventa
 * @author migue
 */
public class RegistroDatos {
    //Declaracion de conexion para transacciones y String que servira para comprobar tickets
    private static String nombretabla;
    private Connection conexionTransaccional;
    
    
    
    public RegistroDatos(){
    }
    public RegistroDatos(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    //Metodo que buscara el ticket solicitado de la tabla solicitada, en defecto usara la del dia actual
    public List <Registro> selectticket(int Nticket, String diaTicket) throws SQLException{
        if(diaTicket.isEmpty() || diaTicket.length()<5 || diaTicket.length()>8){
            nombretabla = VentanaPrincipal.DiaTXT.getText();
        }else if(diaTicket.length()==8){
            nombretabla = diaTicket;
        }
        nombretabla.trim();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Registro ticket = null;
        List<Registro>tickets = new ArrayList<Registro>();
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            System.out.println("realiza conexion a "+nombretabla);
            stmt = conn.prepareStatement("SELECT * FROM quickcafe.`"+nombretabla+"` WHERE Nticket=?");
            stmt.setInt(1,Nticket);
            System.out.println(stmt);
            rs = stmt.executeQuery();
            
            System.out.println("ejecutando");
            while(rs.next()){
                
                System.out.println("bucle");
                int nticket = rs.getInt("Nticket");
                int idcliente = rs.getInt("idcliente");
                int idempleado = rs.getInt("idempleado");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                double preciounidad = rs.getDouble("preciounidad");
                double preciototal = rs.getDouble("preciototal");
                int idproducto = rs.getInt("idproducto");
                String horadeventa = rs.getString("horadeventa");
                
                ticket = new Registro();
                ticket.setNticket(nticket);
                ticket.setIdcliente(idcliente);
                ticket.setIdempleado(idempleado);
                ticket.setCantidad(cantidad);
                ticket.setDescripcion(descripcion);
                ticket.setPreciounidad(preciounidad);
                ticket.setPreciototal(preciototal);
                ticket.setIdproducto(idproducto);
                ticket.setHoradeventa(horadeventa);
                
                tickets.add(ticket);
            }
        }catch(NullPointerException e){
            e.printStackTrace(System.out);
            System.out.println("Error desconocido : "+e);
        
        
        }finally{
            System.out.println(Nticket);
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return tickets;
    }
    
    //Metodo que hara select de las tablas por dia
    public List <Registro> select(String diaTicket) throws SQLException{
        if(diaTicket.isEmpty() || diaTicket.length()<5 || diaTicket.length()>8){
            nombretabla = VentanaPrincipal.DiaTXT.getText();
        }else if(diaTicket.length()==8){
            nombretabla = diaTicket;
        }
        nombretabla.trim();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Registro ticket = null;
        List<Registro>tickets = new ArrayList<Registro>();
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            System.out.println("realiza conexion a "+nombretabla);
            stmt = conn.prepareStatement("SELECT * FROM quickcafe.`"+nombretabla+"`");
            System.out.println(stmt);
            rs = stmt.executeQuery();
            
            System.out.println("ejecutando");
            while(rs.next()){
                
                System.out.println("bucle");
                int nticket = rs.getInt("Nticket");
                int idcliente = rs.getInt("idcliente");
                int idempleado = rs.getInt("idempleado");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                double preciounidad = rs.getDouble("preciounidad");
                double preciototal = rs.getDouble("preciototal");
                int idproducto = rs.getInt("idproducto");
                String horadeventa = rs.getString("horadeventa");
                
                ticket = new Registro();
                ticket.setNticket(nticket);
                ticket.setIdcliente(idcliente);
                ticket.setIdempleado(idempleado);
                ticket.setCantidad(cantidad);
                ticket.setDescripcion(descripcion);
                ticket.setPreciounidad(preciounidad);
                ticket.setPreciototal(preciototal);
                ticket.setIdproducto(idproducto);
                ticket.setHoradeventa(horadeventa);
                
                tickets.add(ticket);
            }
        }catch(NullPointerException e){
            e.printStackTrace(System.out);
            System.out.println("Error desconocido : "+e);
        
        
        }finally{
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return tickets;
    }
    
    public List <Registro> selectcliente(int idCliente, String diaTicket) throws SQLException{
        if(diaTicket.isEmpty() || diaTicket.length()<5 || diaTicket.length()>8){
            nombretabla = VentanaPrincipal.DiaTXT.getText();
        }else if(diaTicket.length()==8){
            nombretabla = diaTicket;
        }
        nombretabla.trim();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Registro ticket = null;
        List<Registro>tickets = new ArrayList<Registro>();
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            System.out.println("realiza conexion a "+nombretabla);
            stmt = conn.prepareStatement("SELECT * FROM quickcafe.`"+nombretabla+"` WHERE idcliente=?");
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            
            System.out.println("ejecutando");
            while(rs.next()){
                
                System.out.println("bucle");
                int nticket = rs.getInt("Nticket");
                int idcliente = rs.getInt("idcliente");
                int idempleado = rs.getInt("idempleado");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                double preciounidad = rs.getDouble("preciounidad");
                double preciototal = rs.getDouble("preciototal");
                int idproducto = rs.getInt("idproducto");
                String horadeventa = rs.getString("horadeventa");
                
                ticket = new Registro();
                ticket.setNticket(nticket);
                ticket.setIdcliente(idcliente);
                ticket.setIdempleado(idempleado);
                ticket.setCantidad(cantidad);
                ticket.setDescripcion(descripcion);
                ticket.setPreciounidad(preciounidad);
                ticket.setPreciototal(preciototal);
                ticket.setIdproducto(idproducto);
                ticket.setHoradeventa(horadeventa);
                
                tickets.add(ticket);
            }
        }catch(NullPointerException e){
            e.printStackTrace(System.out);
            System.out.println("Error desconocido : "+e);
        
        
        }finally{
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return tickets;
    }
    
    //Metodo que recogera los numeros de ticket ya existentes dentro de la tabla diaria
    public List <Integer> selectNumeroTickets(String diaTicket) throws SQLException{
        if(diaTicket.isEmpty() || diaTicket.length()<5 || diaTicket.length()>8){
            nombretabla = VentanaPrincipal.DiaTXT.getText();
        }else if(diaTicket.length()==8){
            nombretabla = diaTicket;
        }
        nombretabla.trim();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int nticket = 0;
        List<Integer>numerotickets = new ArrayList<Integer>();
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            System.out.println("realiza conexion a "+nombretabla);
            stmt = conn.prepareStatement("SELECT Nticket FROM quickcafe.`"+nombretabla+"`");
            rs = stmt.executeQuery();
            while(rs.next()){
                
                nticket = rs.getInt("Nticket");
                numerotickets.add(nticket);
            }
        }catch(NullPointerException e){
            e.printStackTrace(System.out);
            System.out.println("Error desconocido : "+e);
        
        
        }finally{
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return numerotickets;
    }
    
    public List <Registro> selectempleado(int idEmpleado, String diaTicket) throws SQLException{
        if(diaTicket.isEmpty() || diaTicket.length()<5 || diaTicket.length()>8){
            nombretabla = VentanaPrincipal.DiaTXT.getText();
        }else if(diaTicket.length()==8){
            nombretabla = diaTicket;
        }
        nombretabla.trim();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Registro ticket = null;
        List<Registro>tickets = new ArrayList<Registro>();
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            System.out.println("realiza conexion a "+nombretabla);
            stmt = conn.prepareStatement("SELECT * FROM quickcafe.`"+nombretabla+"` WHERE idempleado=?");
            stmt.setInt(1,idEmpleado);
            System.out.println(stmt);
            rs = stmt.executeQuery();
            
            System.out.println("ejecutando");
            while(rs.next()){
                
                int nticket = rs.getInt("Nticket");
                int idcliente = rs.getInt("idcliente");
                int idempleado = rs.getInt("idempleado");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                double preciounidad = rs.getDouble("preciounidad");
                double preciototal = rs.getDouble("preciototal");
                int idproducto = rs.getInt("idproducto");
                String horadeventa = rs.getString("horadeventa");
                
                ticket = new Registro();
                ticket.setNticket(nticket);
                ticket.setIdcliente(idcliente);
                ticket.setIdempleado(idempleado);
                ticket.setCantidad(cantidad);
                ticket.setDescripcion(descripcion);
                ticket.setPreciounidad(preciounidad);
                ticket.setPreciototal(preciototal);
                ticket.setIdproducto(idproducto);
                ticket.setHoradeventa(horadeventa);
                
                tickets.add(ticket);
            }
        }catch(NullPointerException e){
            e.printStackTrace(System.out);
            System.out.println("Error desconocido : "+e);
        
        
        }finally{
            System.out.println(idEmpleado);
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return tickets;
    }
    
    public List <Registro> selectgeneral() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Registro registro = null;
        List<Registro>registros = new ArrayList<Registro>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement("SELECT * FROM registroventapordia");
            rs = stmt.executeQuery();
            while(rs.next()){
                String fechaventa = rs.getString("fechaventa");
                double facturatotal = rs.getDouble("facturatotal");
                registro = new Registro();
                registro.setDescripcion(fechaventa);
                registro.setPreciototal(facturatotal);
                registros.add(registro);
            }
        } finally{
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
        }
        return registros;
    }
    public List <String> selectdefechas() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String>fechas = new ArrayList<String>();
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement("SELECT fechaventa FROM registroventapordia");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                String fecha = rs.getString("fechaventa");
                fechas.add(fecha);
            }
        }finally{
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return fechas;
    }
    
    public List <Registro> selectpordia(String dia) throws SQLException{
        if(dia.isEmpty() || dia.length()<5 || dia.length()>8){
            nombretabla = VentanaPrincipal.DiaTXT.getText();
        }else if(dia.length()==8){
            nombretabla = dia;
        }
        nombretabla.trim();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Registro registro = null;
        List<Registro>registros = new ArrayList<Registro>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement("SELECT * FROM registroventapordia WHERE fechaventa=?");
            stmt.setString(1, dia);
            rs = stmt.executeQuery();
            while(rs.next()){
                String fechaventa = rs.getString("fechaventa");
                double facturatotal = rs.getDouble("facturatotal");
                registro = new Registro();
                registro.setDescripcion(fechaventa);
                registro.setPreciototal(facturatotal);
                registros.add(registro);
            }
        } finally{
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
        }
        return registros;
    }
    
    public int insertfecha (Registro registro) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement("INSERT INTO registroventapordia (fechaventa, facturatotal) VALUES (?,?)");
            
            stmt.setString(1, registro.getDescripcion());
            stmt.setDouble(2, registro.getPreciototal());
            rows =stmt.executeUpdate();
            
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return rows;
    }
    
    public int updatefecha(String fecha, double factura) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : GestionSQL.openConnection();
            System.out.println("Ejecutando Update a fecha");
            stmt = conn.prepareStatement("UPDATE registroventapordia SET facturatotal=? WHERE fecha=?");
            stmt.setDouble(1, factura);
            stmt.setString(2, fecha);
            
            rows = stmt.executeUpdate();
            System.out.println("Numero de registros afectados : "+rows);
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.conexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return rows;
    }
}
