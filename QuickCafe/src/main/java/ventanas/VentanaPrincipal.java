/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import Acciones.ClientesHabitualesService;
import Acciones.EmpleadoService;
import ventanas.Pedidos.Cafes;
import Acciones.ProductoService;
import Acciones.RegistroService;
import Acciones.UtilidadesService;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import ventanas.Administracion.AdministracionCaja;
import ventanas.Administracion.AdministracionClientes;
import ventanas.Administracion.AdministracionEmpleados;
import ventanas.Administracion.RegistrosPorDia;
import ventanas.Pedidos.Bocadillos;
import ventanas.Pedidos.Hamburguesas;
import ventanas.Pedidos.Refrescos;
import ventanas.ayuda.Ayuda;

/**
 *
 * @author migue
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    RegistroService rs = new RegistroService();
    UtilidadesService us = new UtilidadesService();
    public static String nombretabla;
    DateTimeFormatter formatHora = DateTimeFormatter.ofPattern("HH:mm");
    DefaultTableModel modelo = new DefaultTableModel();
    public static boolean promocion = false;
    private boolean findia = false;
    Toolkit mipantalla=Toolkit.getDefaultToolkit();
    Dimension pantalla = mipantalla.getScreenSize();
    private int alto = pantalla.height; private int ancho = pantalla.width;
    

    /**
     * Creacion de los diferentes cardlayour que se usaran para mostrar los 
     * productos
     */
    Cafes panelcafes = new Cafes();
    Refrescos panelrefrescos = new Refrescos();
    Hamburguesas panelhamburguesas = new Hamburguesas();
    Bocadillos panelbocadillos = new Bocadillos();
    CardLayout cafes;
    CardLayout refrescos;
    CardLayout hamburguesas;
    CardLayout bocadillos;
    //Creamos de antemano el productoservice para hacer uso de el mas adelante sin tener que construirlo cada vez
    ProductoService ps = new ProductoService();
    
    
    public VentanaPrincipal() {
        initComponents();
        FondoImagen();
        //iniciamos componentes y con ello todo lo demas
        cafes = (CardLayout)contenedorContenedores.getLayout();
        refrescos = (CardLayout)contenedorContenedores.getLayout();
        hamburguesas = (CardLayout)contenedorContenedores.getLayout();
        bocadillos = (CardLayout)contenedorContenedores.getLayout();
        //incluido el hilo que ira actualizando nuestro reloj en la ventanaprincipal
        hilo.start();
        DiaTXT.setText(fechaactual());
        nombretabla = DiaTXT.getText();
        //Un titulin rapido y un icono para que no nos salga la taza de cafe
        this.setTitle("Caja registradora Java");
        try{
            Image miicono = mipantalla.getImage("src/main/java/imagenes/cajaregistradora.png");
            setIconImage(miicono);
        }catch(Exception e){
            System.out.println("Icono no encontrado : "+e);
        }
    }
    
    
    
    //Creamos nuestro objeto runable que correra en el hilo
    Runnable runable = new Runnable() {
        @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(500);
                HoraTXT.setText(formatHora.format(LocalDateTime.now()));
            }catch(InterruptedException e){
                System.out.println("Fallo en reloj");
                e.printStackTrace();
            }
        }
            
    }
    };
    //asignamos el hilo al objeto runable recien creado
    Thread hilo = new Thread(runable);
    
    //Metodo usado para lograr la fecha cuando se ejecuta el programa
    private static String fechaactual(){
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YY");
        return formatoFecha.format(fecha);
    }
    
    //Este metodo permitira que aumente facilmente el ticket sin calentamientos de cabeza, el select solo se hara
    //al iniciarse el programa
    private static void ticket(){
        int ticket1 = Integer.parseInt(ticketTXT.getText());
        ticket1+=1;
        String ticket2 = String.valueOf(ticket1);
        ticketTXT.setText(ticket2);
    }
    
    //Esto da inicio a la caja
    private void aperturaCaja(){
        try {
            //Realizamos las primeras conexiones para actualizar y preparar la caja para la accion
            ps.cestaCompra(cajapantalla);
            rellenadoChoices();
            ticketTXT.setText("0");
        } catch (SQLException ex) {
            System.out.println("Error listado de inicio");
        }
    }
    //imagen de referencia para el boton ayuda :)
    private void FondoImagen(){
        try{
            Image mifondo = mipantalla.getImage("src/main/java/imagenes/cajaregistradora.png");
            ImageIcon fondin = new ImageIcon(mifondo);
            labelcaja.setIcon(fondin);
        }catch(Exception e){
            System.out.println("Error de carga en fondo");
            System.out.println(e);
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

        PanelPrincipal = new javax.swing.JPanel();
        eleccionEmpresa = new java.awt.Choice();
        etiquetaEmpresa = new java.awt.Label();
        VendedorChoice = new java.awt.Choice();
        etiquetaTiendas = new java.awt.Label();
        calculadora = new javax.swing.JPanel();
        BtnRefresco = new javax.swing.JButton();
        BtnPapelera = new javax.swing.JButton();
        flechaArriba = new javax.swing.JButton();
        BtnRealizarVenta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        contenedorPantalla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cajapantalla = new javax.swing.JTable();
        Sumaprecios = new java.awt.TextField();
        contenedorContenedores = new javax.swing.JPanel();
        ticketTXT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        contenedorOperaciones = new javax.swing.JPanel();
        BtnGestionEmpleados = new javax.swing.JButton();
        BtnGestionClientes = new javax.swing.JButton();
        BtnAdminCaja = new javax.swing.JButton();
        AperturaCajaBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        LuzTablaPnl = new javax.swing.JPanel();
        BtnRegistrodia = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        BtnContenedorCafes = new javax.swing.JButton();
        BtnContenedorRefrescos = new javax.swing.JButton();
        BtnContenedorHamburguesas = new javax.swing.JButton();
        BtnContenedorBocadillos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        eleccionTiendas1 = new java.awt.Choice();
        jLabel3 = new javax.swing.JLabel();
        ClienteChoice = new java.awt.Choice();
        contenedorReloj = new javax.swing.JPanel();
        decoracionReloj = new javax.swing.JPanel();
        DiaTXT = new javax.swing.JLabel();
        HoraTXT = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        labelcaja = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Btnfindia = new javax.swing.JButton();
        fechatxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setName("FramePrincipal"); // NOI18N

        PanelPrincipal.setBackground(new java.awt.Color(94, 172, 255));
        PanelPrincipal.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        PanelPrincipal.setLayout(null);
        PanelPrincipal.add(eleccionEmpresa);
        eleccionEmpresa.setBounds(100, 10, 136, 31);

        etiquetaEmpresa.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 18)); // NOI18N
        etiquetaEmpresa.setText("Empresa: ");
        PanelPrincipal.add(etiquetaEmpresa);
        etiquetaEmpresa.setBounds(10, 10, 85, 31);

        VendedorChoice.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        PanelPrincipal.add(VendedorChoice);
        VendedorChoice.setBounds(430, 80, 100, 31);

        etiquetaTiendas.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 18)); // NOI18N
        etiquetaTiendas.setText("Tienda:");
        PanelPrincipal.add(etiquetaTiendas);
        etiquetaTiendas.setBounds(260, 10, 67, 31);

        calculadora.setBackground(new java.awt.Color(137, 169, 204));

        BtnRefresco.setBackground(new java.awt.Color(185, 145, 61));
        BtnRefresco.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 11)); // NOI18N
        BtnRefresco.setText("Refresco Cliente y Empleado");
        BtnRefresco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefrescoActionPerformed(evt);
            }
        });

        BtnPapelera.setBackground(new java.awt.Color(185, 145, 61));
        BtnPapelera.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 11)); // NOI18N
        BtnPapelera.setText("Borrar Producto");
        BtnPapelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPapeleraActionPerformed(evt);
            }
        });

        flechaArriba.setBackground(java.awt.Color.red);
        flechaArriba.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 11)); // NOI18N
        flechaArriba.setText("Promocion");
        flechaArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flechaArribaActionPerformed(evt);
            }
        });

        BtnRealizarVenta.setBackground(new java.awt.Color(185, 145, 61));
        BtnRealizarVenta.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 11)); // NOI18N
        BtnRealizarVenta.setText("Realizar Venta");
        BtnRealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRealizarVentaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        jLabel4.setText("Realiza la venta");

        jLabel5.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        jLabel5.setText("Borra producto");

        jLabel6.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        jLabel6.setText("Aplicar promocion");

        jLabel7.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        jLabel7.setText("Refresca opciones de cliente y empleado");

        javax.swing.GroupLayout calculadoraLayout = new javax.swing.GroupLayout(calculadora);
        calculadora.setLayout(calculadoraLayout);
        calculadoraLayout.setHorizontalGroup(
            calculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calculadoraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnRefresco, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(calculadoraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(calculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calculadoraLayout.createSequentialGroup()
                        .addGroup(calculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(calculadoraLayout.createSequentialGroup()
                                .addComponent(BtnRealizarVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(calculadoraLayout.createSequentialGroup()
                                .addComponent(BtnPapelera, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(calculadoraLayout.createSequentialGroup()
                                .addComponent(flechaArriba)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 100, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calculadoraLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        calculadoraLayout.setVerticalGroup(
            calculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calculadoraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(calculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnRealizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(calculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnPapelera, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(calculadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(flechaArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnRefresco, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        PanelPrincipal.add(calculadora);
        calculadora.setBounds(320, 370, 350, 290);

        contenedorPantalla.setBackground(new java.awt.Color(137, 169, 204));
        contenedorPantalla.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jScrollPane1.setBackground(new java.awt.Color(255, 153, 0));

        cajapantalla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "idProducto", "Cantidad", "Descripcion", "PrecioUni", "PrecioTot"
            }
        ));
        cajapantalla.setEditingRow(0);
        jScrollPane1.setViewportView(cajapantalla);

        Sumaprecios.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 24)); // NOI18N

        javax.swing.GroupLayout contenedorPantallaLayout = new javax.swing.GroupLayout(contenedorPantalla);
        contenedorPantalla.setLayout(contenedorPantallaLayout);
        contenedorPantallaLayout.setHorizontalGroup(
            contenedorPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contenedorPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorPantallaLayout.createSequentialGroup()
                        .addGap(0, 184, Short.MAX_VALUE)
                        .addComponent(Sumaprecios, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        contenedorPantallaLayout.setVerticalGroup(
            contenedorPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(Sumaprecios, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PanelPrincipal.add(contenedorPantalla);
        contenedorPantalla.setBounds(310, 118, 370, 240);

        contenedorContenedores.setBackground(new java.awt.Color(137, 169, 204));
        contenedorContenedores.setLayout(new java.awt.CardLayout());
        PanelPrincipal.add(contenedorContenedores);
        contenedorContenedores.setBounds(690, 20, 740, 440);

        ticketTXT.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ticketTXT.setEnabled(false);
        PanelPrincipal.add(ticketTXT);
        ticketTXT.setBounds(310, 80, 70, 30);

        jLabel1.setFont(new java.awt.Font("BIZ UDMincho Medium", 0, 11)); // NOI18N
        jLabel1.setText("Nª Ticket");
        PanelPrincipal.add(jLabel1);
        jLabel1.setBounds(310, 60, 60, 12);

        contenedorOperaciones.setBackground(new java.awt.Color(137, 169, 204));

        BtnGestionEmpleados.setBackground(new java.awt.Color(150, 156, 255));
        BtnGestionEmpleados.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        BtnGestionEmpleados.setText("Gestion de Empleados");
        BtnGestionEmpleados.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnGestionEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGestionEmpleadosActionPerformed(evt);
            }
        });

        BtnGestionClientes.setBackground(new java.awt.Color(150, 156, 255));
        BtnGestionClientes.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        BtnGestionClientes.setText("Gestion de Clientes");
        BtnGestionClientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnGestionClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnGestionClientesMouseClicked(evt);
            }
        });

        BtnAdminCaja.setBackground(new java.awt.Color(150, 156, 255));
        BtnAdminCaja.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        BtnAdminCaja.setText("Administracion Caja");
        BtnAdminCaja.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAdminCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdminCajaActionPerformed(evt);
            }
        });

        AperturaCajaBtn.setBackground(new java.awt.Color(150, 156, 255));
        AperturaCajaBtn.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        AperturaCajaBtn.setText("Apertura de Caja");
        AperturaCajaBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AperturaCajaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AperturaCajaBtnActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        LuzTablaPnl.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout LuzTablaPnlLayout = new javax.swing.GroupLayout(LuzTablaPnl);
        LuzTablaPnl.setLayout(LuzTablaPnlLayout);
        LuzTablaPnlLayout.setHorizontalGroup(
            LuzTablaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        LuzTablaPnlLayout.setVerticalGroup(
            LuzTablaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LuzTablaPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LuzTablaPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        BtnRegistrodia.setBackground(new java.awt.Color(150, 156, 255));
        BtnRegistrodia.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        BtnRegistrodia.setText("Registros por dia");
        BtnRegistrodia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnRegistrodia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRegistrodiaMouseClicked(evt);
            }
        });
        BtnRegistrodia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrodiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contenedorOperacionesLayout = new javax.swing.GroupLayout(contenedorOperaciones);
        contenedorOperaciones.setLayout(contenedorOperacionesLayout);
        contenedorOperacionesLayout.setHorizontalGroup(
            contenedorOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contenedorOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BtnRegistrodia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contenedorOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BtnGestionEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnGestionClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAdminCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contenedorOperacionesLayout.createSequentialGroup()
                            .addComponent(AperturaCajaBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        contenedorOperacionesLayout.setVerticalGroup(
            contenedorOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorOperacionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnRegistrodia, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnGestionClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnGestionEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnAdminCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(contenedorOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AperturaCajaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorOperacionesLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        PanelPrincipal.add(contenedorOperaciones);
        contenedorOperaciones.setBounds(20, 60, 180, 430);

        jPanel1.setBackground(new java.awt.Color(137, 169, 204));

        BtnContenedorCafes.setBackground(new java.awt.Color(150, 156, 255));
        BtnContenedorCafes.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        BtnContenedorCafes.setText("Cafes");
        BtnContenedorCafes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnContenedorCafesActionPerformed(evt);
            }
        });

        BtnContenedorRefrescos.setBackground(new java.awt.Color(150, 156, 255));
        BtnContenedorRefrescos.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        BtnContenedorRefrescos.setText("Refrescos");
        BtnContenedorRefrescos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnContenedorRefrescosActionPerformed(evt);
            }
        });

        BtnContenedorHamburguesas.setBackground(new java.awt.Color(150, 156, 255));
        BtnContenedorHamburguesas.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        BtnContenedorHamburguesas.setText("Hamburguesas");
        BtnContenedorHamburguesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnContenedorHamburguesasActionPerformed(evt);
            }
        });

        BtnContenedorBocadillos.setBackground(new java.awt.Color(150, 156, 255));
        BtnContenedorBocadillos.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 12)); // NOI18N
        BtnContenedorBocadillos.setText("Bocadillos");
        BtnContenedorBocadillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnContenedorBocadillosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnContenedorCafes, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnContenedorRefrescos)
                .addGap(18, 18, 18)
                .addComponent(BtnContenedorHamburguesas)
                .addGap(18, 18, 18)
                .addComponent(BtnContenedorBocadillos)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnContenedorCafes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnContenedorRefrescos, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnContenedorHamburguesas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnContenedorBocadillos, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        PanelPrincipal.add(jScrollPane2);
        jScrollPane2.setBounds(690, 480, 500, 130);

        jLabel2.setFont(new java.awt.Font("BIZ UDMincho Medium", 0, 11)); // NOI18N
        jLabel2.setText("Vendedor");
        PanelPrincipal.add(jLabel2);
        jLabel2.setBounds(460, 60, 50, 20);
        PanelPrincipal.add(eleccionTiendas1);
        eleccionTiendas1.setBounds(330, 10, 130, 31);

        jLabel3.setFont(new java.awt.Font("BIZ UDMincho Medium", 0, 11)); // NOI18N
        jLabel3.setText("Cliente");
        PanelPrincipal.add(jLabel3);
        jLabel3.setBounds(570, 60, 50, 20);

        ClienteChoice.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        PanelPrincipal.add(ClienteChoice);
        ClienteChoice.setBounds(550, 80, 100, 31);

        contenedorReloj.setBackground(new java.awt.Color(137, 169, 204));
        contenedorReloj.setLayout(null);

        decoracionReloj.setBackground(new java.awt.Color(255, 255, 255));
        decoracionReloj.setLayout(null);

        DiaTXT.setFont(new java.awt.Font("BIZ UDMincho Medium", 0, 10)); // NOI18N
        DiaTXT.setText("55/55/55");
        decoracionReloj.add(DiaTXT);
        DiaTXT.setBounds(20, 0, 70, 20);

        HoraTXT.setFont(new java.awt.Font("BIZ UDMincho Medium", 0, 10)); // NOI18N
        HoraTXT.setText("55:55");
        decoracionReloj.add(HoraTXT);
        HoraTXT.setBounds(90, 0, 40, 20);

        contenedorReloj.add(decoracionReloj);
        decoracionReloj.setBounds(10, 10, 140, 20);

        PanelPrincipal.add(contenedorReloj);
        contenedorReloj.setBounds(500, 10, 160, 40);

        jPanel3.setBackground(new java.awt.Color(137, 169, 204));

        jButton4.setBackground(new java.awt.Color(185, 145, 61));
        jButton4.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 11)); // NOI18N
        jButton4.setText("Ayuda");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelcaja, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelcaja, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelPrincipal.add(jPanel3);
        jPanel3.setBounds(20, 500, 180, 160);

        jPanel2.setBackground(new java.awt.Color(137, 169, 204));

        Btnfindia.setBackground(new java.awt.Color(0, 255, 51));
        Btnfindia.setFont(new java.awt.Font("BIZ UDMincho Medium", 1, 18)); // NOI18N
        Btnfindia.setText("Fin Dia");
        Btnfindia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnfindiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Btnfindia, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fechatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Btnfindia, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(fechatxt))
                .addContainerGap())
        );

        PanelPrincipal.add(jPanel2);
        jPanel2.setBounds(1200, 500, 230, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1466, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //ActionPermoed para traer a la vista el panel de los cafes
    private void BtnContenedorCafesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnContenedorCafesActionPerformed
        // TODO add your handling code here:
        contenedorContenedores.remove(this);
        contenedorContenedores.add(panelcafes, "cafes");
        cafes.show(contenedorContenedores, "cafes");
        SwingUtilities.updateComponentTreeUI(contenedorContenedores);
        contenedorContenedores.repaint();
    }//GEN-LAST:event_BtnContenedorCafesActionPerformed
    
    //ActionPermoed para traer a la vista el panel de los cafes
    private void BtnGestionClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnGestionClientesMouseClicked
        // TODO add your handling code here:
        AdministracionClientes admindeclientes = new AdministracionClientes();
        admindeclientes.setVisible(true);
        admindeclientes.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_BtnGestionClientesMouseClicked
    
    //ActionPermoed para traer a la vista el panel de los refrescos
    private void BtnContenedorRefrescosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnContenedorRefrescosActionPerformed
        // TODO add your handling code here:
        contenedorContenedores.remove(this);
        contenedorContenedores.add(panelrefrescos,"refrescos");
        refrescos.show(contenedorContenedores, "refrescos");
        SwingUtilities.updateComponentTreeUI(contenedorContenedores);
        contenedorContenedores.repaint();
    }//GEN-LAST:event_BtnContenedorRefrescosActionPerformed
    
    //ActionPermoed para traer a la vista el panel de las hamburguesas
    private void BtnContenedorHamburguesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnContenedorHamburguesasActionPerformed
        // TODO add your handling code here:
        contenedorContenedores.remove(this);
        contenedorContenedores.add(panelhamburguesas,"hamburguesas");
        hamburguesas.show(contenedorContenedores, "hamburguesas");
        SwingUtilities.updateComponentTreeUI(contenedorContenedores);
        contenedorContenedores.repaint();
    }//GEN-LAST:event_BtnContenedorHamburguesasActionPerformed
    
    //ActionPermoed para traer a la vista el panel de los bocadillos
    private void BtnContenedorBocadillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnContenedorBocadillosActionPerformed
        // TODO add your handling code here:
        contenedorContenedores.remove(this);
        contenedorContenedores.add(panelbocadillos,"bocadillos");
        bocadillos.show(contenedorContenedores, "bocadillos");
        SwingUtilities.updateComponentTreeUI(contenedorContenedores);
        contenedorContenedores.repaint();
    }//GEN-LAST:event_BtnContenedorBocadillosActionPerformed

    private void BtnRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRealizarVentaActionPerformed
        UtilidadesService ud = new UtilidadesService();
        try {
            ps.pasarfactura(cajapantalla, promocion);
            ud.copiatabla();
            ud.borradocesta();
            ps.cestaCompra(cajapantalla);
            ticket();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_BtnRealizarVentaActionPerformed

    private void BtnPapeleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPapeleraActionPerformed
        // action que borrara el producto usando la id representada en la tabla
        DefaultTableModel tm = (DefaultTableModel)cajapantalla.getModel();
        String dato = String.valueOf(tm.getValueAt(cajapantalla.getSelectedRow(), 0));
        int id = Integer.parseInt(dato);
        try {
            ps.borrarProducto(id);
            ps.cestaCompra(cajapantalla);
        } catch (SQLException ex) {
            System.out.println("Error al borrar producto : "+ex);
        }
    }//GEN-LAST:event_BtnPapeleraActionPerformed
    
    
    private void BtnRefrescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefrescoActionPerformed
        //boton para refrescar por si se registro un nuevo cliente
        rellenadoChoices();
    }//GEN-LAST:event_BtnRefrescoActionPerformed

    private void flechaArribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flechaArribaActionPerformed
        // boton promocional, detectando si es falso o true aplicaremos o no la rebaja del 20%
        if(promocion == false){
            promocion = true;
        }else if(promocion == true){
            promocion = false;
        }
        if(promocion == true){
            flechaArriba.setBackground(Color.green);
        }else{
            flechaArriba.setBackground(Color.red);
        }
    }//GEN-LAST:event_flechaArribaActionPerformed

    private void BtnGestionEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGestionEmpleadosActionPerformed
        // actionperformed que abrira la gestion de empleados
        AdministracionEmpleados ae = new AdministracionEmpleados();
        ae.setVisible(true);
        ae.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_BtnGestionEmpleadosActionPerformed

    private void AperturaCajaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AperturaCajaBtnActionPerformed
        UtilidadesService ud = new UtilidadesService();
        try {
            // action performed que abrira la caja creando la tabla y dando el primer ticket
            aperturaCaja();
            ud.creandotabla();
            String ticket = String.valueOf(rs.numerotickets(VentanaPrincipal.nombretabla));
            VentanaPrincipal.ticketTXT.setText(ticket);
            LuzTablaPnl.setBackground(Color.green);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tabla ya creada");
        }
    }//GEN-LAST:event_AperturaCajaBtnActionPerformed

    private void BtnAdminCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdminCajaActionPerformed
        //Apertura de administracion de caja
        AdministracionCaja ae = new AdministracionCaja();
        ae.setVisible(true);
        ae.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_BtnAdminCajaActionPerformed

    private void BtnfindiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnfindiaActionPerformed
        String dia = null;
        if(findia){
            findia = false;
            Btnfindia.setBackground(Color.green);
        }else{
            findia = true;
            if(fechatxt.getText() == null || fechatxt.getText().length() < 8){
                dia = DiaTXT.getText();
            }else{
                dia = fechatxt.getText();
            }
            try {
                rs.comprobarfechas(dia);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            Btnfindia.setBackground(Color.red);
        }
    }//GEN-LAST:event_BtnfindiaActionPerformed

    private void BtnRegistrodiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistrodiaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegistrodiaMouseClicked

    private void BtnRegistrodiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrodiaActionPerformed
        // TODO add your handling code here:
        RegistrosPorDia rd = new RegistrosPorDia();
        rd.setVisible(true);
        rd.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_BtnRegistrodiaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Ayuda ayuda = new Ayuda();
        ayuda.setVisible(true);
        ayuda.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void rellenadoChoices(){
        try {
            ClienteChoice.removeAll();
            VendedorChoice.removeAll();
            EmpleadoService es = new EmpleadoService();
            ClientesHabitualesService cs = new ClientesHabitualesService();
            cs.actualizarchoicecliente(ClienteChoice);
            es.actualizadorchoiceempleado(VendedorChoice);
        } catch (SQLException ex) {
            System.out.println("Error en boton para actualizar choice cliente y vendedor");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AperturaCajaBtn;
    private javax.swing.JButton BtnAdminCaja;
    private javax.swing.JButton BtnContenedorBocadillos;
    private javax.swing.JButton BtnContenedorCafes;
    private javax.swing.JButton BtnContenedorHamburguesas;
    private javax.swing.JButton BtnContenedorRefrescos;
    private javax.swing.JButton BtnGestionClientes;
    private javax.swing.JButton BtnGestionEmpleados;
    private javax.swing.JButton BtnPapelera;
    private javax.swing.JButton BtnRealizarVenta;
    private javax.swing.JButton BtnRefresco;
    private javax.swing.JButton BtnRegistrodia;
    private javax.swing.JButton Btnfindia;
    public static java.awt.Choice ClienteChoice;
    public static javax.swing.JLabel DiaTXT;
    private javax.swing.JLabel HoraTXT;
    private javax.swing.JPanel LuzTablaPnl;
    private javax.swing.JPanel PanelPrincipal;
    public static java.awt.TextField Sumaprecios;
    public static java.awt.Choice VendedorChoice;
    public static javax.swing.JTable cajapantalla;
    private javax.swing.JPanel calculadora;
    private javax.swing.JPanel contenedorContenedores;
    private javax.swing.JPanel contenedorOperaciones;
    private javax.swing.JPanel contenedorPantalla;
    private javax.swing.JPanel contenedorReloj;
    private javax.swing.JPanel decoracionReloj;
    private java.awt.Choice eleccionEmpresa;
    private java.awt.Choice eleccionTiendas1;
    private java.awt.Label etiquetaEmpresa;
    private java.awt.Label etiquetaTiendas;
    private javax.swing.JTextField fechatxt;
    private javax.swing.JButton flechaArriba;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelcaja;
    public static javax.swing.JTextField ticketTXT;
    // End of variables declaration//GEN-END:variables
}
