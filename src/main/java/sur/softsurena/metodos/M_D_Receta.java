package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.D_Receta;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_D_Receta {

    /**
     *
     * @param dr
     */
    public synchronized static void agregarRecetaDetalle(List<D_Receta> dr) {

        final String INSERT
                = "EXECUTE PROCEDURE SP_INSERT_DETALLE_RECETAS (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
            dr.stream().forEach(x -> {
                try {
                    ps.setInt(1, x.getId_receta());
                    ps.setInt(2, x.getLinea());
                    ps.setInt(3, x.getId_Medicamento());
                    ps.setBigDecimal(4, x.getCantidad());
                    ps.setString(5, x.getDetalleDosis());
                } catch (SQLException ex) {
                    LOG.log(Level.SEVERE, ex.getMessage(), ex);
                }
            });

            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
