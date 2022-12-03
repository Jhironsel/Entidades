package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Usuarios extends Personas {

    /**
     * Query que permite agregar usuarios al sistema. 
     */
    public static String INSERT
            = "EXECUTE PROCEDURE SP_INSERT_USUARIOS(?,?,?,?,?,?,?,?)";
    
    /**
     * Procedimiento que se encarga de actualizar a los usuarios del sistema.
     */
    public static String UPDATE
            = "EXECUTE PROCEDURE SP_UPDATE_USUARIOS(?,?,?,?,?,?,?,?)";
    
    /**
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
     * Este Query es utilizado para obtener Inf. de los usuarios del sistema.
     * Actualizado: 15 Sep 2022.
     */
    public static String SELECT
            = "SELECT TRIM(u.SEC$USER_NAME) AS USER_NAME, "
            + "     TRIM(u.SEC$FIRST_NAME) AS PNOMBRE, "
            + "     TRIM(u.SEC$MIDDLE_NAME) AS SNOMBRE, "
            + "     TRIM(u.SEC$LAST_NAME) AS APELLIDOS, "
            + "     u.SEC$ACTIVE AS ESTADO, "
            + "     u.SEC$ADMIN AS ADMINISTRADOR, "
            + "     TRIM(u.SEC$DESCRIPTION) AS DESCRIPCION "
            + "FROM SEC$USERS u";
    
    /**
     * Query que me permite comprobar si un usuario existe en el sistema.
     */
    public final static String EXISTE_USUARIO_BY_USER_NAME
            = "SELECT DISTINCT(1) "
            + "FROM SP_SELECT_USUARIOS_TAGS ('all') p "
            + "WHERE TRIM(p.O_USER_NAME) LIKE TRIM(UPPER(?))";
    
    /**
     * Query que me trae todas las tags de un usuario en especifico. 
     */
    public final static String GET_USER_BY_USER_NAME_TAG
            = "SELECT p.O_TAG_NOMBRE, p.O_TAG_VALOR "
            + "FROM SP_SELECT_USUARIOS_TAGS (?) p;";

    /**
     *
     */
    public static String GET_SELECT_USUARIOS_ACTIVOS
            = "SELECT r.IDUSUARIO, r.FECHA, r.HORA "
            + "FROM GET_USUARIO_ACTIVO r";

    

    /**
     * OJO! Revisar este caso, puede ser que el usuario se le asista cambiar su
     * clave en este caso necesitamos pasar el nombre del usuario.
     */
    public static String CAMBIAR_CLAVE
            = "ALTER USER CURRENT_USER PASSWORD ?";

    /**
     * Este campo permite cambiar la contraseña a cualquier usuario.
     */
    public static String CAMBIAR_CLAVE_USER_NAME
            = "ALTER USER ? PASSWORD ?";

    /**
     * Deberia de crearse un store procedure que permita eliminar Usuarios, ya
     * que debemos conservar los registros de los usuarios que hayan hechos
     * cambios en la base de datos.
     */
    public static String DELETE_ROL
            = "REVOKE ? FROM ? GRANTED BY ?";
    
    public static String SELECT_ROLES_USUARIOS = "SELECT ROL FROM GET_ROL WHERE USER_NAME LIKE ?";

    private final String clave;
    private final String descripcion;
    private final Boolean administrador;

    @Override
    public String toString() {
        return super.getUser_name();
    }
    
    
}
