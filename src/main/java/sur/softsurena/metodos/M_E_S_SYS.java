package sur.softsurena.metodos;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.utilidades.Utilidades;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_E_S_SYS {
    
    /**
     * Metodo que permite agregar una imagen en la pantalla principal del
     * sistema, es llamado logo de la aplicacion.
     * 
     * TODO CREAR SP.
     *
     * @param file recibe una ruta de la imagen selecciona la cual es convertida
     * a base64 para luego ser almacenada.
     *
     * @return Devuelve un valor booleano que indica si la operacion tuvo exito
     * o no.
     */
    public synchronized static boolean insertLogo(File file) {
        final String sql
                = "UPDATE OR INSERT INTO V_T_E_S_SYS(ID, LOGO) "
                + "VALUES(1,?) MATCHING(ID);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setString(1, Utilidades.imagenEncode64(file));
            return ps.execute();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ex.getMessage(), 
                    ex
            );
        }
        return false;
    }

    /**
     *
     * @return
     */
    public synchronized static String getLogo() {
        final String sql
                = "SELECT "
                + "     LOGO "
                + "FROM V_T_E_S_SYS "
                + "WHERE ID = 1; ";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString(1);
            } 
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    }
}
