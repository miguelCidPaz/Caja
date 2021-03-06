/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.Pedidos;

import Acciones.ProductoService;
import Domain.Producto;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import ventanas.VentanaPrincipal;

/**
 *
 * @author migue
 */
public class Refrescos extends javax.swing.JPanel {
    Toolkit mipantalla = Toolkit.getDefaultToolkit();
    Dimension pantalla = mipantalla.getScreenSize();
    private int alto = pantalla.height; private int ancho = pantalla.width;
    DateTimeFormatter formatHora = DateTimeFormatter.ofPattern("HH:mm");
    
    /**
     * Creates new form Refrescos
     */
    public Refrescos() {
        initComponents();
        pintarFondoRefrescos();
    }
    
    //Metodo que pasa los datos de todos los botones
    private void pintarFondoRefrescos(){
        dibujar(BtnCoca, "src/main/java/imagenes/refrescos/coca.png", "Coca Cola");
        dibujar(BtnFanta, "src/main/java/imagenes/refrescos/fantas.png", "Fanta");
        dibujar(BtnTonica, "src/main/java/imagenes/refrescos/tonica.png", "Tonica");
        dibujar(BtnCerveza, "src/main/java/imagenes/refrescos/cerve.png", "Cerveza");
        dibujar(BtnCasera, "src/main/java/imagenes/refrescos/casera.png", "Casera");
        dibujar(BtnMonster, "src/main/java/imagenes/refrescos/energy.png", "EnergyDrinks");
        dibujar(BtnBitterKas, "src/main/java/imagenes/refrescos/bitter.png", "Bitter Kas");
        dibujar(BtnZumos, "src/main/java/imagenes/refrescos/zumo.png", "Zumos");

    }
    
