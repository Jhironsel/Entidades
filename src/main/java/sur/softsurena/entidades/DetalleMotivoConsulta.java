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
public class DetalleMotivoConsulta {

    private static final Logger LOG = Logger.getLogger(DetalleMotivoConsulta.class.getName());

    private final int idConsulta;
    private final int turno;
    private final int idMotivoConsulta;

    /**
     * Metodo que elimina un detalle de la consulta de los paciente, por x o y
     * razones.
     *
     * @param dmc
     *
     * @return
     */
    public synchronized String borrarDetalleMotivoConsulta(DetalleMotivoConsulta dmc) {
        final String DELETE
                = "DELETE FROM V_D_MOTIVO_CONSULTA a "
                + "WHERE "
                + "     a.IDCONSULTA = ? AND "
                + "     a.IDMCONSULTA = '?'";

        try (PreparedStatement ps = getCnn().prepareStatement(DELETE)) {

            ps.setInt(1, dmc.getIdConsulta());
            ps.setInt(2, dmc.getIdMotivoConsulta());

            ps.executeUpdate();

            return "Motivo eliminado correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al eliminar detalle de motivo de la consulta.";
        }
    }

    /**
     *
     * @param dmc
     * @return
     */
    public synchronized static String agregarDetallleConsulta(DetalleMotivoConsulta dmc) {
        final String sql
                = "UPDATE OR INSERT INTO T_DETALLEMOTIVOCONSULTA "
                + "(IDCONSULTA, TURNO, IDMCONSULTA) "
                + "VALUES (?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, dmc.getIdConsulta());
            ps.setInt(2, dmc.getTurno());
            ps.setInt(3, dmc.getIdMotivoConsulta());

            ps.executeUpdate();
            return "Detalles agregados correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar Detalle Consulta...";
        }
    }

    public synchronized static ResultSet getDetalleMotivo(int idConsulta, int turno) {
        final String sql = "SELECT IDMCONSULTA "
                + "   FROM V_DETALLEMOTIVOCONSULTA d "
                + "   WHERE d.IDCONSULTA = ? and d.TURNO = ?";
        
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idConsulta);
            ps.setInt(2, turno);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
