package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Privilegios {

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
            = "SELECT r.USER_NAME, r.CEDENTE, r.PRIVILEGIO, \n"
            + "     r.OPCION_PERMISO, r.NOMBRE_RELACION, \n"
            + "     r.NOMBRE_CAMPO, r.TIPO_USUARIO, \n"
            + "     r.TIPO_OBJECTO \n"
            + "FROM GET_PRIVILEGIOS r \n";

    public static String PERMISO_UPDATE_TABLA
            = "SELECT (1) "
            + "FROM GET_PRIVILEGIOS r \n" 
            + "WHERE (TRIM(r.USER_NAME) LIKE TRIM(CURRENT_USER) OR \n"
            + "      TRIM(r.USER_NAME) LIKE TRIM(CURRENT_ROLE)) AND \n"
            + "      TRIM(r.PRIVILEGIO) LIKE TRIM(?) AND \n"
            + "      TRIM(r.NOMBRE_RELACION) LIKE TRIM(?) ";
    
    public static String PERMISO_UPDATE_CAMPO
            = PERMISO_UPDATE_TABLA 
            + "      AND TRIM(r.NOMBRE_CAMPO) LIKE TRIM(?); \n";
    
    public static String GET_ROLES
            = "SELECT r.ROL FROM GET_ROLES r";

    @Override
    public String toString() {
        return nombre_campo;
    }

}
