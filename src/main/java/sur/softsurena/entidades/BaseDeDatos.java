package sur.softsurena.entidades;

public class BaseDeDatos {

    public static String PATH_BASE_DATOS
            = "SELECT MON$DATABASE_NAME FROM MON$DATABASE";

    public static String METADATOS
            = "SELECT TRIM(CURRENT_CONNECTION), TRIM(CURRENT_DATE), "
            + "CURRENT_TIME, TRIM(CURRENT_TIMESTAMP), "
            + "TRIM(CURRENT_TRANSACTION) "
            + "FROM RDB$DATABASE";

    /**
     * Esta variable se esta utilizando para obtener el rol y el usuario que se
     * loguean en el sistema.
     * 
     * Fecha de revision: 24 oct 2022
     */
    public static String USUARIO_ACTUAL
            = "SELECT TRIM(CURRENT_USER), TRIM(CURRENT_ROLE) "
            + "FROM RDB$DATABASE";

    public static String PERIODO
            = "SELECT r.D FROM V_TIME_LIC r";

    public static String EXISTE_REGISTRO
            = "SELECT (1) FROM V_FCH_LC a WHERE a.ID = ?";

}
