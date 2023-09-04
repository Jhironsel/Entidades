package sur.softsurena.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.NonNull;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;

public class Conexion {

    //Variables Privadas
    private static final Logger LOG = Logger.getLogger(Conexion.class.getName());
    private static Connection cnn;
    private static DriverConnectionProvider provider;
    private static String user, clave;
    private static StringBuilder urlDB;
    private static final String PROTOCOLO_FIREBIRD = "jdbc:firebirdsql://";
    private static final String VALIDACIONES_DEL_SISTEMA = "Validaciones del sistema";

    //Variables Publicas
//    public static Alert alerta;
    public static final String NO_ES_POSIBLE_CONECTARSE_AL_SERVIDOR = "No es posible conectarse al servidor: ";
    public static final String USUARIO_NO_IDENTIFICADO = "Usuario no identificado";
    public static final String LIBRERIA_DEL_DRIVER_NO_ENCONTRADA = "Libreria no encontrada";
    public static final String USUARIO_LOGEADO = "Usuario logeado";

    public static Connection getCnn() {
        return cnn;
    }

    public synchronized static void setCnn(Connection cnn) {
        Conexion.cnn = cnn;
    }

    public static DriverConnectionProvider getProvider() {
        return provider;

    }

    public static void setProvider(DriverConnectionProvider provider) {
        Conexion.provider = provider;
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
    public static Boolean verificar() {
        final Properties properties = new Properties();
        //Objecto Properties necesario para la base de datos. 
        properties.setProperty("user", user);
        properties.setProperty("password", clave);
        properties.setProperty("charSet", "UTF8");
        try {
            setCnn(DriverManager.getConnection(urlDB.toString(), properties));
            // Defining the connection provider.
            provider = new DriverConnectionProvider();
            provider.setProperty("user", user);
            provider.setProperty("password", clave);
            provider.setProperty("charSet", "UTF8");
            provider.setUrl(urlDB.toString());
            return true;
        } catch (SQLException ex) {
            if (ex.getMessage().contains("password")) {
                LOG.log(Level.INFO, USUARIO_NO_IDENTIFICADO);
            }
            if (ex.getMessage().contains("Unable to complete network request to host")) {

                StringBuilder mensaje = new StringBuilder();

                mensaje.append(NO_ES_POSIBLE_CONECTARSE_AL_SERVIDOR)
                        .append(urlDB);
                LOG.log(Level.INFO, mensaje.toString(), ex);
            }
            return false;
        }
    }
}
