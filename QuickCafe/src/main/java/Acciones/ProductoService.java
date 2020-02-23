/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Datos.GestionSQL;
import Datos.ProductosDatos;
import Domain.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ventanas.VentanaPrincipal;

/**
 *
 * @author migue
 */
public class ProductoService {
    private Object[] datosfila;
    private DefaultTableModel modelo;
    String[] nombresColumnas={
        "idProducto","Cantidad","Descripcion","Preciounidad","PrecioTotal"
    };
    
    
    
    
    public ProductoService(){
    }
    
    //Limpiamos la tabla que hace de pantalla para la caja
    public void limpiartabla(JTable pantallaCaja){
        modelo = (DefaultTableModel) pantallaCaja.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
    }
    
    //Metodo para dar formato y datos a la tabla que hara de pantalla de caja
    public DefaultTableModel cestaCompra(JTable pantallaCaja)throws SQLException{
        Connection conexion = null;
        limpiartabla(pantallaCaja);
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            DefaultTableModel modelo = (DefaultTableModel) pantallaCaja.getModel();
            ProductosDatos productosdatos = new ProductosDatos();
            List<Producto> listaproductos = productosdatos.select();
            datosfila = new Object[modelo.getColumnCount()];
            
            for(int i = 0; i<listaproductos.size();i++){
                datosfila[0]=listaproductos.get(i).getidProducto();
                datosfila[1]=listaproductos.get(i).getCantidad();
                datosfila[2]=listaproductos.get(i).getDescripcion();
                datosfila[3]=listaproductos.get(i).getPrecio();
                datosfila[4]=listaproductos.get(i).getPrecioTotal();
                modelo.addRow(datosfila);
            }
            
        }catch(SQLException ex){
            System.out.println("Error en cestaCompra ProductoService: "+ex);
            JOptionPane.showMessageDialog(null,"Error en el listado");
            System.out.println("Entramos al rollback");
            try{
                conexion.rollback();
            }catch(SQLException ex1){
                ex1.printStackTrace();
                System.out.println("Error en cerrar conexionex: "+ex1);
            }
        }finally{
            if(conexion != null){
               conexion.close(); 
            }
            
        }
        calculapreciofinal();
        //modelo retornado con todas las modificaciones
        return modelo;
    }
    
    //Metodo que comprueba la tabla antes de añadir producto
    public void comprobarcarrito(JTable pantallaCaja, Producto producto, String hora, String cliente, String empleado, int ticket) throws SQLException{
        //boolean auxiliar que condiciona el metodo completo
        boolean compraenmarcha = true;
        //creamos un FOR que busca por toda la tabla
        for (int i = 0;i<pantallaCaja.getRowCount();i++){
            //creamos un string auxiliar "comparador" que tomara el valor de los campos Descripcion
            String comparador = pantallaCaja.getValueAt(i, 2).toString();
            //si comparador coincide con la descripcion del producto pasara a modificarse
            //la cantidad y el preciofinal de este
            if(comparador.equals(producto.getDescripcion())){
                int cantidad = (int)(pantallaCaja.getValueAt(i, 1));
                producto.setCantidad(producto.getCantidad()+cantidad);
                double precioTotal = producto.getPrecio();
                double preciofinal = (double)(pantallaCaja.getValueAt(i, 4));
                precioTotal = precioTotal+preciofinal;
                producto.setPrecioTotal(precioTotal);
                int cantidadFinal=producto.getCantidad();
                Object cantidadfinal = (Object)cantidadFinal;
                Object preciototal=(Object)precioTotal;
                Object precio = (Object)producto.getPrecio();
                pantallaCaja.setValueAt(precio, i, 3);
                pantallaCaja.setValueAt(cantidadfinal, i, 1);
                pantallaCaja.setValueAt(preciototal, i, 4);
                redimensionarProducto(producto);
                //finalmente el boolean auxiliar pasara a false para evitar la
                //ejecucion del resto del metodo
                compraenmarcha=false;
            }
        }   
            //si comparador no coincidio con ninguno al final del ciclo FOR significa
            //que ese producto no esta en la cesta asi que añadira al carrito junto con
            //toda la informacion residual
            if(compraenmarcha){
                try{
                    añadirCarrito(producto, hora, cliente, empleado, ticket); 
                    compraenmarcha=false;
                }catch(SQLException e){
                    e.printStackTrace(System.out);
                    System.out.println("Error al añadir a carrito");
                } 
            }
            cestaCompra(VentanaPrincipal.cajapantalla);
            //Tras todas las comprobaciones realizamos un ultimo select para obtener
            //la version actualizada de la tabla
            calculapreciofinal();
    };
    
    private void calculapreciofinal(){
        double preciofinal=0;
        String precio=null;
        VentanaPrincipal.Sumaprecios.setText(null);
        int auxiliar = VentanaPrincipal.cajapantalla.getRowCount();
            for(int i=0;i<auxiliar;i++){
                //con un for creamos un segundo auxiliar que tomara el valor de las celdas de preciofinal
                String segundoauxiliar=String.valueOf(VentanaPrincipal.cajapantalla.getValueAt(i, 4));
                //pasamos el dato a double y lo sumamos
                preciofinal = preciofinal+Double.parseDouble(segundoauxiliar);
                //devolvemos el dato a string y finalmente lo pasamos devuelta al cuadro de texto que sirve de suma
                //para la pantalla de la caja
                precio = String.valueOf(preciofinal);
            }
        VentanaPrincipal.Sumaprecios.setText(precio);
    }
        
    //Metodo que añade el producto a la tabla de la BD que nos hace de carrito de la compra
    public void añadirCarrito(Producto producto, String hora, String cliente, String empleado, int ticket)throws SQLException{
        //sobra decirlo, pero por si acaso, toda conexion se inicia a null;
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            ProductosDatos productodatos = new ProductosDatos(conexion);
            //usando el productodatos reciencreado hacemos un insert de nuestro producto 
            //y los datos necesarios, cliente empleado hora y ticket
            productodatos.insert(producto, hora, cliente, empleado, ticket);
            conexion.commit();
            System.out.println("Producto Insertado al carro");
        }catch(SQLException ex){
            //Aqui iria el boton modificar para usar la descripcion ya dada
            ex.printStackTrace(System.out);
            try{
                //si algo sale mal y nos salta excepcion realiza un rollback para mantener
                // el buen estado de la BD
                conexion.rollback();
                System.out.println("ejecutando Rollback");
            }catch(SQLException ex1){
                ex1.printStackTrace(System.out);
            }
        }
        finally{
            if(conexion != null){
               conexion.close(); 
            }
            
        }
    }
    
    //Metodo para modificar la cantidad y el preciofinal del producto usando su id
    public void redimensionarProducto(Producto producto) throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            ProductosDatos productosdatos = new ProductosDatos(conexion);
            
            producto.setidProducto(producto.getidProducto());
            producto.setCantidad(producto.getCantidad());
            producto.setPrecioTotal(producto.getPrecioTotal());
            
            //update usando productosdatos
            productosdatos.update(producto);
            conexion.commit();
            System.out.println("producto redimensionado con exito");
        }catch(SQLException ex){
            System.out.println("Error en modificacion de producto"+ex);
            try{
                conexion.rollback();
            }catch(SQLException ex1){
                ex1.printStackTrace(System.out);
            }
            
        }finally{
            if(conexion != null){
               conexion.close(); 
            }
            
        }
    }
    
    //Metodo para borrar producto de la tabla usando su id
    public void borrarProducto(int id) throws SQLException{
        Connection conexion = null;
        
        try{
            conexion = GestionSQL.openConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            ProductosDatos productosdatos = new ProductosDatos();
            productosdatos.delete(id);
            
            conexion.commit();
            System.out.println("Borrado producto");
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            System.out.println("Error en borrado "+ex);
            try{
                conexion.rollback();
            }catch(SQLException ex1){
                System.out.println("Error en el rollback "+ex1);
            } 
            
            
        }finally{
            if(conexion != null){
               conexion.close();
            }
        }
    }
    
    public void pasarfactura(JTable pantallacaja, boolean promocion){
        double precio=0;
        for(int i = 0; i<pantallacaja.getRowCount();i++){
            System.out.println("idproducto : "+pantallacaja.getValueAt(i, 0)+" cantidad : "+pantallacaja.getValueAt(i, 1)+" descripcion : "+pantallacaja.getValueAt(i, 2)
            +" precio unidad : "+pantallacaja.getValueAt(i, 3));
            precio = precio + (double) pantallacaja.getValueAt(i, 4);
        }
        if(promocion){
                double valorpromocion = precio;
                valorpromocion = valorpromocion *20/100;
                System.out.println("Precio normal : "+precio);
                precio = precio - valorpromocion;
                System.out.println("Precio sin iva : "+precio+" con un descuento de "+valorpromocion);
        }else{
            System.out.println("Promocion deshabilitada");
            System.out.println("precio total : "+precio);
        }
    }
    
}
