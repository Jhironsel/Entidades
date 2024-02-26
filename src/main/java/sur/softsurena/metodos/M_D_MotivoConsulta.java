package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.D_MotivoConsulta;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_D_MotivoConsulta {
    
    /**
     * Metodo que elimina un detalle de la consulta de los paciente, por x o y
     * razones.
     *
     * @param dmc
     *
     * @return
     */
    public synchronized String borrarDetalleMotivoConsulta(D_MotivoConsulta dmc) {
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
    public synchronized static String agregarDetallleConsulta(D_MotivoConsulta dmc) {
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
