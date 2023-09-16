package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@SuperBuilder
@Getter
public class Control_Consulta {

    private static final Logger LOG = Logger.getLogger(Control_Consulta.class.getName());
    
    private final int id;
    private final String user_name;
    private final int cantidad;
    private final String dia;
    private final Time inicial;
    private final Time finall;
    private final Boolean estado;

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
     * @param cc
     * @return 
     */
    public synchronized static String agregarControlConsulta(Control_Consulta cc) {
        final String INSERT
            = "insert into CONTROLCONSULTA (IDUSUARIO, CANTIDADPACIENTE, DIA, INICIAL, FINAL) "
                    + "values (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)){
            ps.setInt(1, cc.getId());
            ps.setInt(2, cc.getCantidad());
            ps.setString(3, cc.getDia());
            ps.setTime(4, cc.getInicial());
            ps.setTime(5, cc.getFinall());

            ps.executeUpdate();
            return "Cambios Guardados";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "No se pueden guardar los cambios";
        }
    }
    
    /**
     * 
     * @param miCC
     * @return 
     */
    public synchronized String modificarControlConsulta(Control_Consulta miCC) {
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

            ps.setInt(1, miCC.getId());
            ps.setInt(2, miCC.getCantidad());
            ps.setString(3, miCC.getDia());
            ps.setTime(4, miCC.getInicial());
            ps.setTime(5, miCC.getFinall());
            ps.setInt(6, miCC.getId());

            ps.executeUpdate(sql);
            return "Consulta modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar consulta...";
        }
    }
    
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
    
    @Override
    public String toString() {
        return user_name;
    }
}
