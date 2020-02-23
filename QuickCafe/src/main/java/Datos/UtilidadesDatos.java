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
import java.util.ArrayList;
import java.util.List;
import ventanas.VentanaPrincipal;

/**
 *
 * @author migue
 */
public class UtilidadesDatos {
    private Connection ConexionTransaccional;
    
    //Strings para manejar ticket de compra aun cerrandose el programa a mediodia
    private static final String SQL_NTICKET="SELECT idnticket FROM nticket";
    private static final String SQL_INSERTICKET = "INSERT INTO nticket (idnticket) VALUES(?)";
    private static final String SQL_DELETETICKET = "DELETE FROM nticket";
    
    //Strings de apoyo para crear nuevas tablas, copiar nuestra cesta en estas y borrarla para proceder a la siguiente venta
    private static String nombretabla = VentanaPrincipal.DiaTXT.getText();
    private static final String SQL_CREATE_TABLE="CREATE TABLE `"+nombretabla+"` (" +
"  `Nticket` int(11) NOT NULL," +
"  `idcliente` int(11) DEFAULT NULL," +
"  `idempleado` int(11) DEFAULT NULL," +
"  `cantidad` int(11) DEFAULT NULL,\n" +
"  `descripcion` varchar(45) DEFAULT NULL," +
"  `preciounidad` double DEFAULT NULL," +
"  `preciototal` double DEFAULT NULL," +
"  `idproducto` int(11) NOT NULL," +
"  `horadeventa` varchar(45) DEFAULT NULL," +
"  PRIMARY KEY (`idproducto`,`Nticket`)" +
") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
    private static final String SQL_VENTAREALIZADA="INSERT INTO `"+nombretabla+"` (Nticket, idcliente, idempleado, idproducto, cantidad, descripcion, preciounidad, preciototal, horadeventa)"
            + " SELECT Nticket, idcliente, idempleado, idproducto, cantidad, descripcion, preciounidad, preciototal, horadeventa FROM cestacompra";
    private static final String SQL_BORRARCAJA="DELETE FROM cestacompra";
    
    
    public UtilidadesDatos(){
        
    }
    
    public UtilidadesDatos(Connection ConexionTransaccional){
        this.ConexionTransaccional = ConexionTransaccional;
    }
    
    //Metodo que crea primer ticket para proceder a las ventas del dia
    public void crearticket(int nticket) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement(SQL_INSERTICKET);
            stmt.setInt(1, nticket);
            System.out.println("Ejecutando : "+SQL_INSERTICKET);
            stmt.executeUpdate();
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
    }
    
    //Metodo que accede a todos los tickets guardados en la tabla
    public List selecticket(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List <Integer> tickets = new ArrayList<Integer>();
        
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement(SQL_NTICKET);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int nticket = rs.getInt("idnticket");
                tickets.add(nticket);
            }
            System.out.println("Nºticket logrado con exito");
        }catch(SQLException e){
            System.out.println("Error en Select del ticket");
            e.printStackTrace(System.out);
        }finally{
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
        return tickets;
    }
    
    //Metodo que borra todos los tickets para cerrar ese dia, esa semana, ese mes, o cuando quieras tu resetear los datos
    public void borrartickets(){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement(SQL_DELETETICKET);
            stmt.execute();
            System.out.println("Tickets limpios");
        }catch(SQLException ex){
            System.out.println("Fallo al borrar tabla tickets : "+ex);
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
    }
    
    //Metodo que copia nuestra tabla cesta en la tabla para el control diario
    public void copiaTabla(){
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            stmt=conn.prepareStatement(SQL_VENTAREALIZADA);
            stmt.execute();
            System.out.println("Contenido de "+nombretabla+" copiado desde cestacompra");
        }catch(SQLException e){
            System.out.println("Error en salvado de datos a "+nombretabla+" "+e);
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
            
        }
    }
    
    //Metodo que crea la tabla diaria como para almacenar las ventas del dia
    public void creartabla() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            stmt=conn.prepareStatement(SQL_CREATE_TABLE);
            stmt.execute();
            System.out.println("tabla "+VentanaPrincipal.nombretabla+" creada");
            System.out.println("probando si esto sale");
        }catch(SQLException e){
            System.out.println("No pudo crearse la tabla "+VentanaPrincipal.nombretabla+ " por "+e);
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
            
        }
    }
    
    //Metodo que borrara la tabla de cesta que es la que usamos para las compras de cada cliente
    public void borrarTabla() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement(SQL_BORRARCAJA);
            stmt.execute();
            System.out.println("Cestacompra reseteada");
        } catch (SQLException ex) {
            System.out.println("Fallo al borrar tabla");
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional == null){
                GestionSQL.closeConnection(conn);
            }
        }
    }
}
