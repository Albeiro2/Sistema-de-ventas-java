
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class SQLOperador extends Conexion {
    
    
    public boolean iniciar_sesion(String usuario, String pass){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from usuarios where usuario =? and pass = ?");
            ps.setString(1, usuario);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            
            if(rs.next()){
                
                return true;
                
            }else{
                return false;
            }
            
            
        } catch (Exception e) {
            System.err.println("Error, "+e);
            return false;
        }
    }
    
    public boolean buscarProducto(String codigo, Producto producto){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from producto where codigo = ?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            
            if(rs.next()){
                producto.setNombre(rs.getString("nombre"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setIdProducto(rs.getInt("id"));
                producto.setIdProveedor(rs.getInt("idProveedor"));
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e){
            System.err.println("Error, "+e);
            return false;
        }finally{
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }
    
    public boolean venderProducto(String codigo,int cantidad){
        PreparedStatement ps = null;
        
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("update producto set cantidad = cantidad - ? where codigo = ?");
            ps.setInt(1, cantidad);
            ps.setString(2, codigo);
            ps.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.err.println("Error, "+e);
            return false;
        }
    }
    
    public boolean facturar(Factura factura){
        PreparedStatement ps = null;
         
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("insert into factura(productos,fecha,total)values(?,?,?)");
            ps.setString(1, factura.getProductos());
            ps.setString(2, factura.getFecha());
            ps.setBigDecimal(3, factura.getTotal());
            ps.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.err.println("Error, "+e);
            return false;
        }
    }
    
    public ArrayList<Factura> cargarVentas(String campo){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<Factura> facturas = new ArrayList<>();
        
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from factura "+campo);
            rs = ps.executeQuery();
            
            while(rs.next()){
               Factura factura = new Factura();
                
               factura.setIdFactura(rs.getInt(1)); 
               factura.setProductos(rs.getString(2));
               java.sql.Date fechaSQL = rs.getDate(3);
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               factura.setFecha(dateFormat.format(fechaSQL));
               factura.setTotal(rs.getBigDecimal(4));
                
               facturas.add(factura);
            }
            return facturas;
            
        } catch (Exception e) {
            System.err.println("Error, "+e);
            
            return null;
        } finally{
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }
}
