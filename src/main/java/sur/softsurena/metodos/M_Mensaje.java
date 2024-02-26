package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Mensaje;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Mensaje {
    
    /**
     * TODO Desarrollo de este metodo.
     * @param mensaje
     * @return
     */
    public synchronized boolean existeMensaje(Mensaje mensaje) {
        final String sql = "";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } 
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
