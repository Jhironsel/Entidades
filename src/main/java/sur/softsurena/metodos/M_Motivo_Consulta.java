package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Motivo_Consulta;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Motivo_Consulta {
    
    /**
     * Metodo que permite eliminar un motivo de consultas a las consultas.
     *
     * 
     * TODO Crear SP.
     * @param mc
     * @return
     */
    public synchronized static String borrarMotivoConsulta(Motivo_Consulta mc) {
        final String sql
            = "DELETE FROM V_Motivos_Consulta WHERE ID = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setInt(1, mc.getId());

            ps.executeUpdate();

            return MOTIVO_DE_CONSULTA_BORRADO_CORRECTAMENTE;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return ERROR_AL_BORRAR_MOTIVO_DE_LA_CONSULTA;
        }
    }
    
    public static final String ERROR_AL_BORRAR_MOTIVO_DE_LA_CONSULTA 
            = "Error al borrar motivo de la consulta.";
    public static final String MOTIVO_DE_CONSULTA_BORRADO_CORRECTAMENTE 
            = "Motivo de consulta borrado correctamente.";

    /**
     * TODO Crear SP.
     * @param m
     * @return
     */
    public synchronized static boolean agregarMotivo(String m) {
        final String sql
            = "INSERT INTO V_MOTIVOS_CONSULTA (DESCRIPCION) values (?)";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
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
