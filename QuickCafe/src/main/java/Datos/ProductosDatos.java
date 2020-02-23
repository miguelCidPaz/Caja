/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Domain.Producto;
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
public class ProductosDatos {
    //Declaracion de nuestra conexion para transacciones
    private Connection ConexionTransaccional;
    //Sentencias para interactuar con la BD
    private static final String SQL_SELECT="SELECT idproducto, cantidad, descripcion, preciounidad, preciototal FROM cestacompra";
    private static final String SQL_INSERT="INSERT INTO cestacompra (Nticket, idcliente, idempleado, cantidad, descripcion, preciounidad, preciototal, idproducto, horadeventa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE="UPDATE cestacompra SET cantidad=?, preciototal=? WHERE idproducto = ?";
    private static final String SQL_DELETE="DELETE FROM cestacompra WHERE idproducto=?";
    
    public ProductosDatos(){
    }
    //Constructor vacio y con la conexion  transaccional previamente creada
    public ProductosDatos(Connection conexionTransaccional){
        this.ConexionTransaccional=conexionTransaccional;
    }
    
    //Metodo Select para Productos, devuelve una lista de productos con todos los productos de la cesta compra
    //todos los atributos del producto incluido su id que servira para identificarlo
    public List<Producto>select()throws SQLException{
        //iniciamos todo a null y preparamos nuestra lista
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List <Producto> productos = new ArrayList<Producto>();
        
        try{//creamos nuestra conexion mediante un ternario que administrara el pool.
            conn = this.ConexionTransaccional !=null ? this.ConexionTransaccional : GestionSQL.openConnection();
            //creamos nuestro objeto preparestatement con la sentencia SQL_SELECT
            stmt = conn.prepareStatement(SQL_SELECT);
            //preparamos nuestro objeto resultset para que nos devuelva los valores
            rs = stmt.executeQuery();
            //Mientras existan valores...
            while(rs.next()){
                //asignamos estos a "auxiliares"
                int idproducto = rs.getInt("idproducto");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                double preciounidad = rs.getDouble("preciounidad");
                double preciototal = rs.getDouble("preciototal");
                
                //y damos forma a nuestro producto usando estos auxiliares
                producto = new Producto();
                producto.setidProducto(idproducto);
                producto.setCantidad(cantidad);
                producto.setDescripcion(descripcion);
                producto.setPrecio(preciounidad);
                producto.setPrecioTotal(preciototal);
                
                //agregamos a la lista el objeto producto
                productos.add(producto);
            }
        }finally{
            //finalmente se cierran conexiones
            GestionSQL.closeConnection(rs);
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
        }
        //devolvemos la lista productos
        return productos;
    }
    
    //Metodo que inserta el producto en la tabla de la BD, recibe su producto desde la clase ProductoService
    public int insert(Producto producto, String hora, String cliente, String empleado, int ticket)throws SQLException{
        int idcliente = Integer.parseInt(cliente); int idempleado = Integer.parseInt(empleado);
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            stmt = conn.prepareStatement (SQL_INSERT);
            
            stmt.setInt(1, ticket);
            stmt.setInt(2, idcliente);
            stmt.setInt(3, idempleado);
            stmt.setInt(4, producto.getCantidad());
            stmt.setString(5, producto.getDescripcion());
            stmt.setDouble(6, producto.getPrecio());
            stmt.setDouble(7, producto.getPrecioTotal());
            stmt.setInt(8, producto.getidProducto());
            stmt.setString(9, hora);
            
            System.out.println("Ejecutando salvado: "+SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Nª de registros modificados: "+rows);
        }finally{
            GestionSQL.closeConnection(stmt);
            if(this.ConexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
        }
        return rows;
    }
    
    //Metodo Update que permite modificar cantidad y preciototal de nuestro producto usando su id
    public int update(Producto producto)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            System.out.println("Ejecutando salvado: "+SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, producto.getCantidad());
            stmt.setDouble(2, producto.getPrecioTotal());
            stmt.setInt(3, producto.getidProducto());
            
            rows = stmt.executeUpdate();
            System.out.println("Nº registros afectados: "+rows);
        }finally{
            if(this.ConexionTransaccional==null){
                GestionSQL.closeConnection(conn);
            }
            GestionSQL.closeConnection(stmt);
        }
        return rows;
    }
    
    //Metodo delete que borrara al producto de la BD usando su id para localizarlo
    public int delete(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try{
            conn = this.ConexionTransaccional != null ? this.ConexionTransaccional : GestionSQL.openConnection();
            System.out.println("Ejecutando salvado: "+SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
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
