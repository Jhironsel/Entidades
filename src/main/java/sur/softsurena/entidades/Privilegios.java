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
     * Abreviatura utilizada para designar los usos de los usuarios en 
     * los dominios y generadores.
     */
    public static char PRIVILEGIO_USAGE = 'G';
    
    /**
     * Abreviatura utilizada para designar los miembros de los usuarios en 
     * los roles.
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
    public static char PRIVILEGIO_REFERENCE = 'R' ;

    public static String SELECT
            = "SELECT r.USER_NAME, r.CEDENTE, r.PRIVILEGIO, "
            + "     r.OPCION_PERMISO, r.NOMBRE_RELACION, "
            + "     r.NOMBRE_CAMPO, r.TIPO_USUARIO, "
            + "     r.TIPO_OBJECTO "
            + "FROM GET_PRIVILEGIOS r ";

    public static String PERMISO_UPDATE_TABLA
            = "SELECT (1) "
            + "FROM GET_PRIVILEGIOS r " 
            + "WHERE (TRIM(r.USER_NAME) LIKE TRIM(CURRENT_USER) OR "
            + "      TRIM(r.USER_NAME) LIKE TRIM(CURRENT_ROLE)) AND "
            + "      TRIM(r.PRIVILEGIO) LIKE TRIM(?) AND "
            + "      TRIM(r.NOMBRE_RELACION) LIKE TRIM(?) ";
    
    public static String PERMISO_UPDATE_CAMPO
            = PERMISO_UPDATE_TABLA 
            + "      AND TRIM(r.NOMBRE_CAMPO) LIKE TRIM(?); ";
    
    public static String GET_ROLES
            = "SELECT r.ROL FROM GET_ROLES r";
    
    /**
     * Es el metodo que nos devuelve los Roles del sistema, los cuales son asig-
     * nados a los usuarios.
     *
     * @return
     */
    public synchronized static ResultSet getRoles() {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                GET_ROLES,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
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
        try ( PreparedStatement ps = getCnn().prepareStatement(
                Privilegios.PERMISO_UPDATE_TABLA,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, "" + p.getPrivilegio());
            ps.setString(2, p.getNombre_relacion());

            try ( ResultSet rs = ps.executeQuery()) {
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
        try ( PreparedStatement ps = getCnn().prepareStatement(Privilegios.PERMISO_UPDATE_CAMPO)) {
            ps.setString(1, "" + p.getPrivilegio());
            ps.setString(2, p.getNombre_relacion());
            ps.setString(3, p.getNombre_campo());
            try ( ResultSet rs = ps.executeQuery()) {
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
