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

@SuperBuilder
@Getter
public class Codigo_Postal {

    private static final Logger LOG = Logger.getLogger(Codigo_Postal.class.getName());

    private final int id;
    private final int idProvincia;
    private final String localidad;
    private final int codigo_postal;

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
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return codigo_postal_list;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public String toString() {
        return localidad;
    }
}
