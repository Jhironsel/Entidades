package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Privilegio {

    private final String user_name;
    private final Character privilegio;
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
     * Abreviatura utilizada para designar los usos de los usuarios en los
     * dominios y generadores.
     */
    public static char PRIVILEGIO_USAGE = 'G';

    /**
     * Abreviatura utilizada para designar los miembros de los usuarios en los
     * roles.
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
    public static char PRIVILEGIO_REFERENCE = 'R';

    @Override
    public String toString() {
        return nombre_campo;
    }
}
