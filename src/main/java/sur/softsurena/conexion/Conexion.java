package sur.softsurena.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lombok.NonNull;
import sur.softsurena.entidades.Resultados;

public class Conexion {

    //Variables Privadas
    private static final Logger LOG = Logger.getLogger(Conexion.class.getName());

    private static Connection cnn;
    private static String user, clave;
    private static StringBuilder urlDB;
    private static final String PROTOCOLO_FIREBIRD = "jdbc:firebirdsql://";

    public static Connection getCnn() {
        return cnn;
    }

    public synchronized static void setCnn(Connection cnn) {
        Conexion.cnn = cnn;
    }

    /**
     * Unico Metodo que permite obtener una instancia de la clase Conexión. La
     * cual requeire de los siguientes parametros de entrada.
     *
     * @param user Es el usuario registrado en el sistema.
     * @param clave Clave de acceso del usuario.
     * @param pathBaseDatos Ruta de acceso hacia la Base de Datos.
     * @param dominio Direccion ip o local de la base de datos.
     * @param puerto Puerto utilizado para la conexion de la base de datos.
     *
     * @return Devuelve una instancia de la clase conexion. La cual inicializa
     * las variables para la conexion a la base de datos.
     */
    public static Conexion getInstance(@NonNull String user, @NonNull String clave,
            @NonNull String pathBaseDatos, @NonNull String dominio,
            @NonNull String puerto) {

        Conexion.user = user;
        Conexion.clave = clave;

        StringBuilder p = new StringBuilder("");

        if (!puerto.isBlank()) {
            p.append(":").append(puerto);
        }

        urlDB = new StringBuilder();
        urlDB.append(PROTOCOLO_FIREBIRD)
                .append(dominio)
                .append(p)
                .append("/")
                .append(pathBaseDatos);

        System.out.println("URL_BD: " + urlDB);

        return ConexionHolder.INSTANCE;
    }

    private static class ConexionHolder {

        private static final Conexion INSTANCE = new Conexion();
    }

    private Conexion() {
    }

    /**
     * Metodo que permite a los usuarios del sistema validar si estan
     * debidamente Loggeado,
     *
     * @return Retorna true si esta dentro o false si tuvo problema en la
     * conexion.
     */
    public static Resultados<Object> verificar() {
        final Properties properties = new Properties();
        //Objecto Properties necesario para la base de datos. 
        properties.setProperty("user", user);
        properties.setProperty("password", clave);
        properties.setProperty("charSet", "UTF8");
        try {
            setCnn(DriverManager.getConnection(urlDB.toString(), properties));
            return Resultados
                    .builder()
                    .mensaje("Mensaje")
                    .estado(Boolean.TRUE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        } catch (java.sql.SQLInvalidAuthorizationSpecException ex1) {
            return Resultados
                    .builder()
                    .mensaje(JAVASQL_SQL_INVALID_AUTHORIZATION_SPEC_EXCEPTI)
                    .estado(Boolean.FALSE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        } catch (SQLException ex) {
            String mensaje = "";
            if (ex.getMessage().contains(E_FECHA_INICIAL_INCORRECTA)) {
                mensaje = E_FECHA_INICIAL_INCORRECTA;
            }

            if (ex.getMessage().contains(E_FECHA_ACTUAL_INCORRECTA)) {
                mensaje = E_FECHA_ACTUAL_INCORRECTA;
            }

            if (ex.getMessage().contains(E_FECHA_VENCIMIENTO)) {
                mensaje = E_FECHA_VENCIMIENTO;
            }

            if (ex.getMessage().contains(E_EQUIPO_NO_REGISTRADO)) {
                mensaje = E_EQUIPO_NO_REGISTRADO;
            }

            if (ex.getMessage().contains(UNABLE_TO_COMPLETE_NETWORK_REQUEST_TO_HOS)) {
                mensaje = UNABLE_TO_COMPLETE_NETWORK_REQUEST_TO_HOS;
            }

            if(mensaje.isBlank()){
                mensaje = ex.getMessage();
            }
            
            return Resultados
                    .builder()
                    .mensaje(mensaje)
                    .estado(Boolean.FALSE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
    public static final String UNABLE_TO_COMPLETE_NETWORK_REQUEST_TO_HOS = "Unable to complete network request to host";
    /**
     * Driver de firebird (Jaybird) no se encuentra en la carpecta /lib del
     * proyecto. https://firebirdsql.org/en/jdbc-driver/
     */
    public static final String LIBRERIA_DEL_DRIVER_NO_ENCONTRADA
            = "Libreria no encontrada";

    /**
     * Esta variable indica que el usuario y la contraseña son incorrecto.
     */
    public static final String JAVASQL_SQL_INVALID_AUTHORIZATION_SPEC_EXCEPTI
            = "JAVASQL_SQL_INVALID_AUTHORIZATION_SPEC_EXCEPTI";
    /**
     * Esta variable indica que la fecha inicial es incorrecta. Debe ajustar la
     * fecha del servidor.
     */
    public static final String E_FECHA_INICIAL_INCORRECTA
            = "E_FECHA_INICIAL_INCORRECTA";
    /**
     * La fecha actual registrada en el revidor es incorrecta.
     */
    public static final String E_FECHA_ACTUAL_INCORRECTA
            = "E_FECHA_ACTUAL_INCORRECTA";
    /**
     * La fecha del producto ha caducado.
     */
    public static final String E_FECHA_VENCIMIENTO = "E_FECHA_VENCIMIENTO";

    /**
     * Indica que no existe registros en el servidor de la base de datos.
     */
    public static final String E_EQUIPO_NO_REGISTRADO
            = "E_EQUIPO_NO_REGISTRADO";
}
