
package principal;

import controlador.Controlador;
import javax.swing.UIManager;
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


public class Principal {
    
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        } catch (Exception e) {
            
        }
       
        Factura factura = new Factura();
        Producto producto = new Producto();
        Proveedor proveedor = new Proveedor();
        SQLOperador operar = new SQLOperador();
        HistorialVentas historialVentas = new HistorialVentas();
        IngresarProductos ingresarProductos = new IngresarProductos();
        Inventario inventario = new Inventario();
        Login login = new Login();
        Programa programa = new Programa();
        Vender vender = new Vender();
        VentanaFactura ventana = new VentanaFactura(vender, true);
        
        Controlador controlador = new Controlador(ventana,factura, producto, proveedor, operar, historialVentas, ingresarProductos, inventario, login, programa, vender);
        
        controlador.iniciar();
    }
}
