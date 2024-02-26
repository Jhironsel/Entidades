package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Control_Consulta;
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
     * @param idControlConsulta
     * 
     * @return 
     */
    public synchronized static String borrarControlConsulta(int idControlConsulta) {
        final String DELETE 
            = "DELETE FROM V_CONTROL_CONSULTA WHERE id = ?";
        
        try (PreparedStatement ps = getCnn().prepareStatement(DELETE);){
            
            ps.setInt(1, idControlConsulta);
            ps.executeUpdate();
            return "Borrado correctamente";
        } catch (SQLException ex) {
            
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar control de la consulta";
        }
    }
    
    /**
     * 
     * @param controlConsulta
     * @return 
     */
    public synchronized static String agregarControlConsulta(Control_Consulta controlConsulta) {
        final String INSERT
            = "insert into CONTROLCONSULTA (IDUSUARIO, CANTIDADPACIENTE, DIA, INICIAL, FINAL) "
                    + "values (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
            ps.setInt(1, controlConsulta.getId());
            ps.setInt(2, controlConsulta.getCantidad());
            ps.setString(3, controlConsulta.getDia());
            ps.setTime(4, controlConsulta.getInicial());
            ps.setTime(5, controlConsulta.getFinall());

            ps.executeUpdate();
            return "Cambios Guardados";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "No se pueden guardar los cambios";
        }
    }
    
    /**
     * 
     * @param controlConsulta
     * @return 
     */
    public synchronized String modificarControlConsulta(Control_Consulta controlConsulta) {
        final String sql = 
                "UPDATE V_CONTROL_CONSULTA SET "
                + "    IDUSUARIO = ? , "
                + "    CANTIDADPACIENTE = ? , "
                + "    DIA = ? , "
                + "    INICIAL = ? , "
                + "    FINAL = ? "
                + "WHERE IDCONTROLCONSULTA = ? ";

        try ( PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

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
    
    public synchronized static ResultSet getFechaDoctores(String fecha, boolean actual) {
        final String sql = "SELECT IDCONTROLCONSULTA, loginName, dia, "
                + "INICIAL, FINAL, nombreCompleto, CANTIDAD_PACIENTE "
                + "FROM GET_controlConsulta "
                + "WHERE dia like (SELECT TCNOMBREDIA "
                + "                FROM NOMBRE_DIA_CORTO (?)) " + (actual ? " and CURRENT_DATE <= ? ;" : "");
        
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

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
    
    public synchronized static ResultSet getHorario(String idUsuario) {
        final String sql = "SELECT IDCONTROLCONSULTA, CANTIDADPACIENTE,"
                + "          DIA, INICIAL, FINAL "
                + "FROM t_controlconsulta "
                + "WHERE IDUSUARIO = ? "
                + "order by DIA, INICIAL, FINAL";
        
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, idUsuario);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
