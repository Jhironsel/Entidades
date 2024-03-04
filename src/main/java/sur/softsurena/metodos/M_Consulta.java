package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Consulta;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Consulta {

    /**
     * Metodo que permite agregar las consulta de los paciente al sistema.
     *
     * @param consulta
     * @return
     */
    public static synchronized Resultados agregarConsulta(Consulta consulta) {
        final String sql
                = "SELECT O_ID FROM SP_INSERT_CONSULTA(?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, consulta.getId_persona());
            ps.setInt(2, consulta.getId_control_consulta());
            ps.setInt(3, consulta.getTurno());
            ps.setDate(4, consulta.getFecha());

            int id;
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                id = rs.getInt("O_ID");
            }
            return Resultados
                    .builder()
                    .id(id)
                    .mensaje(CONSULTA_AGREGADA_CORRECTAMENTE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR_CONSULTA)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR_CONSULTA = "Error al insertar consulta";
    public static final String CONSULTA_AGREGADA_CORRECTAMENTE = "Consulta agregada correctamente";

    /**
     *
     * @param fecha
     * @return
     */
    public synchronized static ResultSet getConsulta(String fecha) {
        final String sql = "SELECT idConsulta, TURNO, idPaciente, FINAL, NOMBRES, APELLIDOS, IDARS, NONSS "
                + "FROM GET_CONSULTAS "
                + "WHERE FECHA = ? and ESTADO;";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, fecha);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static synchronized boolean getControlConsulta(String fecha) {
        final String sql = "SELECT (1) FROM V_CONTROLCONSULTA WHERE fecha = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, fecha);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
