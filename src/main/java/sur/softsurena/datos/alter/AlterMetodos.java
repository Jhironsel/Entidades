package sur.softsurena.datos.alter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static sur.softsurena.conexion.Conexion.getCnn;
import static sur.softsurena.datos.select.SelectMetodos.getCreadorUsuario;
import sur.softsurena.entidades.Usuario;

public class AlterMetodos {

    private static String sql;
    private static PreparedStatement ps;
    
    /**
     * Metodo que permite el cambio de contraseña de los usuario del sistema.
     * 
     * Metodo revisado el 24 de abril 2022.
     * Metodo actualizado el 19 05 2022, Nota, se cambia la posicion del 
     * parametro, primero usuario y luego la clave.
     * @param clave
     * @param usuario 
     */
    public synchronized static boolean cambioClave(String usuario, String clave) {
        try {
            ps = getCnn().prepareStatement(Usuario.CAMBIAR_CLAVE);
            
            ps.setString(1, usuario);
            ps.setString(2, clave);
            
            return ps.execute();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }
    
    /**
     * Para matener la mejor integridad de los datos los usuario se desactivan 
     * del sistema.
     * 
     * Metodo revisado 24 abril 2022.
     * 
     * @param loginName 
     * @param estado
     * @return 
     */
    public synchronized static String borrarUsuario(String loginName, 
            boolean estado) {
        sql = "ALTER USER ? SET " + (estado ? "ACTIVE" : "INACTIVE");
        
        try {
            ps = getCnn().prepareStatement(sql);
            
            ps.setString(1, loginName);
            
            ps.executeUpdate();
            
            return "Usuario " + (estado ? "Activado" : "borrado") +
                    " correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al borrar usuario...";
        }
    }
    
    public synchronized static String borrarUsuario(String idUsuario, String rol) {
        try {
            
            ps = getCnn().prepareStatement(Usuario.QUITAR_ROL_USUARIO);
            
            ps.setString(1, rol);
            ps.setString(2, idUsuario);
            ps.setString(3, getCreadorUsuario(idUsuario));

            ps.executeUpdate(sql);
            
            getCnn().commit();

            sql = "DROP USER ?";            
            ps = getCnn().prepareStatement(sql);
            ps.setString(1, idUsuario);
            ps.executeUpdate(sql);
            
            getCnn().commit();

            return "Usuario borrado correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Ocurrio un error al intentar borrar el Usuario.";
        }
    }
    
    
    
    
}
