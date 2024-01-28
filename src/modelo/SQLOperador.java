
package modelo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    
    public ArrayList<Producto> cargarInventario(String campo){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<Producto> productos = new ArrayList<>();
        
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select p.nombre, p.codigo, p.precio, p.cantidad, pro.nombre as proveedor from producto as p inner join proveedor as pro on p.idProveedor = pro.id "+campo);
            rs = ps.executeQuery();
            
            while(rs.next()){
               Producto producto = new Producto();
                
              producto.setNombre(rs.getString(1));
              producto.setCodigo(rs.getString(2));
              producto.setPrecio(rs.getBigDecimal(3));
              producto.setCantidad(rs.getInt(4));
              producto.setProveedor(rs.getString(5));
                
               productos.add(producto);
            }
            return productos;
            
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
    
    public boolean eliminarProducto(String codigo){
        PreparedStatement ps = null;
        try {
            
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("delete from producto where codigo = ?");
            ps.setString(1, codigo);
            ps.executeUpdate();
            
            return true;
            
            
        } catch (Exception e) {
            System.err.println("Error, "+e);
            return false;
        }finally{
            try {
                ps.close();
            } catch (Exception e) {
                System.err.println("Error, "+e);
            }
        }
        
    }
    
    public boolean modificarProducto(Producto producto,String codigoCaja){
        PreparedStatement ps = null;
        
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("update producto set nombre = ?, codigo = ?, precio = ?, cantidad = ? where codigo = ?");
            ps.setString(1,producto.getNombre());
            ps.setString(2, producto.getCodigo());
            ps.setBigDecimal(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());
            ps.setString(5, codigoCaja);
            ps.executeUpdate();
            return true;
            
            
        } catch (Exception e) {
            System.err.println("Error, "+e);
            return false;
        }finally{
            try {
                ps.close();
            } catch (Exception e) {
                System.err.println("Error, "+e);
            }
        }
    }
    
    public boolean insertarProductoExistente(String codigo, int cantidad){
        PreparedStatement ps = null;
        
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("update producto set cantidad = cantidad + ? where codigo = ?");
            ps.setInt(1, cantidad);
            ps.setString(2, codigo);
            ps.executeUpdate();
            
            return true;
        } catch (Exception e) {
            System.err.println("Error, "+e);
            return false;
        }finally{
            try {
                ps.close();
            } catch (Exception e) {
                System.err.println("Erro, "+e);
            }
        }
    }
    
    public boolean verificarProducto(String codigo){
       PreparedStatement ps = null;
       ResultSet rs = null;
        try {
            Connection conexion  = getConnection();
            ps = conexion.prepareStatement("select * from producto where codigo = ?");
            ps.setString(1, codigo);
            
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
    
    public boolean insertarProductoNuevo(Producto producto){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idProveedor;
        int resultadoFinal =0;
        
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("insert into proveedor(nombre)values(?)");
            ps.setString(1, producto.getProveedor());
            
            int resultado = ps.executeUpdate();
            
            if(resultado > 0){
                ps = conexion.prepareStatement("select id from proveedor where nombre = ?");
                ps.setString(1, producto.getProveedor());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    idProveedor = rs.getInt(1);
                    
                    ps = conexion.prepareStatement("insert into producto(nombre,codigo,precio,cantidad,idProveedor)values(?,?,?,?,?)");
                    ps.setString(1, producto.getNombre());
                    ps.setString(2, producto.getCodigo());
                    ps.setBigDecimal(3, producto.getPrecio());
                    ps.setInt(4, producto.getCantidad());
                    ps.setInt(5, idProveedor);
                    
                   resultadoFinal =  ps.executeUpdate();
                }
            }
            if(resultadoFinal > 0){
                return true;
            }else{
                return false;
            }
            
            
        } catch (Exception e) {
            System.err.println("Error, "+e);
            return false;
        }finally{
            try {
                
            } catch (Exception e) {
                System.err.println("Error, "+e);
                
            }
        }
    }
}
