package sur.softsurena.entidades;

import java.sql.CallableStatement;
import java.sql.Date;
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
public class Turnos {

    private static final Logger LOG = Logger.getLogger(Turnos.class.getName());
    

    private final int id;
    private final Date fecha_inicio;
    private final Time hora_inicio;
    private final Date fecha_final;
    private final Time hora_final;
    private final Boolean estado;
    private final String idUsuario;

    private static String SELECT_IDUSUARIO_ESTADO
            = "SELECT id "
            + "FROM v_turnos "
            + "WHERE TRIM(idUsuario) like ? and estado";
    /**
     * Metodo actualizado el 26 de abril 2022. Este metodo esta combinado con el
     * metodo usuarioTurnoActivo.
     *
     * @param userName Id del usuario a identificar.
     * @return Valor que identifica el turno activo del usuario consultado.
     */
    public synchronized static int idTurnoActivo(String userName) {
        try (PreparedStatement ps = getCnn().prepareStatement(SELECT_IDUSUARIO_ESTADO)) {
            ps.setString(1, userName.trim().toUpperCase());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return -1;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }
    }
    
    /**
     * Metodo utilizado para verificar si un empleado o cajero cuenta con un
     * turno habilitado por un usuario autorizado que le permita facturar en el
     * sistema.
     *
     * Metodo actualizado el 17/05/2022.
     *
     * @param userName Valor que utilizan los usuario en el sistema para iniciar
     * session.
     * @return Retorna true si el usuario cuenta con un turno habierto y false
     * si no cuenta con un turno abierto.
     */
    public synchronized static boolean usuarioTurnoActivo(String userName) {
        if (idTurnoActivo(userName) > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param idUsuario
     * @return 
     */
    public synchronized static boolean habilitarTurno(String idUsuario) {
        final String sql = "EXECUTE PROCEDURE Admin_Habilitar_Turno (?)";
        try(CallableStatement cs = getCnn().prepareCall(sql)){
            
            cs.setString(1, idUsuario);
            
            return cs.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
    
    /**
     * 
     * @param idUsuario
     * @return 
     */
    public synchronized static boolean cerrarTurno(String idUsuario) {
        final String sql = "EXECUTE PROCEDURE Admin_CerrarTurno (?)";
        try (CallableStatement cs = getCnn().prepareCall(sql)){

            cs.setString(1, idUsuario);

            return cs.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
    
    

}
