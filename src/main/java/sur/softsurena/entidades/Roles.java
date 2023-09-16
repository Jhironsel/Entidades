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
    private final int opcionPermiso;

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
                = "SELECT ROL, DESCRIPCION, ADMINISTRACION "
                + "FROM GET_ROL "
                + "WHERE USER_NAME STARTING WITH ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT_ROLES_USUARIOS,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
        final String sql = 
                "SELECT TRIM(NOMBRE_RELACION) NOMBRE_RELACION, "
                + "TRIM(DESCRIPCION) DESCRIPCION, OPCION_PERMISO "
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
                    
                    String nombreRelacion = rs.getString("NOMBRE_RELACION");
                    String descripcion = rs.getString("DESCRIPCION");
                    int opcionPermiso = rs.getInt("OPCION_PERMISO");
                    
                    roles.add(Roles.builder().
                            descripcion(Objects.isNull(descripcion) ? "":descripcion .strip()).
                            nombreProcedimiento(Objects.isNull(nombreRelacion) ? "":nombreRelacion.strip()).
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
     * Es un procedimiento de uso publico, ya que todos van a cambiar su rol en 
     * el sistema.
     *
     * @param i_role Nombre del rol que va a establecerse al usuario que ejecute
     * el metodo.
     * @return
     */
    public synchronized static Resultados<Object> setRole(String i_role) {
        String sql = "EXECUTE PROCEDURE ADMIN_SET_ROLE(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setString(1, i_role);
            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Rol establecido").
                    cantidad(-1).
                    estado(execute).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    id(-1).
                    mensaje("Error al establecer rol").
                    cantidad(-1).
                    build();
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
    public synchronized static Resultados<Object> dropRole(String i_role) {
        String sql = "EXECUTE PROCEDURE ADMIN_BORRAR_ROLE(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setString(1, i_role);
            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Role eliminado").
                    cantidad(-1).
                    estado(execute).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    id(-1).
                    mensaje("Error al borrar rol").
                    cantidad(-1).
                    build();
        }
    }
    
    /**
     * 
     * @param procedimiento
     * @param rol
     * @param admin
     * @return 
     */
    public synchronized static Resultados<Object> asignarRol(
            String procedimiento, String rol, boolean admin) {
        String sql = "EXECUTE PROCEDURE ADMIN_DAR_PERMISO_ROL(?,?,?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            cs.setString(1, procedimiento);
            cs.setString(2, rol);
            cs.setBoolean(3, admin);
            
            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Rol asignado").
                    cantidad(-1).
                    estado(execute).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().id(-1).mensaje("Error al establecer rol").cantidad(-1).build();
        }
    }
    
    /**
     * 
     * @param usuario
     * @param rol
     * @param admin
     * @return 
     */
    public synchronized static Resultados<Object> asignarRolUsuario(
            String rol, String usuario, boolean admin) {
        String sql = "EXECUTE PROCEDURE ADMIN_DAR_ROL_USUARIO(?,?,?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            cs.setString(1, rol);
            cs.setString(2, usuario);
            cs.setBoolean(3, admin);
            
            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Rol asignado a usuario").
                    cantidad(-1).
                    estado(execute).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    id(-1).
                    mensaje("Error al asignar rol").
                    cantidad(-1).
                    build();
        }
    }
    
    /**
     * 
     * @param procedimiento
     * @param rol
     * @param admin
     * @return 
     */
    public synchronized static Resultados<Object> quitarPermisoAdminProcedimiento(
            String procedimiento, String rol) {
        String sql = "EXECUTE PROCEDURE ADMIN_QUITAR_PERMISO_ADMIN_PROCE(?,?)";
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
     * @param rol
     * @param usuario
     * @return 
     */
    public synchronized static Resultados<Object> quitarPermisoAdminRole(
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
     * @param admin
     * @return 
     */
    public synchronized static Resultados<Object> agregarPermisoAdminProcedimiento(
            String procedimiento, String rol) {
        String sql = "EXECUTE PROCEDURE ADMIN_AGREGAR_PERMISO_ADMIN_PROCE(?,?)";
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
     * @param role
     * @param usuario
     * @param admin
     * @return 
     */
    public synchronized static Resultados<Object> agregarPermisoAdminRole(
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
     * 
     * @param procedimiento
     * @param rol
     * @param admin
     * @return 
     */
    public synchronized static Resultados<Object> borrarPermisoAdminProcedimiento(
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
    
    /**
     * 
     * @param usuario
     * @param rol
     * @param admin
     * @return 
     */
    public synchronized static Resultados<Object> quitarRolUsuario(
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

            return Resultados.builder().
                    id(-1).
                    mensaje("Rol borrado correctamente.").
                    cantidad(-1).
                    estado(execute).
                    build();
            
        } catch (SQLException ex) {
            
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            
            return Resultados.builder().
                    id(-1).
                    mensaje("Error al borrar Rol").
                    cantidad(-1).
                    build();
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
                ResultSet.CONCUR_READ_ONLY,
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

    /**
     * SP que permite a los administradores modificar los nombres de los roles y
     * pasar los permisos y asignaciones de los roles al nuevo rol creado.
     * 
     * @param actual recibe el nombre de un rol existente. 
     * @param nuevo recibe el nuevo nombre del rol a crear. 
     * 
     * @return devuelve un objecto Resultados para obtener informacion de la op.
     */
    public synchronized static Resultados<Object> modificarRol(String actual,
            String nuevo) {
        final String sql = "EXECUTE PROCEDURE ADMIN_ALTER_ROLE(?, ?);";

        try (PreparedStatement cs = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
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
        if(Objects.isNull(roleName)){
            return nombreProcedimiento;
        }else{
            return roleName.replace("RDB$ADMIN", "ADMINISTRADOR").strip();
        }
    }

}
