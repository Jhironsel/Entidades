package sur.softsurena.metodos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Consulta;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Consulta {

    /**
     * Metodo que permite agregar las consulta de los paciente al sistema.
     *
     * @param consulta
     * @return
     */
    public static synchronized Resultado agregarConsulta(Consulta consulta) {
        final String sql
                = "SELECT O_ID FROM SP_I_CONSULTA(?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, consulta.getId_persona());
            ps.setInt(2, consulta.getId_control_consulta());
            ps.setInt(3, consulta.getTurno());
            ps.setDate(4, consulta.getFecha());

            ResultSet rs = ps.executeQuery();
            rs.next();

            return Resultado
                    .builder()
                    .id(rs.getInt("O_ID"))
                    .mensaje(CONSULTA_AGREGADA_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_INSERTAR_CONSULTA,
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR_CONSULTA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR_CONSULTA
            = "Error al insertar consulta";
    public static final String CONSULTA_AGREGADA_CORRECTAMENTE
            = "Consulta agregada correctamente";

    /**
     * TODO CREAR VISTA para obtener las consultas del sistema.
     * @param fecha
     * @return
     */
    public synchronized static List<Consulta> getConsulta(Date fecha) {
        final String sql = """
                           SELECT idConsulta, TURNO, idPaciente, FINAL, NOMBRES, APELLIDOS, IDARS, NONSS 
                           FROM GET_CONSULTAS 
                           WHERE FECHA = ? and ESTADO;
                           """;
        List<Consulta> consultaList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setDate(1, fecha);
            
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    consultaList.add(
                            Consulta
                                    .builder()
                                    .build()
                    );
                }
            }

        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ex.getMessage(),
                    ex
            );
        }
        return consultaList;
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
                ResultSet.TYPE_FORWARD_ONLY,
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
    
    public static synchronized Resultado eliminarConsulta(Integer idConsulta){
        final String sql = """
                           EXECUTE PROCEDURE SP_D_CONSULTA (?);
                           """;
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {
            ps.setInt(1, idConsulta);
            
            ps.execute();
            
            return Resultado
                    .builder()
                    .mensaje(CONSULTA_ELIMINADA_CORRECTAMENTE_DEL_SIST)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
            
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_ELIMINAR_LA_CONSULTA_DEL_SISTEMA,
                    ex
            );
        }
        return Resultado
                    .builder()
                    .mensaje(ERROR_AL_ELIMINAR_LA_CONSULTA_DEL_SISTEMA)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
    }
    public static final String CONSULTA_ELIMINADA_CORRECTAMENTE_DEL_SIST 
            = "Consulta eliminada correctamente del sistema.";
    public static final String ERROR_AL_ELIMINAR_LA_CONSULTA_DEL_SISTEMA 
            = "Error al eliminar la consulta del sistema.";
}
