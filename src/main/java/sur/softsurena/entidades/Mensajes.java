package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sur.softsurena.conexion.Conexion.getCnn;


public class Mensajes {

    private static final Logger LOG = Logger.getLogger(Mensajes.class.getName());
    
    /**
     *
     * @param idUsuario
     * @return
     */
    public synchronized boolean existeMensaje(String idUsuario) {
        final String sql = "el sql";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
