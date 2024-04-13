package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Control_Consulta;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Control_Consulta {

    /**
     * Metodo utilizado para eliminar los controles de consultas programadas
     * previamente.
     *
     * TODO CREAR SP.
     *
     * @param idControlConsulta
     *
     * @return
     */
    public synchronized static Resultado borrarControlConsulta(int idControlConsulta) {
        final String sql
                = "EXECUTE PROCEDURE SP_D_CONTROL_CONSULTA (?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idControlConsulta);
            
            ps.executeUpdate();
            
            return Resultado
                    .builder()
                    .mensaje(CONTROL__CONSULTA_BORRADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_BORRAR_CONTROL_DE_LA_CONSULTA,
                    ex
            );
            
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL_BORRAR_CONTROL_DE_LA_CONSULTA)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_BORRAR_CONTROL_DE_LA_CONSULTA
            = "Error al borrar control de la consulta";
    public static final String CONTROL__CONSULTA_BORRADO_CORRECTAMENTE
            = "Control Consulta borrado correctamente";

    /**
     * Permite agregar un control de consulta al sistema.
     *
     * @param controlConsulta
     * @return
     */
    public synchronized static Resultado agregarControlConsulta(Control_Consulta controlConsulta) {
        final String sql
                = """
                  SELECT O_ID
                  FROM SP_I_CONTROL_CONSULTA (?, ?, ?, ?, ?, ?);
                  """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setString(1, controlConsulta.getUser_name());
            ps.setInt(2, controlConsulta.getCantidad());
            ps.setString(3, controlConsulta.getDia());
            ps.setTime(4, controlConsulta.getInicial());
            ps.setTime(5, controlConsulta.getFinall());
            ps.setBoolean(6, controlConsulta.getEstado());

            ResultSet rs = ps.executeQuery();
            rs.next();

            return Resultado
                    .builder()
                    .id(rs.getInt(1))
                    .mensaje(CONTROL_CONSULTA_AGREGADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_AGREGAR__CONTROL__CONSULTA_AL_SIST,
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_AGREGAR__CONTROL__CONSULTA_AL_SIST)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_AGREGAR__CONTROL__CONSULTA_AL_SIST
            = "Error al agregar Control Consulta al sistema.";
    public static final String CONTROL_CONSULTA_AGREGADO_CORRECTAMENTE
            = "Control consulta agregado correctamente.";

    /**
     * TODO Crear SP.
     *
     * @param controlConsulta
     * @return
     */
    public synchronized String modificarControlConsulta(Control_Consulta controlConsulta) {
        final String sql
                = "UPDATE V_CONTROL_CONSULTA SET "
                + "    IDUSUARIO = ? , "
                + "    CANTIDADPACIENTE = ? , "
                + "    DIA = ? , "
                + "    INICIAL = ? , "
                + "    FINAL = ? "
                + "WHERE IDCONTROLCONSULTA = ? ";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, controlConsulta.getId());
            ps.setInt(2, controlConsulta.getCantidad());
            ps.setString(3, controlConsulta.getDia());
            ps.setTime(4, controlConsulta.getInicial());
            ps.setTime(5, controlConsulta.getFinall());
            ps.setInt(6, controlConsulta.getId());

            ps.executeUpdate(sql);
            return CONSULTA_MODIFICADO_CORRECTAMENTE;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return ERROR_AL_MODIFICAR_CONSULTA;
        }
    }
    public static final String CONSULTA_MODIFICADO_CORRECTAMENTE = "Consulta modificado correctamente";
    public static final String ERROR_AL_MODIFICAR_CONSULTA = "Error al modificar consulta...";

    /**
     *
     * @param fecha
     * @param actual
     * @return
     */
    public synchronized static ResultSet getFechaDoctores(String fecha, boolean actual) {
        final String sql
                = "SELECT IDCONTROLCONSULTA, loginName, dia, "
                + "INICIAL, FINAL, nombreCompleto, CANTIDAD_PACIENTE "
                + "FROM GET_controlConsulta "
                + "WHERE dia like (SELECT TCNOMBREDIA "
                + "                FROM NOMBRE_DIA_CORTO (?)) " + (actual ? " and CURRENT_DATE <= ? ;" : "");

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, fecha);

            if (actual) {
                ps.setString(2, fecha);
            }

            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idUsuario
     * @return
     */
    public synchronized static ResultSet getHorario(String idUsuario) {
        final String sql
                = "SELECT IDCONTROLCONSULTA, CANTIDADPACIENTE,"
                + "          DIA, INICIAL, FINAL "
                + "FROM t_controlconsulta "
                + "WHERE IDUSUARIO = ? "
                + "order by DIA, INICIAL, FINAL";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setString(1, idUsuario);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
