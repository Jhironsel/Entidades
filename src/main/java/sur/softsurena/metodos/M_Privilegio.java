package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Privilegio;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Privilegio {

    /**
     * Metodo que realiza las consultas a la base de datos, en busca de los
     * persomisos de los usuarios.
     *
     * @param privilegio Es un objeto que contiene los atributos que permite
     * consultar cuales son los permisos de los usuarios en los diferentes tipos
     * de objectos de la base de datos.
     *
     * @return Devuelve un dato booleano que indica si un usuario cuenta con los
     * permisos necesarios.
     */
    public synchronized static boolean privilegio(Privilegio privilegio) {
        final String sql
                = "SELECT (1) "
                + "FROM GET_PRIVILEGIOS "
                + "WHERE (TRIM(USER_NAME) STARTING WITH TRIM(CURRENT_USER) OR "
                + "      TRIM(USER_NAME) STARTING WITH TRIM(CURRENT_ROLE)) AND "
                + "      TRIM(PRIVILEGIO) STARTING WITH TRIM(?) AND "
                + "      TRIM(NOMBRE_RELACION) STARTING WITH TRIM(?) OR "
                + "      TRIM(NOMBRE_CAMPO) STARTING WITH TRIM(?); ";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )){
            ps.setString(1, privilegio.getPrivilegio().toString());
            ps.setString(2, privilegio.getNombre_relacion());
            ps.setString(3, privilegio.getNombre_campo());
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    "Error al consultar la vista GET_PRIVILEGIOS del sistema.", 
                    ex
            );
            return false;
        }
    }
}
