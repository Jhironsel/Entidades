package sur.softsurena.entidades;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@Getter
@SuperBuilder
public class Roles {

    private static final Logger LOG = Logger.getLogger(Roles.class.getName());

    private final String roleName;
    private final String propietario;
    private final String nombreProcedimiento;
    private final String descripcion;

    /**
     * Metodo utilizado para consultar a la base de datos, los roles creado y
     * aquienes fueron asignados esos roles.
     *
     * Metodo Actualizado el 18/05/2022.
     *
     * @param userName Identificador del usuario en el sistema.
     *
     * @return Devuelve una lista array de los roles por usuario del sistema.
     */
    public synchronized static List<Roles> comprobandoRol(String userName) {
        List<Roles> roles = new ArrayList<>();

        final String SELECT_ROLES_USUARIOS
                = "SELECT ROL, DESCRIPCION "
                + "FROM GET_ROL "
                + "WHERE USER_NAME STARTING WITH ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT_ROLES_USUARIOS,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, userName.trim().toUpperCase());
            try (ResultSet rs = ps.executeQuery()) {
                String aux;
                while (rs.next()) {
                    aux = rs.getString("ROL");
                    if (aux.equalsIgnoreCase("RDB$ADMIN")) {
                        aux = "ADMINISTRADOR";
                    }
                    String descripcion = rs.getString("DESCRIPCION");
                    roles.add(
                            Roles.builder().
                                    roleName(aux.strip()).
                                    descripcion(Objects.isNull(descripcion) ? "":descripcion).
                                    build()
                    );
                }
            }
            return roles;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param userName
     * @return
     */
    public synchronized static List<Roles> comprobandoRolesDisponibles(String userName) {
        List<Roles> roles = new ArrayList<>();

        final String SELECT_ROLES_USUARIOS
                = "SELECT rr.ROL, rr.DESCRIPCION "
                + "FROM GET_ROLES rr "
                + "WHERE rr.ROL NOT IN(SELECT r.ROL "
                + "                    FROM GET_ROL r "
                + "                    WHERE TRIM(r.USER_NAME) = ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT_ROLES_USUARIOS,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, userName.strip().toUpperCase());
            try (ResultSet rs = ps.executeQuery()) {
                String aux, descripcion;
                while (rs.next()) {
                    aux = rs.getString("ROL");
                    descripcion = rs.getString("DESCRIPCION");
                    
                    if (aux.equalsIgnoreCase("RDB$ADMIN")) {
                        aux = "ADMINISTRADOR";
                    }
                    
                    roles.add(
                            Roles.builder().
                                    roleName(aux.strip()).
                                    descripcion(Objects.isNull(descripcion) ? "":descripcion.strip()).
                                    build()
                    );
                }
            }
            return roles;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Este metodo devuelve todos los relacionados a un rol del sistema.
     *
     * @param rol
     * @return
     */
    public synchronized static List<Roles> getPermisosRoles(String rol) {
        final String sql = "SELECT TRIM(USER_NAME) USER_NAME, TRIM(NOMBRE_RELACION) NOMBRE_RELACION, TRIM(DESCRIPCION) DESCRIPCION "
                + "FROM GET_PRIVILEGIOS "
                + "WHERE TRIM(USER_NAME) STARTING WITH ? AND NOMBRE_RELACION STARTING WITH 'SP_'";
        List<Roles> roles = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, rol);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    
                    String userName = rs.getString("USER_NAME");
                    String descripcion = rs.getString("DESCRIPCION");
                    String nombreRelacion = rs.getString("NOMBRE_RELACION");
                    
                    roles.add(Roles.builder().
                            roleName(Objects.isNull(userName) ? "":userName.strip()).
                            descripcion(Objects.isNull(descripcion) ? "":descripcion .strip()).
                            nombreProcedimiento(Objects.isNull(nombreRelacion) ? "":nombreRelacion.strip()).build());
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
        return roles;
    }

    public synchronized static List<Roles> getRolesDisponibles(String rol) {
        final String sql = "SELECT DISTINCT(TRIM(a.NOMBRE_RELACION)) NOMBRE_RELACION, TRIM(a.DESCRIPCION) DESCRIPCION "
                + "FROM GET_PRIVILEGIOS a "
                + "WHERE TRIM(a.USER_NAME) LIKE 'RDB$ADMIN' AND TRIM(a.NOMBRE_RELACION) STARTING WITH 'SP_' AND "
                + "     TRIM(a.NOMBRE_RELACION) NOT IN(SELECT TRIM(NOMBRE_RELACION) "
                + "                              FROM GET_PRIVILEGIOS "
                + "                              WHERE TRIM(USER_NAME) STARTING WITH ?);";
        List<Roles> roles = new ArrayList<>();
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
                    nombreRelacion = rs.getString("NOMBRE_RELACION");
                    roles.add(Roles.builder().
                            descripcion(Objects.isNull(descripcion) ? "": descripcion.strip()).
                            nombreProcedimiento(Objects.isNull(nombreRelacion) ? "":nombreRelacion.strip()).
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
     * Es el metodo que nos devuelve los Roles del sistema, los cuales son asig-
     * nados a los usuarios.
     *
     * @return
     */
    public synchronized static List<Roles> getRoles() {
        final String sql
                = "SELECT ROL, PROPIETARIO, DESCRIPCION FROM GET_ROLES";

        List<Roles> rolesList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    String rol, descripcion, propietario;
                    rol = rs.getString("Rol");
                    descripcion = rs.getString("Descripcion");
                    propietario = rs.getString("PROPIETARIO");
                    rolesList.add(Roles.builder().
                            roleName(Objects.isNull(rol) ? "":rol.strip()).
                            descripcion(Objects.isNull(descripcion) ? "":descripcion.strip()).
                            propietario(Objects.isNull(propietario) ? "":propietario.strip()).build());
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
     * Este metodo nos permite establecer el rol que el usuario usará en el
     * sistema, puede ser ejecutado una vez que el usuario haya iniciado
     * session.
     *
     * @param role Nombre del rol que va a establecerse al usuario que ejecute
     * el metodo.
     * @return
     */
    public synchronized static Resultados<Object> setRole(String role) {
        String sql = "EXECUTE PROCEDURE SP_SET_ROLE (?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setString(1, role);
            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Rol establecido").
                    cantidad(-1).
                    estado(execute).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().id(-1).mensaje("Error al establecer rol").cantidad(-1).build();
        }
    }

    /**
     * Metodo utilizado para crear los roles de los usuarios del sistema.
     *
     * @param rolee Es el role a crear por el usuario.
     * @return
     */
    public synchronized static Resultados<Object> createRole(String rolee) {
        final String sql = "EXECUTE PROCEDURE SP_CREATE_ROLE (?);";

        try (PreparedStatement cs = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, rolee);

            int cantindad = cs.executeUpdate();

            return Resultados.builder().
                    id(-1).
                    mensaje("Rol creado exitosamente.").
                    cantidad(cantindad).
                    estado(false).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    id(-1).
                    mensaje("Error al crear role.").
                    cantidad(-1).
                    estado(false).
                    build();
        }
    }

    public synchronized static Resultados<Object> modificarRol(String actual,
            String nuevo) {
        final String sql = "EXECUTE PROCEDURE SP_ALTER_ROLE (?, ?);";

        try (PreparedStatement cs = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, actual);
            cs.setString(2, nuevo);

            int cantindad = cs.executeUpdate();

            return Resultados.builder().
                    id(-1).
                    mensaje("Rol modificado exitosamente.").
                    cantidad(cantindad).
                    estado(false).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    id(-1).
                    mensaje("Error al modificar role.").
                    cantidad(-1).
                    estado(false).
                    build();
        }
    }

    @Override
    public String toString() {
        return roleName.replace("RDB$ADMIN", "ADMINISTRADOR").strip();
    }

}
