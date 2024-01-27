package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Factura;
import modelo.Producto;
import modelo.Proveedor;
import modelo.SQLOperador;
import vista.HistorialVentas;
import vista.IngresarProductos;
import vista.Inventario;
import vista.Login;
import vista.Programa;
import vista.Vender;
import vista.VentanaFactura;

public class Controlador implements ActionListener,MouseListener{

    VentanaFactura ventana;
    Factura factura;
    Producto producto;
    Proveedor proveedor;
    SQLOperador operar;
    HistorialVentas historialVentas;
    IngresarProductos ingresarProductos;
    Inventario inventario;
    Login login;
    Programa programa;
    Vender vender;
    DefaultTableModel modeloTablaVenta = new DefaultTableModel();

    public Controlador(VentanaFactura ventana, Factura factura, Producto producto, Proveedor proveedor, SQLOperador operar, HistorialVentas historialVentas, IngresarProductos ingresarProductos, Inventario inventario, Login login, Programa programa, Vender vender) {
        this.factura = factura;
        this.producto = producto;
        this.proveedor = proveedor;
        this.operar = operar;
        this.historialVentas = historialVentas;
        this.ingresarProductos = ingresarProductos;
        this.inventario = inventario;
        this.login = login;
        this.programa = programa;
        this.vender = vender;
        this.ventana = ventana;

        vender.tablaProductos.setModel(modeloTablaVenta);
        login.botonIniciar.addActionListener(this);
        programa.botonCerrarSesion.addActionListener(this);
        programa.botonHistorial.addActionListener(this);
        programa.botonIngresarProductos.addActionListener(this);
        programa.botonInventario.addActionListener(this);
        programa.botonVender.addActionListener(this);
        historialVentas.botonRegresar.addActionListener(this);
        inventario.botonRegresar.addActionListener(this);
        ingresarProductos.botonRegresar.addActionListener(this);
        vender.botonRegresar.addActionListener(this);
        vender.botonAgregarProducto.addActionListener(this);
        vender.botonNuevaVenta.addActionListener(this);
        vender.BotonBorrarProducto.addActionListener(this);
        vender.cajaId.setVisible(false);
        vender.botonBuscar.addActionListener(this);
        vender.botonGenerarVenta.addActionListener(this);
        ventana.botonCerrar.addActionListener(this);
        historialVentas.botonCargarVentas.addActionListener(this);
        historialVentas.botonRefrescar.addActionListener(this);
        historialVentas.tablaHistorial.addMouseListener(this);
        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == historialVentas.tablaHistorial){
            resumenVenta();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String pass;
        if (e.getSource() == login.botonIniciar) {
            pass = new String(login.cajaPass.getPassword());
            if (login.cajaUsuario.getText().equals("") || pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Para ingresar rellene todos los campos");
            } else {
                if (operar.iniciar_sesion(login.cajaUsuario.getText(), pass)) {
                    login.dispose();
                    programa.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        }

        if (e.getSource() == programa.botonCerrarSesion) {
            System.exit(0);
        }

        if (e.getSource() == programa.botonHistorial) {
            programa.setVisible(false);
            historialVentas.setVisible(true);
            cargarHistorialDeVentas();
        }
        if (e.getSource() == programa.botonIngresarProductos) {
            programa.setVisible(false);
            ingresarProductos.setVisible(true);
        }

        if (e.getSource() == programa.botonInventario) {
            programa.setVisible(false);
            inventario.setVisible(true);
        }

        if (e.getSource() == programa.botonVender) {
            programa.setVisible(false);
            vender.setVisible(true);
        }

        if (e.getSource() == historialVentas.botonRegresar) {
            historialVentas.dispose();
            programa.setVisible(true);
        }

        if (e.getSource() == ingresarProductos.botonRegresar) {
            ingresarProductos.dispose();
            programa.setVisible(true);
        }

        if (e.getSource() == inventario.botonRegresar) {
            inventario.dispose();
            programa.setVisible(true);
        }

        if (e.getSource() == vender.botonRegresar) {
            vender.setVisible(false);
            programa.setVisible(true);
        }

        if (e.getSource() == vender.botonAgregarProducto) {
            modeloTablaVenderLlenarFila();
        }

        if (e.getSource() == vender.botonNuevaVenta) {
            vaciarTabla();
        }

        if (e.getSource() == vender.BotonBorrarProducto) {
            borrarProducto();
        }

        if (e.getSource() == vender.botonBuscar) {
            buscar_producto();
        }

        if (e.getSource() == vender.botonGenerarVenta) {
            vender_producto();
        }

        if (e.getSource() == ventana.botonCerrar) {
            ventana.dispose();
            vaciarTabla();
        }

        if (e.getSource() == historialVentas.botonCargarVentas) {
            cargarHistorialDeVentas();
            historialVentas.areaResumenVenta.setText(null);
        }

        if (e.getSource() == historialVentas.botonRefrescar) {
            historialVentas.cajaBuscarVenta.setText(null);
            historialVentas.areaResumenVenta.setText(null);
            cargarHistorialDeVentas();
        }

    }

    public void iniciar() {
        login.setSize(690, 430);
        login.setLocationRelativeTo(null);
        login.setTitle("Ventana de inicio");
        login.setVisible(true);
        programa.setTitle("Sistema de ventas");
        programa.setLocationRelativeTo(null);
        programa.setSize(810, 600);
        historialVentas.setTitle("Historial de ventas");
        historialVentas.setLocationRelativeTo(null);
        historialVentas.setSize(820, 600);
        ingresarProductos.setTitle("Agrega nuevos productos");
        ingresarProductos.setLocationRelativeTo(null);
        inventario.setTitle("Inventario");
        inventario.setLocationRelativeTo(null);
        vender.setTitle("Nueva venta");
        vender.setLocationRelativeTo(null);
        vender.setSize(820, 600);
        ingresarProductos.setSize(850, 500);
        inventario.setSize(802, 600);
        modeloTablaVenderLlenarColumnas();
    }

    private void modeloTablaVenderLlenarColumnas() {
        modeloTablaVenta.addColumn("Nombre Producto");
        modeloTablaVenta.addColumn("Codigo");
        modeloTablaVenta.addColumn("Precio");
        modeloTablaVenta.addColumn("Cantidad");
    }

    private void modeloTablaVenderLlenarFila() {

        if (vender.cajaNombre.getText().equals("") || vender.cajaCodigo.getText().equals("") || vender.cajaCantidad.getText().equals("") || vender.cajaPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos para agregar producto");
        } else {
            BigDecimal decimal = new BigDecimal(vender.cajaPrecio.getText());
            Object[] fila = new Object[4];
            fila[0] = vender.cajaNombre.getText();
            fila[1] = vender.cajaCodigo.getText();
            fila[2] = decimal;
            fila[3] = Integer.valueOf(vender.cajaCantidad.getText());

            modeloTablaVenta.addRow(fila);
        }
    }

    private void vaciarTabla() {
        // Eliminar todas las filas del modelo de la tabla
        while (modeloTablaVenta.getRowCount() > 0) {
            modeloTablaVenta.removeRow(0);
        }
        vender.cajaBuscar.setText(null);
        vender.cajaCantidad.setText(null);
        vender.cajaCodigo.setText(null);
        vender.cajaId.setText(null);
        vender.cajaNombre.setText(null);
        vender.cajaPrecio.setText(null);
    }

    private void borrarProducto() {
        if (modeloTablaVenta.getRowCount() > 0) {
            int filaSeleccionada = vender.tablaProductos.getSelectedRow();
            if (filaSeleccionada != -1) {
                modeloTablaVenta.removeRow(filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un producto para borrar.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos en fila para borrar.");
        }
    }

    private void buscar_producto() {
        if (vender.cajaBuscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, digite el codigo del producto que desea buscar");
        } else {
            if (operar.buscarProducto(vender.cajaBuscar.getText(), producto)) {
                vender.cajaNombre.setText(producto.getNombre());
                vender.cajaCodigo.setText(producto.getCodigo());
                vender.cajaPrecio.setText(String.valueOf(producto.getPrecio()));
                vender.cajaId.setText(String.valueOf(producto.getIdProducto()));
            } else {
                JOptionPane.showMessageDialog(null, "Producto no registrado");
            }
        }
    }

    private void vender_producto() {
        String allProducts = "";
        BigDecimal total = BigDecimal.ZERO;
        Object cantidad;
        int cantidad1;
        String codigo;
        int contador = 0;
        if (vender.tablaProductos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos listados para vender");
        } else {
            int numeroFilas = vender.tablaProductos.getRowCount();
            for (int i = 0; i < numeroFilas; i++) {
                cantidad = vender.tablaProductos.getValueAt(i, 3);
                cantidad1 = (int) cantidad;
                BigDecimal cantidadMulti = BigDecimal.valueOf(cantidad1);
                codigo = String.valueOf(vender.tablaProductos.getValueAt(i, 1));
                BigDecimal precio = (BigDecimal) vender.tablaProductos.getValueAt(i, 2);
                precio = precio.multiply(cantidadMulti);
                total = total.add(precio);
                allProducts += String.valueOf(vender.tablaProductos.getValueAt(i, 0)) + ": " + cantidad1 + " unidades" + ": " + precio + "\n";
                if (operar.venderProducto(codigo, cantidad1)) {
                    contador++;
                }
            }
        }
        if (contador == vender.tablaProductos.getRowCount()) {
            Date date = new Date();
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            factura.setProductos(allProducts);
            factura.setTotal(total);
            factura.setFecha(fechaHora.format(date));
            if (operar.facturar(factura)) {
                generarVentanaFactura();
            }

        }
    }

    private void generarVentanaFactura() {

        ventana.areaReporte.setText(factura.getProductos() + "\n" + "Total: " + factura.getTotal() + "\n" + "Fecha: " + factura.getFecha());
        ventana.setTitle("Reporte de venta");
        ventana.setLocationRelativeTo(null);
        ventana.setSize(442, 300);
        ventana.setVisible(true);
    }

    private void cargarHistorialDeVentas() {
        DefaultTableModel modeloHistorial = new DefaultTableModel();
        historialVentas.tablaHistorial.setModel(modeloHistorial);

        String campo = historialVentas.cajaBuscarVenta.getText();
        String where = "";

        if (!"".equals(campo)) { // Si el campo no esta vacio
            where = "where id =" + campo + "";
        }
        modeloHistorial.addColumn("idFactura");
        modeloHistorial.addColumn("Productos");
        modeloHistorial.addColumn("Fecha");
        modeloHistorial.addColumn("Total");

        if (operar.cargarVentas(where) != null) {
            for (Factura factura : operar.cargarVentas(where)) {
                Object[] fila = new Object[4];
                fila[0] = factura.getIdFactura();
                fila[1] = factura.getProductos();
                fila[2] = factura.getFecha();
                fila[3] = factura.getTotal();
                modeloHistorial.addRow(fila);
            }

        }
    }
    
    
    private void resumenVenta(){
        String productos,fecha;
        BigDecimal total;
        int filaSeleccion = historialVentas.tablaHistorial.getSelectedRow();
        if(filaSeleccion != -1){
            Object[] valores  = new Object[4];
              for(int i=0;i<4;i++){
                  valores[i] = historialVentas.tablaHistorial.getValueAt(filaSeleccion, i);
              }
              
              productos = (String)valores[1];
              fecha = (String) valores[2];
              total = (BigDecimal) valores[3];
              historialVentas.areaResumenVenta.setText(productos+"\n"+"Total: "+total+"\n"+"Fecha: "+fecha);
        }
    }

    
}
