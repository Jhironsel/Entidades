package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@Getter
@SuperBuilder
public class Cajeros extends Usuario{

    private static final Logger LOG = Logger.getLogger(Cajeros.class.getName());
    
    /**
     * Metodo que devuelve una lista de atributos de los usuarios del sistema. 
     * 
     * @return El valor devuelto de este metodo es una lista de atributos de 
     * los usuarios del sistema. 
     */
    public synchronized static List<Cajeros> getCajeros() {
        List<Cajeros> cajerosList = new ArrayList<>();

        final String SELECT
                = "SELECT USER_NAME, ROL, PNOMBRE, SNOMBRE, APELLIDOS, ESTADO, DESCRIPCION "
                + "FROM SP_SELECT_GET_CAJEROS;";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    cajerosList.add(Cajeros.builder().
                            user_name(rs.getString("USER_NAME")).
                            rol(rs.getString("ROL")).
                            pnombre(rs.getString("PNOMBRE")).
                            snombre(rs.getString("SNOMBRE")).
                            apellidos(rs.getString("APELLIDOS")).
                            estado(rs.getBoolean("ESTADO")).
                            descripcion(rs.getString("DESCRIPCION")).build());
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return cajerosList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * Metodo que devuelve una lista de nombres de usuarios del sistema. 
     * @return El valor devuelto de este metodo es una lista de nombres de 
     * usuarios del sistema. 
     */
    public synchronized static List<Cajeros> getCajerosName() {
        List<Cajeros> cajerosList = new ArrayList<>();

        final String SELECT
                = "SELECT USER_NAME FROM SP_SELECT_GET_CAJEROS;";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    cajerosList.add(Cajeros.builder().
                            user_name(rs.getString("USER_NAME")).
                            build());
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return cajerosList;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
