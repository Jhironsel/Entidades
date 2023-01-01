package sur.softsurena.entidades;

import java.sql.CallableStatement;
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

@Getter
@SuperBuilder
public class Usuarios extends Personas {

    private static final Logger LOG = Logger.getLogger(Usuarios.class.getName());

    /**
     * Query que permite agregar usuarios al sistema.
     */
    public static final String INSERT
            = "EXECUTE PROCEDURE SP_INSERT_USUARIOS(?,?,?,?,?,?,?,?)";

    /**
     * Procedimiento que se encarga de actualizar a los usuarios del sistema.
     */
    public static final String UPDATE
            = "EXECUTE PROCEDURE SP_UPDATE_USUARIOS(?,?,?,?,?,?,?,?)";

    /**
     * Query que me trae todas las tags de un usuario en especifico.
     */
    public final static String GET_USER_BY_USER_NAME_TAG
            = "SELECT p.O_TAG_NOMBRE, p.O_TAG_VALOR "
            + "FROM SP_SELECT_USUARIOS_TAGS (?) p;";

    /**
     * Este campo permite cambiar la contraseña a cualquier usuario.
     */
    public static String CAMBIAR_CLAVE_USER_NAME
            = "ALTER USER ? PASSWORD ?";

    public static String SELECT_ROLES_USUARIOS = "SELECT ROL FROM GET_ROL WHERE USER_NAME LIKE ?";

    private final String clave;
    private final String descripcion;
    private final Boolean administrador;

    /**
     * OJO! Revisar este caso, puede ser que el usuario se le asista cambiar su
     * clave en este caso necesitamos pasar el nombre del usuario.
     */
    public static String CAMBIAR_CLAVE
            = "ALTER USER CURRENT_USER PASSWORD ?";

