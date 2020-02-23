/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author migue
 */
public class Registro {
    private int nticket;
    private int idcliente;
    private int idempleado;
    private int cantidad;
    private String descripcion;
    private double preciounidad;
    private double preciototal;
    private int idproducto;
    private String horadeventa;

    public int getNticket() {
        return nticket;
    }

    public void setNticket(int nticket) {
        this.nticket = nticket;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPreciounidad() {
        return preciounidad;
    }

    public void setPreciounidad(double preciounidad) {
        this.preciounidad = preciounidad;
    }

    public double getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(double preciototal) {
        this.preciototal = preciototal;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getHoradeventa() {
        return horadeventa;
    }

    public void setHoradeventa(String horadeventa) {
        this.horadeventa = horadeventa;
    }

    public Registro() {
    }

    @Override
    public String toString() {
        return "Registro{" + "nticket=" + nticket + ", idcliente=" + idcliente + ", idempleado=" + idempleado + ", cantidad=" + cantidad + ", descripcion=" + descripcion + ", preciounidad=" + preciounidad + ", preciototal=" + preciototal + ", idproducto=" + idproducto + ", horadeventa=" + horadeventa + '}';
    }
    
    
}
