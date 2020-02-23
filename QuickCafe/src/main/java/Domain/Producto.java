/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Miguel
 * Clase del Domain para fabricar nuestros productos con su constructor, 
 * sus getter and setters y su modificacion del tostring why not
 */
public class Producto {
    private int cantidad;
    private String descripcion;
    private double precio;
    private double preciototal;
    private int idProducto;

    public Producto() {
    }

    @Override
    public String toString() {
        return "Productos{" + "cantidad=" + cantidad + ", descripcion=" + descripcion + ", precio=" + precio + ", preciototal=" + preciototal + ", idProducto=" + idProducto + '}';
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public int getidProducto(){
        return idProducto;
    }
    
    public void setidProducto(int idProducto){
        this.idProducto = idProducto;
    }
    
    public double getPrecioTotal(){
        return preciototal;
    }
    
    public void setPrecioTotal(double preciototal){
        this.preciototal = preciototal;
    }
}
