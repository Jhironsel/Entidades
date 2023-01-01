package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Codigo_Postal {

    private static final Logger LOG = Logger.getLogger(Codigo_Postal.class.getName());
    
    private int id;
    private int idProvincia;
    private String localidad;
    private int codigo_postal;

    public static String SELECT
            = "SELECT r.ID, r.IDPROVINCIA, r.LOCALIDAD, r.CODIGO_POSTAL "
            + "FROM V_CODIGOS_POSTALES r WHERE r.IDPROVINCIA = ?;";

    /**
     * Metodo que me permite obtener los codigo postales de la republica
     * dominicana.
     *
     * @param id_provincia
     * @return
     */
    public synchronized static ResultSet getCodigoPostal(int id_provincia) {
        try ( PreparedStatement ps1 = getCnn().prepareStatement(
                SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps1.setInt(1, id_provincia);
            return ps1.executeQuery();
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
