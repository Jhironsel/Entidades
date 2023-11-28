package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Privilegios {

    private static final Logger LOG = Logger.getLogger(Privilegios.class.getName());

    private final String user_name;
    private final char privilegio;
    private final String nombre_relacion;
    private final String nombre_campo;

    /**
     * Abreviatura utilizada para designar los select de los usuarios.
     */
    public static char PRIVILEGIO_SELECT = 'S';

    /**
     * Abreviatura utilizada para designar los insert de los usuarios.
     */
    public static char PRIVILEGIO_INSERT = 'I';

    /**
     * Abreviatura utilizada para designar los update de los usuarios.
     */
    public static char PRIVILEGIO_UPDATE = 'U';

    /**
     * Abreviatura utilizada para designar los delete de los usuarios.
     */
    public static char PRIVILEGIO_DELETE = 'D';

    /**
     * Abreviatura utilizada para designar los usos de los usuarios en los
     * dominios y generadores.
     */
    public static char PRIVILEGIO_USAGE = 'G';

    /**
     * Abreviatura utilizada para designar los miembros de los usuarios en los
     * roles.
     */
    public static char PRIVILEGIO_MEMBERSHIP = 'M';

    /**
     * Abreviatura utilizada para designar los permisos de ejecución de los
     * usuarios en los Store Procedure.
     */
    public static char PRIVILEGIO_EXECUTE = 'X';

    /**
     * Abreviatura utilizada para permitir a los usuarios ceder los mismo
     * permisos que ellos tienen o hayan sido sedidos.
     */
    public static char PRIVILEGIO_REFERENCE = 'R';


    /**
     * Metodo que realiza las consultas a la base de datos, en busca de los 
     * persomisos de los usuarios. 
     * 
     * @param p Es un objeto que contiene los atributos que permite consultar
     * cuales son los permisos de los usuarios en los diferentes tipos de 
     * objectos de la base de datos. 
     * 
     * @return Devuelve un dato booleano que indica si un usuario cuenta con 
     * los permisos necesarios. 
     */
    public synchronized static Boolean privilegioTabla(Privilegios p) {
        final String sql
            = "SELECT (1) "
            + "FROM GET_PRIVILEGIOS "
            + "WHERE ("
            + "      TRIM(USER_NAME) STARTING WITH TRIM(CURRENT_USER) OR "
            + "      TRIM(USER_NAME) STARTING WITH TRIM(CURRENT_ROLE) OR "
            + "      TRIM(USER_NAME) STARTING WITH 'PUBLIC') AND "
            + "      TRIM(PRIVILEGIO) STARTING WITH TRIM(?) AND "
            + "      TRIM(NOMBRE_RELACION) STARTING WITH TRIM(?) ";
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, "" + p.getPrivilegio());
            ps.setString(2, p.getNombre_relacion());

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

    /**
     *
     * @param p
     * @return
     */
    public synchronized static boolean privilegioCampo(Privilegios p) {
        final String PERMISO_UPDATE_CAMPO
            = "SELECT (1) "
            + "FROM GET_PRIVILEGIOS "
            + "WHERE (TRIM(USER_NAME) LIKE TRIM(CURRENT_USER) OR "
            + "      TRIM(USER_NAME) LIKE TRIM(CURRENT_ROLE)) AND "
            + "      TRIM(PRIVILEGIO) LIKE TRIM(?) AND "
            + "      TRIM(NOMBRE_RELACION) LIKE TRIM(?) "
            + "      AND TRIM(NOMBRE_CAMPO) LIKE TRIM(?); ";
        try (PreparedStatement ps = getCnn().prepareStatement(PERMISO_UPDATE_CAMPO)) {
            ps.setString(1, "" + p.getPrivilegio());
            ps.setString(2, p.getNombre_relacion());
            ps.setString(3, p.getNombre_campo());
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

    @Override
    public String toString() {
        return nombre_campo;
    }

}
