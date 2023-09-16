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
public class Datos_nacimiento {

    private static final Logger LOG = Logger.getLogger(Datos_nacimiento.class.getName());

    private final int idPaciente;
    private final String fecha;
    private final String pesoNacimiento;
    private final String altura;
    private final String PC;
    private final boolean cesarea;
    private final String tiempoGestacion;

    public synchronized String agregarDatosNacimiento(Datos_nacimiento dato) {
        final String sql = "UPDATE OR INSERT INTO V_DATOSNACIMIENTO "
                + "(IDPACIENTE, FECHANACIMIENTO, PESONACIMIENTOKG, ALTURA, "
                + "CESAREA, TIEMPOGESTACION, PC) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, dato.getIdPaciente());
            ps.setString(2, dato.getFecha());
            ps.setString(3, dato.getPesoNacimiento());
            ps.setString(4, dato.getAltura());
            ps.setBoolean(5, dato.isCesarea());
            ps.setString(6, dato.getTiempoGestacion());
            ps.setString(7, dato.getPC());

            ps.executeUpdate();

            return "Datos guardado correctamente";

        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar datos de Nacimiento de: " + dato.getIdPaciente();
        }
    }

    public synchronized static ResultSet getAlturaPeso(int idPaciente) {
        final String sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                + "FROM PRO_PESO_ALTURA(?)";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPCefalico(int idPaciente) {
        final String sql = "SELECT OUT_FECHANACIMIENTO, "
                + "OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, "
                + "OUT_PC "
                + "FROM PRO_PC(?)";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPesoKG(int idPaciente) {
        final String sql = "SELECT OUT_FECHANACIMIENTO, "
                + "OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, "
                + "OUT_PC "
                + "FROM PRO_PESO_EDAD(?)";
        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getLongitudOEstatura(int idPaciente) {
        final String sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                + "FROM PRO_LONGITUD_ALTURA_EDAD(?)";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getLongitudPeso(int idPaciente) {
        final String sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                + "FROM PRO_PESO_LONGITUD(?)";

        try (PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
