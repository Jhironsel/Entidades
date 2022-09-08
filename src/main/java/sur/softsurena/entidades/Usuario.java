package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Usuario extends Personas {

    public static String UPDATE
            ="EXECUTE PROCEDURE SP_UPDATE_USUARIOS (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    /**
     * OJO! Revisar este caso, puede ser que el usuario se le asista cambiar su clave
     * en este caso necesitamos pasar el nombre del usuario. 
     */
    public static String CAMBIAR_CLAVE
            = "ALTER USER CURRENT_USER PASSWORD ?";
    
    public static String DELETE_ROL
            = "REVOKE ? FROM ? GRANTED BY ?";
    
    public final static String GET_ALL_USER
            = "SELECT p.O_USER_NAME, p.O_PRIMER_NOMBRE, p.O_SEGUNDO_NOMBRE, p.O_APELLIDOS, "
            + "     p.O_ESTADO_ACTIVO, p.O_ADMINISTRADOR, p.O_TAG_NOMBRE, p.O_TAG_VALOR "
            + "FROM SP_SELECT_USUARIOS_TAGS (?) p;";

    public final static String EXISTE_USUARIO
            = "SELECT DISTINCT(1) "
            + "FROM SP_SELECT_USUARIOS_TAGS ('all') p "
            + "WHERE TRIM(p.O_USER_NAME) LIKE TRIM(UPPER(?))";


    public static String SELECT_ROLES_USUARIOS
            = "SELECT r.ROL "
            + "FROM GET_USER_ROLES r "
            + "WHERE UPPER(TRIM(r.USER_NAME)) LIKE UPPER(TRIM(?))";

    public static String GET_SELECT_USUARIOS_ACTIVOS
            = "SELECT r.IDUSUARIO, r.FECHA, r.HORA "
            + "FROM GET_USUARIO_ACTIVO r";

    public static String GET_SELECT_USUARIOS
            = "SELECT LOGINUSER, P_NOMBRE, S_NOMBRE, APELLIDOS, Movil,"
            + "TELEFONO, Correo, Especialidad, rol, sq, SuperUsuario, "
            + "ESTADO "
            + "FROM GET_USUARIOS "
            + "WHERE ESTADO IS ?";
    
    public static String GET_SELECT_DOCTORES
            = "SELECT LOGINUSER, P_NOMBRE, S_NOMBRE, APELLIDOS "
            + "FROM GET_USUARIOS "
            + "WHERE ESTADO and rol like 'DOCTOR'";

    public static String DELEGA
            = "SELECT (1) "
            + "FROM GET_usuarios u "
            + "WHERE trim(u.idusuario) = upper(trim(?)) and "
            + "u.autorizado";

    private final String clave;
    private final String rol;
    private final Boolean administrador;
    private final String cod_Exequatur;
    private final String especialidad;
}
