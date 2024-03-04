package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Dato_Nacimiento;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Dato_Nacimiento {
    
    /**
     * TODO CREAR SP.
     * @param dato
     * @return 
     */
    public synchronized String agregarDatosNacimiento(Dato_Nacimiento dato) {
        final String sql 
                = "UPDATE OR INSERT INTO V_DATOSNACIMIENTO "
                + "(IDPACIENTE, FECHANACIMIENTO, PESONACIMIENTOKG, ALTURA, "
                + "CESAREA, TIEMPOGESTACION, PC) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, dato.getIdPaciente());
            ps.setString(2, dato.getFecha());
            ps.setString(3, dato.getPesoNacimiento());
            ps.setString(4, dato.getAltura());
            ps.setBoolean(5, dato.isCesarea());
            ps.setString(6, dato.getTiempoGestacion());
            ps.setString(7, dato.getPC());

            ps.executeUpdate();

            return DATOS_GUARDADO_CORRECTAMENTE;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return ERROR_AL_INSERTAR_DATOS_DE__NACIMIENTO;
        }
    }
    public static final String ERROR_AL_INSERTAR_DATOS_DE__NACIMIENTO = "Error al insertar datos de Nacimiento.";
    public static final String DATOS_GUARDADO_CORRECTAMENTE = "Datos guardado correctamente";

    /**
     * 
     * @param idPaciente
     * @return 
     */
    public synchronized static ResultSet getAlturaPeso(int idPaciente) {
        final String sql 
                = "SELECT "
                + "     OUT_FECHANACIMIENTO, "
                + "     OUT_FECHACONSULTA, "
                + "     OUT_DEFERENCIAFECHA, "
                + "     OUT_LONGITUD, "
                + "     OUT_ESTATURA "
                + "FROM PRO_PESO_ALTURA(?)";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPCefalico(int idPaciente) {
        final String sql 
                = "SELECT "
                + "     OUT_FECHANACIMIENTO, "
                + "     OUT_FECHACONSULTA, "
                + "     OUT_DEFERENCIAFECHA, "
                + "     OUT_PC "
                + "FROM PRO_PC(?)";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPesoKG(int idPaciente) {
        final String sql 
                = "SELECT "
                + "     OUT_FECHANACIMIENTO, "
                + "     OUT_FECHACONSULTA, "
                + "     OUT_DEFERENCIAFECHA, "
                + "     OUT_PC "
                + "FROM PRO_PESO_EDAD(?)";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getLongitudOEstatura(int idPaciente) {
        final String sql 
                = "SELECT "
                + "     OUT_FECHANACIMIENTO, "
                + "     OUT_FECHACONSULTA, "
                + "     OUT_DEFERENCIAFECHA, "
                + "     OUT_LONGITUD, "
                + "     OUT_ESTATURA "
                + "FROM PRO_LONGITUD_ALTURA_EDAD(?)";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getLongitudPeso(int idPaciente) {
        final String sql 
                = "SELECT "
                + "     OUT_FECHANACIMIENTO, "
                + "     OUT_FECHACONSULTA, "
                + "     OUT_DEFERENCIAFECHA, "
                + "     OUT_LONGITUD, "
                + "     OUT_ESTATURA "
                + "FROM PRO_PESO_LONGITUD(?)";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