    /**
     * Metodo que permite el cambio de contraseña de los usuario del sistema.
     *
     * Metodo revisado el 24 de abril 2022. Metodo actualizado el 19 05 2022,
     * Nota, se cambia la posicion del parametro, primero usuario y luego la
     * clave.
     *
     * @param clave
     * @param usuario
     */
    public synchronized static boolean cambioClave(String clave) {
        try (PreparedStatement ps = getCnn().prepareStatement(CAMBIAR_CLAVE);) {
            ps.setString(1, clave);

            return ps.execute();

        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    /**
     * Para matener la mejor integridad de los datos los usuario se desactivan
     * del sistema.
     *
     * Metodo revisado 24 abril 2022.
     *
     * @param loginName
     * @param estado
     * @return
     */
    public synchronized static String borrarUsuario(String loginName,
            boolean estado) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                "ALTER USER ? SET " + (estado ? "ACTIVE" : "INACTIVE"));) {
            ps.setString(1, loginName);
            ps.executeUpdate();
            return "Usuario " + (estado ? "Activado" : "borrado")
                    + " correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al borrar usuario...";
        }
    }

    /**
     * Deberia de crearse un store procedure que permita eliminar Usuarios, ya
     * que debemos conservar los registros de los usuarios que hayan hechos
     * cambios en la base de datos.
     */
    public static String DELETE_ROL
            = "REVOKE ? FROM ? GRANTED BY ?";

    /**
     *
     * @param idUsuario
     * @param rol
     * @return
     */
    public synchronized static String borrarUsuario(String idUsuario, String rol) {
        try (PreparedStatement ps = getCnn().prepareStatement(DELETE_ROL);) {
            ps.setString(1, rol);
            ps.setString(2, idUsuario);
            ps.setString(3, getCreadorUsuario(idUsuario));

            ps.executeUpdate();

            try (PreparedStatement ps2 = getCnn().prepareStatement("DROP USER ?");) {
                ps2.setString(1, idUsuario);
                ps2.executeUpdate();
            } catch (SQLException ex) {
                //Instalar Logger
                return "Ocurrio un error al intentar borrar el Usuario.";
            }

            return "Usuario borrado correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Ocurrio un error al intentar borrar el Usuario.";
        }
    }
    /**
     * Esta variable se esta utilizando para obtener el rol y el usuario que se
     * loguean en el sistema.
     *
     * Fecha de revision: 24 oct 2022
     */
    public static String USUARIO_ACTUAL
            = "SELECT TRIM(CURRENT_USER), TRIM(CURRENT_ROLE) "
            + "FROM RDB$DATABASE";

    /**
     * Nitido. Metodo para consultar cual es el usuario actual del sistema.
     *
     * Una vez iniciada la session del usuario en el sistema, hacemos una
     * consulta a la base de datos, que nos devuelve el usuario y el rol de
     * este.
     *
     * Metodo actualizado el 17/05/2022.
     *
     * Metodo actualizado el 24 oct 2022:
     *
     * Nota:Se cambió el tipo de resultado que devuelve de un ResultSet a una
     * clase Usuarios.
     *
     * @return Retorna un String con el dato de cual es usuario del sistema que
     * ha iniciado sessión actualmente.
     */
    public synchronized static Usuarios getUsuarioActual() {
        try (PreparedStatement ps = getCnn().prepareStatement(
                USUARIO_ACTUAL, ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY, ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                return Usuarios.builder().
                        user_name(rs.getString(1)).
                        rol(rs.getString(2)).build();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Consulta que nos permite tener el nombre de usuario, primer nombre,
     * segundo nombre, apellidos, estado del usuario, si es administrador y una
     * breve descripcion del usuario que se haya registrado.
     *
     */
    public final static String GET_USER_BY_USER_NAME
            = "SELECT TRIM(p.O_USER_NAME) AS O_USER_NAME, "
            + "TRIM(p.O_PRIMER_NOMBRE) AS O_PRIMER_NOMBRE, "
            + "TRIM(p.O_SEGUNDO_NOMBRE) AS O_SEGUNDO_NOMBRE, "
            + "TRIM(p.O_APELLIDOS) AS O_APELLIDOS, "
            + "p.O_ESTADO_ACTIVO, p.O_ADMINISTRADOR, p.O_DESCRIPCION "
            + "FROM SP_SELECT_USUARIOS_TAGS (?) p;";

    /**
     * Metodo para llamar a los usuarios del sistema, este ejecuta un
     * procedimiento almacenado que realizar el SELECT complejo en la BD.
     *
     * Actualizado 09 Julio 2022, se agregar el campo estatico de la clase
     * Usuario el cual contiene el SQL de la consulta de este metodo.
     *
     * @param userName Es el identificador que utiliza el usuario para iniciar
     * session en el sistema, este campo tambien puede recibir un string con el
     * valor all para obtener todos los usuarios del sistema.
     *
     * @return retorna un conjunto de valores dependiendo del valor pasado por
     * parametro, si se le pasa all recibi un conjunto de datos de todos los
     * usuarios del sistema.
     */
    public synchronized static Usuarios getUsuario(String userName) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_USER_BY_USER_NAME,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                return Usuarios.builder().
                        user_name(rs.getString("O_USER_NAME")).
                        pNombre(rs.getString("O_PRIMER_NOMBRE")).
                        sNombre(rs.getString("O_SEGUNDO_NOMBRE")).
                        apellidos(rs.getString("O_APELLIDOS")).
                        estado(rs.getBoolean("O_ESTADO_ACTIVO")).
                        administrador(rs.getBoolean("O_ADMINISTRADOR")).
                        descripcion(rs.getString("O_DESCRIPCION")).build();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param estado
     * @return
     */
//    public synchronized static ResultSet getUsuario(boolean estado) {
//        try ( PreparedStatement ps = getCnn().prepareStatement(
//                Usuarios.GET_SELECT_USUARIOS,
//                ResultSet.TYPE_SCROLL_INSENSITIVE,
//                ResultSet.CONCUR_READ_ONLY,
//                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
//            ps.setBoolean(1, estado);
//            return ps.executeQuery();
//        } catch (SQLException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            return null;
//        }
//    }
    /**
     *
     * @return
     */
//    public synchronized static ResultSet getUsuarioDoctor() {
//        try ( PreparedStatement ps = getCnn().prepareStatement(Usuarios.GET_SELECT_DOCTORES,
//                ResultSet.TYPE_SCROLL_INSENSITIVE,
//                ResultSet.CONCUR_READ_ONLY,
//                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
//
//            return ps.executeQuery();
//
//        } catch (SQLException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            return null;
//        }
//    }
    /**
     * Se está utilizando para llenar los comboBox de los usuarios activos.
     *
     * @return
     */
    public synchronized static ResultSet getUsuariosActivo() {
        try (PreparedStatement ps = getCnn().prepareStatement("")) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Se esta utilizando para llenar la tabla de usuarios.
     *
     * Este Query es utilizado para obtener Inf. de los usuarios del sistema.
     * Actualizado: 15 Sep 2022.
     *
     * @return
     */
    public synchronized static List<Usuarios> getUsuarios() {
        List<Usuarios> usuarios = new ArrayList<>();
        Usuarios u;

        final String SELECT
                = "SELECT TRIM(u.SEC$USER_NAME) AS USER_NAME, "
                + "     TRIM(u.SEC$FIRST_NAME) AS PNOMBRE, "
                + "     TRIM(u.SEC$MIDDLE_NAME) AS SNOMBRE, "
                + "     TRIM(u.SEC$LAST_NAME) AS APELLIDOS, "
                + "     u.SEC$ACTIVE AS ESTADO, "
                + "     u.SEC$ADMIN AS ADMINISTRADOR, "
                + "     TRIM(u.SEC$DESCRIPTION) AS DESCRIPCION "
                + "FROM SEC$USERS u";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    u = Usuarios.builder().
                            pNombre((rs.getString("PNOMBRE") == null ? "" : rs.getString("PNOMBRE"))).
                            sNombre((rs.getString("SNOMBRE") == null ? "" : rs.getString("SNOMBRE"))).
                            apellidos((rs.getString("APELLIDOS") == null ? "" : rs.getString("APELLIDOS"))).
                            administrador(rs.getBoolean("ADMINISTRADOR")).
                            estado(rs.getBoolean("ESTADO")).
                            user_name(rs.getString("USER_NAME")).
                            descripcion(rs.getString("DESCRIPCION")).build();
                    usuarios.add(u);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return usuarios;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idUsuario
     * @return
     */
//    public synchronized static boolean delega(String idUsuario) {
//        try ( PreparedStatement ps = getCnn().prepareStatement(DELEGA)) {
//            ps.setString(1, idUsuario);
//            try ( ResultSet rs = ps.executeQuery()) {
//                return rs.next();
//            } catch (SQLException ex) {
//                LOG.log(Level.SEVERE, ex.getMessage(), ex);
//                return false;
//            }
//        } catch (SQLException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            return false;
//        }
//    }
    
    /**
     *
     * @return
     */
    public synchronized static ResultSet getCajerosActivos() {
        //Para que sirve este metodo...
        final String GET_SELECT_USUARIOS_ACTIVOS
            = "SELECT r.IDUSUARIO, r.FECHA, r.HORA "
            + "FROM GET_USUARIO_ACTIVO r";
        
        try (PreparedStatement ps = getCnn().prepareStatement(GET_SELECT_USUARIOS_ACTIVOS)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

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
    public synchronized static ArrayList<String> comprobandoRol(String userName) {
        ArrayList<String> roles = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                Usuarios.SELECT_ROLES_USUARIOS,
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
                    roles.add(aux);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return roles;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para modificar los usuarios del sistema con el rol
     * doctor, el cual permite agregar al registro su Exequatur y Especialidad.
     *
     * Metodo actualizado el 19 05 2022.
     *
     * @param u Un objeto de la case Usuario.
     * @return Devuelve un mensaje que indica si la actualizacion fue exitosa.
     */
    public static synchronized String agregarModificarUsuario(Usuarios u, String sql) {
        try (CallableStatement cs = getCnn().prepareCall(sql)) {
            cs.setString(1, u.getUser_name());
            cs.setString(2, u.getClave());
            cs.setString(3, u.getPNombre());
            cs.setString(4, u.getSNombre());
            cs.setString(5, u.getApellidos());
            cs.setBoolean(6, u.getEstado());
            cs.setBoolean(7, u.getAdministrador());
            cs.setString(8, u.getDescripcion());

            cs.executeUpdate();

            return "Usuario Agregado o Modificado Correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al Modificar Usuario...";
        }
    }

    /**
     * Este metodo debe ser investigado pues no fue documentado.
     *
     * @param idUsuario
     * @return
     */
    public synchronized static String getCreadorUsuario(String idUsuario) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                "SELECT creador "
                + "FROM get_creador "
                + "WHERE TRIM(usuario) like ?;")) {

            ps.setString(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("creador");
                } else {
                    return null;
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que permite verificar si existe un usuario en el sistema, esto con
     * el objetivo de permitir a un usuario ser registrado en la base de datos.
     *
     * Query que me permite comprobar si un usuario existe en el sistema.
     *
     * @param userName Identificador unico de un usuario para ser utilizado como
     * parte de login de inicio en el sistema.
     *
     * Actualizado el dia 09 julio 2022, Nota: se agrega el campo de la clase
     * Usuario, para que tome el SQL de la consulta.
     *
     * @return retorna un valor booleano que nos permite saber si existe "TRUE"
     * o no "FALSE" un usuario en el sistema.
     */
    public synchronized static boolean existeUsuarioByUserName(String userName) {

        final String EXISTE_USUARIO_BY_USER_NAME
                = "SELECT DISTINCT(1) "
                + "FROM SP_SELECT_USUARIOS_TAGS ('all') p "
                + "WHERE TRIM(p.O_USER_NAME) LIKE TRIM(UPPER(?))";
        try (PreparedStatement ps = getCnn().prepareStatement(EXISTE_USUARIO_BY_USER_NAME)) {
            ps.setString(1, userName);
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
        return super.getUser_name();
    }

}
