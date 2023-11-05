package sur.softsurena.entidades;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.utilidades.Utilidades;

@SuperBuilder
@Getter
public class E_S_SYS {

    private static final Logger LOG = Logger.getLogger(E_S_SYS.class.getName());

    private final int ID_E_S_SYS;
    private final String nombre;
    private final String telefono;
    private final Date FCHI;
    private final Date FCHA;
    private final Date FCHV;
    private final String IDMAC;
    private final String direccion;
    private final String mensaje_footer;
    private final File fileLogo;
    private final String LOGO;
    
    /**
     * Metodo que permite agregar una imagen en la pantalla principal del 
     * sistema, es llamado logo de la aplicacion.
     * 
     * @param file recibe una ruta de la imagen selecciona la cual es convertida
     * a base64 para luego ser almacenada. 
     * 
     * @return Devuelve un valor booleano que indica si la operacion tuvo 
     * exito o no.
     */
    public synchronized static boolean insertLogo(File file){
        final String INSERT_LOGO
            = "UPDATE OR INSERT INTO V_E_S_SYS(ID, LOGO) "
            + "VALUES(1,?) MATCHING(ID);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT_LOGO)){
            ps.setString(1, Utilidades.imagenEncode64(file));
            return ps.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
    
    /**
     * 
     * @return 
     */
    public synchronized static String getLogo() {
        final String sql
            = "SELECT LOGO FROM V_E_S_SYS WHERE ID = 1; ";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    }
}
