/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Miguel
 * Clase del Domain para fabricar nuestros Clientes habituales con su constructor, 
 * sus getter and setters y su modificacion del tostring why not
 */
public class ClienteHabitual {
    private int idCliente;
    private String DNI;
    private String Nombre;
    private String Email;
    private String Telefono;

    public ClienteHabitual() {
    }

    @Override
    public String toString() {
        return "ClienteHabitual{" + "idCliente=" + idCliente + ", DNI=" + DNI + ", Nombre=" + Nombre + ", Email=" + Email + ", Telefono=" + Telefono + '}';
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getEmail() {
        return Email;
    }

    public String getTelefono() {
        return Telefono;
    }
    
}
