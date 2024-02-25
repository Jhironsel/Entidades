package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Motivo_Consulta;

/**
 *
 * @author jhironsel
 */
public class M_Motivo_Consulta {
    private static final Logger LOG = Logger.getLogger(M_Motivo_Consulta.class.getName());
    
    /**
     * Metodo que permite eliminar un motivo de consultas a las consultas.
     *
     * @param mc
     * @return
     */
    public synchronized static String borrarMotivoConsulta(Motivo_Consulta mc) {
        final String sql
            = "DELETE FROM V_Motivos_Consulta WHERE ID = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, mc.getId());

            ps.executeUpdate();

            return "Motivo de consulta borrado correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar motivo de la consulta.";
        }
    }

    /**
     *
     * @param m
     * @return
     */
    public synchronized static boolean agregarMotivo(String m) {
        final String sql
            = "INSERT INTO V_MOTIVOS_CONSULTA (DESCRIPCION) values (?)";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, m);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     *
     * @return
     */
    public synchronized static ResultSet getMotivo() {
        final String sql = "SELECT IDMCONSULTA, DESCRIPCION, DEFENICION "
                + "FROM V_MOTIVOS_CONSULTA "
                + "WHERE ESTADO "
                + "ORDER BY 1";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            
            return ps.executeQuery();
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