    //Metodo usado para dibujar los botones
    private void dibujar(JButton boton, String url, String texto){
        try{
            Image primera = mipantalla.getImage(url);
            ImageIcon segunda = new ImageIcon(primera);
            boton.setIcon(segunda);
            boton.setText(texto);
            boton.setHorizontalTextPosition(SwingConstants.CENTER);
            boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        }catch(Exception e){
            System.out.println("Error en "+boton+" : "+e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelRefrescos = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        BtnCoca = new javax.swing.JButton();
        BtnFanta = new javax.swing.JButton();
        BtnTonica = new javax.swing.JButton();
        BtnCerveza = new javax.swing.JButton();
        BtnMonster = new javax.swing.JButton();
        BtnCasera = new javax.swing.JButton();
        BtnBitterKas = new javax.swing.JButton();
        BtnZumos = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(137, 169, 204));
        jPanel1.setLayout(null);

        BtnCoca.setBackground(new java.awt.Color(185, 145, 61));
        BtnCoca.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 14)); // NOI18N
        BtnCoca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCocaActionPerformed(evt);
            }
        });
        jPanel1.add(BtnCoca);
        BtnCoca.setBounds(10, 11, 107, 120);

        BtnFanta.setBackground(new java.awt.Color(185, 145, 61));
        BtnFanta.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 14)); // NOI18N
        BtnFanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFantaActionPerformed(evt);
            }
        });
        jPanel1.add(BtnFanta);
        BtnFanta.setBounds(137, 11, 105, 120);

        BtnTonica.setBackground(new java.awt.Color(185, 145, 61));
        BtnTonica.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 14)); // NOI18N
        BtnTonica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTonicaActionPerformed(evt);
            }
        });
        jPanel1.add(BtnTonica);
        BtnTonica.setBounds(260, 11, 105, 120);

        BtnCerveza.setBackground(new java.awt.Color(185, 145, 61));
        BtnCerveza.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 14)); // NOI18N
        BtnCerveza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCervezaActionPerformed(evt);
            }
        });
        jPanel1.add(BtnCerveza);
        BtnCerveza.setBounds(383, 11, 103, 120);

        BtnMonster.setBackground(new java.awt.Color(185, 145, 61));
        BtnMonster.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 14)); // NOI18N
        BtnMonster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMonsterActionPerformed(evt);
            }
        });
        jPanel1.add(BtnMonster);
        BtnMonster.setBounds(10, 150, 107, 120);

        BtnCasera.setBackground(new java.awt.Color(185, 145, 61));
        BtnCasera.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 14)); // NOI18N
        BtnCasera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCaseraActionPerformed(evt);
            }
        });
        jPanel1.add(BtnCasera);
        BtnCasera.setBounds(504, 11, 105, 120);

        BtnBitterKas.setBackground(new java.awt.Color(185, 145, 61));
        BtnBitterKas.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 14)); // NOI18N
        BtnBitterKas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBitterKasActionPerformed(evt);
            }
        });
        jPanel1.add(BtnBitterKas);
        BtnBitterKas.setBounds(140, 150, 110, 120);

        BtnZumos.setBackground(new java.awt.Color(185, 145, 61));
        BtnZumos.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 14)); // NOI18N
        BtnZumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnZumosActionPerformed(evt);
            }
        });
        jPanel1.add(BtnZumos);
        BtnZumos.setBounds(270, 150, 100, 120);

        PanelRefrescos.setViewportView(jPanel1);

        add(PanelRefrescos, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCocaActionPerformed
        // TODO add your handling code here:
        String descripcion = "Coca Cola"; int id = 017;
        crearproducto(descripcion,id);
    }//GEN-LAST:event_BtnCocaActionPerformed

    private void BtnFantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFantaActionPerformed
        // TODO add your handling code here:
        String descripcion = "Fanta"; int id = 020;
        crearproducto(descripcion, id);
    }//GEN-LAST:event_BtnFantaActionPerformed

    private void BtnTonicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTonicaActionPerformed
        // TODO add your handling code here:
        String descripcion = "Tonica"; int id = 021;
        crearproducto(descripcion, id);
    }//GEN-LAST:event_BtnTonicaActionPerformed

    private void BtnCervezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCervezaActionPerformed
        // TODO add your handling code here:
        String descripcion = "Cerveza"; int id = 022;
        crearproducto(descripcion, id);
    }//GEN-LAST:event_BtnCervezaActionPerformed

    private void BtnCaseraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCaseraActionPerformed
        // TODO add your handling code here:
        String descripcion = "Casera"; int id = 023;
        crearproducto(descripcion, id);
    }//GEN-LAST:event_BtnCaseraActionPerformed

    private void BtnMonsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMonsterActionPerformed
        // TODO add your handling code here:
        String descripcion = "Energy Drink"; int id = 024;
        crearproducto(descripcion, id);
    }//GEN-LAST:event_BtnMonsterActionPerformed

    private void BtnBitterKasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBitterKasActionPerformed
        // TODO add your handling code here:
        String descripcion = "Bitter Kas"; int id = 025;
        crearproducto(descripcion, id);
    }//GEN-LAST:event_BtnBitterKasActionPerformed

    private void BtnZumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnZumosActionPerformed
        // TODO add your handling code here:
        String descripcion = "Zumo"; int id = 026;
        crearproducto(descripcion, id);
    }//GEN-LAST:event_BtnZumosActionPerformed
    
    private void crearproducto(String descripcion, int id){
        
        try {
            String horaconsumicion = formatHora.format(LocalDateTime.now());
            Producto producto = new Producto();
            String clienteid = VentanaPrincipal.ClienteChoice.getSelectedItem();
            String empleado = VentanaPrincipal.VendedorChoice.getSelectedItem();
            int Ntiket = Integer.parseInt(VentanaPrincipal.ticketTXT.getText());
            ProductoService pd = new ProductoService();
            producto.setCantidad(1);producto.setDescripcion(descripcion);producto.setPrecio(1.00);
            producto.setPrecioTotal(producto.getPrecio()); producto.setidProducto(id);
            if(VentanaPrincipal.promocion){
                double descuento = producto.getPrecio()*20/100;
                producto.setPrecio(producto.getPrecio()-descuento);
            }
            pd.cestaCompra(VentanaPrincipal.cajapantalla);
            pd.comprobarcarrito(VentanaPrincipal.cajapantalla, producto, horaconsumicion, clienteid, empleado, Ntiket);
        } catch (SQLException ex) {
            System.out.println("Error al añadir producto "+ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBitterKas;
    private javax.swing.JButton BtnCasera;
    private javax.swing.JButton BtnCerveza;
    private javax.swing.JButton BtnCoca;
    private javax.swing.JButton BtnFanta;
    private javax.swing.JButton BtnMonster;
    private javax.swing.JButton BtnTonica;
    private javax.swing.JButton BtnZumos;
    private javax.swing.JScrollPane PanelRefrescos;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
