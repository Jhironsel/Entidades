package sur.softsurena.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lombok.NonNull;

public class Conexion {

    private static final Logger LOG = Logger.getLogger(Conexion.class.getName());
    
    private static Connection cnn;
    private static String user, clave, role, pathBaeDatos, dominio, puerto; 

    public static Connection getCnn() {
        return cnn;
    }

    public synchronized static void setCnn(Connection cnn) {
        Conexion.cnn = cnn;
    }
    
    public static Conexion getInstance(
            @NonNull String user, 
            @NonNull String clave, 
            @NonNull String role,
            @NonNull String pathBaseDatos, 
            @NonNull String dominio, 
            @NonNull String puerto) {
        Conexion.user = user;
        Conexion.clave = clave;
        Conexion.role = role;
        Conexion.pathBaeDatos = pathBaseDatos;
        Conexion.dominio = dominio;
        Conexion.puerto = puerto;

        return ConexionHolder.INSTANCE;
    }

    private static class ConexionHolder {
        private static final Conexion INSTANCE = new Conexion();
    }

    public Boolean verificar() {
        final Properties info = new Properties();;
        //Objecto Properties necesario para la base de datos. 
        info.setProperty("user", user);
        info.setProperty("password", clave);
        info.setProperty("roleName", role);
        info.setProperty("charSet", "UTF8");
        
        String url = "jdbc:firebirdsql://" + dominio + 
                (puerto.isBlank() || puerto.isEmpty() ? "":":"+puerto) + "/" + 
                pathBaeDatos;
        
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            setCnn(DriverManager.getConnection(url, info));
        } catch (ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            JOptionPane.showMessageDialog(null, "Libreria del driver no encontrada");
            return true;
        } catch (SQLException ex) {
            if (ex.getMessage().contains("password")) {
                JOptionPane.showMessageDialog(null, "Usuario no identificado");
            }
            if (ex.getMessage().contains("Unable to complete network request to host")) {
                JOptionPane.showMessageDialog(null, "No es posible conectarse al servidor: " + dominio);
            }
            LOG.log(Level.INFO, ex.getMessage(), ex);
            return true;
        }
        return false;
    }

    /**
     * Metodo constructor que permite obtener una instancia unica de la clase
     * Conexión.
     */
    private Conexion() {}
}
