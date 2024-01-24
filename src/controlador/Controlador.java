
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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


public class Controlador implements ActionListener {
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
   
    public Controlador(Factura factura,Producto producto,Proveedor proveedor, SQLOperador operar,HistorialVentas historialVentas,IngresarProductos ingresarProductos,Inventario inventario, Login login, Programa programa, Vender vender){
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
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String pass;  
        if(e.getSource() == login.botonIniciar){
            pass = new String(login.cajaPass.getPassword());
            if(login.cajaUsuario.getText().equals("") || pass.equals("")){
                JOptionPane.showMessageDialog(null, "Para ingresar rellene todos los campos");
            }else{
                if(operar.iniciar_sesion(login.cajaUsuario.getText(), pass)){
                    login.dispose();
                    programa.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        }
        
        if(e.getSource() == programa.botonCerrarSesion){
            System.exit(0);
        }
        
        if(e.getSource() == programa.botonHistorial){
            programa.setVisible(false);
            historialVentas.setVisible(true);
        }
        if(e.getSource() == programa.botonIngresarProductos){
            programa.setVisible(false);
            ingresarProductos.setVisible(true);
        }
        
        if(e.getSource() == programa.botonInventario){
            programa.setVisible(false);
            inventario.setVisible(true);
        }
        
        if(e.getSource() == programa.botonVender){
            programa.setVisible(false);
            vender.setVisible(true);
        }
        
        if(e.getSource() == historialVentas.botonRegresar){
            historialVentas.dispose();
            programa.setVisible(true);
        }
        
        if(e.getSource() == ingresarProductos.botonRegresar){
            ingresarProductos.dispose();
            programa.setVisible(true);
        }
        
        if(e.getSource() == inventario.botonRegresar){
            inventario.dispose();
            programa.setVisible(true);
        }
        
        if(e.getSource() == vender.botonRegresar){
            vender.setVisible(false);
            programa.setVisible(true);
        }
        
    }
    
    public void iniciar(){
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
    }
    
}
