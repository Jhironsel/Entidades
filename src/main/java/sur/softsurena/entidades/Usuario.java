package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Usuario extends Persona{
    
    public final static String GET_ALL_USER = 
            "SELECT p.O_USER_NAME, p.O_PRIMER_NOMBRE, p.O_SEGUNDO_NOMBRE, p.O_APELLIDOS, " + 
            "     p.O_ESTADO_ACTIVO, p.O_ADMINISTRADOR, p.O_TAG_NOMBRE, p.O_TAG_VALOR " +
            "FROM SP_SELECT_USUARIOS_TAGS (?) p;";
    
    public final static String EXISTE_USUARIO = "SELECT DISTINCT(1) "
                + "FROM SP_SELECT_USUARIOS_TAGS ('all') p "
                + "WHERE TRIM(p.O_USER_NAME) LIKE TRIM(UPPER(?))";
    
    private final String userName;
    private final String clave;
    private final String rol;
    private final Boolean administrador;
    private final String cod_Exequatur;
    private final String especialidad;
}
