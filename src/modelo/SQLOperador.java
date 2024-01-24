
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
