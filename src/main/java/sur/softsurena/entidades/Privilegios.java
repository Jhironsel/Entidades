package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
     * Es el metodo que nos devuelve los Roles del sistema, los cuales son asig-
     * nados a los usuarios.
     *
     * @return
     */
    public synchronized static List<Roles> getRoles() {
        final String GET_ROLES
                = "SELECT ROL, PROPIETARIO, DESCRIPCION FROM GET_ROLES";
        
        List<Roles> rolesList = new ArrayList<>();
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_ROLES,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    rolesList.add(Roles.builder().
                            roleName(rs.getString("Rol")).
                            descripcion(rs.getString("Descripcion")).
                            propietario(rs.getString("PROPIETARIO")).build());
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            
            return rolesList;
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param p
     * @return
     */
    public synchronized static boolean privilegioTabla(Privilegios p) {
        final String PERMISO_UPDATE_TABLA
            = "SELECT (1) "
            + "FROM GET_PRIVILEGIOS "
            + "WHERE (TRIM(USER_NAME) LIKE TRIM(CURRENT_USER) OR "
            + "      TRIM(USER_NAME) LIKE TRIM(CURRENT_ROLE)) AND "
            + "      TRIM(PRIVILEGIO) LIKE TRIM(?) AND "
            + "      TRIM(NOMBRE_RELACION) LIKE TRIM(?) ";
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                PERMISO_UPDATE_TABLA,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
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
