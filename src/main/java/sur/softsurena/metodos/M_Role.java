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
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Role {

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
    public synchronized static List<Role> comprobandoRol(String userName) {
        List<Role> roles = new ArrayList<>();

        final String sql
                = "SELECT ROL, DESCRIPCION, ADMINISTRACION "
                + "FROM GET_ROL "
                + "WHERE TRIM(USER_NAME) STARTING WITH TRIM(?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, userName.strip().toUpperCase());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roles.add(Role.builder().
                            roleName(rs.getString("ROL")).
                            descripcion(rs.getString("DESCRIPCION")).
                            opcionPermiso(rs.getInt("ADMINISTRACION")).
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
    public synchronized static List<Role> comprobandoRolesDisponibles(String userName) {
        List<Role> roles = new ArrayList<>();
        final String sql
                = "SELECT rr.ROL, rr.DESCRIPCION "
                + "FROM GET_ROLES rr "
                + "WHERE rr.ROL NOT IN(SELECT r.ROL "
                + "                    FROM GET_ROL r "
                + "                    WHERE TRIM(r.USER_NAME) = ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, userName.strip().toUpperCase());

            try (ResultSet rs = ps.executeQuery()) {
                String aux, descripcion;
                while (rs.next()) {
                    aux = rs.getString("ROL");
                    descripcion = rs.getString("DESCRIPCION");

                    if (aux.equalsIgnoreCase("RDB$ADMIN")) {
                        aux = "ADMINISTRADOR";
                    }

                    roles.add(Role.builder().
                            roleName(aux.strip()).
                            descripcion(Objects.isNull(descripcion) ? "" : descripcion.strip()).
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
     * Es el metodo que nos devuelve los Roles del sistema, los cuales son asig-
     * nados a los usuarios.
     *
     * @return
     */
    public synchronized static List<Role> getRoles() {
        final String sql
                = "SELECT ROL, PROPIETARIO, DESCRIPCION FROM GET_ROLES";

        List<Role> rolesList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {

                    String rol, descripcion, propietario;

                    rol = rs.getString("Rol");
                    descripcion = rs.getString("Descripcion");
                    propietario = rs.getString("PROPIETARIO");

                    rolesList.add(Role.builder().
                            roleName(Objects.isNull(rol) ? "" : rol.strip()).
                            descripcion(Objects.isNull(descripcion) ? "" : descripcion.strip()).
                            propietario(Objects.isNull(propietario) ? "" : propietario.strip()).build());
                }
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
     * Es un procedimiento de uso publico, ya que todos van a cambiar su rol en
     * el sistema.
     *
     * @param i_role Nombre del rol que va a establecerse al usuario que ejecute
     * el metodo.
     * @return
     */
    public synchronized static Resultado<Object> setRole(String i_role) {
        String sql = "EXECUTE PROCEDURE ADMIN_SET_ROLE(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setString(1, i_role);
            cs.execute();
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ROL_ESTABLECIDO)
                    .cantidad(-1)
                    .estado(true)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_ESTABLECER_ROL)
                    .cantidad(-1)
                    .estado(false)
                    .build();
        }
    }
    public static final String ROL_ESTABLECIDO = "Rol establecido.";
    public static final String ERROR_AL_ESTABLECER_ROL = "Error al establecer rol.";

    /**
     * Este metodo nos permite establecer el rol que el usuario usará en el
     * sistema, puede ser ejecutado una vez que el usuario haya iniciado
     * session.
     *
     * Es un procedimiento de uso publico, ya que todos van a cambiar su rol en
     * el sistema.
     *
     * @param i_role Nombre del rol que va a establecerse al usuario que ejecute
     * el metodo.
     * @return
     */
    public synchronized static Resultado<Object> dropRole(String i_role) {
        String sql = "EXECUTE PROCEDURE ADMIN_BORRAR_ROLE(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setString(1, i_role);
            boolean execute = cs.execute();

            return Resultado.builder().
                    id(-1).
                    mensaje(ROLE_ELIMINADO).
                    cantidad(-1).
                    estado(execute).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado.builder().
                    id(-1).
                    mensaje(ERROR_AL_BORRAR_ROL).
                    cantidad(-1).
                    build();
        }
    }
    public static final String ERROR_AL_BORRAR_ROL = "Error al borrar rol";
    public static final String ROLE_ELIMINADO = "Role eliminado";

    /**
     * Este metodo permite ejecutar procedimientos que otorgan permisos a los
     * roles.
     *
     * @param procedimiento Es el procedimiento que se ejecuta en la BD. Debe de
     * tener las iniciales de PERM_ para ejecutarse.
     *
     * @param rol es el rol a la cual se le va a dar los permisos necesarios
     * para ejecutar el procedimiento.
     *
     * @param admin Es la bandera que indica si el permiso tendrá derecho de
     * administrarlo o de cedelo a otros usuario.
     *
     * @param otorgar Permite determinar si el usuario va a recibir el permiso
     * en el caso de que sea true, en caso contrario se le quita los permisos
     * false.
     *
     * @return Devuelve un objecto de la clase resultados que indica si la
     * operacion fue un exito si o no.
     */
    public synchronized static Resultado asignarRol(
            String procedimiento, String rol, boolean admin, boolean otorgar) {
        if (!procedimiento.startsWith("PERM_")) {
            return Resultado.builder().
                    id(-1).
                    mensaje(PROCEDIMIENTO_INCORRECTO_A_LA_BASE_DE_DAT).
                    cantidad(-1).
                    estado(Boolean.FALSE).
                    build();
        }
        String sql = "EXECUTE PROCEDURE " + procedimiento + " (?,?,?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setString(1, rol);
            cs.setBoolean(2, admin);
            cs.setBoolean(3, otorgar);
            boolean execute = cs.execute();
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(PERMISO__ACTUALIZADO)
                    .cantidad(-1)
                    .estado(execute)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_ESTABLECER_ROL1)
                    .cantidad(-1)
                    .build();
        }
    }
    public static final String PERMISO__ACTUALIZADO = "Permiso Actualizado.";
    public static final String ERROR_AL_ESTABLECER_ROL1 = "Error al establecer rol";
    public static final String PROCEDIMIENTO_INCORRECTO_A_LA_BASE_DE_DAT = "Procedimiento incorrecto a la base de datos.";

    /**
     *
     * @param usuario
     * @param rol
     * @param admin
     * @return
     */
    public synchronized static Resultado asignarRolUsuario(
            String rol, String usuario, boolean admin) {
        String sql = "EXECUTE PROCEDURE ADMIN_DAR_ROL_USUARIO(?,?,?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setString(1, rol);
            cs.setString(2, usuario);
            cs.setBoolean(3, admin);

            boolean execute = cs.execute();

            return Resultado
                    .builder()
                    .mensaje(ROL_ASIGNADO_A_USUARIO)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_ASIGNAR_ROL)
                    .build();
        }
    }
    public static final String ERROR_AL_ASIGNAR_ROL = "Error al asignar rol";
    public static final String ROL_ASIGNADO_A_USUARIO = "Rol asignado a usuario";

    /**
     *
     * @param usuario
     * @param rol
     * @return
     */
    public synchronized static Resultado<Object> quitarRolUsuario(
            String rol, String usuario) {
        String sql = "EXECUTE PROCEDURE ADMIN_QUITAR_ROL_USUARIO(?,?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, rol);
            cs.setString(2, usuario);

            boolean execute = cs.execute();

            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ROL_BORRADO_CORRECTAMENTE)
                    .cantidad(-1)
                    .estado(execute)
                    .build();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);

            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_BORRAR__ROL)
                    .cantidad(-1)
                    .build();
        }
    }
    public static final String ERROR_AL_BORRAR__ROL = "Error al borrar Rol";
    public static final String ROL_BORRADO_CORRECTAMENTE = "Rol borrado correctamente.";

    /**
     * Metodo utilizado para crear los roles de los usuarios del sistema.
     *
     * @param rolee Es el role a crear por el usuario.
     * @return
     */
    public synchronized static Resultado<Object> createRole(String rolee) {
        final String sql = "EXECUTE PROCEDURE ADMIN_CREATE_ROLE(?);";

        try (PreparedStatement cs = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, rolee);

            int cantindad = cs.executeUpdate();

            return Resultado.builder().
                    id(-1).
                    mensaje(ROL_CREADO_EXITOSAMENTE).
                    cantidad(cantindad).
                    estado(false).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado.builder().
                    id(-1).
                    mensaje(ERROR_AL_CREAR_ROLE).
                    cantidad(-1).
                    estado(false).
                    build();
        }
    }
    public static final String ERROR_AL_CREAR_ROLE = "Error al crear role.";
    public static final String ROL_CREADO_EXITOSAMENTE = "Rol creado exitosamente.";

    /**
     * SP que permite a los administradores modificar los nombres de los roles y
     * pasar los permisos y asignaciones de los roles al nuevo rol creado.
     *
     * @param actual recibe el nombre de un rol existente.
     * @param nuevo recibe el nuevo nombre del rol a crear.
     *
     * @return devuelve un objecto Resultados para obtener informacion de la op.
     */
    public synchronized static Resultado<Object> modificarRol(String actual,
            String nuevo) {
        final String sql = "EXECUTE PROCEDURE ADMIN_ALTER_ROLE(?, ?);";

        try (PreparedStatement cs = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            cs.setString(1, actual);
            cs.setString(2, nuevo);

            int cantindad = cs.executeUpdate();

            return Resultado.builder().
                    id(-1).
                    mensaje("Rol modificado exitosamente.").
                    cantidad(cantindad).
                    estado(false).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultado.builder().
                    id(-1).
                    mensaje("Error al modificar role.").
                    cantidad(-1).
                    estado(false).
                    build();
        }
    }
}
