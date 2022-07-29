package sur.softsurena.conexion;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import lombok.extern.java.Log;
import org.firebirdsql.event.DatabaseEvent;
import org.firebirdsql.event.FBEventManager;

@Log
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
    public static Conexion getInstance(String user, String clave, String role) {
        Conexion.role = role;
        Conexion.user = user;
        Conexion.clave = clave;
        if(user.isBlank() || user.isEmpty() || clave.isBlank() || clave.isEmpty() 
                || role.isBlank() || role.isEmpty()){
            return null;
        }
        return ConexionHolder.INSTANCE;
    }

    public static Conexion getInstance() {
        return ConexionHolder.INSTANCE;
    }

    private boolean verificar(String user, String clave, String role) {
        final Properties props, host;

        //Necesito cargar este proximo valor desde un properties
        baseDeDatos = "/firebird/data/BaseDeDatosSoftSurena.fdb";

        //Objecto Properties necesario para la base de datos. 
        props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", clave);
        props.setProperty("roleName", role);
        props.setProperty("charSet", "UTF8");

        //Leer el archivo Properties del sistema para obtener los valores del 
        //servidor, como son ip, puerto, localización de la bd.
        host = new Properties();

        try {//Cargamos el properties. 
            host.load(new FileReader("sur/softsurena/properties/propiedades.properties"));
        } catch (IOException ex) {
            //Instalar Logger
        }

        //
        if (Boolean.valueOf(host.getProperty("ProtocoloActivo", "false"))) {
            ip = host.getProperty("Ip_Servidor1", "127") + "."
                    + host.getProperty("Ip_Servidor2", "0") + "."
                    + host.getProperty("Ip_Servidor3", "0") + "."
                    + host.getProperty("Ip_Servidor4", "1");
        } else if (Boolean.valueOf(host.getProperty("NombreActivo", "false"))) {
            ip = host.getProperty("Nombre_del_Servidor", "localhost");
        }else{
            ip = "localhost";
        }

        if (Boolean.valueOf(host.getProperty("Con_Puerto", "false"))) {
            puerto = ":" + host.getProperty("Puerto_del_Servidor", "3050");
        }

        String file = "jdbc:firebirdsql://" + ip + puerto + "/" + baseDeDatos;

        /*Aqui se lleva a cabo la conexion a la base de datos.*/
        try {    
            //Class.forName("org.firebirdsql.jdbc.FBDriver");
            setCnn(DriverManager.getConnection(file, props));    
//        } catch (ClassNotFoundException ex) {
            //Instalar Logger
//            JOptionPane.showMessageDialog(null, "Libreria del driver no encontrada");
//            return Boolean.FALSE;
            return true;
        } catch (SQLException ex) {
            //Instalar Logger
            if (ex.getMessage().contains("password")) {
                JOptionPane.showMessageDialog(null, "Usuario no identificado");
            }

            if (ex.getMessage().contains("Unable to complete network request to host")) {
                JOptionPane.showMessageDialog(null, "No es posible conectarse al servidor: " + ip);
            }
            
            log.log(Level.SEVERE, ex.getMessage());
            
            return Boolean.FALSE;
        }
    }

    private static class ConexionHolder {

        private static final Conexion INSTANCE = new Conexion(Conexion.user, Conexion.clave, Conexion.role);
    }//--------Fin patron singleton.

    public synchronized Boolean validarUsuarioPro(String user, String clave, String role) {
        
        if(!verificar(user, clave, role)){
            return null;
        }
        
        
        setHost(ip);//Aqui hay un error... OJO!
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
