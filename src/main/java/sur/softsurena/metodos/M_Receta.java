package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Receta {
    
    public synchronized static int agregarReceta(int idPaciente, int idConsulta) {
        final String sql
                = "INSERT INTO V_RECETAS (IDCONSULTA) VALUES (?)";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
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
}
