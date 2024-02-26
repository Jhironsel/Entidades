package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Cajero;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Cajero {
    
    /**
     * Metodo que devuelve una lista de atributos de los usuarios del sistema. 
     * 
     * @return El valor devuelto de este metodo es una lista de atributos de 
     * los usuarios del sistema. 
     */
    public synchronized static List<Cajero> getCajeros() {
        List<Cajero> cajerosList = new ArrayList<>();

        final String SELECT
                = "SELECT USER_NAME, ROL, PNOMBRE, SNOMBRE, APELLIDOS, ESTADO, DESCRIPCION "
                + "FROM GET_CAJEROS;";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    cajerosList.add(Cajero.builder().
                            user_name(rs.getString("USER_NAME")).
                            rol(rs.getString("ROL")).
                            pnombre(rs.getString("PNOMBRE")).
                            snombre(rs.getString("SNOMBRE")).
                            apellidos(rs.getString("APELLIDOS")).
                            estado(rs.getBoolean("ESTADO")).
                            descripcion(rs.getString("DESCRIPCION")).build());
                }
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
    public synchronized static List<Cajero> getCajerosName() {
        List<Cajero> cajerosList = new ArrayList<>();

        final String SELECT
                = "SELECT USER_NAME FROM GET_CAJEROS;";

        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    cajerosList.add(Cajero.builder().
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
