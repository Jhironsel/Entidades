package sur.softsurena.entidades;

import java.sql.Date;
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
public class Consultas extends Paciente {

    private static final Logger LOG = Logger.getLogger(Consultas.class.getName());

    private final int id;
    private final int id_motivo_consulta;
    private final int id_control_consulta;
    private final Date fecha;
    private final int turno;
    private final Boolean estado;
    private final String usuario;

    /**
     * Metodo que permite agregar las consulta de los paciente al sistema.
     *
     * @param c
     * @return
     */
    public synchronized String agregarConsulta(Consultas c) {
        final String INSERT
                = "INSERT INTO V_CONSULTAS (ID_PACIENTE, ID_CONTROL_CONSULTA, "
                + "                         FECHA, TURNO, ESTADO, IDUSUARIO) "
                + "VALUES ('ID_PACIENTE', 'ID_CONTROL_CONSULTA', 'FECHA', "
                + "         'TURNO', 'ESTADO', 'IDUSUARIO*');";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
            ps.setInt(1, c.getId_persona());
            ps.setInt(2, c.getId_control_consulta());
            ps.setDate(3, c.getFecha());
            ps.setInt(4, c.getTurno());
            ps.setBoolean(5, c.getEstado());

            ps.executeUpdate();
            return "Consulta agregada correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al insertar consulta";
        }
    }

    /**
     *
     * @param fecha
     * @return
     */
    public synchronized static ResultSet getConsulta(String fecha) {
        final String sql = "SELECT idConsulta, TURNO, idPaciente, FINAL, NOMBRES, APELLIDOS, IDARS, NONSS "
                + "FROM GET_CONSULTAS "
                + "WHERE FECHA = ? and ESTADO";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, fecha);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized boolean getControlConsulta(String fecha) {
        final String sql = "SELECT (1) FROM V_CONTROLCONSULTA WHERE fecha = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, fecha);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
