package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Recetas {

    private static final Logger LOG = Logger.getLogger(Recetas.class.getName());
    
    private final int id;
    private final int idConsulta;
    private final Timestamp fecha;
    private final String usuario;
    
    /**
     * 
     */
    private static final String INSERT
            = "INSERT INTO V_RECETAS (IDCONSULTA) VALUES (?)";

    public synchronized static int agregarReceta(int idPaciente, int idConsulta) {

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
            ps.setInt(1, idConsulta);

            ps.executeUpdate();

//            sql = "SELECT IDRECETAS "
//                    + "FROM V_RECETAS "
//                    + "WHERE IDPACIENTE = ? and IDCONSULTA = ?";
//
//            ps = getCnn().prepareStatement(sql);
//
//            ps.setInt(1, idPaciente);
//            ps.setInt(2, idConsulta);
//
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                return rs.getInt("IDRECETAS");
//            } else {
//                return -1;
//            }

             } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "Recetas{" + "id=" + id + ", idConsulta=" + idConsulta + ", fecha=" + fecha + ", usuario=" + usuario + '}';
    }
    
    
}
