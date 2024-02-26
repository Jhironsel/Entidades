package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Codigo_Postal;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Codigo_Postal {
    
    /**
     * Metodo que me permite obtener los codigo postales de la republica
     * dominicana.
     *
     * @param id_provincia
     * @return
     */
    public synchronized static List<Codigo_Postal> getCodigoPostal(int id_provincia) {
        final String SELECT
                = "SELECT ID, IDPROVINCIA, LOCALIDAD, CODIGO_POSTAL "
                + "FROM V_CODIGOS_POSTALES "
                + "WHERE r.IDPROVINCIA = ?;";
        
        List<Codigo_Postal> codigo_postal_list = new ArrayList<>();
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, id_provincia);

            try (ResultSet rs = ps.executeQuery();) {
                while(rs.next()){
                    codigo_postal_list.add(
                            Codigo_Postal.
                                    builder().
                                    id(rs.getInt("ID")).
                                    idProvincia(rs.getInt("IDPROVINCIA")).
                                    localidad(rs.getString("LOCALIDAD")).
                                    codigo_postal(rs.getInt("CODIGO_POSTAL")).
                                    build()
                    );
                }
            }
            return codigo_postal_list;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
