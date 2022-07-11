package sur.softsurena.conexion;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.firebirdsql.event.DatabaseEvent;
import org.firebirdsql.event.FBEventManager;

public class Conexion extends FBEventManager {

    //Variariables.
    private static Connection cnn;
    private static String role, user, clave, baseDeDatos = "", ip = "", puerto = "";
    //-------Fin Variables


    /*Metodos Getter y Setter de cnn*/
    public static Connection getCnn() {
        return cnn;
    }

    public synchronized static void setCnn(Connection cnn) {
        
        if(Conexion.cnn != null){
            return;
        }
        
        Conexion.cnn = cnn;
    }//-------Fin---------

    /*Patron Singleton*/
    public static Conexion getInstance(String role, String user, String clave) {
        Conexion.role = role;
        Conexion.user = user;
        Conexion.clave = clave;
        return ConexionHolder.INSTANCE;
    }

    public static Conexion getInstance() {
        return ConexionHolder.INSTANCE;
    }

    private static class ConexionHolder {

        private static final Conexion INSTANCE = new Conexion(Conexion.user, Conexion.clave, Conexion.role);
    }//--------Fin patron singleton.

    private synchronized Boolean validarUsuarioPro(String user, String clave, String role) {
        final Properties props, host;

        baseDeDatos = "/firebird/data/BaseDeDatosSoftSurena.fdb";

        props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", clave);
        props.setProperty("roleName", role);
        props.setProperty("charSet", "UTF8");

        host = new Properties();

        try {
            host.load(new FileReader("sur/softsurena/properties/propiedades.properties"));
        } catch (IOException ex) {
            //Instalar Logger
        }

        if (Boolean.valueOf(host.getProperty("ProtocoloActivo", "false"))) {
            ip = host.getProperty("Ip_Servidor1") + "."
                    + host.getProperty("Ip_Servidor2") + "."
                    + host.getProperty("Ip_Servidor3") + "."
                    + host.getProperty("Ip_Servidor4");
        }

        if (Boolean.valueOf(host.getProperty("NombreActivo", "false"))) {
            ip = host.getProperty("Nombre_del_Servidor");
        }

        if (Boolean.valueOf(host.getProperty("Con_Puerto", "false"))) {
            puerto = ":" + host.getProperty("Puerto_del_Servidor");
        }

        String file = "jdbc:firebirdsql://" + ip + puerto + "/" + baseDeDatos;

        file = "jdbc:firebirdsql://localhost/" + baseDeDatos;

        /*Aqui se lleva a cabo la conexion a la base de datos.*/
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            setCnn(DriverManager.getConnection(file, props));
            
            
        } catch (ClassNotFoundException ex) {
            //Instalar Logger
            JOptionPane.showMessageDialog(null, "Libreria del driver no encontrada");
            return Boolean.FALSE;
        } catch (SQLException ex) {
            //Instalar Logger
            if (ex.getMessage().contains("password")) {
                JOptionPane.showMessageDialog(null, "Usuario no identificado");
            }

            if (ex.getMessage().contains("Unable to complete network request to host")) {
                JOptionPane.showMessageDialog(null, "No es posible conectarse al servidor: " + ip);
            }
            return Boolean.FALSE;
        }
        
        
        setHost(ip);//Aqui hay un error...OJO!
        setUser(user);
        setPassword(clave);
        setDatabase(baseDeDatos);

        try {
            connect();

            addEventListener("new_item", (DatabaseEvent event) -> {
                System.out.println(
                        "Event [" + event.getEventName() + "] occured "
                        + event.getEventCount() + " time(s)"
                );
            });

            addEventListener("new_client", (DatabaseEvent event) -> {
                System.out.println("Event ["
                        + event.getEventName() + "] occured "
                        + event.getEventCount() + " time(s)");
            });
        } catch (SQLException ex) {
            return Boolean.FALSE;
            //Instalar Logger
        }
        
        return Boolean.TRUE;
    }

    /*------Constructor.-----------*/
    private Conexion(String user, String clave, String role) {

        validarUsuarioPro(user, clave, role);
        /*Subcribirse a los eventos de la base de datos. */
        //if (!role.equalsIgnoreCase("none")) {
        
        //}
    }/*------Fin de constructor.*/


    /*-----Metodo para cerrar la conexion.*/
    public synchronized static void cerrarConexion() {
        if (getCnn() == null) {
            return;
        }

        try {
            getCnn().close();
        } catch (SQLException ex) {
            //Instalar Logger
        }
    }

}
