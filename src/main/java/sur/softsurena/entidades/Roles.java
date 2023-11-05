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
    private final boolean conAdmin;

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

        final String sql
                = "SELECT ROL, DESCRIPCION, ADMINISTRACION "
                + "FROM GET_ROL "
                + "WHERE USER_NAME STARTING WITH ?";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            ps.setString(1, userName.strip().toUpperCase());
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String descripcion = rs.getString("DESCRIPCION");
                    roles.add(
                            Roles.builder().
                                    roleName(rs.getString("ROL")).
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
    public synchronized static Resultados<Object> asignarRol(
            String procedimiento, String rol, boolean admin, boolean otorgar) {
        
        if(!procedimiento.startsWith("PERM_")){
            return Resultados.builder().
                    id(-1).
                    mensaje("Procedimiento incorrecto a la base de datos.").
                    cantidad(-1).
                    estado(Boolean.FALSE).
                    build();
        }
        
        String sql = "EXECUTE PROCEDURE "+procedimiento+" (?,?,?)";
        
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            cs.setString(1, rol);
            cs.setBoolean(2, admin);
            cs.setBoolean(3, otorgar);
            
            boolean execute = cs.execute();

            return Resultados.builder().
                    id(-1).
                    mensaje("Permiso Actualizado.").
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
        final String sql = "EXECUTE PROCEDURE ADMIN_CREATE_ROLE(?);";

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
            if(roleName.equalsIgnoreCase("RDB$ADMIN")){
                return "ADMINISTRADOR";
            }else if(roleName.equalsIgnoreCase("ADMINISTRADOR")){
                return "RDB$ADMIN";
            }
        }
        return roleName.strip();
    }

}
