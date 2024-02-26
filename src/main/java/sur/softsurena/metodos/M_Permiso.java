package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Role;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Permiso {
    
    /**
     * Este metodo devuelve todos los relacionados a un rol del sistema.
     *
     * @param rol
     * @return
     */
    public synchronized static List<Role> getPermisosAsignados(String rol) {
        final String sql
                = "SELECT NOMBRE_RELACION, GRANT_OPTION, DESCRIPCION "
                + "FROM GET_PERMISOS_ASIGNADOS "
                + "WHERE ID_TIPO_OBJ = 5 AND "
                + "      TRIM(USUARIO) STARTING WITH ? AND "
                + "      TRIM(NOMBRE_RELACION) STARTING WITH 'PERM_';";

        List<Role> roles = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, rol);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {

                    String nombreRelacion = rs.getString("NOMBRE_RELACION");
                    String descripcion = rs.getString("DESCRIPCION");
                    int opcionPermiso = rs.getInt("GRANT_OPTION");

                    roles.add(Role.builder().
                            descripcion(Objects.isNull(descripcion) ? "" : descripcion.strip()).
                            nombreProcedimiento(Objects.isNull(nombreRelacion) ? "" : nombreRelacion.strip()).
                            opcionPermiso(opcionPermiso).
                            build());
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
        return roles;
    }

    /**
     * Consulta que permite obtener los permisos que tiene un rol disponible en
     * el sistema.
     *
     * @param rol
     * @return
     */
    public synchronized static List<Role> getPermisosDisponibles(String rol) {

        final String sql = "SELECT p.PROCEDIMIENTO, p.DESCRIPCION  "
                + "FROM VS_PROCEDIMIENTOS p "
                + "LEFT JOIN VS_PRIVILEGIO r ON TRIM(r.NOMBRE_RELACION) LIKE TRIM(p.PROCEDIMIENTO) AND"
                + "     TRIM(r.USUARIO)  LIKE ? "
                + "WHERE p.PROCEDIMIENTO STARTING WITH 'PERM_' AND "
                + "     TRIM(r.USUARIO) IS NULL AND "
                + "     p.PROCEDIMIENTO NOT STARTING WITH 'TRANSITIONS'";

        List<Role> roles = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, rol);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    String descripcion, nombreRelacion;
                    descripcion = rs.getString("DESCRIPCION");
                    nombreRelacion = rs.getString("PROCEDIMIENTO");
                    roles.add(Role.builder().
                            descripcion(Objects.isNull(descripcion) ? "" : descripcion.strip()).
                            nombreProcedimiento(Objects.isNull(nombreRelacion) ? "" : nombreRelacion.strip()).
                            build());
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
        return roles;
    }

    /**
     *
     * @param rol
     * @param usuario
     * @return
     */
    public synchronized static Resultados quitarPermisoAdminRole(
            String rol, String usuario) {

        String sql = "EXECUTE PROCEDURE ADMIN_QUITAR_PERMISO_ADMIN_ROL(?,?)";

        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, rol);
            cs.setString(2, usuario);

            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Procedimiento sin control administrativo.").
                    cantidad(-1).
                    estado(execute).
                    build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);

            return Resultados.builder().
                    id(-1).
                    mensaje("Error al quitar control administrativo.").
                    cantidad(-1).
                    build();
        }
    }

    /**
     *
     * @param procedimiento
     * @param rol
     * @return
     */
    public synchronized static Resultados quitarPermisoAdminProcedimiento(
            String procedimiento, String rol) {

        String sql = "EXECUTE PROCEDURE ADMIN_QUITAR_PERMISO_ADMIN_PROCE (?,?);";

        try (PreparedStatement cs = getCnn().prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, procedimiento);
            cs.setString(2, rol);

            boolean execute = cs.execute();

            return Resultados.builder().
                    mensaje(PROCEDIMIENTO_SIN_CONTROL_ADMINISTRATIVO).
                    estado(execute).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);

            return Resultados.builder().
                    mensaje(ERROR_AL_QUITAR_CONTROL_ADMINISTRATIVO).
                    estado(Boolean.FALSE).
                    build();
        }
    }
    public static final String ERROR_AL_QUITAR_CONTROL_ADMINISTRATIVO = "Error al quitar control administrativo.";
    public static final String PROCEDIMIENTO_SIN_CONTROL_ADMINISTRATIVO = "Procedimiento sin control administrativo.";

    /**
     *
     * @param procedimiento
     * @param rol
     * @return
     */
    public synchronized static Resultados agregarPermisoAdminProcedimiento(
            String procedimiento, String rol) {

        String sql = "EXECUTE PROCEDURE ADMIN_AGREGAR_PERMISO_ADMIN_PROCE (?,?);";

        try (PreparedStatement cs = getCnn().prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, procedimiento);
            cs.setString(2, rol);

            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Procedimiento con control administrativo.").
                    cantidad(-1).
                    estado(execute).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);

            return Resultados.builder().
                    id(-1).
                    mensaje("Error al quitar control administrativo.").
                    cantidad(-1).
                    build();
        }
    }

    /**
     *
     * @param role
     * @param usuario
     * @return
     */
    public synchronized static Resultados agregarPermisoAdminRole(
            String role, String usuario) {

        String sql = "EXECUTE PROCEDURE ADMIN_AGREGAR_PERMISO_ADMIN_ROLE(?,?)";

        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, role);
            cs.setString(2, usuario);

            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Role sin control administrativo.").
                    cantidad(-1).
                    estado(execute).
                    build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);

            return Resultados.builder().
                    id(-1).
                    mensaje("Error al quitar control administrativo a role.").
                    cantidad(-1).
                    build();
        }
    }

    /**
     * Metodo que elimina los permisos asignados a los roles.
     *
     * @param procedimiento
     * @param rol
     * @param admin
     * @return
     */
    public synchronized static Resultados borrarPermisoAdminProcedimiento(
            String procedimiento, String rol) {

        String sql = "EXECUTE PROCEDURE ADMIN_BORRAR_PERMISO_ADMIN_PROCE(?,?)";

        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, procedimiento);
            cs.setString(2, rol);

            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Permiso borrado correctamente.").
                    cantidad(-1).
                    estado(execute).
                    build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);

            return Resultados.builder().
                    id(-1).
                    mensaje("Error al borrar permiso").
                    cantidad(-1).
                    build();
        }
    }
}
